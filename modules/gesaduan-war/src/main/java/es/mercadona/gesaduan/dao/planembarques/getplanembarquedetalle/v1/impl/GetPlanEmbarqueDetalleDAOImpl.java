package es.mercadona.gesaduan.dao.planembarques.getplanembarquedetalle.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.planembarques.getplanembarquedetalle.v1.GetPlanEmbarqueDetalleDAO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.InputPlanEmbarqueDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.CategoriaDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.DatosPlanEmbarqueDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.DosierDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.EquipoDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.EquipoSimpleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.OutputPlanEmbarqueDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.PedidoDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.SuministroDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.TipoCargaDTO;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public class GetPlanEmbarqueDetalleDAOImpl extends BaseDAO<PlanEmbarquesJPA> implements GetPlanEmbarqueDetalleDAO {

	@Inject
	private org.slf4j.Logger logger;

	@Override
	public void setEntityClass() {
		this.entityClass = PlanEmbarquesJPA.class;
	}

	@Override
	public OutputPlanEmbarqueDetalleDTO consultarPlanEmbarque(InputDatosDetalleDTO datos) {

		OutputPlanEmbarqueDetalleDTO result = new OutputPlanEmbarqueDetalleDTO();
		DatosPlanEmbarqueDetalleDTO planEmbarque = null;
		InputPlanEmbarqueDetalleDTO input = datos.getDatos();

		try {

			Long codigoPlanEmbarque = input.getCodigoEmbarque();
			final StringBuilder sql = new StringBuilder();

			String select = "SELECT ";
			String campos = "PE.COD_N_EMBARQUE, " + "TO_CHAR(PE.FEC_DT_EMBARQUE,'DD/MM/YYYY'), "
					+ "BL.COD_N_BLOQUE_LOGISTICO, " + "BL.TXT_NOMBRE, PUE.COD_N_PUERTO, " + "PUE.TXT_NOMBRE_PUERTO, "
					+ "PUD.COD_N_PUERTO, " + "PUD.TXT_NOMBRE_PUERTO, " + "PN.COD_N_PROVEEDOR, "
					+ "PN.TXT_RAZON_SOCIAL, " + "NVL(PE.NUM_EQUIPOS, 0), " + "EPE.COD_N_ESTADO,"
					+ "EPE.TXT_NOMBRE_ESTADO, " + "PE.COD_V_USUARIO_VALIDACION ";
			String from = "FROM D_PLAN_EMBARQUE PE "
					+ "INNER JOIN D_BLOQUE_LOGISTICO_R BL ON (BL.COD_N_BLOQUE_LOGISTICO = PE.COD_N_BLOQUE_ORIGEN) "
					+ "INNER JOIN D_PUERTO PUE ON (PUE.COD_N_PUERTO = PE.COD_N_PUERTO_EMBARQUE) "
					+ "INNER JOIN D_PUERTO PUD ON (PUD.COD_N_PUERTO = PE.COD_N_PUERTO_DESEMBARQUE) "
					+ "INNER JOIN D_ESTADO_PLAN_EMBARQUE EPE ON (EPE.COD_N_ESTADO = PE.COD_N_ESTADO) "
					+ "LEFT JOIN D_PROVEEDOR_R PN ON (PN.COD_N_PROVEEDOR = PE.COD_N_NAVIERA)";
			String where = "WHERE PE.COD_N_EMBARQUE = ?codigoPlanEmbarque ";

			sql.append(select).append(campos).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoPlanEmbarque", codigoPlanEmbarque);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			List<EquipoDTO> listaEquipo = null;
			listaEquipo = new ArrayList<>();

			List<DosierDTO> listaDosier = null;
			listaDosier = new ArrayList<>();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					planEmbarque = new DatosPlanEmbarqueDetalleDTO();

					planEmbarque.setCodigoEmbarque(Long.parseLong(String.valueOf(tmp[0])));
					planEmbarque.setFechaEmbarque(String.valueOf(tmp[1]));
					planEmbarque.setCodigoBloqueOrigen(Integer.parseInt(String.valueOf(tmp[2])));
					planEmbarque.setNombreBloqueOrigen(String.valueOf(tmp[3]));
					planEmbarque.setCodigoPuertoEmbarque(Integer.parseInt(String.valueOf(tmp[4])));
					planEmbarque.setNombrePuertoEmbarque(String.valueOf(tmp[5]));
					planEmbarque.setCodigoPuertoDesembarque(Integer.parseInt(String.valueOf(tmp[6])));
					planEmbarque.setNombrePuertoDesembarque(String.valueOf(tmp[7]));
					if (tmp[8] != null)
						planEmbarque.setCodigoNaviera(String.valueOf(tmp[8]));
					if (tmp[9] != null)
						planEmbarque.setNombreNaviera(String.valueOf(tmp[9]));
					planEmbarque.setNumeroEquipos(Integer.parseInt(String.valueOf(tmp[10])));
					planEmbarque.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[11])));
					planEmbarque.setNombreEstado(String.valueOf(tmp[12]));
					if (tmp[13] != null)
						planEmbarque.setCodigoUsuarioValidacion(String.valueOf(tmp[13]));

					listaEquipo = consultarEquiposPlanEmbarque(datos);

					if ("S".equals(input.getMcaIncluyeDosieres()) && !listaEquipo.isEmpty()) {
						listaDosier = consultarDosieresPlanEmbarque(datos);
					}

					planEmbarque.setEquipo(listaEquipo);
					planEmbarque.setDosier(listaDosier);
				}
			}

			Map<String, String> mapaMetaData = new HashMap<>();
			mapaMetaData.put("totalItemsCount", (listaEquipo != null) ? String.valueOf(listaEquipo.size()) : "0");

			result.setMetadatos(mapaMetaData);
			result.setDatos(planEmbarque);

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarPlanEmbarque", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}

	private List<EquipoDTO> consultarEquiposPlanEmbarque(InputDatosDetalleDTO datos) {

		InputPlanEmbarqueDetalleDTO input = datos.getDatos();
		List<EquipoDTO> listaEquipo = null;

		try {

			Long codigoPlanEmbarque = input.getCodigoEmbarque();

			String usuarioCreacion = null;
			if (input.getUsuarioCreacion() != null)
				usuarioCreacion = input.getUsuarioCreacion();

			String mcaOcultaLlenos = null;
			if (input.getMcaOcultaLlenos() != null)
				mcaOcultaLlenos = input.getMcaOcultaLlenos();

			String mcaIncluyeCargas = "N";
			if (input.getMcaIncluyeCargas() != null)
				mcaIncluyeCargas = input.getMcaIncluyeCargas();

			String orden = null;
			if (input.getOrden() != null)
				orden = input.getOrden();

			List<SuministroDTO> suministros = null;
			if (input.getSuministro() != null)
				suministros = input.getSuministro();

			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();

			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";
			String campos = "DISTINCT ET.COD_N_EQUIPO, "
					+ "ET.TXT_MATRICULA, "
					+ "PT.COD_N_PROVEEDOR, "
					+ "PT.TXT_RAZON_SOCIAL, "
					+ "T.COD_N_TEMPERATURA, "
					+ "T.TXT_TEMPERATURA, "
					+ "ET.NUM_CAPACIDAD, "
					+ "ET.NUM_OCUPACION, "
					+ "TO_CHAR(ET.FEC_DT_CARGA,'DD/MM/YYYY HH24:MI:SS'), "
					+ "ET.COD_N_ESTADO, "
					+ "EE.TXT_NOMBRE_ESTADO, "
					+ "ET.COD_V_USUARIO_CREACION, "
					+ "(ET.NUM_OCUPACION / ET.NUM_CAPACIDAD) AS PORCENTAJE, "
					+ "ET.COD_N_ESTADO_DOCUMENTACION, "
					+ "EDE.TXT_NOMBRE_ESTADO ";
			String from = "FROM D_EQUIPO_TRANSPORTE ET "
					+ "LEFT JOIN D_ESTADO_EQUIPO EE ON (EE.COD_N_ESTADO = ET.COD_N_ESTADO) "
					+ "LEFT JOIN D_PROVEEDOR_R PT ON (PT.COD_N_PROVEEDOR = ET.COD_N_PROVEEDOR AND PT.MCA_TRANSPORTISTA = 'S') "
					+ "LEFT JOIN D_TEMPERATURA T ON (T.COD_N_TEMPERATURA = ET.COD_N_TEMPERATURA) "
					+ "LEFT JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) "
					+ "LEFT JOIN D_CARGA CA2 ON (CA2.COD_V_CARGA = EC.COD_V_CARGA AND CA2.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) "
					+ "LEFT JOIN D_ESTADO_DOCUMENTACION_EQUIPO EDE ON (EDE.COD_N_ESTADO = ET.COD_N_ESTADO_DOCUMENTACION) "
					+ "LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA2.COD_N_TIPO_SUMINISTRO) ";
			String where = "WHERE ET.COD_N_EMBARQUE = ?codigoPlanEmbarque ";

			if (usuarioCreacion != null)
				where += "AND UPPER(ET.COD_V_USUARIO_CREACION) = UPPER(?usuarioCreacion) ";
			if ("S".equals(mcaOcultaLlenos))
				where += "AND ET.NUM_CAPACIDAD > ET.NUM_OCUPACION ";

			if (suministros != null) {
				where += "AND CA.COD_N_TIPO_SUMINISTRO IN (";
				for (int i = 0; i < suministros.size(); i++) {
					if (suministros.size() - 1 == i)
						where += "" + suministros.get(i).getCodigoSuministro() + "";
					else
						where += "" + suministros.get(i).getCodigoSuministro() + ", ";
				}
				where += ") ";
			}

			String countFin = ")";
			String order = "";

			if (orden.equals("-matricula"))
				order += "ORDER BY ET.TXT_MATRICULA DESC";
			else if (orden.equals("+matricula"))
				order += "ORDER BY ET.TXT_MATRICULA ASC";
			else if (orden.equals("-nombreTransportista"))
				order += "ORDER BY PT.TXT_RAZON_SOCIAL DESC";
			else if (orden.equals("+nombreTransportista"))
				order += "ORDER BY PT.TXT_RAZON_SOCIAL ASC";
			else if (orden.equals("-numeroOcupacion"))
				order += "ORDER BY ET.NUM_OCUPACION DESC";
			else if (orden.equals("+numeroOcupacion"))
				order += "ORDER BY ET.NUM_OCUPACION ASC";
			else if (orden.equals("-valorTemperatura"))
				order += "ORDER BY T.TXT_TEMPERATURA DESC";
			else if (orden.equals("+valorTemperatura"))
				order += "ORDER BY T.TXT_TEMPERATURA ASC";
			else if (orden.equals("-fechaCarga"))
				order += "ORDER BY ET.FEC_DT_CARGA DESC";
			else if (orden.equals("+fechaCarga"))
				order += "ORDER BY ET.FEC_DT_CARGA ASC";
			else if (orden.equals("-codigoUsuarioCreacion"))
				order += "ORDER BY ET.COD_V_USUARIO_CREACION DESC";
			else if (orden.equals("+codigoUsuarioCreacion"))
				order += "ORDER BY ET.COD_V_USUARIO_CREACION ASC";
			else if (orden.equals("-nombreEstado"))
				order += "ORDER BY EE.TXT_NOMBRE_ESTADO DESC";
			else if (orden.equals("+nombreEstado"))
				order += "ORDER BY EE.TXT_NOMBRE_ESTADO ASC";
			else if (orden.equals("-ocupacion"))
				order += "ORDER BY PORCENTAJE DESC, ET.NUM_CAPACIDAD ASC";
			else if (orden.equals("+ocupacion"))
				order += "ORDER BY PORCENTAJE ASC, ET.NUM_CAPACIDAD ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());

			query.setParameter("codigoPlanEmbarque", codigoPlanEmbarque);
			queryCount.setParameter("codigoPlanEmbarque", codigoPlanEmbarque);

			if (usuarioCreacion != null) {
				query.setParameter("usuarioCreacion", usuarioCreacion);
				queryCount.setParameter("usuarioCreacion", usuarioCreacion);
			}

			this.logger.debug("({}-{}) QUERY - {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUANP)",
					"consultarEquiposPlanEmbarque", query.toString());

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			List<SuministroDTO> listaSuministro = null;
			List<CategoriaDTO> listaCategoria = null;
			List<CargaDTO> listaCarga = null;
			listaEquipo = new ArrayList<>();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					EquipoDTO equipo = null;

					equipo = new EquipoDTO();
					equipo.setCodigoEquipo(Long.parseLong(String.valueOf(tmp[0])));
					equipo.setMatricula(String.valueOf(tmp[1]));
					if (tmp[2] != null)
						equipo.setCodigoTransportista(String.valueOf(tmp[2]));
					if (tmp[3] != null)
						equipo.setNombreTransportista(String.valueOf(tmp[3]));
					equipo.setCodigoTemperatura(Integer.parseInt(String.valueOf(tmp[4])));
					equipo.setValorTemperatura(String.valueOf(tmp[5]));
					equipo.setNumeroCapacidad(Double.parseDouble(String.valueOf(tmp[6])));
					if (tmp[7] != null)
						equipo.setNumeroOcupacion(Double.parseDouble(String.valueOf(tmp[7])));
					if (tmp[8] != null)
						equipo.setFechaCarga(String.valueOf(tmp[8]));
					equipo.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[9])));
					equipo.setNombreEstado(String.valueOf(tmp[10]));
					if (tmp[11] != null)
						equipo.setCodigoUsuarioCreacion(String.valueOf(tmp[11]));
					equipo.setCodigoEstadoDocumentacion(Integer.parseInt(String.valueOf(tmp[13])));
					equipo.setNombreEstadoDocumentacion(String.valueOf(tmp[14]));

					listaSuministro = consultarSuministroEquipos(equipo.getCodigoEquipo());
					listaCategoria = consultarCategoriaEquipos(equipo.getCodigoEquipo());
					equipo.setSuministro(listaSuministro);
					equipo.setCategoria(listaCategoria);
					if ("S".equals(mcaIncluyeCargas)) {
						listaCarga = consultarCargaEquipos(datos, equipo.getCodigoEquipo());
					} else {
						listaCarga = new ArrayList<>();
					}
					equipo.setCarga(listaCarga);

					listaEquipo.add(equipo);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarEquiposPlanEmbarque", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaEquipo;
	}

	private List<SuministroDTO> consultarSuministroEquipos(Long codigoEquipo) {

		List<SuministroDTO> listaSuministro = null;

		try {

			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();

			String select = "SELECT ";
			String campos = "DISTINCT TS.COD_N_TIPO_SUMINISTRO, TS.TXT_NOMBRE_TIPO_SUMINISTRO ";
			String from = "FROM S_EQUIPO_CARGA EC "
					+ "LEFT JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) "
					+ "INNER JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) ";
			String where = "WHERE EC.COD_N_EQUIPO = ?codigoEquipo ";
			String order = "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());

			query.setParameter("codigoEquipo", codigoEquipo);
			queryCount.setParameter("codigoEquipo", codigoEquipo);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			listaSuministro = new ArrayList<>();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					SuministroDTO suministro = new SuministroDTO();
					suministro.setCodigoSuministro(Long.parseLong(String.valueOf(tmp[0])));
					suministro.setNombreSuministro(String.valueOf(tmp[1]));

					listaSuministro.add(suministro);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarSuministroEquipos", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaSuministro;
	}

	private List<CategoriaDTO> consultarCategoriaEquipos(Long codigoEquipo) {

		List<CategoriaDTO> listaCategoria = null;

		try {

			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();

			String select = "SELECT ";
			String campos = " DISTINCT COD_N_CATEGORIA,TXT_NOMBRE_CATEGORIA FROM ";
			String from = "SELECT " + "NVL(CC.COD_N_CATEGORIA,-1) COD_N_CATEGORIA , "
					+ "NVL(CC.TXT_NOMBRE_CATEGORIA,'Sin categoria') TXT_NOMBRE_CATEGORIA " + "FROM S_EQUIPO_CARGA EC "
					+ "LEFT JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) "
					+ "LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA = CA.COD_N_CATEGORIA) ";
			String where = "WHERE EC.COD_N_EQUIPO = ?codigoEquipo ";
			String order = "ORDER BY CC.TXT_NOMBRE_CATEGORIA ASC";

			sql.append(select).append(campos).append("(").append(from).append(where).append(order).append(")");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());

			query.setParameter("codigoEquipo", codigoEquipo);
			queryCount.setParameter("codigoEquipo", codigoEquipo);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			listaCategoria = new ArrayList<>();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					CategoriaDTO categoria = new CategoriaDTO();
					categoria.setCodigoCategoria(Long.parseLong(String.valueOf(tmp[0])));
					categoria.setNombreCategoria(String.valueOf(tmp[1]));

					listaCategoria.add(categoria);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarCategoriaEquipos", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaCategoria;
	}

	private List<CargaDTO> consultarCargaEquipos(InputDatosDetalleDTO datos, Long codigoEquipo) {

		List<CargaDTO> listaCarga = new ArrayList<>();
		InputPlanEmbarqueDetalleDTO input = datos.getDatos();

		try {

			String mcaIncluyePedidos = "N";
			List<TipoCargaDTO> tipoCarga = null;
			if (input.getMcaIncluyePedidos() != null) {
				mcaIncluyePedidos = input.getMcaIncluyePedidos();
				tipoCarga = input.getTipoCarga();
			}

			final StringBuilder sql = new StringBuilder();

			String select = "SELECT ";
			String campos = "DISTINCT CA.COD_V_CARGA, " + "TC.COD_N_TIPO_CARGA, " + "TC.TXT_NOMBRE_TIPO_CARGA, "
					+ "TS.COD_N_TIPO_SUMINISTRO, " + "TS.TXT_NOMBRE_TIPO_SUMINISTRO, " + "CC.COD_N_CATEGORIA, "
					+ "CC.TXT_NOMBRE_CATEGORIA, " + "PP.COD_N_PROVEEDOR, " + "PP.TXT_RAZON_SOCIAL, "
					+ "CA.COD_V_ALMACEN_ORIGEN, " + "CA.COD_V_CENTRO_DESTINO, "
					+ "TO_CHAR(CA.FEC_D_ENTREGA,'DD/MM/YYYY'), " + "TO_CHAR(CA.FEC_DT_SERVICIO,'DD/MM/YYYY'), "
					+ "ESC.COD_N_ESTADO, " + "ESC.TXT_NOMBRE_ESTADO, " + "EC.NUM_HUECO_OCUPADO, "
					+ "EC.NUM_PESO_OCUPADO, " + "EC.NUM_DIVISION, " + "CA.MCA_CONTIENE_LPC, "
					+ "CA.MCA_PEDIDOS_SIN_VALIDAR ";
			String from = "FROM D_EQUIPO_TRANSPORTE ET "
					+ "INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) "
					+ "INNER JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) "
					+ "INNER JOIN D_TIPO_CARGA TC ON (TC.COD_N_TIPO_CARGA = CA .COD_N_TIPO_CARGA) "
					+ "LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) "
					+ "LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA = CA.COD_N_CATEGORIA) "
					+ "LEFT JOIN D_PROVEEDOR_R PP ON (PP.COD_N_PROVEEDOR = CA.COD_N_PROVEEDOR) "
					+ "INNER JOIN D_ESTADO_CARGA ESC ON (ESC.COD_N_ESTADO = CA.COD_N_ESTADO) ";
			String where = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo ";

			String order = "ORDER BY CA.COD_V_CARGA ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);
			final Query query = getEntityManager().createNativeQuery(sql.toString());

			if (codigoEquipo != null) {
				query.setParameter("codigoEquipo", codigoEquipo);
			}

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			List<PedidoDTO> listaPedido = null;

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					CargaDTO carga = new CargaDTO();
					carga.setCodigoCarga(String.valueOf(tmp[0]));
					carga.setCodigoTipoCarga(Integer.parseInt(String.valueOf(tmp[1])));
					carga.setNombreTipoCarga(String.valueOf(tmp[2]));
					carga.setCodigoSuministro(Integer.parseInt(String.valueOf(tmp[3])));
					carga.setNombreSuministro(String.valueOf(tmp[4]));
					if (tmp[5] != null)
						carga.setCodigoCategoria(Integer.parseInt(String.valueOf(tmp[5])));
					if (tmp[6] != null)
						carga.setNombreCategoria(String.valueOf(tmp[6]));
					if (tmp[7] != null)
						carga.setCodigoProveedor(String.valueOf(tmp[7]));
					if (tmp[8] != null)
						carga.setNombreProveedor(String.valueOf(tmp[8]));
					carga.setCodigoAlmacenOrigen(String.valueOf(tmp[9]));
					carga.setCodigoCentroDestino(String.valueOf(tmp[10]));
					carga.setFechaEntrega(String.valueOf(tmp[11]));
					carga.setFechaServicio(String.valueOf(tmp[12]));
					carga.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[13])));
					carga.setNombreEstado(String.valueOf(tmp[14]));
					carga.setNumeroHuecoOcupado(Double.parseDouble(String.valueOf(tmp[15])));
					carga.setNumeroPesoOcupado(Double.parseDouble(String.valueOf(tmp[16])));
					carga.setNumeroDivision(Integer.parseInt(String.valueOf(tmp[17])));
					carga.setMarcaLpC(String.valueOf(tmp[18]));
					carga.setPedidosSinValidar(String.valueOf(tmp[19]));

					listaPedido = new ArrayList<>();
					if ("S".equals(mcaIncluyePedidos) && tipoCargaCorrecto(tipoCarga, carga.getCodigoTipoCarga())) {
						listaPedido = consultarPedidosCarga(carga.getCodigoAlmacenOrigen(), carga.getCodigoCarga());
					}
					carga.setPedido(listaPedido);
					listaCarga.add(carga);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarCargaEquipos", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaCarga;
	}

	private List<PedidoDTO> consultarPedidosCarga(String codigoAlmacenOrigen, String codigoCarga) {

		List<PedidoDTO> pedidos = new ArrayList<>();

		try {

			final StringBuilder sql = new StringBuilder();

			String select = "SELECT ";
			String campos = "DISTINCT CP.COD_V_PEDIDO ";
			String from = "FROM S_CARGA_PEDIDO CP ";
			String where = "WHERE CP.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen "
					+ " AND CP.COD_V_CARGA = ?codigoCarga ";
			String order = "ORDER BY CP.COD_V_PEDIDO ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);
			final Query query = getEntityManager().createNativeQuery(sql.toString());

			if (codigoAlmacenOrigen != null) {
				query.setParameter("codigoAlmacenOrigen", codigoAlmacenOrigen);
			}
			if (codigoCarga != null) {
				query.setParameter("codigoCarga", codigoCarga);
			}

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object tmp : listado) {
					PedidoDTO pedido = new PedidoDTO();
					pedido.setCodigoPedido(String.valueOf(tmp));
					pedidos.add(pedido);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarPedidosCarga", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return pedidos;
	}

	private boolean tipoCargaCorrecto(List<TipoCargaDTO> tipoCarga, int codigoTipoCarga) {

		if (tipoCarga != null && !tipoCarga.isEmpty()) {
			for (int i = 0; i < tipoCarga.size(); i++) {
				int codigoTipoCargaTemp = Integer.parseInt(tipoCarga.get(i).getCodigoTipoCarga().toString());
				if (codigoTipoCargaTemp == codigoTipoCarga)
					return true;
			}
		}

		return false;
	}

	private List<DosierDTO> consultarDosieresPlanEmbarque(InputDatosDetalleDTO datos) {

		InputPlanEmbarqueDetalleDTO input = datos.getDatos();
		List<DosierDTO> listaDosier = new ArrayList<>();

		try {

			Long codigoPlanEmbarque = input.getCodigoEmbarque();

			String orden = "+numDosier";
			if (input.getOrden() != null) {

				Set<String> strings = new HashSet<>();
				strings.add("-numDosier");
				strings.add("+numDosier");
				strings.add("-fechaCreacion");
				strings.add("+fechaCreacion");
				strings.add("-usuarioCreacion");
				strings.add("+usuarioCreacion");
				strings.add("-codigoEstado");
				strings.add("+codigoEstado");
				strings.add("-fechaDescarga");
				strings.add("+fechaDescarga");

				if (strings.contains(input.getOrden())) {
					orden = input.getOrden();
				}
			}

			final StringBuilder sql = new StringBuilder();

			String select = "SELECT * FROM (SELECT ";
			String campos = "DISTINCT D.NUM_DOSIER, " + "D.NUM_ANYO, "
					+ "TO_CHAR(D.FEC_DT_CREACION,'DD/MM/YYYY') FEC_DT_CREACION, " + "D.COD_V_USUARIO_CREACION, "
					+ "D.COD_N_ESTADO," + "ED.TXT_NOMBRE_ESTADO, "
					+ "TO_CHAR(D.FEC_DT_DESCARGA,'DD/MM/YYYY') FEC_DT_DESCARGA ";
			String from = "FROM D_DOSIER D " + "JOIN D_ESTADO_DOSIER ED ON (ED.COD_N_ESTADO = D.COD_N_ESTADO) ";
			String where = "WHERE D.COD_N_EMBARQUE = ?codigoPlanEmbarque ";

			String order = ")";

			if (orden.equals("-numDosier"))
				order += "ORDER BY NUM_DOSIER DESC,NUM_ANYO DESC";
			else if (orden.equals("+numDosier"))
				order += "ORDER BY NUM_DOSIER ASC,NUM_ANYO ASC";
			else if (orden.equals("-fechaCreacion"))
				order += "ORDER BY TO_DATE(FEC_DT_CREACION,'DD/MM/YYYY') DESC";
			else if (orden.equals("+fechaCreacion"))
				order += "ORDER BY TO_DATE(FEC_DT_CREACION,'DD/MM/YYYY') ASC";
			else if (orden.equals("-usuarioCreacion"))
				order += "ORDER BY COD_V_USUARIO_CREACION DESC";
			else if (orden.equals("+usuarioCreacion"))
				order += "ORDER BY COD_V_USUARIO_CREACION ASC";
			else if (orden.equals("-codigoEstado"))
				order += "ORDER BY COD_N_ESTADO DESC";
			else if (orden.equals("+codigoEstado"))
				order += "ORDER BY COD_N_ESTADO ASC";
			else if (orden.equals("-fechaDescarga"))
				order += "ORDER BY TO_DATE(FEC_DT_DESCARGA,'DD/MM/YYYY') DESC";
			else if (orden.equals("+fechaDescarga"))
				order += "ORDER BY TO_DATE(FEC_DT_DESCARGA,'DD/MM/YYYY') ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);

			final Query query = getEntityManager().createNativeQuery(sql.toString());

			query.setParameter("codigoPlanEmbarque", codigoPlanEmbarque);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {

					DosierDTO dosier = new DosierDTO();
					dosier.setNumDosier(Long.parseLong(String.valueOf(tmp[0])));
					dosier.setAnyoDosier(Integer.parseInt(String.valueOf(tmp[1])));
					dosier.setFechaCreacion(String.valueOf(tmp[2]));
					dosier.setUsuarioCreacion(String.valueOf(tmp[3]));
					dosier.setCodigoEstado(Long.parseLong(String.valueOf(tmp[4])));
					dosier.setNombreEstado(String.valueOf(tmp[5]));
					dosier.setFechaDescarga(String.valueOf(tmp[6]));

					List<EquipoSimpleDTO> equiposDosier = consultarEquipoDosieresPlanEmbarque(dosier.getNumDosier(),
							dosier.getAnyoDosier());

					dosier.setEquipo(equiposDosier);

					listaDosier.add(dosier);

				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarDosieresPlanEmbarque", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaDosier;
	}

	private List<EquipoSimpleDTO> consultarEquipoDosieresPlanEmbarque(Long numDosiser, Integer anyoDosier) {

		List<EquipoSimpleDTO> listaEquipoSimple = new ArrayList<>();

		try {

			final StringBuilder sql = new StringBuilder();

			String select = "SELECT ";
			String campos = "DISTINCT DE.COD_N_EQUIPO, " + "DE.TXT_MATRICULA ";
			String from = "FROM S_DOSIER_EQUIPO DE ";
			String where = "WHERE DE.NUM_DOSIER = ?numDosiser AND  " + "DE.NUM_ANYO = ?anyoDosier ";

			String order = "ORDER BY DE.TXT_MATRICULA ";

			sql.append(select).append(campos).append(from).append(where).append(order);

			final Query query = getEntityManager().createNativeQuery(sql.toString());

			query.setParameter("numDosiser", numDosiser);
			query.setParameter("anyoDosier", anyoDosier);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {

					EquipoSimpleDTO equipoSimple = new EquipoSimpleDTO();
					equipoSimple.setCodigoEquipo(Long.parseLong(String.valueOf(tmp[0])));
					equipoSimple.setMatricula(String.valueOf(tmp[1]));

					listaEquipoSimple.add(equipoSimple);

				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "GetPlanEmbarqueDetalleDAOImpl(GESADUAN)",
					"consultarEquipoDosieresPlanEmbarque", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaEquipoSimple;
	}
}