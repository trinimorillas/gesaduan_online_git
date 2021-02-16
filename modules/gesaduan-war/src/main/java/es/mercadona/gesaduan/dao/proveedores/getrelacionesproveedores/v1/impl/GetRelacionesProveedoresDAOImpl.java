package es.mercadona.gesaduan.dao.proveedores.getrelacionesproveedores.v1.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.proveedores.getrelacionesproveedores.v1.GetRelacionesProveedoresDAO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.InputRelacionesProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull.DatosRelacionesProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull.OutputRelacionesProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull.RelacionesProveedoresDTO;
import es.mercadona.gesaduan.jpa.proveedores.getproveedores.v1.ProveedoresJPA;

public class GetRelacionesProveedoresDAOImpl extends BaseDAO<ProveedoresJPA> implements GetRelacionesProveedoresDAO {
	
	@Inject
	private org.slf4j.Logger logger;		

	@SuppressWarnings("unchecked")
	@Override
	public OutputRelacionesProveedoresDTO getRelacionesProveedores(InputRelacionesProveedoresDTO input,
			BoPage pagination) {

		OutputRelacionesProveedoresDTO response = new OutputRelacionesProveedoresDTO();
		DatosRelacionesProveedoresDTO datos = new DatosRelacionesProveedoresDTO();
		List<RelacionesProveedoresDTO> listaRelaciones = new ArrayList<>();
		
		try {			

			String patternOutputDateTime = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
			SimpleDateFormat formatDateTime = new SimpleDateFormat(patternOutputDateTime);
			Date fechaOutput = null;
	
			String proveedorRelacionado = (input.getNumeroProveedorRelacionado() != null)
					? input.getNumeroProveedorRelacionado()
					: null;
			String nombreProveedorRelacionado = (input.getNombreProveedorRelacionado() != null)
					? input.getNombreProveedorRelacionado()
					: null;
	
			String tipo = input.getTipoBusqueda();
			boolean esVigente = input.getRelacionesVigentes();
	
			String codigoProveedor = input.getCodigoProveedor();
	
			Integer paginaTamanyo = pagination.getLimit().intValue();
			Integer paginaInicio = (pagination.getPage().intValue() * paginaTamanyo) - paginaTamanyo;
	
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
	
			if (tipo.equals("PROV")) {
				
				if(esVigente) {
					sql.append(" SELECT AGENCIA.COD_N_LEGACY_PROVEEDOR, ");
					sql.append(" AGENCIA.TXT_RAZON_SOCIAL, ");
					sql.append(" AGENCIA.TXT_DISTRITO, ");
					sql.append(" RPP.FEC_D_INICIO, ");
					sql.append(" RPP.FEC_D_FIN, ");
					sql.append(" AGENCIA.COD_N_PROVEEDOR ");
					sql.append(" FROM D_PROVEEDOR_R PROV ");
					sql.append(" JOIN S_RELACION_PROVEEDOR_R RPP ON RPP.COD_N_PROVEEDOR = PROV.COD_N_PROVEEDOR ");
					sql.append(" JOIN D_PROVEEDOR_R AGENCIA ON AGENCIA.COD_N_PROVEEDOR = rpp.cod_n_agencia_aduana ");
					sql.append(" WHERE PROV.COD_N_PROVEEDOR = ?codigoProveedor ");
					sql.append(" AND (RPP.FEC_D_FIN IS NULL OR RPP.FEC_D_FIN >= TRUNC(SYSDATE)) ");
				}else {
					sql.append(" SELECT AGENCIA.COD_N_LEGACY_PROVEEDOR, ");
					sql.append(" AGENCIA.TXT_RAZON_SOCIAL, ");
					sql.append(" AGENCIA.TXT_DISTRITO, ");
					sql.append(" NULL AS FEC_D_INICIO, ");
					sql.append(" NULL AS FEC_D_FIN, ");
					sql.append(" AGENCIA.COD_N_PROVEEDOR ");
					sql.append(" FROM D_PROVEEDOR_R AGENCIA ");
					sql.append(" WHERE AGENCIA.MCA_AGENTE_ADUANA = 'S' ");
					sql.append(" AND AGENCIA.MCA_ACTIVO_CSM = 'S' ");
					sql.append(" AND NOT EXISTS ");
					sql.append(" (SELECT AGEN.COD_N_AGENCIA_ADUANA ");
					sql.append(" FROM S_RELACION_PROVEEDOR_R AGEN ");
					sql.append(" WHERE AGENCIA.COD_N_PROVEEDOR = AGEN.COD_N_AGENCIA_ADUANA ");
					sql.append(" AND (AGEN.FEC_D_INICIO <= sysdate ");
					sql.append(" AND (AGEN.FEC_D_FIN IS NULL ");
					sql.append(" OR AGEN.FEC_D_FIN >= SYSDATE)) ");
					sql.append(" AND AGEN.COD_N_PROVEEDOR = ?codigoProveedor) ");
				}
	
				if (proveedorRelacionado != null) {
					sql.append(
							" AND (AGENCIA.COD_N_LEGACY_PROVEEDOR = ?proveedorRelacionado OR AGENCIA.COD_N_PROVEEDOR = ?proveedorRelacionado) ");
				}
	
				if (nombreProveedorRelacionado != null) {
					sql.append(
							" AND UPPER(AGENCIA.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?nombreProveedorRelacionado) || '%') ");
				}
	
				String orden = input.getOrden();
				if (orden.equals("+codigo")) {
					sql.append(" ORDER BY AGENCIA.COD_N_LEGACY_PROVEEDOR ASC ");
				} else if (orden.equals("-codigo")) {
					sql.append(" ORDER BY AGENCIA.COD_N_LEGACY_PROVEEDOR DESC ");
				} else if (orden.equals("+nombre")) {
					sql.append(" ORDER BY AGENCIA.TXT_RAZON_SOCIAL ASC ");
				} else if (orden.equals("-nombre")) {
					sql.append(" ORDER BY AGENCIA.TXT_RAZON_SOCIAL DESC ");
				}
	
				final Query query = getEntityManager().createNativeQuery(sql.toString());
	
				sqlCount.append(" SELECT COUNT(*) FROM( ").append(sql).append(" )");
	
				final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
	
				query.setParameter("codigoProveedor", codigoProveedor);
				queryCount.setParameter("codigoProveedor", codigoProveedor);
				
				if (proveedorRelacionado != null) {
					query.setParameter("proveedorRelacionado", proveedorRelacionado);
					queryCount.setParameter("proveedorRelacionado", proveedorRelacionado);
				}
				if (nombreProveedorRelacionado != null) {
					query.setParameter("nombreProveedorRelacionado", nombreProveedorRelacionado);
					queryCount.setParameter("nombreProveedorRelacionado", nombreProveedorRelacionado);
				}
	
				query.setFirstResult(paginaInicio);
				query.setMaxResults(paginaTamanyo);
	
				List<Object[]> listado = query.getResultList();
	
				if (listado != null && !listado.isEmpty()) {
					for (Object[] tmp : listado) {
						RelacionesProveedoresDTO relacion = new RelacionesProveedoresDTO();
	
						if (tmp[5] != null) {
							relacion.setCodigo(Long.parseLong((String.valueOf(tmp[5]))));
						} else {
							relacion.setCodigo(new Long(0));
						}
	
						relacion.setNombre(String.valueOf(tmp[1]));
						relacion.setProvincia(String.valueOf(tmp[2]));
						relacion.setCodigoPublico(String.valueOf(tmp[0]));
	
						if (tmp[3] != null) {
							fechaOutput = (Date) tmp[3];
							String dateToString = formatDateTime.format(fechaOutput);
							relacion.setFechaInicio(dateToString);
						} else {
							relacion.setFechaInicio(String.valueOf(tmp[3]));
						}
	
						if (tmp[4] != null) {
							fechaOutput = (Date) tmp[4];
							String dateToString = formatDateTime.format(fechaOutput);
							relacion.setFechaFin(dateToString);
						} else {
							relacion.setFechaFin(String.valueOf(tmp[4]));
						}
	
						listaRelaciones.add(relacion);
					}
				}
	
				Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));
	
				HashMap<String, String> metadatos = new HashMap<>();
				metadatos.put("totalItemsCount", totalResults.toString());
	
				response.setMetadatos(metadatos);
	
			} else if (tipo.equals("AGEN")) {
				
				if(esVigente) {
					sql.append(" SELECT ");
					sql.append(" PROV.COD_N_LEGACY_PROVEEDOR, ");
					sql.append(" PROV.TXT_RAZON_SOCIAL, ");
					sql.append(" PROV.TXT_DISTRITO, ");
					sql.append(" RPA.FEC_D_INICIO, ");
					sql.append(" RPA.FEC_D_FIN, ");
					sql.append(" PROV.COD_N_PROVEEDOR ");
					sql.append(" FROM D_PROVEEDOR_R PROV ");
					sql.append(" LEFT JOIN S_RELACION_PROVEEDOR_R RPA ON RPA.COD_N_PROVEEDOR = PROV.COD_N_PROVEEDOR ");
					sql.append(" WHERE RPA.COD_N_AGENCIA_ADUANA = ?codigoProveedor ");
					sql.append(" AND (RPA.FEC_D_FIN IS NULL OR RPA.FEC_D_FIN >= TRUNC(SYSDATE)) ");
				}else {
					sql.append(" SELECT PROV.COD_N_LEGACY_PROVEEDOR, ");
					sql.append(" PROV.TXT_RAZON_SOCIAL, ");
					sql.append(" PROV.TXT_DISTRITO, ");
					sql.append(" NULL AS FEC_D_INICIO, ");
					sql.append(" NULL AS FEC_D_FIN, ");
					sql.append(" PROV.COD_N_PROVEEDOR ");
					sql.append(" FROM D_PROVEEDOR_R PROV ");
					sql.append(" WHERE PROV.MCA_AGENTE_ADUANA = 'N' ");
					sql.append(" AND PROV.MCA_ACTIVO_CSM = 'S' ");
					sql.append(" AND NOT EXISTS ");
					sql.append(" (SELECT AGEN.COD_N_AGENCIA_ADUANA ");
					sql.append(" FROM S_RELACION_PROVEEDOR_R AGEN ");
					sql.append(" WHERE PROV.COD_N_PROVEEDOR = AGEN.COD_N_PROVEEDOR ");
					sql.append(" AND (AGEN.FEC_D_INICIO <= sysdate ");
					sql.append(" AND (AGEN.FEC_D_FIN IS NULL ");
					sql.append(" OR AGEN.FEC_D_FIN >= SYSDATE)) ");
					sql.append(" AND AGEN.COD_N_AGENCIA_ADUANA = ?codigoProveedor) ");
				}
	
				if (proveedorRelacionado != null) {
					sql.append(
							" AND (PROV.COD_N_LEGACY_PROVEEDOR = ?proveedorRelacionado OR PROV.COD_N_PROVEEDOR = ?proveedorRelacionado) ");
				}
	
				if (nombreProveedorRelacionado != null) {
					sql.append(" AND UPPER(PROV.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?nombreProveedorRelacionado) || '%') ");
				}
	
				String orden = input.getOrden();
				if (orden.equals("+codigo")) {
					sql.append(" ORDER BY PROV.COD_N_LEGACY_PROVEEDOR ASC ");
				} else if (orden.equals("-codigo")) {
					sql.append(" ORDER BY PROV.COD_N_LEGACY_PROVEEDOR DESC ");
				} else if (orden.equals("+nombre")) {
					sql.append(" ORDER BY PROV.TXT_RAZON_SOCIAL ASC ");
				} else if (orden.equals("-nombre")) {
					sql.append(" ORDER BY PROV.TXT_RAZON_SOCIAL DESC ");
				}
	
				final Query query = getEntityManager().createNativeQuery(sql.toString());
	
				sqlCount.append(" SELECT COUNT(*) FROM( ").append(sql).append(" )");
	
				final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
	
				query.setParameter("codigoProveedor", codigoProveedor);
				queryCount.setParameter("codigoProveedor", codigoProveedor);
				
				if (proveedorRelacionado != null) {
					query.setParameter("proveedorRelacionado", proveedorRelacionado);
					queryCount.setParameter("proveedorRelacionado", proveedorRelacionado);
				}
				if (nombreProveedorRelacionado != null) {
					query.setParameter("nombreProveedorRelacionado", nombreProveedorRelacionado);
					queryCount.setParameter("nombreProveedorRelacionado", nombreProveedorRelacionado);
				}
	
				query.setFirstResult(paginaInicio);
				query.setMaxResults(paginaTamanyo);
	
				List<Object[]> listado = query.getResultList();
	
				if (listado != null && !listado.isEmpty()) {
					for (Object[] tmp : listado) {
						RelacionesProveedoresDTO relacion = new RelacionesProveedoresDTO();
	
						if (tmp[5] != null) {
							relacion.setCodigo(Long.parseLong((String.valueOf(tmp[5]))));
						} else {
							relacion.setCodigo(new Long(0));
						}
	
						relacion.setCodigoPublico(String.valueOf(tmp[0]));
						relacion.setNombre(String.valueOf(tmp[1]));
						relacion.setProvincia(String.valueOf(tmp[2]));
						if (tmp[3] != null) {
							fechaOutput = (Date) tmp[3];
							String dateToString = formatDateTime.format(fechaOutput);
							relacion.setFechaInicio(dateToString);
						} else {
							relacion.setFechaInicio(String.valueOf(tmp[3]));
						}
	
						if (tmp[4] != null) {
							fechaOutput = (Date) tmp[4];
							String dateToString = formatDateTime.format(fechaOutput);
							relacion.setFechaFin(dateToString);
						} else {
							relacion.setFechaFin(String.valueOf(tmp[4]));
						}
						listaRelaciones.add(relacion);
					}
				}
	
				Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));
	
				HashMap<String, String> metadatos = new HashMap<>();
				metadatos.put("totalItemsCount", totalResults.toString());
	
				response.setMetadatos(metadatos);
	
			}
	
			datos.setRelacionProveedor(listaRelaciones);
			response.setDatos(datos);
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetRelacionesProveedoresDAOImpl(GESADUAN)","getRelacionesProveedores",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		

		return response;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = ProveedoresJPA.class;
	}

}
