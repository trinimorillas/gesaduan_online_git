package es.mercadona.gesaduan.dao.proveedores.getproveedores.v1.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.proveedores.getproveedores.v1.GetProveedoresDAO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.InputGetProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull.OutputGetProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull.ProveedorDTO;
import es.mercadona.gesaduan.jpa.proveedores.getproveedores.v1.ProveedoresJPA;

@SuppressWarnings("unchecked")
public class GetProveedoresDAOImpl extends BaseDAO<ProveedoresJPA> implements GetProveedoresDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = ProveedoresJPA.class;
	}

	
	@Override
	public OutputGetProveedoresDTO obtenerProveedores(InputGetProveedoresDTO input, BoPage paginacion) {

		List<ProveedorDTO> resultList = new ArrayList<>();
		OutputGetProveedoresDTO result = new OutputGetProveedoresDTO();
		
		try {		

			final StringBuilder sqlService = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			Integer paginaInicio = null; 		
			if(paginacion.getPage() != null) {
				paginaInicio = paginacion.getPage().intValue();
			}
			
			Integer paginaTamanyo = null; 		
			if(paginacion.getLimit() != null) {
				paginaTamanyo = paginacion.getLimit().intValue();
			}
					
			String nombreProveedor = null;
			if(input.getNombreProveedor() != null) {
				nombreProveedor = input.getNombreProveedor();
			}
			
			String codigoProveedorLegacy = null;
			if(input.getCodigoProveedorLegacy() != null) {
				codigoProveedorLegacy = input.getCodigoProveedorLegacy();
			}
			
			String codigoProveedorPublico = null;
			if(input.getCodigoProveedorPublico() != null) {
				codigoProveedorPublico = input.getCodigoProveedorPublico();
			}
			
			String mcaNaviera = null;
			if(input.getMcaNaviera() != null) {
				mcaNaviera = input.getMcaNaviera();
			}
			
			String mcaTransportista = null;
			if(input.getMcaTransportista() != null) {
				mcaTransportista = input.getMcaTransportista();
			}
			
			String mcaProducto = null;
			if(input.getMcaProducto() != null) {
				mcaProducto = input.getMcaProducto();
			}		
			
			boolean estaActivo = input.isEstaActivo();
			boolean esAgencia = input.isEsAgencia();
			
			
			String orden = input.getOrden();		
			
			String select = "SELECT ";
			String count = "COUNT(*) ";
			String campos = "PR.COD_N_PROVEEDOR, PR.COD_N_LEGACY_PROVEEDOR, PR.TXT_RAZON_SOCIAL, PR.MCA_AGENTE_ADUANA, PR.MCA_ACTIVO_CSM, PROV.TXT_NOMBRE, PR.FEC_D_ACTIVACION ";
			String from = "FROM D_PROVEEDOR_R PR ";
			from += "INNER JOIN D_PROVINCIA_R PROV ON (PROV.COD_V_ALTERNATIVO = PR.COD_V_PROVINCIA) "; 
			String where = "WHERE 1 = 1 ";
			String order = "";		
			
			if (mcaNaviera == null && mcaTransportista == null && mcaProducto == null) {
				where += "AND MCA_ACTIVO_CSM = ?mcaActivo ";				
				where += "AND MCA_AGENTE_ADUANA = ?esAgenteAduana ";
			}
			
			if(codigoProveedorLegacy != null) {
				where += "AND (COD_N_LEGACY_PROVEEDOR = ?codLegacyProv1 OR  COD_N_PROVEEDOR = ?codLegacyProv2) ";
			}
			
			if(codigoProveedorPublico != null) {
				where += "AND (COD_N_PROVEEDOR = ?codProv1 OR  COD_N_LEGACY_PROVEEDOR = ?codProv2) ";
			}
			
			if(nombreProveedor != null) {
				where += "AND UPPER(TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?nomProveedor) ||'%') ";
			}
			
			if ("S".equals(mcaNaviera)) where += " AND MCA_NAVIERA = 'S' ";
			if ("S".equals(mcaTransportista)) where += " AND MCA_TRANSPORTISTA = 'S' ";
			if ("S".equals(mcaProducto)) where += " AND NVL(MCA_NAVIERA,'N') = 'N' AND NVL(MCA_TRANSPORTISTA,'N') = 'N'	AND NVL(MCA_AGENTE_ADUANA,'N') = 'N'  ";		
			
			if (orden != null) {
				if (orden.equals("+codigoProveedor"))
					order += " ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(PR.COD_N_LEGACY_PROVEEDOR), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(PR.COD_N_LEGACY_PROVEEDOR) END, PR.COD_N_LEGACY_PROVEEDOR";
				else if(orden.equals("-codigoProveedor"))
					order += " ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(PR.COD_N_LEGACY_PROVEEDOR), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(PR.COD_N_LEGACY_PROVEEDOR) END DESC, PR.COD_N_LEGACY_PROVEEDOR DESC";
				else if(orden.equals("+nombreProveedor"))
					order += " ORDER BY txt_razon_social asc";
				else if (orden.equals("-nombreProveedor"))
					order += " ORDER BY txt_razon_social desc";
			}
			
			sqlCount.append(select).append(count).append(from).append(where);
			sqlService.append(select).append(campos).append(from).append(where).append(order);
			
			final Query query = getEntityManager().createNativeQuery(sqlService.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());	
			
			if (mcaNaviera == null && mcaTransportista == null && mcaProducto == null) {
				if (estaActivo) {
					query.setParameter("mcaActivo", new String("S"));
					queryCount.setParameter("mcaActivo", new String("S"));
				}
				
				if (!estaActivo) {
					query.setParameter("mcaActivo", new String("N"));
					queryCount.setParameter("mcaActivo", new String("N"));
				}	
				
				if (esAgencia) {		
					query.setParameter("esAgenteAduana", new String("S"));
					queryCount.setParameter("esAgenteAduana", new String("S"));
				} else {
					query.setParameter("esAgenteAduana", new String("N"));
					queryCount.setParameter("esAgenteAduana", new String("N"));
				}
			}
	
			if (codigoProveedorLegacy != null) {
				query.setParameter("codLegacyProv1", codigoProveedorLegacy);
				queryCount.setParameter("codLegacyProv1", codigoProveedorLegacy);
				query.setParameter("codLegacyProv2", codigoProveedorLegacy);
				queryCount.setParameter("codLegacyProv2", codigoProveedorLegacy);				
			}
			
			if (codigoProveedorPublico != null) {
				query.setParameter("codProv1", codigoProveedorPublico);
				queryCount.setParameter("codProv1", codigoProveedorPublico);
				query.setParameter("codProv2", codigoProveedorPublico);
				queryCount.setParameter("codProv2", codigoProveedorPublico);				
			}
			
			if (nombreProveedor != null) {
				query.setParameter("nomProveedor", nombreProveedor);
				queryCount.setParameter("nomProveedor", nombreProveedor);	
			}
							
			if (paginaInicio != null) {
				if (paginaTamanyo != null) {
					paginaInicio = (paginaInicio * paginaTamanyo) - paginaTamanyo;
				} else {
					paginaInicio = (paginaInicio * 10) - 10;
				}
			}
						
			query.setFirstResult(paginaInicio);
			query.setMaxResults(paginaTamanyo);		
			
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					ProveedorDTO proDTO = new ProveedorDTO();
					
					String patternOutputDateTime = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
					SimpleDateFormat formatDateTime = new SimpleDateFormat(patternOutputDateTime);
					Date fechaOutput = null;
	
					proDTO.setCodigoPublico(String.valueOf(tmp[0]));
					proDTO.setCodigoLegacy(String.valueOf(tmp[1]));
					proDTO.setNombre(String.valueOf(tmp[2]));
					proDTO.setProvincia(String.valueOf(tmp[5]));
					if (tmp[6] != null) {
						fechaOutput = (Date) tmp[6];
						String dateToString = formatDateTime.format(fechaOutput);
						proDTO.setFechaActivacion(dateToString);
					} else {
						proDTO.setFechaActivacion(String.valueOf(tmp[6]));
					}
					
	
					resultList.add(proDTO);
				}
			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));
			
			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());
			
			
			result.setDatos(resultList);
			result.setMetadatos(mapaMetaData);
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetProveedoresDAOImpl(GESADUAN)","obtenerProveedores",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}

}
