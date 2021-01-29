package es.mercadona.gesaduan.dao.reas.getreasdetalle.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.reas.getreasdetalle.v1.GetReasDetalleDAO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.InputReasDetalleDTO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.restfull.DatosDetalleDTO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.restfull.OutputReasDetalleDTO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.restfull.ProductosDetalleDTO;

import es.mercadona.gesaduan.jpa.reas.v1.ReasPostJPA;


@SuppressWarnings({ "unchecked" })
public class GetReasDetalleDAOImpl extends DaoBaseImpl<String, ReasPostJPA> implements GetReasDetalleDAO{


	@Inject
	private org.slf4j.Logger logger;
	  
	@Inject
	private SecurityService securityService;
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public void setEntityClass() {
		this.entityClass = ReasPostJPA.class;
		
	}
	
	
	@Override
	public OutputReasDetalleDTO getReasDetalle(InputReasDetalleDTO input) {
		
		OutputReasDetalleDTO result = new OutputReasDetalleDTO();
		String codigoRea = input.getCodigoRea();
		List<DatosDetalleDTO> resultList =  new ArrayList<>();
		HashMap<String,String> metadatos = new HashMap<>();
		
		resultList = getReasProductos(codigoRea);
		result.setDatos(resultList);
		result.setMetadatos(metadatos);
		
		return result;
	}
	
	
	private List<ProductosDetalleDTO> getProductos(String codigoRea){
		
		final StringBuilder sqlProductos = new StringBuilder();
		
		sqlProductos.append(" SELECT PR.COD_N_PRODUCTO, ");
		sqlProductos.append(" PR.TXT_DENOMINA_ETIQUETA, ");
		sqlProductos.append(" PR.NUM_FORMATO_VENTA, ");
		sqlProductos.append(" PR.TXT_MARCA, ");
		sqlProductos.append(" PR.TXT_DENOMINA_ALTERNATIVA, ");
		sqlProductos.append(" PR.TXT_FORMATO_ALTERNATIVO, ");
		sqlProductos.append(" TR.COD_N_TARIC, ");
		sqlProductos.append(" RP.COD_V_REA, ");
		sqlProductos.append(" CPR.COD_V_SECCION ");
		sqlProductos.append(" FROM D_PRODUCTO_R PR  ");
		sqlProductos.append(" INNER JOIN S_REA_PRODUCTO RP ON RP.COD_N_PRODUCTO = PR.COD_N_PRODUCTO AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN IS NULL OR RP.FEC_D_FIN >= SYSDATE) ");
		sqlProductos.append(" INNER JOIN S_COMPONENTES_PRODUCTO_R CPR ON CPR.COD_N_PRODUCTO = PR.COD_N_PRODUCTO ");
		sqlProductos.append(" INNER JOIN S_TARIC_PRODUCTO TR ON TR.COD_N_PRODUCTO = PR.COD_N_PRODUCTO AND TR.FEC_D_INICIO <= SYSDATE AND (TR.FEC_D_FIN IS NULL OR TR.FEC_D_FIN >= SYSDATE) ");
		sqlProductos.append(" WHERE RP.COD_V_REA = ?codigoRea ");
		
		final Query queryProductos = getEntityManager().createNativeQuery(sqlProductos.toString());
		
		queryProductos.setParameter("codigoRea", codigoRea);
		
		List<Object[]> listado = queryProductos.getResultList();
		
		List<ProductosDetalleDTO> resultList = new ArrayList<>();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
				ProductosDetalleDTO productos = new ProductosDetalleDTO();
					
					productos.setCodigo(String.valueOf(tmp[0]));
					productos.setDenominacion(String.valueOf(tmp[1]));
					productos.setFormatoVenta(String.valueOf(tmp[2]));
					productos.setMarca(String.valueOf(tmp[3]));
					productos.setDenominacionAlt(String.valueOf(tmp[4]));
					productos.setFormatoVentaAlt(String.valueOf(tmp[5]));
					productos.setCodigoTaric(String.valueOf(tmp[6]));
					productos.setCodigoRea(String.valueOf(tmp[7]));
					if(tmp[8].equals("15")) {
						productos.setEsListoParaComer(true);
					}else {
						productos.setEsListoParaComer(false);
					}
					
					resultList.add(productos);
				}
			}
		
		return resultList;
	}
	
	
	private List<DatosDetalleDTO> getReasProductos(String codigoRea){
		
		List<DatosDetalleDTO> resultList = null;
		
		try {
			final StringBuilder sqlReas = new StringBuilder();
			
			sqlReas.append(" SELECT DISTINCT CR.COD_V_REA, TR.COD_N_TARIC, COUNT(DISTINCT RP.COD_N_PRODUCTO) AS PRODUCTOS FROM D_CODIGO_REA CR ");
			sqlReas.append(" INNER JOIN S_TARIC_REA TR ON TR.COD_V_REA = CR.COD_V_REA AND TR.FEC_D_INICIO <= SYSDATE AND (TR.FEC_D_FIN IS NULL OR TR.FEC_D_FIN >= SYSDATE)");
			sqlReas.append(" INNER JOIN S_REA_PRODUCTO RP ON CR.COD_V_REA = RP.COD_V_REA AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN IS NULL OR RP.FEC_D_FIN >= SYSDATE)");
			sqlReas.append(" WHERE CR.COD_V_REA = ?codigoRea ");
			sqlReas.append(" GROUP BY CR.COD_V_REA, TR.COD_N_TARIC ");
			
			final Query queryReas = getEntityManager().createNativeQuery(sqlReas.toString());
			
			queryReas.setParameter("codigoRea", codigoRea);
			
			List<Object[]> listado = queryReas.getResultList();
			
			resultList = new ArrayList<>();
			
			List<ProductosDetalleDTO> productosList = new ArrayList<>();
			productosList = getProductos(codigoRea);
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
						DatosDetalleDTO reas = new DatosDetalleDTO();
						
						List<ProductosDetalleDTO> addProd =  new ArrayList<>();
						
						for(ProductosDetalleDTO prod : productosList ) {
							String productoRea = prod.getCodigoRea();
							String rea = String.valueOf(tmp[0]);
							ProductosDetalleDTO productosPorRea = new ProductosDetalleDTO();
							
							if(productoRea.equals(rea)) {
								productosPorRea.setCodigo(prod.getCodigo());
								productosPorRea.setDenominacion(prod.getDenominacion());
								productosPorRea.setFormatoVenta(prod.getFormatoVenta());
								productosPorRea.setMarca(prod.getMarca());
								productosPorRea.setDenominacionAlt(prod.getDenominacionAlt());
								productosPorRea.setFormatoVentaAlt(prod.getFormatoVentaAlt());
								productosPorRea.setCodigoTaric(prod.getCodigoTaric());
								productosPorRea.setCodigoRea(prod.getCodigoRea());
								productosPorRea.setEsListoParaComer(prod.isEsListoParaComer());
								
								addProd.add(productosPorRea);
							}
							
						}
						
						reas.setCodigo(String.valueOf(tmp[0]));
						reas.setCodigoTaric(String.valueOf(tmp[1]));
						reas.setNumeroProductos(Long.parseLong(String.valueOf(tmp[2])));
						reas.setProductos(addProd);
						
						
						resultList.add(reas);
					}
				}
		} catch (NumberFormatException e) {
			establecerSalidaError(e, "getReasProductos", "error.NumberFormatException");
			throw new ApplicationException(e.getMessage());
		} catch (Exception e) {
			establecerSalidaError(e, "getReasProductos", "error.Exception");
			throw new ApplicationException(e.getMessage());
		}
		
		return resultList;
	}

	private void establecerSalidaError(Exception exception, String metodo, String codError) {

	    String login = this.securityService.getPrincipal().getLogin();
	    
	    this.logger.error("Error ejecutando la clase: GetReasDetalleDAOImpl",
	        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
  }


	@Override
	public boolean checkExistRea(InputReasDetalleDTO input) {
		
		ReasPostJPA rea = findById(input.getCodigoRea());  
		if(rea != null) {
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}
	
}
