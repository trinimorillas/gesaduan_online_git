package es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1.GetPuertoAgenciaDAO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputDatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.AgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.DatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.PuertoDTO;
import es.mercadona.gesaduan.jpa.puertos.v1.PuertosJPA;

public class GetPuertoAgenciaDAOImpl extends BaseDAO<PuertosJPA> implements GetPuertoAgenciaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = PuertosJPA.class;		
	}
	
	@Override
	public OutputPuertoAgenciaDTO listarPuertoAgencia(InputDatosPuertoAgenciaDTO datos) {
		
		OutputPuertoAgenciaDTO result = new OutputPuertoAgenciaDTO();
		DatosPuertoAgenciaDTO listaPuertoAgencia = new DatosPuertoAgenciaDTO();
		List<PuertoDTO> listaPuertos = null;
		
		try {
			
			Long codigoPuerto = null;
			if (datos.getDatos().getCodigoPuerto() != null)
				codigoPuerto = datos.getDatos().getCodigoPuerto();
			
			Long codigoAgencia = null;
			if (datos.getDatos().getCodigoAgencia() != null)
				codigoAgencia = datos.getDatos().getCodigoAgencia();
			
			String orden = datos.getDatos().getOrden();
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT ";		
			String campos = "PA.COD_N_PUERTO, PU.TXT_NOMBRE_PUERTO, PA.COD_V_AGENCIA_ADUANA, PR.TXT_RAZON_SOCIAL ";
			String from   = "FROM S_PUERTO_AGENCIA PA " +
						    "INNER JOIN D_PUERTO PU ON (PU.COD_N_PUERTO = PA.COD_N_PUERTO) " +
					        "INNER JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) ";
			String where =  "WHERE 1 = 1 ";
			
			if (codigoPuerto != null)  where += "AND PA.COD_N_PUERTO = ?codigoPuerto ";
			if (codigoAgencia != null) where += "AND PA.COD_V_AGENCIA_ADUANA <> ?codigoAgencia ";
			
			String order = "";
			if (orden.equals("-nombrePuerto"))
				order += "ORDER BY PU.TXT_NOMBRE_PUERTO DESC";
			else if (orden.equals("+nombrePuerto"))
				order += "ORDER BY PU.TXT_NOMBRE_PUERTO ASC";
			else if (order.equals("-nombreAgenciaPreferente"))
				order += "ORDER BY PR.TXT_RAZON_SOCIAL DESC";
			else if (order.equals("+nombreAgenciaPreferente"))
				order += "ORDER BY PR.TXT_RAZON_SOCIAL ASC";
			
			sql.append(select).append(campos).append(from).append(where).append(order);
				
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			if (codigoPuerto != null) query.setParameter("codigoPuerto", codigoPuerto);
			if (codigoAgencia != null) query.setParameter("codigoAgencia", codigoAgencia);
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				listaPuertos = new ArrayList<>();
				for (Object[] tmp : listado) {
					PuertoDTO puerto = new PuertoDTO();
					if (tmp[0] != null) puerto.setCodigoPuerto(Long.parseLong(String.valueOf(tmp[0])));
					if (tmp[1] != null) puerto.setNombrePuerto(String.valueOf(tmp[1]));
					if (tmp[2] != null) puerto.setCodigoAgenciaPreferente(String.valueOf(tmp[2]));
					if (tmp[3] != null) puerto.setNombreAgenciaPreferente(String.valueOf(tmp[3]));
					listaPuertos.add(puerto);
					
					final StringBuilder sqlAgencia = new StringBuilder();
					String selectAgencia = "SELECT ";		
					String camposAgencia = "PA.COD_V_AGENCIA_ADUANA, PR.TXT_RAZON_SOCIAL ";
					String fromAgencia   = "FROM S_PUERTO_AGENCIA PA " +
								    	   "INNER JOIN D_PUERTO PU ON (PU.COD_N_PUERTO = PA.COD_N_PUERTO) " +
							               "INNER JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) ";
					String whereAgencia  = "WHERE PA.COD_N_PUERTO = ?codigoPuertoAgencia AND PA.MCA_AGENCIA_PREFERENTE = 'N'";
					
					sqlAgencia.append(selectAgencia).append(camposAgencia).append(fromAgencia).append(whereAgencia);
					
					final Query queryAgencia = getEntityManager().createNativeQuery(sqlAgencia.toString());
					query.setParameter("codigoPuertoAgencia", String.valueOf(tmp[0]));
					
					@SuppressWarnings("unchecked")
					List<Object[]> listadoAgencia = queryAgencia.getResultList();
					
					if (listadoAgencia != null && !listadoAgencia.isEmpty()) {
						List<AgenciaDTO> listaAgencias = new ArrayList<>();
						for (Object[] tmpAgencia : listadoAgencia) {
							AgenciaDTO agencia = new AgenciaDTO();
							if (tmpAgencia[0] != null) agencia.setCodigoAgencia(Long.parseLong(String.valueOf(tmpAgencia[0])));
							if (tmpAgencia[1] != null) agencia.setNombreAgencia(String.valueOf(tmpAgencia[1]));
							listaAgencias.add(agencia);
						}
						puerto.setAgencia(listaAgencias);
					}					
				}
				
				listaPuertoAgencia.setPuerto(listaPuertos);
			}

			result.setDatos(listaPuertoAgencia);			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetPuertosDAOImpl(GESADUAN)","listarPuertos",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
