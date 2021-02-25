package es.mercadona.gesaduan.dao.planembarques.getplanembarques.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.planembarques.getplanembarques.v1.GetPlanEmbarquesDAO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputPlanEmbarquesDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull.DatosPlanEmbarquesDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull.OutputPlanEmbarquesDTO;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public class GetPlanEmbarquesDAOImpl extends BaseDAO<PlanEmbarquesJPA> implements GetPlanEmbarquesDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = PlanEmbarquesJPA.class;		
	}
	
	@Override
	public OutputPlanEmbarquesDTO listarPlanEmbarques(InputPlanEmbarquesDTO input, BoPage pagination) {
		
		List<DatosPlanEmbarquesDTO> resultList = new ArrayList<>();
		OutputPlanEmbarquesDTO result = new OutputPlanEmbarquesDTO();
		
		try {		

			String fechaEmbarque = null;
			if (input.getFechaEmbarque() != null)
				fechaEmbarque = input.getFechaEmbarque();
	
			Integer codigoBloqueOrigen = null;
			if (input.getCodigoBloqueOrigen() != null)
				codigoBloqueOrigen = input.getCodigoBloqueOrigen();
			
			Integer codigoPuertoEmbarque = null;
			if (input.getCodigoPuertoEmbarque() != null)
				codigoPuertoEmbarque = input.getCodigoPuertoEmbarque();
			
			Integer codigoPuertoDesembarque = null;
			if (input.getCodigoPuertoDesembarque() != null)
				codigoPuertoDesembarque = input.getCodigoPuertoDesembarque();
			
			Integer codigoEstado = null;
			if (input.getCodigoEstado() != null)
				codigoEstado = input.getCodigoEstado();
			
			Integer paginaInicio = null;
			if (pagination.getPage() != null)
				paginaInicio = pagination.getPage().intValue();
			
			Integer paginaTamanyo = null;
			if (pagination.getLimit() != null)
				paginaTamanyo = pagination.getLimit().intValue();
			
			String orden = null;
			if (input.getOrden() != null)
				orden = input.getOrden();
			
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";
			String campos = "PE.COD_N_EMBARQUE, TO_CHAR(PE.FEC_DT_EMBARQUE,'DD/MM/YYYY'), BL.COD_N_BLOQUE_LOGISTICO, BL.TXT_NOMBRE, PUE.COD_N_PUERTO, PUE.TXT_NOMBRE_PUERTO, PUD.COD_N_PUERTO, " + 
					"PUD.TXT_NOMBRE_PUERTO, PR.COD_N_PROVEEDOR, PR.TXT_RAZON_SOCIAL, NVL(PE.NUM_EQUIPOS, 0), EPE.COD_N_ESTADO, EPE.TXT_NOMBRE_ESTADO, PE.COD_V_USUARIO_VALIDACION ";
			String from = "FROM D_PLAN_EMBARQUE PE " +
					"INNER JOIN D_PUERTO PUE ON PE.COD_N_PUERTO_EMBARQUE = PUE.COD_N_PUERTO " +
					"INNER JOIN D_PUERTO PUD ON PE.COD_N_PUERTO_DESEMBARQUE = PUD.COD_N_PUERTO " +
					"INNER JOIN D_BLOQUE_LOGISTICO_R BL ON BL.COD_N_BLOQUE_LOGISTICO = PE.COD_N_BLOQUE_ORIGEN " +
					"INNER JOIN D_ESTADO_PLAN_EMBARQUE EPE ON EPE.COD_N_ESTADO = PE.COD_N_ESTADO " +
					"LEFT JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = PE.COD_N_NAVIERA)";
			String where = " WHERE 1 = 1 ";
			String order = "";
			
			if (fechaEmbarque != null) where += "AND TO_CHAR(PE.FEC_DT_EMBARQUE,'DD/MM/YYYY') = ?fechaEmbarque ";		
			if (codigoPuertoEmbarque != null) where += "AND PE.COD_N_PUERTO_EMBARQUE = ?codigoPuertoEmbarque ";		
			if (codigoPuertoDesembarque != null) where += "AND PE.COD_N_PUERTO_DESEMBARQUE = ?codigoPuertoDesembarque ";		
			if (codigoBloqueOrigen != null) where += "AND PE.COD_N_BLOQUE_ORIGEN = ?codigoBloqueOrigen ";		
			if (codigoEstado != null) where += "AND PE.COD_N_ESTADO = ?codigoEstado ";
				
			String countFin = ")";
			
			if (orden.equals("-fechaEmbarque"))
				order += "ORDER BY PE.FEC_DT_EMBARQUE DESC";
			else if (orden.equals("+fechaEmbarque"))
				order += "ORDER BY PE.FEC_DT_EMBARQUE ASC";
			else if (orden.equals("-nombreBloqueOrigen"))
				order += "ORDER BY BL.TXT_NOMBRE DESC";
			else if (orden.equals("+nombreBloqueOrigen"))
				order += "ORDER BY BL.TXT_NOMBRE ASC";
			else if (orden.equals("-nombrePuertoEmbarque"))
				order += "ORDER BY PUE.TXT_NOMBRE_PUERTO DESC";
			else if (orden.equals("+nombrePuertoEmbarque"))
				order += "ORDER BY PUE.TXT_NOMBRE_PUERTO ASC";
			else if (orden.equals("-nombrePuertoDesembarque"))
				order += "ORDER BY PUD.TXT_NOMBRE_PUERTO DESC";
			else if (orden.equals("+nombrePuertoDesembarque"))
				order += "ORDER BY PUD.TXT_NOMBRE_PUERTO ASC";
			else if (orden.equals("-nombreNaviera"))
				order += "ORDER BY PR.TXT_RAZON_SOCIAL DESC";
			else if (orden.equals("+nombreNaviera"))
				order += "ORDER BY PR.TXT_RAZON_SOCIAL ASC";
			else if (orden.equals("-nombreEstado"))
				order += "ORDER BY EPE.TXT_NOMBRE_ESTADO DESC";
			else if (orden.equals("+nombreEstado"))
				order += "ORDER BY EPE.TXT_NOMBRE_ESTADO ASC";
			else if (orden.equals("-usuarioValidacion"))
				order += "ORDER BY PE.COD_V_USUARIO_VALIDACION DESC";
			else if (orden.equals("+usuarioValidacion"))
				order += "ORDER BY PE.COD_V_USUARIO_VALIDACION ASC";
			
			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			if (fechaEmbarque != null) {
				query.setParameter("fechaEmbarque", fechaEmbarque);
				queryCount.setParameter("fechaEmbarque", fechaEmbarque);
			}
			
			if (codigoPuertoEmbarque != null) {
				query.setParameter("codigoPuertoEmbarque", codigoPuertoEmbarque);
				queryCount.setParameter("codigoPuertoEmbarque", codigoPuertoEmbarque);
			}
			
			if (codigoPuertoDesembarque != null) {
				query.setParameter("codigoPuertoDesembarque", codigoPuertoDesembarque);
				queryCount.setParameter("codigoPuertoDesembarque", codigoPuertoDesembarque);
			}
			
			if (codigoBloqueOrigen != null) {
				query.setParameter("codigoBloqueOrigen", codigoBloqueOrigen);
				queryCount.setParameter("codigoBloqueOrigen", codigoBloqueOrigen);
			}
			
			if (codigoEstado != null) {
				query.setParameter("codigoEstado", codigoEstado);
				queryCount.setParameter("codigoEstado", codigoEstado);
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
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
	
			if (listado != null && !listado.isEmpty()) {
	
				for (Object[] tmp : listado) {
					DatosPlanEmbarquesDTO datos = new DatosPlanEmbarquesDTO();
					
					datos.setCodigoEmbarque(Long.parseLong(String.valueOf(tmp[0])));
					datos.setFechaEmbarque(String.valueOf(tmp[1]));				
					datos.setCodigoBloqueOrigen(Integer.parseInt(String.valueOf(tmp[2])));
					datos.setNombreBloqueOrigen(String.valueOf(tmp[3]));
					datos.setCodigoPuertoEmbarque(Integer.parseInt(String.valueOf(tmp[4])));
					datos.setNombrePuertoEmbarque(String.valueOf(tmp[5]));
					datos.setCodigoPuertoDesembarque(Integer.parseInt(String.valueOf(tmp[6])));
					datos.setNombrePuertoDesembarque(String.valueOf(tmp[7]));
					if (tmp[8] != null) datos.setCodigoNaviera(String.valueOf(tmp[8]));
					if (tmp[9] != null) datos.setNombreNaviera(String.valueOf(tmp[9]));
					datos.setNumeroEquipos(Integer.parseInt(String.valueOf(tmp[10])));
					datos.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[11])));
					datos.setNombreEstado(String.valueOf(tmp[12]));
					if (tmp[13] != null) datos.setCodigoUsuarioValidacion(String.valueOf(tmp[13]));
					
					resultList.add(datos);
				}
			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());
	

			result.setMetadatos(mapaMetaData);
			result.setDatos(resultList);

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetPlanEmbarquesDAOImpl(GESADUAN)","listarPlanEmbarques",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
