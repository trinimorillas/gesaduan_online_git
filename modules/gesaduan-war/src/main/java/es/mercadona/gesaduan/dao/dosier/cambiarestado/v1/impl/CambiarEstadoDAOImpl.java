package es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.business.dosierapi.getdossierdocument.v1.GetDocumentService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.GuardarPDFDAO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.DatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.OutputDossierDocHeadDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.getdocumento.v1.DocumentoDataJPA;

public class CambiarEstadoDAOImpl extends DaoBaseImpl<Long, DosierJPA> implements CambiarEstadoDAO {

	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private GetDocumentService getDocumentoService;
	
	@Inject
	private GuardarPDFDAO guardarPDF;

	@PersistenceContext
	private EntityManager entityM;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = DosierJPA.class;
	}

	private static final String LOG_FILE = "CambiarEstadoDAOImpl(GESADUAN)";
	
	@Transactional
	@Override
	public Boolean tieneErrorDosier(DosierJPA dosierJPA) {		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MCA_ERROR FROM D_DOSIER ");
			sql.append("WHERE NUM_DOSIER = ?numDosier AND ");
			sql.append("NUM_ANYO = ?anyoDosier");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			
			String restultadoQuery = query.getSingleResult().toString();
			
			if ("S".equals(restultadoQuery)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "comprobarDosierError", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void generarPDF(DosierJPA dosierJPA) {
		try {
			// Prepara llamada al servicio para crear el fichero
			InputDossierDocumentDTO inputDocumentoDTO = new InputDossierDocumentDTO();
	
			inputDocumentoDTO.setDossierNumber(Long.toString(dosierJPA.getId().getNumDosier()));
			inputDocumentoDTO.setDossierYear(Integer.toString(dosierJPA.getId().getAnyoDosier()));
			inputDocumentoDTO.setDocumentType("pdf");
	
			OutputDossierDocHeadDTO outputDocumentoDTO = null;
			outputDocumentoDTO = getDocumentoService.preparaDocumento(inputDocumentoDTO);
			
			if (outputDocumentoDTO != null) {
				byte[] file = null;
				file = outputDocumentoDTO.getFicheroPDF();
				DocumentoDataJPA documento = new DocumentoDataJPA();				
				documento.setNumDosier(dosierJPA.getId().getNumDosier());
				documento.setAnyo(dosierJPA.getId().getAnyoDosier());
				documento.setFicheroPdf(file);
				guardarPDF.guardarPDF(documento);
			}
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE, "getDocumentoInvalidado", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());
		}
	}

	@Transactional
	@Override
	public void actualizaContenedores(DosierJPA dosierJPA) {
		StringBuilder update = new StringBuilder();
		
		try {
			// Si la carga pasa de no validada a no asignada se inicializan los campos restantes
			update.append("UPDATE O_CONTENEDOR_EXPEDIDO SET ");
			update.append("NUM_DOSIER = NULL, ");
			update.append("NUM_ANYO = NULL, ");
			update.append("FEC_D_MODIFICACION = SYSDATE, ");
			update.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update.append("WHERE ");
			update.append("NUM_DOSIER = ?numDosier AND ");
			update.append("NUM_ANYO = ?anyoDosier");

			final Query query = getEntityManager().createNativeQuery(update.toString());

			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());

			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "actualizaContenedores", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void actualizaEquipos(DosierJPA dosierJPA) {
		StringBuilder update = new StringBuilder();
		StringBuilder update2 = new StringBuilder();
		
		try {
			// Si se invalida el único dosier del equipo, el estado de documentación del equipo pasará a "Pendiente"
			update.append("UPDATE D_EQUIPO_TRANSPORTE ET SET ");
			update.append("COD_N_ESTADO_DOCUMENTACION = 1, ");
			update.append("FEC_DT_MODIFICACION = SYSDATE, ");
			update.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update.append("WHERE EXISTS (");
			update.append("SELECT 1 ");
			update.append("FROM S_DOSIER_EQUIPO ");
			update.append("WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND ");
			update.append("NUM_DOSIER = ?numDosier AND ");
			update.append("NUM_ANYO = ?anyoDosier");
			update.append(")");
			update.append("AND NOT EXISTS (");
			update.append("SELECT 1 ");
			update.append("FROM O_CONTENEDOR_EXPEDIDO ");
			update.append("WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND ");
			update.append("NUM_DOSIER IS NOT NULL ");
			update.append(")");

			final Query query = getEntityManager().createNativeQuery(update.toString());

			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());

			query.executeUpdate();

			// Si alguno de los contenedores del equipo se encontraba asociado a otro dosier,
			// el estado de documentación del equipo pasará a "En curso"
			update2.append("UPDATE D_EQUIPO_TRANSPORTE ET SET ");
			update2.append("COD_N_ESTADO_DOCUMENTACION = 2, ");
			update2.append("FEC_DT_MODIFICACION = SYSDATE, ");
			update2.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update2.append("WHERE EXISTS (");
			update2.append("SELECT 1 ");
			update2.append("FROM S_DOSIER_EQUIPO ");
			update2.append("WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND ");
			update2.append("NUM_DOSIER = ?numDosier AND ");
			update2.append("NUM_ANYO = ?anyoDosier");
			update2.append(")");
			update2.append("AND EXISTS (");
			update2.append("SELECT 1 ");
			update2.append("FROM O_CONTENEDOR_EXPEDIDO ");
			update2.append("WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO ");
			update2.append("AND NUM_DOSIER IS NOT NULL)");

			final Query query2 = getEntityManager().createNativeQuery(update2.toString());

			query2.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query2.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query2.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());

			query2.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "actualizaEquipos", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void eliminaRelacionEquipo(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			// Si la carga pasa de no validada a no asignada se inicializan los campos restantes
			delete.append("DELETE S_DOSIER_EQUIPO DE ");
			delete.append("WHERE ");
			delete.append("DE.NUM_DOSIER = ?numDosier AND ");
			delete.append("DE.NUM_ANYO = ?anyoDosier");

			final Query query = getEntityManager().createNativeQuery(delete.toString());

			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminaRelacionEquipo", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public OutputCambiarEstadoDTO cambiarEstado(DosierJPA dosierJPA) {
		StringBuilder update = new StringBuilder();
		OutputCambiarEstadoDTO retorno = new OutputCambiarEstadoDTO();
		
		try {
			// Si la carga pasa de no validada a no asignada se inicializan los campos restantes
			update.append("UPDATE D_DOSIER SET ");
			update.append("COD_N_ESTADO  = 3, ");
			update.append("FEC_DT_MODIFICACION = SYSDATE, ");
			update.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update.append("WHERE ");
			update.append("NUM_DOSIER = ?numDosier AND ");
			update.append("NUM_ANYO = ?anyoDosier");

			final Query query = getEntityManager().createNativeQuery(update.toString());

			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());

			query.executeUpdate();

			DatosCambiarEstadoDTO datos = new DatosCambiarEstadoDTO();

			datos.setNumDosier(dosierJPA.getId().getNumDosier());
			datos.setAnyoDosier(dosierJPA.getId().getAnyoDosier());

			retorno.setDatos(datos);

			return retorno;
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "actualizaContenedores", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

	}
	
	@Transactional
	@Override
	public void eliminarAlertas(DosierJPA dosierJPA) {
		eliminarAlertasFacturasDosier(dosierJPA);
		eliminarAlertasAsociacionContactos(dosierJPA);
		eliminarAlertasFacturas(dosierJPA);
		eliminarContactoAlertas(dosierJPA);
		eliminarAlertasUsuarios(dosierJPA);
		eliminarAlertasGenerales(dosierJPA);
		eliminarLineasFacturas(dosierJPA);
		eliminarRelacionPedidos(dosierJPA);
		eliminarContenedores(dosierJPA);
		eliminarCabeceraFacturas(dosierJPA);
	}
	
	@Transactional
	@Override
	public void eliminarAlertasFacturasDosier(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM S_USUARIO_NOTIF_ALERTA_EXPED SUN ");
			delete.append("WHERE (SUN.COD_N_ALERTA, SUN.COD_V_ELEMENTO, SUN.COD_V_EXPEDICION, SUN.FEC_D_ALBARAN) IN (");
			delete.append("SELECT SNA.COD_N_ALERTA, SNA.COD_V_ELEMENTO, SNA.COD_V_EXPEDICION, SNA.FEC_D_ALBARAN FROM S_NOTIF_ALERTA_EXPED_DV SNA ");
			delete.append("LEFT JOIN O_DECLARACION_VALOR_CAB DV ON ");
			delete.append("DV.COD_N_DECLARACION_VALOR = SNA.COD_N_DECLARACION_VALOR AND DV.NUM_ANYO = SNA.NUM_ANYO AND DV.COD_N_VERSION = SNA.COD_N_VERSION ");
			delete.append("WHERE DV.NUM_DOSIER = ?numDosier AND DV.NUM_ANYO_DOSIER = ?anyoDosier)");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarAlertasFacturasDosier", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarAlertasAsociacionContactos(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM S_CONTACTO_NOTIF_ALERTA_EXPED SCN ");
			delete.append("WHERE (SCN.COD_N_ALERTA, SCN.COD_V_ELEMENTO, SCN.COD_V_EXPEDICION, SCN.FEC_D_ALBARAN) IN (");
			delete.append("SELECT SNA.COD_N_ALERTA, SNA.COD_V_ELEMENTO, SNA.COD_V_EXPEDICION, SNA.FEC_D_ALBARAN FROM S_NOTIF_ALERTA_EXPED_DV SNA ");
			delete.append("LEFT JOIN O_DECLARACION_VALOR_CAB DV ON (DV.COD_N_DECLARACION_VALOR = SNA.COD_N_DECLARACION_VALOR AND DV.NUM_ANYO = SNA.NUM_ANYO AND DV.COD_N_VERSION = SNA.COD_N_VERSION) ");
			delete.append("WHERE DV.NUM_DOSIER = ?numDosier AND DV.NUM_ANYO_DOSIER = ?anyoDosier) ");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarAlertasAsociacionContactos", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarAlertasFacturas(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM S_NOTIF_ALERTA_EXPED_DV SNA ");
			delete.append("WHERE (SNA.COD_N_DECLARACION_VALOR, SNA.NUM_ANYO, SNA.COD_N_VERSION) IN (");
			delete.append("SELECT DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION FROM O_DECLARACION_VALOR_CAB DV ");
			delete.append("WHERE DV.NUM_DOSIER = ?numDosier AND DV.NUM_ANYO_DOSIER = ?anyoDosier)");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarAlertasFacturas", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarContactoAlertas(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM S_CONTACTO_NOTIFICACION_ALERTA SCN ");
			delete.append("WHERE (SCN.COD_N_ALERTA, SCN.COD_V_ELEMENTO) IN (");
			delete.append("SELECT SNA.COD_N_ALERTA, SNA.COD_V_ELEMENTO FROM S_NOTIFICACION_ALERTA SNA ");
			delete.append("WHERE SNA.COD_V_ELEMENTO = SUBSTR(?anyoDosier,3,2)||'-'||LPAD(?numDosier,5,'0') AND SNA.COD_N_ALERTA <> 47)");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarContactoAlertas", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarAlertasUsuarios(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM S_USUARIO_NOTIFICACION_ALERTA SUN ");
			delete.append("WHERE (SUN.COD_N_ALERTA, SUN.COD_V_ELEMENTO) IN (");
			delete.append("SELECT SNA.COD_N_ALERTA, SNA.COD_V_ELEMENTO FROM S_NOTIFICACION_ALERTA SNA ");
			delete.append("WHERE SNA.COD_V_ELEMENTO = SUBSTR(?anyoDosier,3,2)||'-'||LPAD(?numDosier,5,'0') AND SNA.COD_N_ALERTA <> 47)");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarAlertasUsuarios", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarAlertasGenerales(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM S_NOTIFICACION_ALERTA SNA ");
			delete.append("WHERE SNA.COD_V_ELEMENTO = SUBSTR(?anyoDosier,3,2)||'-'||LPAD(?numDosier,5,'0') AND SNA.COD_N_ALERTA <> 47");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarAlertasGenerales", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarLineasFacturas(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM O_DECLARACION_VALOR_LIN L ");
			delete.append("WHERE EXISTS (SELECT D.COD_N_DECLARACION_VALOR ");  
			delete.append("FROM O_DECLARACION_VALOR_CAB D ");
			delete.append("WHERE D.COD_N_DECLARACION_VALOR = L.COD_N_DECLARACION_VALOR AND ");
			delete.append("D.NUM_ANYO = L.NUM_ANYO AND ");
			delete.append("D.COD_N_VERSION = L.COD_N_VERSION AND ");
			delete.append("D.NUM_DOSIER = ?numDosier AND ");
			delete.append("D.NUM_ANYO_DOSIER = ?anyoDosier)");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarLineasFacturas", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarRelacionPedidos(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM S_DECLARACION_VALOR_PEDIDO DVP ");
			delete.append("WHERE EXISTS (");
			delete.append("SELECT 1 ");
			delete.append("FROM O_DECLARACION_VALOR_CAB DVC ");
			delete.append("WHERE DVC.COD_N_DECLARACION_VALOR = DVP.COD_N_DECLARACION_VALOR AND ");
			delete.append("DVC.NUM_ANYO = DVP.NUM_ANYO_DV ");
			delete.append("AND DVC.NUM_DOSIER = ?numDosier AND DVC.NUM_ANYO_DOSIER = ?anyoDosier)");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarRelacionPedidos", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarContenedores(DosierJPA dosierJPA) {
		StringBuilder update = new StringBuilder();
		
		try {
			update.append("UPDATE O_CONTENEDOR_EXPEDIDO ");
			update.append("SET COD_N_DECLARACION_VALOR = NULL, NUM_ANYO_DV = NULL, COD_N_VERSION_DV = NULL ");
			update.append("WHERE NUM_DOSIER = ?numDosier AND ");
			update.append("NUM_ANYO = ?anyoDosier");

			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarContenedores", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void eliminarCabeceraFacturas(DosierJPA dosierJPA) {
		StringBuilder delete = new StringBuilder();
		
		try {
			delete.append("DELETE FROM O_DECLARACION_VALOR_CAB D ");
		    delete.append("WHERE D.NUM_DOSIER = ?numDosier AND ");
		    delete.append("D.NUM_ANYO_DOSIER = ?anyoDosier");

			final Query query = getEntityManager().createNativeQuery(delete.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "eliminarCabeceraFacturas", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void insertarAlerta(DosierJPA dosierJPA, String codigoUsuario) {
		StringBuilder sql = new StringBuilder();
		
		try {
			sql.append("INSERT INTO S_NOTIFICACION_ALERTA (COD_N_ALERTA, COD_V_ELEMENTO, MCA_CORREO_ENVIADO, MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 47, SUBSTR(NUM_ANYO,3,2)||'-'||LPAD(NUM_DOSIER,5,'0'), 'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM (");
			sql.append("SELECT D.NUM_ANYO, D.NUM_DOSIER, ROW_NUMBER() OVER(PARTITION BY D.NUM_ANYO, D.NUM_DOSIER ORDER BY D.NUM_DOSIER) NUMERO ");
			sql.append("FROM D_DOSIER D ");
			sql.append("WHERE D.NUM_DOSIER = ?numDosier ");
			sql.append("AND D.NUM_ANYO = ?anyoDosier ");
			sql.append("AND NOT EXISTS (");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIFICACION_ALERTA NA ");
			sql.append("WHERE COD_V_ELEMENTO = NUM_ANYO||'-'||NUM_DOSIER ");
			sql.append("AND NA.COD_N_ALERTA = 47)");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS (");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 47 ");
			sql.append(")");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "insertarAlerta", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}

}