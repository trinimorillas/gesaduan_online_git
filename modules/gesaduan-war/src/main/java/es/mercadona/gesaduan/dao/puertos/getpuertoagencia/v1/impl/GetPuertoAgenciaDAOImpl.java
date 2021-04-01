package es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1.GetPuertoAgenciaDAO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.AgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.DatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.PuertoDTO;
import es.mercadona.gesaduan.jpa.puertos.v1.PuertosJPA;
import es.mercadona.gesaduan.common.Constantes;

public class GetPuertoAgenciaDAOImpl extends BaseDAO<PuertosJPA> implements GetPuertoAgenciaDAO {

	@Inject
	private org.slf4j.Logger logger;

	@Override
	public void setEntityClass() {
		this.entityClass = PuertosJPA.class;
	}
	
	private final String nombreClase = "GetPuertosDAOImpl(GESADUAN)";

	@Override
	public OutputPuertoAgenciaDTO listarPuertos(InputPuertoAgenciaDTO datos) {
		OutputPuertoAgenciaDTO result = new OutputPuertoAgenciaDTO();
		DatosPuertoAgenciaDTO listaPuertoAgencia = new DatosPuertoAgenciaDTO();
		List<PuertoDTO> listaPuertos = null;

		try {
			String orden = datos.getOrden();
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("PU.COD_N_PUERTO, PU.TXT_NOMBRE_PUERTO, PR.COD_N_PROVEEDOR, PR.TXT_RAZON_SOCIAL ");
			sql.append("FROM D_PUERTO PU ");
			sql.append("LEFT JOIN S_PUERTO_AGENCIA PA ON (PA.COD_N_PUERTO = PU.COD_N_PUERTO AND PA.MCA_AGENCIA_PREFERENTE = 'S') ");
			sql.append("LEFT JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) ");
			sql.append("WHERE NOT EXISTS (");
			sql.append("SELECT 1 ");
			sql.append("FROM S_PUERTO_AGENCIA TMP ");
			sql.append("WHERE TMP.COD_N_PUERTO = PU.COD_N_PUERTO ");
			sql.append("AND TMP.COD_V_AGENCIA_ADUANA = ?codigoAgencia) ");

			String order = getOrden(orden);
			sql.append(order);


			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoAgencia", datos.getCodigoAgencia());

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
				}
				listaPuertoAgencia.setPuerto(listaPuertos);
			}
			result.setDatos(listaPuertoAgencia);
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, "listarPuertos", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}

	@Override
	public OutputPuertoAgenciaDTO listarAgencias(InputPuertoAgenciaDTO datos) {
		OutputPuertoAgenciaDTO result = new OutputPuertoAgenciaDTO();
		DatosPuertoAgenciaDTO listaPuertoAgencia = new DatosPuertoAgenciaDTO();
		List<PuertoDTO> listaPuertos = null;

		try {
			Long codigoPuerto = null;
			if (datos.getCodigoPuerto() != null)
				codigoPuerto = datos.getCodigoPuerto();

			String orden = datos.getOrden();
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("PA.COD_N_PUERTO, PU.TXT_NOMBRE_PUERTO, PA.COD_V_AGENCIA_ADUANA, PR.TXT_RAZON_SOCIAL ");
			sql.append("FROM S_PUERTO_AGENCIA PA ");
			sql.append("INNER JOIN D_PUERTO PU ON (PU.COD_N_PUERTO = PA.COD_N_PUERTO) ");
			sql.append("INNER JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) ");
			sql.append("WHERE PA.MCA_AGENCIA_PREFERENTE = 'S' AND PA.COD_N_PUERTO = ?codigoPuerto ");

			String order = getOrden(orden);
			sql.append(order);


			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoPuerto", codigoPuerto);

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
					sqlAgencia.append("SELECT ");
					sqlAgencia.append("PA.COD_V_AGENCIA_ADUANA, PR.TXT_RAZON_SOCIAL ");
					sqlAgencia.append("FROM S_PUERTO_AGENCIA PA ");
					sqlAgencia.append("INNER JOIN D_PUERTO PU ON (PU.COD_N_PUERTO = PA.COD_N_PUERTO) ");
					sqlAgencia.append("INNER JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) ");
					sqlAgencia.append("WHERE PA.COD_N_PUERTO = ?codigoPuertoAgencia AND PA.MCA_AGENCIA_PREFERENTE = 'N'");

					final Query queryAgencia = getEntityManager().createNativeQuery(sqlAgencia.toString());
					queryAgencia.setParameter("codigoPuertoAgencia", Long.parseLong(String.valueOf(tmp[0])));

					@SuppressWarnings("unchecked")
					List<Object[]> listadoAgencia = queryAgencia.getResultList();

					if (listadoAgencia != null && !listadoAgencia.isEmpty()) {
						List<AgenciaDTO> listaAgencias = new ArrayList<>();
						for (Object[] tmpAgencia : listadoAgencia) {
							AgenciaDTO agencia = new AgenciaDTO();
							if (tmpAgencia[0] != null) agencia.setCodigoAgencia(String.valueOf(tmpAgencia[0]));
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, "listarAgencias", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}
	
	@Override
	public OutputPuertoAgenciaDTO listarPuertosAgencias(InputPuertoAgenciaDTO datos) {
		OutputPuertoAgenciaDTO result = new OutputPuertoAgenciaDTO();
		DatosPuertoAgenciaDTO listaPuertoAgencia = new DatosPuertoAgenciaDTO();
		List<PuertoDTO> listaPuertos = null;

		try {
			Long codigoPuerto = null;
			if (datos.getCodigoPuerto() != null)
				codigoPuerto = datos.getCodigoPuerto();

			String orden = datos.getOrden();
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("PU.COD_N_PUERTO, PU.TXT_NOMBRE_PUERTO, PA.COD_V_AGENCIA_ADUANA, PR.TXT_RAZON_SOCIAL ");
			sql.append("FROM D_PUERTO PU ");
			sql.append("LEFT JOIN S_PUERTO_AGENCIA PA ON (PA.COD_N_PUERTO = PU.COD_N_PUERTO AND PA.MCA_AGENCIA_PREFERENTE = 'S') ");
			sql.append("LEFT JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) ");

			String order = getOrden(orden);
			sql.append(order);


			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoPuerto", codigoPuerto);

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
					sqlAgencia.append("SELECT ");
					sqlAgencia.append("PA.COD_V_AGENCIA_ADUANA, PR.TXT_RAZON_SOCIAL ");
					sqlAgencia.append("FROM S_PUERTO_AGENCIA PA ");
					sqlAgencia.append("INNER JOIN D_PUERTO PU ON (PU.COD_N_PUERTO = PA.COD_N_PUERTO) ");
					sqlAgencia.append("INNER JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) ");
					sqlAgencia.append("WHERE PA.COD_N_PUERTO = ?codigoPuertoAgencia AND PA.MCA_AGENCIA_PREFERENTE = 'N'");

					final Query queryAgencia = getEntityManager().createNativeQuery(sqlAgencia.toString());
					queryAgencia.setParameter("codigoPuertoAgencia", Long.parseLong(String.valueOf(tmp[0])));

					@SuppressWarnings("unchecked")
					List<Object[]> listadoAgencia = queryAgencia.getResultList();

					if (listadoAgencia != null && !listadoAgencia.isEmpty()) {
						List<AgenciaDTO> listaAgencias = new ArrayList<>();
						for (Object[] tmpAgencia : listadoAgencia) {
							AgenciaDTO agencia = new AgenciaDTO();
							if (tmpAgencia[0] != null) agencia.setCodigoAgencia(String.valueOf(tmpAgencia[0]));
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, "listarPuertosAgencias", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}
	
	private String getOrden(String orden) {
		String order = "";
		if (orden.equals("-nombrePuerto")) order += "ORDER BY PU.TXT_NOMBRE_PUERTO DESC";
		else if (orden.equals("+nombrePuerto")) order += "ORDER BY PU.TXT_NOMBRE_PUERTO ASC";
		else if (orden.equals("-nombreAgenciaPreferente")) order += "ORDER BY PR.TXT_RAZON_SOCIAL DESC";
		else if (orden.equals("+nombreAgenciaPreferente")) order += "ORDER BY PR.TXT_RAZON_SOCIAL ASC";
		
		return order;
	}

}