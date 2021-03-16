package es.mercadona.gesaduan.dao.proveedores.getproveedoresdetalle.v1.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.proveedores.getproveedoresdetalle.v1.GetProveedoresDetalleDAO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.InputProveedoresDetalleDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.DatosProveedoresDetalleDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.DireccionFiscalDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.OutputProveedoresDetalleDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.PersonasContactoDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.PuertoDTO;
import es.mercadona.gesaduan.jpa.proveedores.getproveedores.v1.ProveedoresJPA;

@SuppressWarnings("unchecked")
public class GetProveedoresDetalleDAOImpl extends BaseDAO<ProveedoresJPA> implements GetProveedoresDetalleDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = ProveedoresJPA.class;
	}

	/*
	 * obtener detalle prov
	 * 
	 */
	@Override
	public OutputProveedoresDetalleDTO getProveedoresDetalle(InputProveedoresDetalleDTO input) {

		OutputProveedoresDetalleDTO result = new OutputProveedoresDetalleDTO();
		DatosProveedoresDetalleDTO datos = new DatosProveedoresDetalleDTO();

		HashMap<String, String> metadatos = new HashMap<>();

		String codigoProveedor = input.getCodigoProveedor();

		datos = getDetalle(codigoProveedor);
	
		result.setDatos(datos);
		result.setMetadatos(metadatos);

		return result;
	}

	
	private DatosProveedoresDetalleDTO getDetalle(String codigoProveedor){
		
		DatosProveedoresDetalleDTO result = new DatosProveedoresDetalleDTO();
		
		try {	
			String esAgencia;
			final StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT PROV.COD_N_LEGACY_PROVEEDOR,");
			sql.append(" PROV.TXT_RAZON_SOCIAL,");
			sql.append(" PROV.TXT_DISTRITO,");
			sql.append(" PROV.COD_N_PROVEEDOR,");
			sql.append(" PROV.FEC_D_ACTIVACION,");
			sql.append(" PROV.TXT_NOMBRE_CALLE,");
			sql.append(" PROV.TXT_NUMERO_CALLE,");
			sql.append(" PROV.TXT_CODIGO_POSTAL,");
			sql.append(" PROV.TXT_LOCALIDAD,");
			sql.append(" PROV.MCA_AGENTE_ADUANA");
			sql.append(" FROM D_PROVEEDOR_R PROV");
			sql.append(" WHERE PROV.COD_N_PROVEEDOR = ?codigoProveedor");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("codigoProveedor", codigoProveedor);
			
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					
						DireccionFiscalDTO datosDireccion = new DireccionFiscalDTO();
						List<PuertoDTO> listaPuerto = new ArrayList<>();
						
						String patternOutputDateTime = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
						SimpleDateFormat formatDateTime = new SimpleDateFormat(patternOutputDateTime);
						Date fechaOutput = null;
						
						result.setCodigoPublico(String.valueOf(tmp[0]));
						result.setNombre(String.valueOf(tmp[1]));
						result.setProvincia(String.valueOf(tmp[2]));
						result.setCodigo(String.valueOf(tmp[3]));
						if(tmp[4] != null) {
							fechaOutput = (Date) tmp[4];
							String dateToString = formatDateTime.format(fechaOutput);
							result.setFechaActivacion(dateToString);
						}else {
							result.setFechaActivacion(String.valueOf(tmp[4]));
						}
						
						
						datosDireccion.setCalle(String.valueOf(tmp[5]));
						datosDireccion.setNumero(String.valueOf(tmp[6]));
						datosDireccion.setCodigoPostal(String.valueOf(tmp[7]));
						datosDireccion.setLocalidad(String.valueOf(tmp[8]));
						datosDireccion.setProvincia(String.valueOf(tmp[2]));
						esAgencia = String.valueOf(tmp[9]);
						
						result.setDireccionFiscal(datosDireccion);
						
						if (esAgencia.equals("S")) {
							final StringBuilder sqlPuerto = new StringBuilder();
							String select = "SELECT PA.COD_N_PUERTO, PU.TXT_NOMBRE_PUERTO, PA.MCA_AGENCIA_PREFERENTE ";
							String from =   "FROM S_PUERTO_AGENCIA PA " +
										    "INNER JOIN D_PUERTO PU ON (PU.COD_N_PUERTO = PA.COD_N_PUERTO)";
							String where =  "WHERE PA.COD_V_AGENCIA_ADUANA = ?codigoProveedor";
							
							sqlPuerto.append(select).append(from).append(where);
							final Query queryPuerto = getEntityManager().createNativeQuery(sqlPuerto.toString());							
							queryPuerto.setParameter("codigoProveedor", codigoProveedor);
							
							List<Object[]> listadoPuerto = queryPuerto.getResultList();
							
							if (listadoPuerto != null && !listadoPuerto.isEmpty()) {								
								for (Object[] tmpPuerto : listadoPuerto) {
									PuertoDTO puerto = new PuertoDTO();
									if (tmpPuerto[0] != null) puerto.setCodigoPuerto(Long.parseLong(String.valueOf(tmpPuerto[0])));
									if (tmpPuerto[1] != null) puerto.setNombrePuerto(String.valueOf(tmpPuerto[1]));
									if (tmpPuerto[2] != null) puerto.setMcaPreferente(String.valueOf(tmpPuerto[2]));
									listaPuerto.add(puerto);
								}
								
							}
						}
						result.setPuerto(listaPuerto);
					}
				}
	
			result.setPersonasContacto(getPersonasContacto(codigoProveedor));
			
		} catch (Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetProveedoresDetalleDAOImpl(GESADUAN)","getDetalle",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());
		}			
		
		return result;
	}
	

	
	private List<PersonasContactoDTO> getPersonasContacto(String codigoProveedor){

		List<PersonasContactoDTO> resultList = new ArrayList<>();		
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT CP.COD_N_CONTACTO, "); 
			sql.append(" CP.COD_N_MECANISMO_CONTACTO_EMAIL, ");
			sql.append(" CP.COD_N_LOCALIZACION_EMAIL, ");
			sql.append(" CP.COD_N_MECANISMO_CONTACTO_SMS, ");
			sql.append(" CP.MCA_ENVIO_SMS, ");
			sql.append(" CP.MCA_ENVIO_EMAIL, ");
			sql.append(" CP.COD_N_LOCALIZACION_SMS ");
			sql.append(" FROM D_PROVEEDOR_R PROV ");
			sql.append(" LEFT JOIN S_CONTACTO_PROVEEDOR_R CP ON CP.COD_N_PROVEEDOR = PROV.COD_N_PROVEEDOR ");
			sql.append(" WHERE PROV.COD_N_PROVEEDOR = ?codigoProveedor ");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("codigoProveedor", codigoProveedor);
			
			List<Object[]> listado = query.getResultList();			
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					PersonasContactoDTO datos = new PersonasContactoDTO();
						
					if(tmp[0] != null) {
						datos.setCodigoContacto(Long.parseLong((String.valueOf(tmp[0]))));
					}else {
						datos.setCodigoContacto(new Long(0));
					}
					
											
					datos.setCodMecanismoContactoEmail((tmp[1] == null) ? null : ((BigDecimal) tmp[1]).longValue());
					datos.setCodLocalizacionEmail((tmp[2] == null) ? null : ((BigDecimal) tmp[2]).longValue());
					datos.setCodMecanismoContactoSMS((tmp[3] == null) ? null :  ((BigDecimal) tmp[3]).longValue());
					datos.setCodLocalizacionSMS((tmp[6] == null) ? null : ((BigDecimal) tmp[6]).longValue());
						
					if(tmp[4] != null) {
						if(tmp[4].equals("S")) {
							datos.setEnvioSMS(true);
						}else {
							datos.setEnvioSMS(false);
						}
					}
	
					if(tmp[5] != null) {
						if(tmp[5].equals("S")) {
							datos.setEnvioEmail(true);
						}else {
							datos.setEnvioEmail(false);
						}
					}
	
						
					resultList.add(datos);
				}
			}
			
		} catch (Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetProveedoresDetalleDAOImpl(GESADUAN)","getPersonasContacto",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());
		}			
		
		return resultList;
	}
	

	@Override
	public String getCodigoProveedorSap(String codigoProveedor) {
		
		try {
			final StringBuilder sql = new StringBuilder();
			
			String select = " select cod_n_proveedor ";
			String from = " from d_proveedor_r ";
			String where = " where cod_n_proveedor = ?codigoProveedor or cod_n_legacy_proveedor = ?codigoProveedor";
			
			sql.append(select).append(from).append(where);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
					
			query.setParameter("codigoProveedor", codigoProveedor);
			
			return (String) query.getSingleResult();
			
		} catch (Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetProveedoresDetalleDAOImpl(GESADUAN)","getCodigoProveedorSap",ex.getClass().getSimpleName(),ex.getMessage());	
			return null;
		}	
		
	}

}
