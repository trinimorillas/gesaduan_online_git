package es.mercadona.gesaduan.dao.equipotransporte.getcontenedores.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.equipotransporte.getcontenedores.v1.GetContenedoresDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.InputDatosGetContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull.DatosGetCargasContenedoresDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull.OutputGetContenedoresDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.ContenedorDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public class GetContenedoresDAOImpl extends BaseDAO<EquipoTransporteJPA> implements GetContenedoresDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = EquipoTransporteJPA.class;		
	}
	
	@Override
	public OutputGetContenedoresDTO listarContenedores(InputDatosGetContenedorDTO datos) {
		
		OutputGetContenedoresDTO result = new OutputGetContenedoresDTO();
		DatosGetCargasContenedoresDTO listaCargas = new DatosGetCargasContenedoresDTO();
		
		try {		
			Long codigoEquipo = datos.getDatos().getCodigoEquipo();
			
			String codigoCentroOrigen = null;
			if (datos.getDatos().getCodigoCentroOrigen() != null)
				codigoCentroOrigen = datos.getDatos().getCodigoCentroOrigen();
			
			String codigoCentroDestino = null;
			if (datos.getDatos().getCodigoCentroDestino() != null)
				codigoCentroDestino = datos.getDatos().getCodigoCentroDestino();
			
			String mcaFacturado = null;
			if (datos.getDatos().getMcaFacturado() != null)
				mcaFacturado = datos.getDatos().getMcaFacturado();			
			
			String mcaCargasDivididas = null;
			if (datos.getDatos().getMcaCargasDivididas() != null)
				mcaCargasDivididas = datos.getDatos().getMcaCargasDivididas();
			
			Long codigoSuministro = null;
			if (datos.getDatos().getCodigoSuministro() != null)
				codigoSuministro = datos.getDatos().getCodigoSuministro();
			
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
	
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "  EC.COD_V_CARGA, " + 
			                "  CA.COD_N_TIPO_SUMINISTRO, " + 
			                "  TS.TXT_NOMBRE_TIPO_SUMINISTRO, " + 
			                "  EC.COD_V_ALMACEN_ORIGEN, " +
				      		"  CA.COD_V_CENTRO_DESTINO, " + 
			                "  EC.NUM_HUECO_OCUPADO, " + 
				      		"  EC.NUM_PESO_OCUPADO, " + 
			                "  EC.NUM_DIVISION, " + 
				      		"  CA.MCA_PEDIDOS_SIN_VALIDAR ";			
			String from = 	"FROM S_EQUIPO_CARGA EC " +
							"INNER JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) " +
							"LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) ";
			String where = 	"WHERE EC.COD_N_EQUIPO = ?codigoEquipo ";
			
			if (codigoCentroOrigen != null) where += " AND EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen ";
			if (codigoCentroDestino  != null) where += " AND CA.COD_V_CENTRO_DESTINO = ?codigoCentroDestino ";
			if (mcaCargasDivididas != null) {
				if ("S".equals(mcaCargasDivididas)) where += " AND EC.NUM_DIVISION > 0 ";
				else where += " AND EC.NUM_DIVISION = 0 ";
			}
			if (codigoSuministro != null) where += " AND CA.COD_N_TIPO_SUMINISTRO = ?codigoSuministro ";
			
			String countFin = ")";
			String order = "ORDER BY EC.COD_V_CARGA ASC";
			
			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("codigoEquipo", codigoEquipo);

			if (codigoCentroOrigen != null) query.setParameter("codigoCentroOrigen", codigoCentroOrigen);			
			if (codigoCentroDestino != null) query.setParameter("codigoCentroDestino", codigoCentroDestino);			
			if (mcaCargasDivididas != null) query.setParameter("mcaCargasDivididas", mcaCargasDivididas);			
			if (codigoSuministro != null) query.setParameter("codigoSuministro", codigoSuministro);
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();		
			List<CargaDTO> cargas = null;
			
			if (listado != null && !listado.isEmpty()) {
				cargas = new ArrayList<>();
				
				for (Object[] tmp : listado) {
					CargaDTO carga = new CargaDTO();
					if (tmp[0] != null) carga.setCodigoCarga(String.valueOf(tmp[0]));
					if (tmp[1] != null) carga.setCodigoSuministro(Integer.parseInt(String.valueOf(tmp[1])));
					if (tmp[2] != null) carga.setNombreSuministro(String.valueOf(tmp[2]));
					if (tmp[3] != null) carga.setCodigoAlmacenOrigen(String.valueOf(tmp[3]));
					if (tmp[4] != null) carga.setCodigoCentroDestino(String.valueOf(tmp[4]));
					if (tmp[5] != null) carga.setNumeroHuecoOcupado(Double.parseDouble(String.valueOf(tmp[5])));
					if (tmp[6] != null) carga.setNumeroPesoOcupado(Double.parseDouble(String.valueOf(tmp[6])));
					if (tmp[7] != null) carga.setNumeroDivision(Integer.parseInt(String.valueOf(tmp[7])));
					if (tmp[8] != null) carga.setPedidosSinValidar(String.valueOf(tmp[8]));
					carga.setContenedor(getContenedor(carga, codigoEquipo, mcaFacturado));					
					cargas.add(carga);
				}
				
				listaCargas.setCarga(cargas);
					
			}

			Map<String, Double> mapaMetaData = new HashMap<String, Double>();
			
			Double numeroTotalContenedores = 0D;
			Double numeroTotalHuecos = 0D;
			Double numeroTotalContenedoresFacturados = 0D;
			
			numeroTotalContenedoresFacturados = totalContenedoresFacturados(codigoEquipo);
			numeroTotalContenedores = numeroTotalContenedoresFacturados + totalContenedoresNoFacturados(codigoEquipo);
			numeroTotalHuecos = totalHuecos(codigoEquipo);
			
			mapaMetaData.put("totalContenedores", numeroTotalContenedores);
			mapaMetaData.put("totalHuecos", numeroTotalHuecos);
			mapaMetaData.put("totalContenedoresFacturados", numeroTotalContenedoresFacturados);

			result.setDatos(listaCargas);
			result.setMetadatos(mapaMetaData);
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetContenedoresDAOImpl(GESADUAN)","listarContenedores",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		
		return result;
	}
	
	private List<ContenedorDTO> getContenedor(CargaDTO carga, Long codigoEquipo, String mcaFacturado) {
		List<ContenedorDTO> listaContenedor = null;		
		String codigoCarga = carga.getCodigoCarga();
		String codigoAlmacenOrigen = carga.getCodigoAlmacenOrigen();		

		try {
			String select = "";
			 
			String parte1 = "SELECT " + 
						    "  CE.NUM_CONTENEDOR, " + 
						    "  'S' MCA_FACTURACION, " +
						    "  TO_CHAR(CE.FEC_DT_EXPEDICION,'YYYYMMDDHH24MI') FECHA_EXPEDICION " +						    
							"FROM O_CONTENEDOR_EXPEDIDO CE " +
							"WHERE " + 
							"  CE.COD_N_EQUIPO = ?codigoEquipo AND " + 
							"  CE.COD_V_CARGA = ?codigoCarga AND " + 
							"  CE.COD_V_ALMACEN = ?codigoAlmacenOrigen ";			
			
			String parte2 = "SELECT " + 
						    "  DISTINCT CE.NUM_CONTENEDOR, " +
				           	"  'N' MCA_FACTURACION, " +
				           	"  TO_CHAR(CE.FEC_DT_EXPEDICION,'YYYYMMDDHH24MI') FECHA_EXPEDICION " +						    
				           	"FROM O_CONTENEDOR_EXPEDIDO CE " +
				           	"INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND EC.COD_V_CARGA = CE.COD_V_CARGA) " +
				           	"WHERE " + 
				           	"  EC.COD_N_EQUIPO = ?codigoEquipo AND " + 
				           	"  EC.COD_V_CARGA = ?codigoCarga AND " + 
				           	"  EC.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen AND " + 
				           	"  CE.COD_N_EQUIPO IS NULL ";
			
			if ("S".equals(mcaFacturado)) select = parte1;
			else if ("N".equals(mcaFacturado)) select = parte2;
			else select = parte1 + " UNION " + parte2;
			
			select = "SELECT NUM_CONTENEDOR,MCA_FACTURACION,FECHA_EXPEDICION FROM(" + select + ") ORDER BY NUM_CONTENEDOR";
			
			final Query query = getEntityManager().createNativeQuery(select);			
			query.setParameter("codigoEquipo", codigoEquipo);
			query.setParameter("codigoAlmacenOrigen", codigoAlmacenOrigen);
			query.setParameter("codigoCarga", codigoCarga);			
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				listaContenedor = new ArrayList<>(); 
				for (Object[] tmp : listado) {		
					ContenedorDTO contenedor = new ContenedorDTO();
					if (tmp[0] != null) contenedor.setNumContenedor(Long.parseLong(String.valueOf(tmp[0])));
					if (tmp[1] != null) contenedor.setMcaFacturado(String.valueOf(tmp[1]));
					if (tmp[2] != null) contenedor.setFechaExpedicion(String.valueOf(tmp[2]));					
					listaContenedor.add(contenedor);
				}
			}		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetContenedoresDAOImpl(GESADUAN)","getContenedores",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return listaContenedor;
	}
	
	@Override
	public Double totalContenedoresFacturados(Long codigoEquipo) {		
		try {		
			String select = "SELECT COUNT(*) FROM O_CONTENEDOR_EXPEDIDO CE " +
							"INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND EC.COD_V_CARGA = CE.COD_V_CARGA AND EC.COD_N_EQUIPO = CE.COD_N_EQUIPO) " +
							"WHERE CE.COD_N_EQUIPO = ?codigoEquipo";

			final Query query = getEntityManager().createNativeQuery(select);
			query.setParameter("codigoEquipo", codigoEquipo);		
			Double restultadoQuery = Double.parseDouble(query.getSingleResult().toString());
			
			return restultadoQuery;		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetContenedoresDAOImpl(GESADUAN)","totalContenedores",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
	
	@Override
	public Double totalHuecos(Long codigoEquipo) {		
		try {		
			String select = "SELECT SUM(NUM_HUECO_OCUPADO) FROM S_EQUIPO_CARGA WHERE COD_N_EQUIPO = ?codigoEquipo";

			final Query query = getEntityManager().createNativeQuery(select);
			query.setParameter("codigoEquipo", codigoEquipo);		
			Double restultadoQuery = Double.parseDouble(query.getSingleResult().toString());
			
			return restultadoQuery;		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetContenedoresDAOImpl(GESADUAN)","totalHuecos",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
	
	@Override
	public Double totalContenedoresNoFacturados(Long codigoEquipo) {		
		try {		
			String select = "SELECT COUNT(*) FROM (" +
							"SELECT DISTINCT CE.NUM_CONTENEDOR " +
                            "FROM O_CONTENEDOR_EXPEDIDO CE " + 
                            "INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN AND EC.COD_V_CARGA = CE.COD_V_CARGA) " +
                            "INNER JOIN D_CARGA CA ON (CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN AND CA.COD_V_CARGA = EC.COD_V_CARGA) " +
                            "WHERE EC.COD_N_EQUIPO = ?codigoEquipo " +
                            "AND CE.COD_N_EQUIPO IS NULL " +
                            "AND CE.COD_V_CARGA IS NULL)";

			final Query query = getEntityManager().createNativeQuery(select);
			query.setParameter("codigoEquipo", codigoEquipo);		
			Double restultadoQuery = Double.parseDouble(query.getSingleResult().toString());
			
			return restultadoQuery;		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetContenedoresDAOImpl(GESADUAN)","totalHuecos",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}

}