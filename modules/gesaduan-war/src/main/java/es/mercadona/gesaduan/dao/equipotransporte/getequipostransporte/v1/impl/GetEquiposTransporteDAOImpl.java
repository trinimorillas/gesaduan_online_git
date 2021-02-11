package es.mercadona.gesaduan.dao.equipotransporte.getequipostransporte.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.equipotransporte.getequipostransporte.v1.GetEquiposTransporteDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.EstadoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.InputDatosEquipoTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.InputEquiposTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.TipoCargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.restfull.DatosEquiposTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.restfull.OutputEquiposTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.EquipoInputDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.PedidoDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.SuministroDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public class GetEquiposTransporteDAOImpl extends BaseDAO<EquipoTransporteJPA> implements GetEquiposTransporteDAO {

	@Inject
	private org.slf4j.Logger logger;

	@Override
	public void setEntityClass() {
		this.entityClass = EquipoTransporteJPA.class;
	}

	@Override
	public OutputEquiposTransporteDTO listarEquiposTransporte(InputDatosEquipoTransporteDTO datos, BoPage pagination) {

		List<DatosEquiposTransporteDTO> resultList = new ArrayList<>();
		OutputEquiposTransporteDTO result = new OutputEquiposTransporteDTO();
		InputEquiposTransporteDTO input = datos.getDatos();

		try {

			Long codigoEmbarque = null;
			if (input.getCodigoEmbarque() != null)
				codigoEmbarque = input.getCodigoEmbarque();

			String matricula = null;
			if (input.getMatricula() != null)
				matricula = input.getMatricula();

			Integer codigoCentroOrigen = null;
			if (input.getCodigoCentroOrigen() != null)
				codigoCentroOrigen = input.getCodigoCentroOrigen();

			Integer codigoPuertoDesembarque = null;
			if (input.getCodigoPuertoDesembarque() != null)
				codigoPuertoDesembarque = input.getCodigoPuertoDesembarque();

			String fechaCarga = null;
			if (input.getFechaCarga() != null)
				fechaCarga = input.getFechaCarga();

			String fechaEmbarque = null;
			if (input.getFechaEmbarque() != null)
				fechaEmbarque = input.getFechaEmbarque();

			Integer codigoEstado = null;
			if (input.getCodigoEstado() != null)
				codigoEstado = input.getCodigoEstado();

			String usuarioCreacion = null;
			if (input.getUsuarioCreacion() != null)
				usuarioCreacion = input.getUsuarioCreacion();

			String mcaOcultaLlenos = null;
			if (input.getMcaOcultaLlenos() != null)
				mcaOcultaLlenos = input.getMcaOcultaLlenos();

			String mcaIncluyeCargas = "N";
			if (input.getMcaIncluyeCargas() != null)
				mcaIncluyeCargas = input.getMcaIncluyeCargas();

			List<EstadoDTO> estados = null;
			if (input.getEstados() != null)
				estados = input.getEstados();

			List<EquipoInputDTO> equipos = null;
			if (input.getEquipos() != null)
				equipos = input.getEquipos();

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
			String campos = "ET.COD_N_EQUIPO, ET.TXT_MATRICULA, PR.COD_N_PROVEEDOR, PR.TXT_RAZON_SOCIAL, T.COD_N_TEMPERATURA, " +
							"T.TXT_TEMPERATURA, ET.NUM_CAPACIDAD, ET.NUM_OCUPACION, ET.COD_N_ESTADO, EE.TXT_NOMBRE_ESTADO, TO_CHAR(ET.FEC_DT_CARGA,'DD/MM/YYYY HH24:MI:SS'), " +
							"ET.TXT_OBSERVACIONES, ET.COD_V_USUARIO_CREACION, ET.COD_N_EMBARQUE, PUD.COD_N_PUERTO, PUD.TXT_NOMBRE_PUERTO, " +
							"TO_CHAR(PE.FEC_DT_EMBARQUE,'DD/MM/YYYY'), (ET.NUM_OCUPACION/ET.NUM_CAPACIDAD) AS PORCENTAJE ";
			String from = "FROM D_EQUIPO_TRANSPORTE ET " +
					"INNER JOIN D_PLAN_EMBARQUE PE ON (PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE) " +
					"INNER JOIN D_ESTADO_EQUIPO EE ON (EE.COD_N_ESTADO = ET.COD_N_ESTADO) " +
					"LEFT JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) " +
					"LEFT JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) " +
					"INNER JOIN D_PUERTO PUD ON (PUD.COD_N_PUERTO = PE.COD_N_PUERTO_DESEMBARQUE) " +
					"LEFT JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = ET.COD_N_PROVEEDOR) " +
					"LEFT JOIN D_TEMPERATURA T ON (T.COD_N_TEMPERATURA = ET.COD_N_TEMPERATURA)";
			String where = " WHERE 1 = 1 ";
			String order = "";

			if (codigoEmbarque != null) where += "AND ET.COD_N_EMBARQUE = ?codigoEmbarque ";
			if (matricula != null) where += "AND UPPER(ET.TXT_MATRICULA) LIKE '%'|| (UPPER(?matricula) ||'%') ";
			if (codigoCentroOrigen != null) where += "AND CA.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen ";
			if (codigoPuertoDesembarque != null) where += "AND PE.COD_N_PUERTO_DESEMBARQUE = ?codigoPuertoDesembarque ";
			if (fechaCarga != null) where += "AND TRUNC(ET.FEC_DT_CARGA) = ?fechaCarga ";
			if (fechaEmbarque != null) where += "AND PE.FEC_DT_EMBARQUE = ?fechaEmbarque ";
			if (codigoEstado != null) where += "AND ET.COD_N_ESTADO = ?codigoEstado ";
			if (usuarioCreacion != null) where += "AND UPPER(ET.COD_V_USUARIO_CREACION) = UPPER(?usuarioCreacion) ";
			if (mcaOcultaLlenos != null) {
				if ("S".equals(mcaOcultaLlenos)) where += "AND ET.NUM_CAPACIDAD > ET.NUM_OCUPACION ";
			}

			if (estados != null && estados.size() > 0) {
				where += "AND PE.COD_N_ESTADO IN (";
				for (int i = 0; i < estados.size(); i++) {
					if (estados.size()-1 == i) where += ""+estados.get(i).getCodigoEstado()+"";
					else where += ""+estados.get(i).getCodigoEstado()+", ";
				}
				where += ") ";
			}

			if (equipos != null && equipos.size() > 0) {
				where += "AND ET.COD_N_EQUIPO IN (";
				for (int i = 0; i < equipos.size(); i++) {
					if (equipos.size()-1 == i) where += ""+ equipos.get(i).getCodigoEquipo()+"";
					else where += ""+ equipos.get(i).getCodigoEquipo()+", ";
				}
				where += ") ";
			}

			where += " GROUP BY ";
			where += "ET.COD_N_EQUIPO, ";
			where += "ET.TXT_MATRICULA, ";
			where += "PR.COD_N_PROVEEDOR, ";
			where += "PR.TXT_RAZON_SOCIAL, ";
			where += "T.COD_N_TEMPERATURA, ";
			where += "T.TXT_TEMPERATURA, ";
			where += "ET.NUM_CAPACIDAD, ";
			where += "ET.NUM_OCUPACION, ";
			where += "ET.COD_N_ESTADO, ";
			where += "EE.TXT_NOMBRE_ESTADO, ";
			where += "ET.FEC_DT_CARGA, ";
			where += "ET.TXT_OBSERVACIONES, ";
			where += "ET.COD_V_USUARIO_CREACION, ";
			where += "ET.COD_N_EMBARQUE, ";
			where += "PUD.COD_N_PUERTO, ";
			where += "PUD.TXT_NOMBRE_PUERTO, ";
			where += "PE.FEC_DT_EMBARQUE ";

			String countFin = ")";

			if (orden.equals("-matricula"))
				order += "ORDER BY ET.TXT_MATRICULA DESC";
			else if (orden.equals("+matricula"))
				order += "ORDER BY ET.TXT_MATRICULA ASC";
			else if (orden.equals("-ocupacion"))
				order += "ORDER BY PORCENTAJE DESC, ET.NUM_CAPACIDAD ASC";
			else if (orden.equals("+ocupacion"))
				order += "ORDER BY PORCENTAJE ASC, ET.NUM_CAPACIDAD ASC";
			else if (orden.equals("-puertoDestino"))
				order += "ORDER BY PUD.TXT_NOMBRE_PUERTO DESC";
			else if (orden.equals("+puertoDestino"))
				order += "ORDER BY PUD.TXT_NOMBRE_PUERTO ASC";
			else if (orden.equals("-fechaCarga"))
				order += "ORDER BY ET.FEC_DT_CARGA DESC";
			else if (orden.equals("+fechaCarga"))
				order += "ORDER BY ET.FEC_DT_CARGA ASC";
			else if (orden.equals("-fechaEmbarque"))
				order += "ORDER BY PE.FEC_DT_EMBARQUE DESC";
			else if (orden.equals("+fechaEmbarque"))
				order += "ORDER BY PE.FEC_DT_EMBARQUE ASC";
			else if (orden.equals("-nombreEstado"))
				order += "ORDER BY EE.TXT_NOMBRE_ESTADO DESC";
			else if (orden.equals("+nombreEstado"))
				order += "ORDER BY EE.TXT_NOMBRE_ESTADO ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());

			if (codigoEmbarque != null) {
				query.setParameter("codigoEmbarque", codigoEmbarque);
				queryCount.setParameter("codigoEmbarque", codigoEmbarque);
			}

			if (matricula != null) {
				query.setParameter("matricula", matricula);
				queryCount.setParameter("matricula", matricula);
			}

			if (codigoCentroOrigen != null) {
				query.setParameter("codigoCentroOrigen", codigoCentroOrigen);
				queryCount.setParameter("codigoCentroOrigen", codigoCentroOrigen);
			}

			if (codigoPuertoDesembarque != null) {
				query.setParameter("codigoPuertoDesembarque", codigoPuertoDesembarque);
				queryCount.setParameter("codigoPuertoDesembarque", codigoPuertoDesembarque);
			}

			if (fechaCarga != null) {
				query.setParameter("fechaCarga", fechaCarga);
				queryCount.setParameter("fechaCarga", fechaCarga);
			}

			if (fechaEmbarque != null) {
				query.setParameter("fechaEmbarque", fechaEmbarque);
				queryCount.setParameter("fechaEmbarque", fechaEmbarque);
			}

			if (codigoEstado != null) {
				query.setParameter("codigoEstado", codigoEstado);
				queryCount.setParameter("codigoEstado", codigoEstado);
			}

			if (usuarioCreacion != null) {
				query.setParameter("usuarioCreacion", usuarioCreacion);
				queryCount.setParameter("usuarioCreacion", usuarioCreacion);
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

			List<SuministroDTO> listaSuministros = null;
			List<TipoCargaDTO> listaTipoCargas = null;
			List<CargaDTO> listaCarga = null;

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					DatosEquiposTransporteDTO equipoTransporte = new DatosEquiposTransporteDTO();
					equipoTransporte.setCodigoEquipo(Long.parseLong(String.valueOf(tmp[0])));
					equipoTransporte.setMatricula(String.valueOf(tmp[1]));
					if (tmp[2] != null) equipoTransporte.setCodigoTransportista(Long.parseLong(String.valueOf(tmp[2])));
					if (tmp[3] != null) equipoTransporte.setNombreTransportista(String.valueOf(tmp[3]));
					if (tmp[4] != null) equipoTransporte.setCodigoTemperatura(Integer.parseInt(String.valueOf(tmp[4])));
					if (tmp[5] != null) equipoTransporte.setValorTemperatura(String.valueOf(tmp[5]));
					equipoTransporte.setCapacidad(Double.parseDouble(String.valueOf(tmp[6])));
					if (tmp[7] != null) equipoTransporte.setOcupacion(Double.parseDouble(String.valueOf(tmp[7])));
					equipoTransporte.setCodigoEstado(String.valueOf(tmp[8]));
					equipoTransporte.setNombreEstado(String.valueOf(tmp[9]));
					if (tmp[10] != null) equipoTransporte.setFechaCarga(String.valueOf(tmp[10]));
					if (tmp[11] != null) equipoTransporte.setObservaciones(String.valueOf(tmp[11]));
					if (tmp[12] != null) equipoTransporte.setCodigoUsuarioCreacion(String.valueOf(tmp[12]));
					equipoTransporte.setCodigoEmbarque(Long.parseLong(String.valueOf(tmp[13])));
					equipoTransporte.setCodigoPuertoDesembarque(Integer.parseInt(String.valueOf(tmp[14])));
					equipoTransporte.setNombrePuertoDesembarque(String.valueOf(tmp[15]));
					if (tmp[16] != null) equipoTransporte.setFechaEmbarque(String.valueOf(tmp[16]));

					if ("S".equals(mcaIncluyeCargas)) {
						listaCarga = consultarCargaEquipos(equipoTransporte.getCodigoEquipo());
					} else {
						listaCarga = new ArrayList<>();
					}
					equipoTransporte.setCarga(listaCarga);

					resultList.add(equipoTransporte);

					listaSuministros = new ArrayList<>();
					listaTipoCargas = new ArrayList<>();

					listaSuministros = getSuministrosEquipo(equipoTransporte.getCodigoEquipo());
					listaTipoCargas = getTipoCargaEquipo(equipoTransporte.getCodigoEquipo());

					equipoTransporte.setSuministro(listaSuministros);
					equipoTransporte.setTipoCarga(listaTipoCargas);
				}

			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setDatos(resultList);
			result.setMetadatos(mapaMetaData);
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetEquiposTransporteDAOImpl(GESADUAN)","listarEquiposTransporte",ex.getClass().getSimpleName(),ex.getMessage());
			throw new ApplicationException(ex.getMessage());
		}

		return result;
	}

	private List<SuministroDTO> getSuministrosEquipo(Long codigoEquipo) {

		List<SuministroDTO> listaSuministro = null;

		try {

			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();

			String select = "SELECT ";
			String campos = "DISTINCT TS.COD_N_TIPO_SUMINISTRO, TS.TXT_NOMBRE_TIPO_SUMINISTRO ";
			String from = "FROM S_EQUIPO_CARGA EC " +
					"LEFT JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) " +
					"LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) ";

			String where = "WHERE EC.COD_N_EQUIPO = ?codigoEquipo ";
			String order = "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());

			query.setParameter("codigoEquipo", codigoEquipo);
			queryCount.setParameter("codigoEquipo", codigoEquipo);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			listaSuministro = new ArrayList<SuministroDTO>();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					SuministroDTO suministro = new SuministroDTO();
					suministro.setCodigoSuministro(Long.parseLong(String.valueOf(tmp[0])));
					suministro.setNombreSuministro(String.valueOf(tmp[1]));

					listaSuministro.add(suministro);
				}
			}

		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetEquiposTransporteDAOImpl(GESADUAN)","getSuministrosEquipo",ex.getClass().getSimpleName(),ex.getMessage());
			throw new ApplicationException(ex.getMessage());
		}

		return listaSuministro;
	}

	private List<TipoCargaDTO> getTipoCargaEquipo(Long codigoEquipo) {

		List<TipoCargaDTO> listaTipoCarga = null;

		try {

			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();

			String select = "SELECT ";
			String campos = "DISTINCT TC.COD_N_TIPO_CARGA, TC.TXT_NOMBRE_TIPO_CARGA ";
			String from = "FROM S_EQUIPO_CARGA EC " +
					"LEFT JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) " +
					"LEFT JOIN D_TIPO_CARGA TC ON (TC.COD_N_TIPO_CARGA = CA.COD_N_TIPO_CARGA) ";

			String where = "WHERE EC.COD_N_EQUIPO = ?codigoEquipo ";
			String order = "ORDER BY TC.TXT_NOMBRE_TIPO_CARGA ASC";

			sql.append(select).append(campos).append(from).append(where).append(order);

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());

			query.setParameter("codigoEquipo", codigoEquipo);
			queryCount.setParameter("codigoEquipo", codigoEquipo);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			listaTipoCarga = new ArrayList<TipoCargaDTO>();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					TipoCargaDTO tipoCarga = new TipoCargaDTO();
					tipoCarga.setCodigoTipoCarga(Long.parseLong(String.valueOf(tmp[0])));
					tipoCarga.setNombreTipoCarga(String.valueOf(tmp[1]));

					listaTipoCarga.add(tipoCarga);
				}
			}

		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetEquiposTransporteDAOImpl(GESADUAN)","getTipoCargaEquipo",ex.getClass().getSimpleName(),ex.getMessage());
			throw new ApplicationException(ex.getMessage());
		}

		return listaTipoCarga;
	}

	private List<CargaDTO> consultarCargaEquipos(Long codigoEquipo) {

		List<CargaDTO> listaCarga = new ArrayList<>();

		try {

			final StringBuilder sql = new StringBuilder();

			String select = "SELECT ";
			String campos = "CA.COD_V_CARGA, TC.COD_N_TIPO_CARGA, TC.TXT_NOMBRE_TIPO_CARGA, TS.COD_N_TIPO_SUMINISTRO, TS.TXT_NOMBRE_TIPO_SUMINISTRO, " +
					"CC.COD_N_CATEGORIA, CC.TXT_NOMBRE_CATEGORIA, PP.COD_N_PROVEEDOR, PP.TXT_RAZON_SOCIAL, CA.COD_V_ALMACEN_ORIGEN, CA.COD_V_CENTRO_DESTINO, " +
					"TO_CHAR(CA.FEC_D_ENTREGA,'DD/MM/YYYY'), TO_CHAR(CA.FEC_DT_SERVICIO,'DD/MM/YYYY'), ESC.COD_N_ESTADO, ESC.TXT_NOMBRE_ESTADO, " +
					"EC.NUM_HUECO_OCUPADO, EC.NUM_PESO_OCUPADO, EC.NUM_DIVISION, CA.MCA_CONTIENE_LPC, CA.MCA_PEDIDOS_SIN_VALIDAR ";
			String from = "FROM D_EQUIPO_TRANSPORTE ET " +
					"INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) " +
					"INNER JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) " +
					"INNER JOIN D_TIPO_CARGA TC ON (TC.COD_N_TIPO_CARGA = CA .COD_N_TIPO_CARGA) " +
					"LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) " +
					"LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA = CA.COD_N_CATEGORIA) " +
					"LEFT JOIN D_PROVEEDOR_R PP ON (PP.COD_N_PROVEEDOR = CA.COD_N_PROVEEDOR) " +
					"INNER JOIN D_ESTADO_CARGA ESC ON (ESC.COD_N_ESTADO = CA.COD_N_ESTADO) ";
			String where = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo ";
			String groupBy = "GROUP BY CA.COD_V_CARGA, " +
					"TC.COD_N_TIPO_CARGA, " +
					"TC.TXT_NOMBRE_TIPO_CARGA, " +
					"TS.COD_N_TIPO_SUMINISTRO, " +
					"TS.TXT_NOMBRE_TIPO_SUMINISTRO, " +
					"CC.COD_N_CATEGORIA, " +
					"CC.TXT_NOMBRE_CATEGORIA, " +
					"PP.COD_N_PROVEEDOR, " +
					"PP.TXT_RAZON_SOCIAL, " +
					"CA.COD_V_ALMACEN_ORIGEN, " +
					"CA.COD_V_CENTRO_DESTINO, " +
					"CA.FEC_D_ENTREGA, " +
					"CA.FEC_DT_SERVICIO, " +
					"ESC.COD_N_ESTADO, " +
					"ESC.TXT_NOMBRE_ESTADO, " +
					"EC.NUM_HUECO_OCUPADO, " +
					"EC.NUM_PESO_OCUPADO, " +
					"EC.NUM_DIVISION, " +
					"CA.MCA_CONTIENE_LPC, " +
					"CA.MCA_PEDIDOS_SIN_VALIDAR ";

			String order = "ORDER BY CA.COD_V_CARGA ASC";


			sql.append(select).append(campos).append(from).append(where).append(groupBy).append(order);
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
					if (tmp[5] != null) carga.setCodigoCategoria(Integer.parseInt(String.valueOf(tmp[5])));
					if (tmp[6] != null) carga.setNombreCategoria(String.valueOf(tmp[6]));
					if (tmp[7] != null) carga.setCodigoProveedor(Long.parseLong(String.valueOf(tmp[7])));
					if (tmp[8] != null) carga.setNombreProveedor(String.valueOf(tmp[8]));
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

					listaPedido = consultarPedidosCargaEquipos(carga.getCodigoAlmacenOrigen(),carga.getCodigoCarga());

					carga.setPedido(listaPedido);

					listaCarga.add(carga);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetPlanEmbarqueDetalleDAOImpl(GESADUAN)","consultarCargaEquipos",e.getClass().getSimpleName(),e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaCarga;
	}

	private List<PedidoDTO> consultarPedidosCargaEquipos(String codigoAlmacenOrigen,String codigoCarga) {

		List<PedidoDTO> listaPedidos = new ArrayList<>();

		try {

			final StringBuilder sql = new StringBuilder();

			String select = "SELECT ";
			String campos = "SCP.COD_V_PEDIDO, SCP.COD_V_DIVISION_PEDIDO ";
			String from = "FROM S_CARGA_PEDIDO SCP " ;
			String where = "WHERE SCP.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
			                " AND SCP.COD_V_CARGA = ?codigoCarga ";

			String order = "ORDER BY SCP.COD_V_PEDIDO ASC";


			sql.append(select).append(campos).append(from).append(where).append(order);
			final Query query = getEntityManager().createNativeQuery(sql.toString());

			query.setParameter("codigoAlmacenOrigen", codigoAlmacenOrigen);
			query.setParameter("codigoCarga", codigoCarga);

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					PedidoDTO pedido = new PedidoDTO();
					pedido.setCodigoPedido(String.valueOf(tmp[0]));
					pedido.setCodigoDivision(String.valueOf(tmp[1]));
					listaPedidos.add(pedido);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetPlanEmbarqueDetalleDAOImpl(GESADUAN)","consultarPedidosCargaEquipos",e.getClass().getSimpleName(),e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return listaPedidos;
	}

}