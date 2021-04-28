package es.mercadona.gesaduan.dao.dosier.putdosier.v1.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.dosier.putdosier.v1.PutDosierDAO;
import es.mercadona.gesaduan.jpa.dosier.DosierContenedorJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierEquipoJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierPkJPA;

public class PutDosierDAOImpl extends DaoBaseImpl<Long, DosierJPA> implements PutDosierDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
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
	
	private static final String NOMBRE_CLASE = "PutDosierDAOImpl(GESADUAN)";
	
	@Transactional
	@Override
	public void crearDosier(DosierJPA dosierJPA) {		
		try {		
			String gesaduan = "GESADUAN";
			Date fechaHoy = new Date();
			dosierJPA.setCodigoEstado(1);
			dosierJPA.setFechaCreacion(fechaHoy);
			dosierJPA.setCodigoAplicacion(gesaduan);
			dosierJPA.setMcaError("N");
			entityM.persist(dosierJPA);
			entityM.flush();						
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "crearDosier", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	@Transactional
	@Override
	public void crearRelacionDosierEquipo(DosierEquipoJPA dosierEquipoJPA) {
		
		try {

			String gesaduan = "GESADUAN";
			Date fechaHoy = new Date();
			dosierEquipoJPA.setFechaCreacion(fechaHoy);
			dosierEquipoJPA.setCodigoAplicacion(gesaduan);
			entityM.persist(dosierEquipoJPA);
			entityM.flush();			
			
			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "crearRelacionDosierEquipo", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}	
	
	@Transactional
	@Override
	public void actualizaEstadoDocumentacionEquipo(DosierEquipoJPA dosierEquipoJPA) {
		try {
			StringBuilder update = new StringBuilder();
			
			update.append("UPDATE D_EQUIPO_TRANSPORTE ET ");
			update.append("SET ET.COD_N_ESTADO_DOCUMENTACION = 3, ");
			update.append("ET.FEC_DT_MODIFICACION = SYSDATE, ");
			update.append("ET.COD_V_USUARIO_MODIFICACION = ?codigoUsuario ");
			update.append("WHERE ET.COD_N_EQUIPO = ?codigoEquipo ");
			update.append("AND NOT EXISTS (SELECT 1 FROM O_CONTENEDOR_EXPEDIDO WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND NUM_DOSIER IS NULL)");
			
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("codigoUsuario", dosierEquipoJPA.getUsuarioCreacion());
			query.setParameter("codigoEquipo", dosierEquipoJPA.getCodigoEquipo());
			query.executeUpdate();		
			
			StringBuilder update2 = new StringBuilder();
			
			update2.append("UPDATE D_EQUIPO_TRANSPORTE ET ");
			update2.append("SET ET.COD_N_ESTADO_DOCUMENTACION = 2, ");
			update2.append("ET.FEC_DT_MODIFICACION = SYSDATE, ");
			update2.append("ET.COD_V_USUARIO_MODIFICACION = ?codigoUsuario ");
			update2.append("WHERE ET.COD_N_EQUIPO = ?codigoEquipo ");
			update2.append("AND EXISTS (SELECT 1 FROM O_CONTENEDOR_EXPEDIDO WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND NUM_DOSIER IS NULL)");
			
			final Query query2 = getEntityManager().createNativeQuery(update2.toString());
			query2.setParameter("codigoUsuario", dosierEquipoJPA.getUsuarioCreacion());
			query2.setParameter("codigoEquipo", dosierEquipoJPA.getCodigoEquipo());
			query2.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "actualizaEstadoDocumentacionEquipo", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}	
	
	@Transactional
	@Override
	public void crearRelacionDosierContenedor(DosierContenedorJPA dosierContenedorJPA) {		
		try {			
			StringBuilder update = new StringBuilder();
		
			update.append("UPDATE O_CONTENEDOR_EXPEDIDO CE ");
			update.append("SET CE.NUM_DOSIER = ?numDosier, ");
			update.append("CE.NUM_ANYO = ?anyoDosier, ");				
			update.append("CE.FEC_D_MODIFICACION = SYSDATE, ");
			update.append("CE.COD_V_USR_MODIFICACION = ?codigoUsuario ");
			update.append("WHERE CE.COD_V_ALMACEN = ?codigoAlmacen ");
			update.append("AND CE.NUM_CONTENEDOR = ?numContenedor ");
			update.append("AND CE.FEC_DT_EXPEDICION = TO_DATE(?fechaExpedicion,'DD/MM/YYYY HH24:MI:SS') ");
			update.append("AND CE.COD_N_EQUIPO = ?codigoEquipo ");			
					
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("numDosier", dosierContenedorJPA.getNumDosier());
			query.setParameter("anyoDosier", dosierContenedorJPA.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierContenedorJPA.getUsuarioCreacion());
			query.setParameter("codigoAlmacen", dosierContenedorJPA.getCodigoAlmacen());			
			query.setParameter("numContenedor", dosierContenedorJPA.getNumContenedor());
			query.setParameter("fechaExpedicion", dosierContenedorJPA.getFechaExpedicion());
			query.setParameter("codigoEquipo", dosierContenedorJPA.getCodigoEquipo());			
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "crearRelacionDosierContenedor", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}	
	
	@Transactional
	@Override
	public void crearRelacionDosierContenedorDeEquipo(DosierContenedorJPA dosierContenedorJPA) {		
		try {			
			StringBuilder update = new StringBuilder();
		
			update.append("UPDATE O_CONTENEDOR_EXPEDIDO CE ");
			update.append("SET CE.NUM_DOSIER = ?numDosier, ");
			update.append("CE.NUM_ANYO = ?anyoDosier, ");				
			update.append("CE.FEC_D_MODIFICACION = SYSDATE, ");
			update.append("CE.COD_V_USR_MODIFICACION = ?codigoUsuario ");
			update.append("WHERE CE.COD_N_EQUIPO = ?codigoEquipo ");
					
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("numDosier", dosierContenedorJPA.getNumDosier());
			query.setParameter("anyoDosier", dosierContenedorJPA.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierContenedorJPA.getUsuarioCreacion());
			query.setParameter("codigoEquipo", dosierContenedorJPA.getCodigoEquipo());			
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "crearRelacionDosierContenedorDeEquipo", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}		
	
	@Transactional	
	@Override
	public DosierPkJPA getNewDosierPk() {		
		try {			
			DosierPkJPA dosierPk = null;			
			
			String select = "SELECT TO_NUMBER(TXT_VALOR)+1, TO_CHAR(SYSDATE, 'YYYY') FROM C_VARIABLE WHERE COD_V_VARIABLE = 'NUM_DOSIER'";

			final Query query = getEntityManager().createNativeQuery(select);
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {			
									
					dosierPk = new DosierPkJPA();
					dosierPk.setNumDosier(Long.parseLong(String.valueOf(tmp[0])));
					dosierPk.setAnyoDosier(Integer.parseInt(String.valueOf(tmp[1])));
				}			
			}
			
			return dosierPk;			
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getNewDosierPk", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}	
	
	@Transactional	
	@Override
	public void updateNumDosier(Long lastNumDosier) {		
		try {			
			StringBuilder update = new StringBuilder();
		
			update.append("UPDATE C_VARIABLE SET TXT_VALOR = ?lastNumDosier WHERE COD_V_VARIABLE = 'NUM_DOSIER' ");
					
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("lastNumDosier", lastNumDosier);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "updateNumDosier", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public Integer getNumCategorias(DosierEquipoJPA dosierEquipo) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("SELECT COUNT(DISTINCT NVL(C.COD_N_CATEGORIA,0)) ");
			sql.append("FROM O_CONTENEDOR_EXPEDIDO CE ");
			sql.append("INNER JOIN D_CARGA C ON (C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND C.COD_V_CARGA = CE.COD_V_CARGA AND C.COD_N_TIPO_CARGA IN (1, 2)) ");
			sql.append("WHERE CE.NUM_DOSIER = ?numDosier ");
			sql.append("AND CE.NUM_ANYO = ?anyoDosier ");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosierEquipo.getNumDosier());
			query.setParameter("anyoDosier", dosierEquipo.getAnyoDosier());
			return Integer.parseInt(query.getSingleResult().toString());
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getNumCategorias", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());
		}
	}
	
	@Transactional	
	@Override
	public void crearFacturas(DosierEquipoJPA dosierEquipo) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO O_DECLARACION_VALOR_CAB (COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PROVINCIA_CARGA,TXT_CONDICIONES_ENTREGA, ");
			sql.append("MCA_FACTURA_DV,MCA_ULTIMA_VIGENTE,MCA_CARGA_AUTOMATICA,MCA_DV_CORRECTA,MCA_ENVIO,MCA_DESCARGA,FEC_DT_CREACION,FEC_D_CREACION, ");
			sql.append("COD_V_APLICACION,COD_V_USUARIO_CREACION,COD_N_BLOQUE_LOGISTICO,COD_N_CATEGORIA,FEC_DT_EXPEDICION,NUM_DOSIER,NUM_ANYO_DOSIER) ");
			sql.append("SELECT DECLARACION_VALOR_SEQ.NEXTVAL,NUM_ANYO,1,COD_N_PROVINCIA,'EXW','F','S','S','N','N','N',SYSDATE,SYSDATE,'GESADUAN', ?codigoUsuario, ");
			sql.append("COD_N_BLOQUE_LOGISTICO,COD_N_CATEGORIA,FEC_DT_EXPEDICION,NUM_DOSIER,NUM_ANYO ");
			sql.append("FROM ");
			sql.append("(SELECT DISTINCT BL.COD_N_PROVINCIA,PE.COD_N_BLOQUE_ORIGEN AS COD_N_BLOQUE_LOGISTICO, ");
			sql.append("C.COD_N_CATEGORIA,CE.NUM_DOSIER,CE.NUM_ANYO, ");
			sql.append("(SELECT MIN(FEC_DT_CARGA) ");
			sql.append("FROM D_EQUIPO_TRANSPORTE ET ");
			sql.append("INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO AND EC.COD_V_CARGA = CE.COD_V_CARGA  ");
			sql.append("AND EC.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN) ");
			sql.append("WHERE ET.COD_N_EQUIPO = CE.COD_N_EQUIPO ");
			sql.append(") AS FEC_DT_EXPEDICION, ");
			sql.append("ROW_NUMBER() OVER(PARTITION BY CE.NUM_DOSIER,CE.NUM_ANYO ORDER BY C.COD_N_CATEGORIA ASC) ORDEN ");
			sql.append("FROM O_CONTENEDOR_EXPEDIDO CE ");
			sql.append("INNER JOIN D_DOSIER D ON (D.NUM_DOSIER = CE.NUM_DOSIER AND D.NUM_ANYO = CE.NUM_ANYO) ");
			sql.append("INNER JOIN D_PLAN_EMBARQUE PE ON (PE.COD_N_EMBARQUE = D.COD_N_EMBARQUE) ");
			sql.append("INNER JOIN D_BLOQUE_LOGISTICO_R BL ON (BL.COD_N_BLOQUE_LOGISTICO = PE.COD_N_BLOQUE_ORIGEN) ");
			sql.append("INNER JOIN D_CARGA C ON (C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND C.COD_V_CARGA = CE.COD_V_CARGA ");
			sql.append("AND C.COD_N_TIPO_CARGA IN (1,2)) ");
			sql.append("WHERE CE.NUM_DOSIER = ?numDosier ");
			sql.append("AND CE.NUM_ANYO = ?anyoDosier ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE NVL(DV.COD_N_CATEGORIA,0) = NVL(C.COD_N_CATEGORIA,0) ");
			sql.append("AND DV.NUM_DOSIER = CE.NUM_DOSIER ");
			sql.append("AND DV.NUM_ANYO_DOSIER = CE.NUM_ANYO ");
			sql.append(") ");
			sql.append(") ");
			sql.append("WHERE ORDEN = 1");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosierEquipo.getNumDosier());
			query.setParameter("anyoDosier", dosierEquipo.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierEquipo.getUsuarioCreacion());
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "crearFacturas", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void actualizarContenedores(DosierEquipoJPA dosierEquipo) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("MERGE INTO O_CONTENEDOR_EXPEDIDO OCE USING ( ");
			sql.append("SELECT DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, CE.COD_V_ALMACEN, CE.NUM_CONTENEDOR, CE.FEC_DT_EXPEDICION ");
			sql.append("FROM O_CONTENEDOR_EXPEDIDO CE ");
			sql.append("INNER JOIN D_CARGA C ON (C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND C.COD_V_CARGA = CE.COD_V_CARGA  ");
			sql.append("AND C.COD_N_TIPO_CARGA IN (1,2)) ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.NUM_DOSIER = CE.NUM_DOSIER AND DV.NUM_ANYO_DOSIER = CE.NUM_ANYO ");
			sql.append("AND NVL(DV.COD_N_CATEGORIA,0) = NVL(C.COD_N_CATEGORIA,0)) ");
			sql.append("WHERE CE.NUM_DOSIER = ?numDosier ");
			sql.append("AND CE.NUM_ANYO = ?anyoDosier ");
			sql.append("GROUP BY DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, CE.COD_V_ALMACEN, CE.NUM_CONTENEDOR, CE.FEC_DT_EXPEDICION ");
			sql.append(") TEMP ");
			sql.append("ON (OCE.COD_V_ALMACEN = TEMP.COD_V_ALMACEN AND OCE.NUM_CONTENEDOR = TEMP.NUM_CONTENEDOR AND OCE.FEC_DT_EXPEDICION = TEMP.FEC_DT_EXPEDICION) ");
			sql.append("WHEN MATCHED THEN UPDATE SET ");
			sql.append("OCE.COD_N_DECLARACION_VALOR = TEMP.COD_N_DECLARACION_VALOR, ");
			sql.append("OCE.NUM_ANYO_DV = TEMP.NUM_ANYO, ");
			sql.append("OCE.COD_N_VERSION_DV = TEMP.COD_N_VERSION, ");
			sql.append("OCE.FEC_D_MODIFICACION = SYSDATE, ");
			sql.append("OCE.COD_V_USR_MODIFICACION = ?codigoUsuario ");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosierEquipo.getNumDosier());
			query.setParameter("anyoDosier", dosierEquipo.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierEquipo.getUsuarioCreacion());
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "insertarPedidos", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void insertarLineasFacturas(DosierEquipoJPA dosierEquipo) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO O_DECLARACION_VALOR_LIN (COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_MERCA,COD_N_TARIC,COD_V_REA, ");
			sql.append("TXT_NOMBRE_ALTERNATIVO,TXT_FORMATO_VENTA_ALTERNATIVO,TXT_MARCA,TXT_NOMBRE_BULTO_DV,NUM_BULTOS,NUM_PESO_NETO,NUM_PESO_BRUTO, ");
			sql.append("NUM_VOLUMEN,NUM_CANTIDAD,NUM_PRECIO_UNIDAD,NUM_IMPORTE_TOTAL,NUM_GRADO_ALCOHOL,NUM_GRADO_PLATO,COD_V_PAIS,MCA_PRODUCTO_LPC, ");
			sql.append("FEC_D_CREACION,COD_V_APLICACION,COD_V_USUARIO_CREACION) ");
			sql.append("SELECT COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PRODUCTO,COD_N_TARIC,COD_V_REA,TXT_NOMBRE_PRODUCTO,TXT_FORMATO, ");
			sql.append("TXT_MARCA,NOMBRE_BULTO,NUM_BULTOS,NUM_PESO_NETO,NUM_PESO_BRUTO,NUM_VOLUMEN,NUM_CANTIDAD,NUM_PRECIO_UNIDAD,NUM_IMPORTE_TOTAL, ");
			sql.append("NUM_GRADO_ALCOHOL,NUM_GRADO_PLATO,COD_V_PAIS,MCA_PRODUCTO_LPC,SYSDATE,'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ");
			sql.append("(SELECT COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PRODUCTO,COD_N_TARIC,COD_V_REA,TXT_NOMBRE_PRODUCTO,TXT_FORMATO, ");
			sql.append("TXT_MARCA,NOMBRE_BULTO,SUM(NUM_BULTOS) OVER (PARTITION BY COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION, ");
			sql.append("COD_N_PRODUCTO) AS NUM_BULTOS, ");
			sql.append("SUM(NUM_PESO_NETO) OVER (PARTITION BY COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PRODUCTO) AS NUM_PESO_NETO, ");
			sql.append("SUM(NUM_PESO_BRUTO) OVER (PARTITION BY COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PRODUCTO) AS NUM_PESO_BRUTO, ");
			sql.append("SUM(NUM_VOLUMEN) OVER (PARTITION BY COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PRODUCTO) AS NUM_VOLUMEN, ");
			sql.append("SUM(NUM_CANTIDAD) OVER (PARTITION BY COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PRODUCTO) AS NUM_CANTIDAD, ");
			sql.append("NUM_PRECIO_UNIDAD,SUM(NUM_IMPORTE_TOTAL) OVER (PARTITION BY COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION, ");
			sql.append("COD_N_PRODUCTO) AS NUM_IMPORTE_TOTAL, ");
			sql.append("NUM_GRADO_ALCOHOL,NUM_GRADO_PLATO,COD_V_PAIS,MCA_PRODUCTO_LPC, ");
			sql.append("ROW_NUMBER() OVER (PARTITION BY COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION,COD_N_PRODUCTO ORDER BY COD_N_PRODUCTO ASC) ");
			sql.append("AS PRIMERO ");
			sql.append("FROM (SELECT DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION,E.COD_N_PRODUCTO,TP.COD_N_TARIC,RP.COD_V_REA, ");
			sql.append("DECODE(P.TXT_DENOMINA_ALTERNATIVA,NULL,DP.TXT_DESCRIPCION,P.TXT_DENOMINA_ALTERNATIVA) AS TXT_NOMBRE_PRODUCTO, ");
			sql.append("DECODE(P.TXT_FORMATO_ALTERNATIVO,NULL,P.NUM_FORMATO_VENTA||' '||P.TXT_UNIDAD_MEDIDA, ");
			sql.append("P.TXT_FORMATO_ALTERNATIVO) AS TXT_FORMATO, ");
			sql.append("P.TXT_MARCA,'PENDIENTE:NOMBRE_BULTO' AS NOMBRE_BULTO,E.NUM_FORMATOS_VENTA_EXPEDIDO AS NUM_BULTOS, ");
			sql.append("(E.NUM_CANTIDAD_EXPEDIDO_PESO/1000)*TO_NUMBER('0,9') AS NUM_PESO_NETO, ");
			sql.append("E.NUM_CANTIDAD_EXPEDIDO_PESO/1000 AS NUM_PESO_BRUTO, ");
			sql.append("DECODE(P.TXT_UNIDAD_MEDIDA,'ML',P.NUM_FORMATO_VENTA/1000,'LI',P.NUM_FORMATO_VENTA)*E.NUM_CANTIDAD_EXPEDIDO_UNIDAD ");
			sql.append("AS NUM_VOLUMEN, ");
			sql.append("DECODE(E.COD_V_TIPO_CANTIDAD,'P',E.NUM_CANTIDAD_EXPEDIDO_PESO,'C',E.NUM_CANTIDAD_EXPEDIDO_UNIDAD) AS NUM_CANTIDAD, ");
			sql.append("PCA.NUM_PCA AS NUM_PRECIO_UNIDAD, ");
			sql.append("ROUND(DECODE(E.COD_V_TIPO_CANTIDAD,'P',(ROUND(E.NUM_CANTIDAD_EXPEDIDO_PESO/1000,2))*PCA.NUM_PCA, ");
			sql.append("E.NUM_CANTIDAD_EXPEDIDO_UNIDAD*PCA.NUM_PCA),2) AS NUM_IMPORTE_TOTAL, ");
			sql.append("DECODE(AP.NUM_GRADO_ALCOHOL, NULL, NULL, AP.NUM_GRADO_ALCOHOL*1000) AS NUM_GRADO_ALCOHOL, ");
			sql.append("DECODE(AP.NUM_GRADO_PLATO, NULL, NULL, AP.NUM_GRADO_PLATO*100) AS NUM_GRADO_PLATO, ");
			sql.append("DECODE(RP.COD_V_REA,NULL,DECODE(CT.MCA_FITO,'S',P.COD_V_PAIS_ISO_ALFA2,NULL),P.COD_V_PAIS_ISO_ALFA2) AS COD_V_PAIS, ");
			sql.append("DECODE(CPR.COD_V_SECCION, '15', 'S', 'N') AS MCA_PRODUCTO_LPC ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("INNER JOIN O_CONTENEDOR_EXPEDIDO CE ON (CE.COD_N_DECLARACION_VALOR= DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND CE.NUM_ANYO_DV= DV.NUM_ANYO ");
			sql.append("AND CE.COD_N_VERSION_DV= DV.COD_N_VERSION) ");
			sql.append("INNER JOIN O_EXPEDIDO E ON (E.NUM_CONTENEDOR_BASE = CE.NUM_CONTENEDOR AND E.COD_V_ALMACEN = CE.COD_V_ALMACEN ");
			sql.append("AND E.FEC_DT_EXPEDICION = CE.FEC_DT_EXPEDICION) ");
			sql.append("INNER JOIN D_PRODUCTO_R P ON (P.COD_N_PRODUCTO = E.COD_N_PRODUCTO) ");
			sql.append("LEFT JOIN S_TARIC_PRODUCTO TP ON (TP.COD_N_PRODUCTO = E.COD_N_PRODUCTO) ");
			sql.append("LEFT JOIN D_CODIGO_TARIC CT ON CT.COD_N_TARIC = TP.COD_N_TARIC");
			sql.append("LEFT JOIN S_REA_PRODUCTO RP ON (RP.COD_N_PRODUCTO = E.COD_N_PRODUCTO ");
			sql.append("AND RP.FEC_D_INICIO <= TRUNC(SYSDATE) ");
			sql.append("AND (RP.FEC_D_FIN IS NULL OR  RP.FEC_D_FIN >= TRUNC(SYSDATE))) ");
			sql.append("LEFT JOIN S_DENOMINACION_PRODUCTO_I18N_R DP ON (DP.COD_N_PRODUCTO = P.COD_N_PRODUCTO AND DP.COD_V_LOCALE = 'es_ES') ");
			sql.append("LEFT JOIN S_PCA_PRODUCTO PCA ON (PCA.COD_N_PRODUCTO = E.COD_N_PRODUCTO AND PCA.COD_V_CENTRO = E.COD_V_ALMACEN) ");
			sql.append("LEFT JOIN S_ALCOHOL_PRODUCTO_R AP ON (AP.COD_N_PRODUCTO = E.COD_N_PRODUCTO) ");
			sql.append("LEFT JOIN S_COMPONENTES_PRODUCTO_R CPR ON (CPR.COD_N_PRODUCTO = E.COD_N_PRODUCTO) ");
			sql.append("WHERE DV.NUM_DOSIER = ?numDosier ");
			sql.append("AND DV.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND P.COD_N_PRODUCTO IS NOT NULL ");
			sql.append(") ");
			sql.append(") ");
			sql.append("WHERE PRIMERO = 1");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosierEquipo.getNumDosier());
			query.setParameter("anyoDosier", dosierEquipo.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierEquipo.getUsuarioCreacion());
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "insertarLineasFacturas", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void updateFacturas(DosierEquipoJPA dosierEquipo) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("MERGE INTO O_DECLARACION_VALOR_CAB DV USING ( ");
			sql.append("SELECT DVC.COD_N_DECLARACION_VALOR,DVC.NUM_ANYO,DVC.COD_N_VERSION,CE.NUM_DOSIER,CE.NUM_ANYO AS NUM_ANYO_DOSIER ");
			sql.append("FROM O_CONTENEDOR_EXPEDIDO CE ");
			sql.append("INNER JOIN D_CARGA C ON (C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND C.COD_V_CARGA = CE.COD_V_CARGA AND C.COD_N_TIPO_CARGA IN (3,4)) ");
			sql.append("INNER JOIN S_CARGA_PEDIDO CP ON (CP.COD_V_ALMACEN_ORIGEN = C.COD_V_ALMACEN_ORIGEN AND CP.COD_V_CARGA = C.COD_V_CARGA) ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DVC ON (DVC.COD_V_PEDIDO = CP.COD_V_PEDIDO) ");
			sql.append("WHERE CE.NUM_DOSIER = ?numDosier ");
			sql.append("AND CE.NUM_ANYO = ?anyoDosier ");
			sql.append(") TEMP ");
			sql.append("ON (DV.COD_N_DECLARACION_VALOR = TEMP.COD_N_DECLARACION_VALOR AND DV.NUM_ANYO = TEMP.NUM_ANYO AND DV.COD_N_VERSION = TEMP.COD_N_VERSION) ");
			sql.append("WHEN MATCHED THEN UPDATE SET ");
			sql.append("DV.NUM_DOSIER = TEMP.NUM_DOSIER, ");
			sql.append("DV.NUM_ANYO_DOSIER = TEMP.NUM_ANYO_DOSIER, ");
			sql.append("DV.FEC_D_MODIFICACION = SYSDATE, ");
			sql.append("DV.COD_V_APLICACION = 'GESADUAN', ");
			sql.append("DV.COD_V_USR_MODIFICACION = ?codigoUsuario");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosierEquipo.getNumDosier());
			query.setParameter("anyoDosier", dosierEquipo.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierEquipo.getUsuarioCreacion());
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "updateFacturas", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void validarFacturas(DosierPkJPA dosier, String codigoUsuario) {		
		validarTaricProducto(dosier, codigoUsuario);
		validarVolumenAlcohol(dosier, codigoUsuario);
		validarPaisREA(dosier, codigoUsuario);
		validarPrecioProducto(dosier, codigoUsuario);
		alertaErrorFactura(dosier);
		alertaFacturaOK(dosier, codigoUsuario);
		facturaOK(dosier);
		errorDosier(dosier);
		dosierOK(dosier, codigoUsuario);
	}
	
	@Transactional	
	@Override
	public void validarTaricProducto(DosierPkJPA dosier, String codigoUsuario) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_NOTIF_ALERTA_EXPED_DV (COD_N_ALERTA, COD_V_ELEMENTO, COD_V_EXPEDICION, FEC_D_ALBARAN, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, MCA_CORREO_ENVIADO, ");
			sql.append("MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 27, COD_N_MERCA, '-', SYSDATE, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT DVL.COD_N_MERCA, DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, ");
			sql.append("ROW_NUMBER() OVER(PARTITION BY DVL.COD_N_MERCA,DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION  ");
			sql.append("ORDER BY DVL.COD_N_MERCA) NUMERO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_LIN DVL ON (DVL.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR  ");
			sql.append("AND DVL.NUM_ANYO = DV.NUM_ANYO AND DVL.COD_N_VERSION = DV.COD_N_VERSION) ");
			sql.append("WHERE DV.COD_V_EXPEDICION IS NULL AND DV.NUM_DOSIER = ?numDosier ");
			sql.append("AND DV.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND DVL.COD_N_TARIC IS NULL ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NA ");
			sql.append("WHERE NA.COD_V_ELEMENTO = DVL.COD_N_MERCA ");
			sql.append("AND NA.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND NA.NUM_ANYO = DV.NUM_ANYO ");
			sql.append("AND NA.COD_N_VERSION = DV.COD_N_VERSION ");
			sql.append("AND NA.COD_N_ALERTA = 27) ");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 27 ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "validarTaricProducto", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void validarVolumenAlcohol(DosierPkJPA dosier, String codigoUsuario) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_NOTIF_ALERTA_EXPED_DV (COD_N_ALERTA, COD_V_ELEMENTO, COD_V_EXPEDICION, FEC_D_ALBARAN, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, MCA_CORREO_ENVIADO, ");
			sql.append("MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 29, COD_N_MERCA, '-', SYSDATE, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT DVL.COD_N_MERCA, DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, ");
			sql.append("ROW_NUMBER() OVER(PARTITION BY DVL.COD_N_MERCA,DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION  ");
			sql.append("ORDER BY DVL.COD_N_MERCA) NUMERO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_LIN DVL ON (DVL.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR  ");
			sql.append("AND DVL.NUM_ANYO = DV.NUM_ANYO AND DVL.COD_N_VERSION = DV.COD_N_VERSION) ");
			sql.append("INNER JOIN S_ALCOHOL_PRODUCTO_R ALC ON (ALC.COD_N_PRODUCTO = DVL.COD_N_MERCA) ");
			sql.append("INNER JOIN D_PRODUCTO_R P ON (P.COD_N_PRODUCTO = DVL.COD_N_MERCA) ");
			sql.append("WHERE DV.COD_V_EXPEDICION IS NULL AND DV.NUM_DOSIER = ?numDosier ");
			sql.append("AND DV.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND ((P.TXT_UNIDAD_MEDIDA <> 'LI' AND P.TXT_UNIDAD_MEDIDA <> 'ML') OR (P.NUM_FORMATO_VENTA = 0)) ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NA ");
			sql.append("WHERE NA.COD_V_ELEMENTO = DVL.COD_N_MERCA ");
			sql.append("AND NA.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND NA.NUM_ANYO = DV.NUM_ANYO ");
			sql.append("AND NA.COD_N_VERSION = DV.COD_N_VERSION ");
			sql.append("AND NA.COD_N_ALERTA = 29) ");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 29 ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "validarVolumenAlcohol", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void validarPaisREA(DosierPkJPA dosier, String codigoUsuario) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_NOTIF_ALERTA_EXPED_DV (COD_N_ALERTA, COD_V_ELEMENTO, COD_V_EXPEDICION, FEC_D_ALBARAN, ");
			sql.append("COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, MCA_CORREO_ENVIADO, ");
			sql.append("MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 48, COD_N_MERCA, '-', SYSDATE, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT DVL.COD_N_MERCA, DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, ");
			sql.append("ROW_NUMBER() OVER(PARTITION BY DVL.COD_N_MERCA,DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION  ");
			sql.append("ORDER BY DVL.COD_N_MERCA) NUMERO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_LIN DVL ON (DVL.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR  ");
			sql.append("AND DVL.NUM_ANYO = DV.NUM_ANYO AND DVL.COD_N_VERSION = DV.COD_N_VERSION) ");
			sql.append("INNER JOIN D_PRODUCTO_R P ON (P.COD_N_PRODUCTO = DVL.COD_N_MERCA) ");
			sql.append("INNER JOIN S_TARIC_PRODUCTO TP ON (TP.COD_N_PRODUCTO = DVL.COD_N_MERCA) ");
			sql.append("LEFT JOIN S_REA_PRODUCTO REA ON (REA.COD_N_PRODUCTO = DVL.COD_N_MERCA) ");
			sql.append("WHERE DV.COD_V_EXPEDICION IS NULL ");
			sql.append("AND DV.NUM_DOSIER = ?numDosier ");
			sql.append("AND DV.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND (REA.COD_V_REA IS NOT NULL OR TP.MCA_FITO = 'S') ");
			sql.append("AND P.COD_V_PAIS_ISO_ALFA2 IS NULL ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NA ");
			sql.append("WHERE NA.COD_V_ELEMENTO = DVL.COD_N_MERCA ");
			sql.append("AND NA.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND NA.NUM_ANYO = DV.NUM_ANYO ");
			sql.append("AND NA.COD_N_VERSION = DV.COD_N_VERSION ");
			sql.append("AND NA.COD_N_ALERTA = 48) ");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 48 ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "validarPaisREA", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void validarPrecioProducto(DosierPkJPA dosier, String codigoUsuario) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_NOTIF_ALERTA_EXPED_DV (COD_N_ALERTA, COD_V_ELEMENTO, COD_V_EXPEDICION, FEC_D_ALBARAN, ");
			sql.append("COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, MCA_CORREO_ENVIADO, ");
			sql.append("MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 45, COD_N_MERCA||'-'||COD_V_ALMACEN, '-', SYSDATE, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT DVL.COD_N_MERCA, DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION,CE.COD_V_ALMACEN, ");
			sql.append("ROW_NUMBER() OVER( ");
			sql.append("PARTITION BY DVL.COD_N_MERCA,CE.COD_V_ALMACEN,DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION ");
			sql.append("ORDER BY DVL.COD_N_MERCA,CE.COD_V_ALMACEN) NUMERO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_LIN DVL ON (DVL.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND DVL.NUM_ANYO = DV.NUM_ANYO AND DVL.COD_N_VERSION = DV.COD_N_VERSION) ");
			sql.append("INNER JOIN O_CONTENEDOR_EXPEDIDO CE ON (CE.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND CE.NUM_ANYO_DV = DV.NUM_ANYO AND CE.COD_N_VERSION_DV= DV.COD_N_VERSION) ");
			sql.append("LEFT JOIN S_PCA_PRODUCTO PCA ON (PCA.COD_N_PRODUCTO = DVL.COD_N_MERCA AND PCA.COD_V_CENTRO = CE.COD_V_ALMACEN) ");
			sql.append("WHERE DV.COD_V_EXPEDICION IS NULL ");
			sql.append("AND PCA.NUM_PCA IS NULL ");
			sql.append("AND DV.NUM_DOSIER = ?numDosier ");
			sql.append("AND DV.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NA ");
			sql.append("WHERE NA.COD_V_ELEMENTO = DVL.COD_N_MERCA||'-'||CE.COD_V_ALMACEN ");
			sql.append("AND NA.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND NA.NUM_ANYO = DV.NUM_ANYO ");
			sql.append("AND NA.COD_N_VERSION = DV.COD_N_VERSION ");
			sql.append("AND NA.COD_N_ALERTA = 45) ");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 45 ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "validarPaisREA", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void alertaErrorFactura(DosierPkJPA dosier) {		
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("UPDATE O_DECLARACION_VALOR_LIN DVL SET MCA_ERROR = 'S' ");
			sql.append("WHERE EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DVC ");
			sql.append("WHERE DVC.COD_V_EXPEDICION IS NULL ");
			sql.append("AND DVC.NUM_DOSIER = ?numDosier ");
			sql.append("AND DVC.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND DVC.COD_N_DECLARACION_VALOR = DVL.COD_N_DECLARACION_VALOR ");
			sql.append("AND DVC.NUM_ANYO = DVL.NUM_ANYO ");
			sql.append("AND DVC.COD_N_VERSION = DVL.COD_N_VERSION ");
			sql.append(") ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV N ");
			sql.append("WHERE (N.COD_V_ELEMENTO = TO_CHAR(DVL.COD_N_MERCA) ");
			sql.append("OR (SUBSTR(N.COD_V_ELEMENTO,1,INSTR(N.COD_V_ELEMENTO,'-')-1) IS NOT NULL ");
			sql.append("AND N.COD_V_ELEMENTO = TO_CHAR(SUBSTR(N.COD_V_ELEMENTO,1,INSTR(N.COD_V_ELEMENTO,'-')-1)) ");
			sql.append(") ");
			sql.append(") ");
			sql.append("AND N.COD_N_DECLARACION_VALOR = DVL.COD_N_DECLARACION_VALOR ");
			sql.append("AND N.NUM_ANYO = DVL.NUM_ANYO ");
			sql.append("AND N.COD_N_VERSION = DVL.COD_N_VERSION ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "alertaErrorFactura", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void alertaFacturaOK(DosierPkJPA dosier, String codigoUsuario) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_NOTIF_ALERTA_EXPED_DV (COD_N_ALERTA, COD_V_ELEMENTO, COD_V_EXPEDICION, FEC_D_ALBARAN, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, MCA_CORREO_ENVIADO, ");
			sql.append("MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 1, COD_N_DECLARACION_VALOR, '-', SYSDATE, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, ");
			sql.append("ROW_NUMBER() OVER(PARTITION BY DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION ORDER BY DV.COD_N_VERSION) NUMERO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE DV.COD_V_EXPEDICION IS NULL AND DV.NUM_DOSIER = ?numDosier ");
			sql.append("AND DV.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NA ");
			sql.append("WHERE NA.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND NA.NUM_ANYO = DV.NUM_ANYO ");
			sql.append("AND NA.COD_N_VERSION = DV.COD_N_VERSION ");
			sql.append("AND NA.COD_N_ALERTA <> 46) ");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 1 ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "alertaFacturaOK", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void facturaOK(DosierPkJPA dosier) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("UPDATE O_DECLARACION_VALOR_CAB SET MCA_DV_CORRECTA = 'S' ");
			sql.append("WHERE (COD_N_DECLARACION_VALOR,NUM_ANYO,COD_N_VERSION) IN ( ");
			sql.append("SELECT F.COD_N_DECLARACION_VALOR,F.NUM_ANYO,F.COD_N_VERSION ");
			sql.append("FROM O_DECLARACION_VALOR_CAB F ");
			sql.append("INNER JOIN S_NOTIF_ALERTA_EXPED_DV NAE ON (NAE.COD_N_DECLARACION_VALOR = F.COD_N_DECLARACION_VALOR  ");
			sql.append("AND NAE.NUM_ANYO = F.NUM_ANYO AND NAE.COD_N_VERSION = F.COD_N_VERSION AND NAE.COD_N_ALERTA = 1) ");
			sql.append("WHERE F.COD_V_EXPEDICION IS NULL AND F.NUM_DOSIER = ?numDosier ");
			sql.append("AND F.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("AND F.MCA_ULTIMA_VIGENTE = 'S' ");
			sql.append("AND F.COD_V_EXPEDICION IS NULL ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "facturaOK", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void errorDosier(DosierPkJPA dosier) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("UPDATE D_DOSIER D ");
			sql.append("SET D.MCA_ERROR = 'S' ");
			sql.append("WHERE D.NUM_DOSIER = ?numDosier ");
			sql.append("AND D.NUM_ANYO = ?anyoDosier ");
			sql.append("AND 0 <> ( ");
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM O_DECLARACION_VALOR_CAB F ");
			sql.append("WHERE F.NUM_DOSIER = D.NUM_DOSIER ");
			sql.append("AND F.NUM_ANYO_DOSIER = D.NUM_ANYO ");
			sql.append("AND F.MCA_ULTIMA_VIGENTE = 'S' ");
			sql.append("AND F.MCA_DV_CORRECTA = 'N' ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "errorDosier", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void dosierOK(DosierPkJPA dosier, String codigoUsuario) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_NOTIFICACION_ALERTA (COD_N_ALERTA, COD_V_ELEMENTO, MCA_CORREO_ENVIADO, ");
			sql.append("MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 0, SUBSTR(NUM_ANYO_DOSIER,3,2)||'-'||LPAD(NUM_DOSIER,5,'0'), 'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT F.COD_N_DECLARACION_VALOR, F.NUM_ANYO, F.COD_N_VERSION, F.NUM_ANYO_DOSIER, F.NUM_DOSIER, ");
			sql.append("ROW_NUMBER() OVER(PARTITION BY F.NUM_ANYO_DOSIER,F.NUM_DOSIER ORDER BY F.NUM_DOSIER) NUMERO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB F ");
			sql.append("INNER JOIN D_DOSIER D ON (D.NUM_DOSIER = F.NUM_DOSIER AND D.NUM_ANYO = F.NUM_ANYO_DOSIER) ");
			sql.append("WHERE F.NUM_DOSIER = ?numDosier ");
			sql.append("AND F.NUM_ANYO_DOSIER = ?anyoDosier ");
			sql.append("and MCA_ERROR = 'N' ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NA ");
			sql.append("WHERE NA.COD_N_DECLARACION_VALOR = F.COD_N_DECLARACION_VALOR ");
			sql.append("AND NA.NUM_ANYO = F.NUM_ANYO ");
			sql.append("AND NA.COD_N_VERSION = F.COD_N_VERSION ");
			sql.append("AND NA.COD_N_ALERTA = 0) ");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 0 ");
			sql.append(")");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "dosierOK", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void updateContenedoresFicticios(DosierEquipoJPA dosier, String codigoUsuario) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("MERGE INTO O_CONTENEDOR_EXPEDIDO OCE USING ( ");
			sql.append("SELECT DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, CE.COD_V_ALMACEN, CE.NUM_CONTENEDOR, CE.FEC_DT_EXPEDICION ");
			sql.append("FROM O_CONTENEDOR_EXPEDIDO CE ");
			sql.append("INNER JOIN D_CARGA C ON (C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND C.COD_V_CARGA = CE.COD_V_CARGA ");
			sql.append("AND C.COD_N_TIPO_CARGA IN (3,4)) ");
			sql.append("INNER JOIN S_CARGA_PEDIDO CP ON (CP.COD_V_CARGA = CE.COD_V_CARGA AND CP.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN) ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.NUM_DOSIER = CE.NUM_DOSIER AND DV.NUM_ANYO_DOSIER = CE.NUM_ANYO  ");
			sql.append("AND DV.COD_V_PEDIDO = CP.COD_V_PEDIDO) ");
			sql.append("WHERE CE.NUM_DOSIER = ?numDosier ");
			sql.append("AND CE.NUM_ANYO = ?anyoDosier ");
			sql.append("GROUP BY DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, CE.COD_V_ALMACEN, CE.NUM_CONTENEDOR, CE.FEC_DT_EXPEDICION ");
			sql.append(") TEMP ");
			sql.append("ON (OCE.COD_V_ALMACEN = TEMP.COD_V_ALMACEN AND OCE.NUM_CONTENEDOR = TEMP.NUM_CONTENEDOR  ");
			sql.append("AND OCE.FEC_DT_EXPEDICION = TEMP.FEC_DT_EXPEDICION) ");
			sql.append("WHEN MATCHED THEN UPDATE SET ");
			sql.append("OCE.COD_N_DECLARACION_VALOR = TEMP.COD_N_DECLARACION_VALOR, ");
			sql.append("OCE.NUM_ANYO_DV = TEMP.NUM_ANYO, ");
			sql.append("OCE.COD_N_VERSION_DV = TEMP.COD_N_VERSION, ");
			sql.append("OCE.FEC_D_MODIFICACION = SYSDATE, ");
			sql.append("OCE.COD_V_USR_MODIFICACION = ?codigoUsuario");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "updateContenedoresFicticios", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void relFacturaPedidoCargaTT(DosierEquipoJPA dosier, String codigoUsuario) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_DECLARACION_VALOR_PEDIDO (COD_N_DECLARACION_VALOR,NUM_ANYO_DV,COD_N_VERSION_DV,COD_V_PEDIDO, ");
			sql.append("FEC_DT_CREACION,COD_V_APLICACION,COD_V_USUARIO_CREACION) ");
			sql.append("SELECT DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION, E.COD_V_PEDIDO, ");
			sql.append("SYSDATE,'GESADUAN', ?codigoUsuario ");
			sql.append("FROM O_CONTENEDOR_EXPEDIDO CE ");
			sql.append("INNER JOIN D_CARGA C ON (C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND C.COD_V_CARGA = CE.COD_V_CARGA  ");
			sql.append("AND C.COD_N_TIPO_CARGA IN (1,2)) ");
			sql.append("INNER JOIN O_EXPEDIDO E ON (E.NUM_CONTENEDOR_BASE = CE.NUM_CONTENEDOR AND E.COD_V_ALMACEN = CE.COD_V_ALMACEN  ");
			sql.append("AND E.FEC_DT_EXPEDICION = CE.FEC_DT_EXPEDICION) ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.NUM_DOSIER = CE.NUM_DOSIER AND DV.NUM_ANYO_DOSIER = CE.NUM_ANYO  ");
			sql.append("AND NVL(DV.COD_N_CATEGORIA,0) = NVL(C.COD_N_CATEGORIA,0)) ");
			sql.append("WHERE CE.NUM_DOSIER = ?numDosier ");
			sql.append("AND CE.NUM_ANYO = ?anyoDosier ");
			sql.append("GROUP BY DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION,E.COD_V_PEDIDO ");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "relFacturaPedidoCargaTT", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	@Override
	public void relFacturaPedidoCargaDG(DosierEquipoJPA dosier, String codigoUsuario) {
		try {			
			StringBuilder sql = new StringBuilder();
		
			sql.append("INSERT INTO S_DECLARACION_VALOR_PEDIDO (COD_N_DECLARACION_VALOR,NUM_ANYO_DV,COD_N_VERSION_DV,COD_V_PEDIDO, ");
			sql.append("FEC_DT_CREACION,COD_V_APLICACION,COD_V_USUARIO_CREACION) ");
			sql.append("SELECT DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION,DV.COD_V_PEDIDO, ");
			sql.append("SYSDATE,'GESADUAN', ?codigoUsuario ");
			sql.append("FROM O_CONTENEDOR_EXPEDIDO CE ");
			sql.append("INNER JOIN D_CARGA C ON (C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND C.COD_V_CARGA = CE.COD_V_CARGA AND C.COD_N_TIPO_CARGA IN (3,4)) ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.COD_N_DECLARACION_VALOR = CE.COD_N_DECLARACION_VALOR AND DV.NUM_ANYO = CE.NUM_ANYO_DV AND DV.COD_N_VERSION = CE.COD_N_VERSION_DV) ");
			sql.append("WHERE CE.NUM_DOSIER = ?numDosier ");
			sql.append("AND CE.NUM_ANYO = ?anyoDosier ");
			sql.append("GROUP BY DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION,DV.COD_V_PEDIDO ");
					
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDosier", dosier.getNumDosier());
			query.setParameter("anyoDosier", dosier.getAnyoDosier());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "relFacturaPedidoCargaDG", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}

}
