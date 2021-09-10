package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putreturns.v2.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putreturns.v2.PutReturnsDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.ItemListDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.ValueDeclarationJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.ValueDeclarationPK;
import es.mercadona.gesaduan.util.DaoUtil;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.DeclarationLineJPA;

@Stateless
public class PutReturnsDAOImpl extends DaoBaseImpl<ValueDeclarationPK, ValueDeclarationJPA> implements PutReturnsDAO {
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = ValueDeclarationJPA.class;
	}

	@Inject
	private org.slf4j.Logger logger;

	@PersistenceContext
	private EntityManager entityM;
	
	private static final String NOMBRE_CLASE = "PutReturnsDAOImpl(GESADUAN)";
	private static final String VALUE_DECLARATION_NUMBER = "valueDeclarationNumber";
	private static final String VALUE_DECLARATION_YEAR = "valueDeclarationYear";
	private static final String VALUE_DECLARATION_VERSION = "valueDeclarationVersion";

	@Override
	@Transactional
	public void putReturns(ValueDeclarationJPA input) {
		try {
			entityM.persist(input);
			entityM.flush();			
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "putReturns", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	public Long getDeclarationNumber() {
		Long result;
		try {		
			final StringBuilder sql = new StringBuilder();
				
			sql.append("SELECT DECLARACION_VALOR_SEQ.NEXTVAL FROM DUAL");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			result = Long.parseLong(String.valueOf(query.getSingleResult()));
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getDeclarationNumber", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		
		return result;
	}
	
	@Transactional
	public Integer getProvincia(String codigoAlternativo) {
		Integer result = null;
		try {		
			final StringBuilder sql = new StringBuilder();
				
			sql.append("SELECT COD_N_PROVINCIA FROM D_PROVINCIA_R WHERE COD_V_ALTERNATIVO = ?codigoAlternativo");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoAlternativo", codigoAlternativo);
			
			@SuppressWarnings("unchecked")
			List<BigDecimal> listado = query.getResultList();			
	
			if (listado != null && !listado.isEmpty()) {
				for (BigDecimal tmp : listado) {
					result = DaoUtil.getParameterResultInteger(tmp);
				}
			}
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getDeclarationNumber", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		return result;
	}
	
	public DeclarationLineJPA getValoresProducto(ValueDeclarationJPA factura, DeclarationLineJPA lineaProducto, ItemListDTO item) {
		try {		
			final StringBuilder sql = new StringBuilder();
				
			sql.append("SELECT DISTINCT TP.COD_N_TARIC, RP.COD_V_REA, ");
			sql.append("(DECODE(?quantityUnit, 'ML', DP.NUM_FORMATO_VENTA/1000, 'LI', DP.NUM_FORMATO_VENTA) * ?cantidad), ");
			sql.append("DECODE(AP.NUM_GRADO_ALCOHOL, NULL, NULL, AP.NUM_GRADO_ALCOHOL*1000), ");
			sql.append("DECODE(AP.NUM_GRADO_PLATO, NULL, NULL, AP.NUM_GRADO_PLATO*100), ");
			sql.append("DECODE(CP.COD_V_SECCION, '15', 'S', 'N'), ");
			sql.append("DECODE(RP.COD_V_REA, NULL, DECODE(CT.MCA_FITO, 'S', DP.COD_V_PAIS_ISO_ALFA2, NULL), DP.COD_V_PAIS_ISO_ALFA2) ");
			sql.append("FROM D_PRODUCTO_R DP ");
			sql.append("LEFT JOIN S_TARIC_PRODUCTO TP ON TP.COD_N_PRODUCTO = DP.COD_N_PRODUCTO ");
			sql.append("LEFT JOIN S_REA_PRODUCTO RP ON RP.COD_N_PRODUCTO = DP.COD_N_PRODUCTO ");
			sql.append("LEFT JOIN S_ALCOHOL_PRODUCTO_R AP ON AP.COD_N_PRODUCTO = DP.COD_N_PRODUCTO ");
			sql.append("LEFT JOIN S_COMPONENTES_PRODUCTO_R CP ON CP.COD_N_PRODUCTO = DP.COD_N_PRODUCTO ");
			sql.append("LEFT JOIN D_CODIGO_TARIC CT ON CT.COD_N_TARIC = TP.COD_N_TARIC ");
			sql.append("WHERE DP.COD_N_PRODUCTO = ?codigoProducto");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("quantityUnit", item.getQuantityUnit());
			query.setParameter("cantidad", item.getQuantity());
			query.setParameter("codigoProducto", item.getItemId());
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();			
	
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					lineaProducto.setItemTaric(DaoUtil.getParameterResultLong(tmp[0]));
					lineaProducto.setItemSsrId(DaoUtil.getParameterResultString(tmp[1]));
					lineaProducto.setVolume(DaoUtil.getParameterResultDouble(tmp[2]));
					lineaProducto.setAlcoholPercentage(DaoUtil.getParameterResultDouble(tmp[3]));
					lineaProducto.setPlateGrade(DaoUtil.getParameterResultDouble(tmp[4]));
					lineaProducto.setIsReadyToEat(DaoUtil.getParameterResultString(tmp[5]));
				}
			}
			
			if (lineaProducto.getItemTaric() == null) {
				generarAlerta("27", lineaProducto.getItemId().toString(), factura);
			}
			if (lineaProducto.getVolume() == null) {
				generarAlerta("29", lineaProducto.getItemId().toString(), factura);
			}
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getValoresProducto", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		return lineaProducto;
	}
	
	@Transactional
	public Long getTaric(String codigoProducto, ValueDeclarationJPA factura) {
		Long codigoTaric = null;
		try {		
			final StringBuilder sql = new StringBuilder();
				
			sql.append("SELECT TE.COD_N_TARIC FROM S_TARIC_ENVASE TE WHERE TE.COD_V_ENVASE = ?codigoProducto");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoProducto", codigoProducto);
			
			@SuppressWarnings("unchecked")
			List<Long> listado = query.getResultList();			
	
			if (listado != null && !listado.isEmpty()) {
				for (Long tmp : listado) {
					codigoTaric = DaoUtil.getParameterResultLong(tmp);
				}
			}
			
			if (codigoTaric == null) {
				generarAlerta("52", codigoProducto, factura);
			}
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getTaric", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		
		return codigoTaric;
	}
	
	public void actualizarCabecera(ValueDeclarationJPA factura, String valor) {
		try {		
			final StringBuilder sql = new StringBuilder();
				
			sql.append("UPDATE O_DECLARACION_VALOR_CAB SET MCA_DV_CORRECTA = ?valor ");
			sql.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND COD_N_VERSION = ?valueDeclarationVersion");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter(VALUE_DECLARATION_NUMBER, factura.getValueDeclarationNumber());
			query.setParameter(VALUE_DECLARATION_YEAR, factura.getValueDeclarationYear());
			query.setParameter(VALUE_DECLARATION_VERSION, factura.getValueDeclarationVersion());
			query.setParameter("valor", valor);
			query.executeUpdate();
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "actualizarCabecera", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	public void generarAlerta(String numeroAlerta, String elemento, ValueDeclarationJPA factura) {
		try {
			final StringBuilder sql = new StringBuilder();
				
			sql.append("INSERT INTO S_NOTIF_ALERTA_EXPED_DV (COD_N_ALERTA, COD_V_ELEMENTO, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("MCA_CORREO_ENVIADO, MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, ");
			sql.append("COD_V_USUARIO_CREACION, COD_V_EXPEDICION, FEC_D_ALBARAN) ");
			sql.append("VALUES (?numeroAlerta, ?elemento, ?valueDeclarationNumber, ?valueDeclarationYear, ?valueDeclarationVersion, ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?userId, '-', SYSDATE)");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numeroAlerta", numeroAlerta);
			query.setParameter("elemento", elemento);
			query.setParameter(VALUE_DECLARATION_NUMBER, factura.getValueDeclarationNumber());
			query.setParameter(VALUE_DECLARATION_YEAR, factura.getValueDeclarationYear());
			query.setParameter(VALUE_DECLARATION_VERSION, factura.getValueDeclarationVersion());
			query.setParameter("userId", factura.getUserId());
			query.executeUpdate();
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "generarAlerta", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	public Boolean existeAlerta(ValueDeclarationJPA factura) {
		Boolean existeAlerta = false;
		
		try {
			final StringBuilder sql = new StringBuilder();
				
			sql.append("SELECT COUNT(*) FROM S_NOTIF_ALERTA_EXPED_DV ");
			sql.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND COD_N_VERSION = ?valueDeclarationVersion");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter(VALUE_DECLARATION_NUMBER, factura.getValueDeclarationNumber());
			query.setParameter(VALUE_DECLARATION_YEAR, factura.getValueDeclarationYear());
			query.setParameter(VALUE_DECLARATION_VERSION, factura.getValueDeclarationVersion());
			Integer result = Integer.parseInt(String.valueOf(query.getSingleResult()));
			if (result > 0) {
				existeAlerta = true;
			}
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "existeAlerta", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		
		return existeAlerta;
	}

}