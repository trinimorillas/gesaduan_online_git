package es.mercadona.gesaduan.dao.cargas.getcargas.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.cargas.getcargas.v1.GetCargasDAO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.EstadoDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.InputDatosCargasDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull.DatosCargasDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull.OutputCargasDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull.PedidoDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;

public class GetCargasDAOImpl extends BaseDAO<CargasJPA> implements GetCargasDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = CargasJPA.class;		
	}
	
	@Override
	public OutputCargasDTO listarCargas(InputDatosCargasDTO datos, BoPage pagination) {
		
		List<DatosCargasDTO> listaCargas = null;
		DatosCargasDTO cargas = null;
		OutputCargasDTO result = new OutputCargasDTO();
		
		try {		

			String codigoCarga = null;
			if (datos.getDatos().getCodigoCarga() != null)
				codigoCarga = datos.getDatos().getCodigoCarga();
	
			String codigoPedido = null;
			if (datos.getDatos().getCodigoPedido() != null)
				codigoPedido = datos.getDatos().getCodigoPedido();
			
			String codigoProveedor = null;
			if (datos.getDatos().getCodigoProveedor() != null)
				codigoProveedor = datos.getDatos().getCodigoProveedor();
			
			Integer codigoTipoCarga = null;
			if (datos.getDatos().getCodigoTipoCarga() != null)
				codigoTipoCarga = datos.getDatos().getCodigoTipoCarga();
			
			Integer codigoSuministro = null;
			if (datos.getDatos().getCodigoSuministro() != null)
				codigoSuministro = datos.getDatos().getCodigoSuministro();
			
			String fechaServicio = null;
			if (datos.getDatos().getFechaServicio() != null)
				fechaServicio = datos.getDatos().getFechaServicio();
			
			String fechaEntrega = null;
			if (datos.getDatos().getFechaEntrega() != null)
				fechaEntrega = datos.getDatos().getFechaEntrega();
			
			String fechaIntroduccion = null;
			if (datos.getDatos().getFechaIntroduccion() != null)
				fechaIntroduccion = datos.getDatos().getFechaIntroduccion();
			
			Integer codigoBloqueOrigen = null;
			if (datos.getDatos().getCodigoBloqueOrigen() != null)
				codigoBloqueOrigen = datos.getDatos().getCodigoBloqueOrigen();
			
			String codigoCentroOrigen = null;
			if (datos.getDatos().getCodigoCentroOrigen() != null)
				codigoCentroOrigen = datos.getDatos().getCodigoCentroOrigen();
			
			String codigoCentroDestino = null;
			if (datos.getDatos().getCodigoCentroDestino() != null)
				codigoCentroDestino = datos.getDatos().getCodigoCentroDestino();
			
			String usuarioCreacion = null;
			if (datos.getDatos().getUsuarioCreacion() != null)
				usuarioCreacion = datos.getDatos().getUsuarioCreacion();
			
			String mcaPedidosSinValidar  = null;
			if (datos.getDatos().getMcaPedidosSinValidar() != null)
				mcaPedidosSinValidar  = datos.getDatos().getMcaPedidosSinValidar();
			
			String aplicacionOrigen  = null;
			if (datos.getDatos().getAplicacionOrigen() != null)
				aplicacionOrigen  = datos.getDatos().getAplicacionOrigen();
			
			List<EstadoDTO> estados = null;
			if (datos.getDatos().getEstado() != null)
				estados = datos.getDatos().getEstado();
			
			Integer paginaInicio = null;
			if (pagination.getPage() != null)
				paginaInicio = pagination.getPage().intValue();
			
			Integer paginaTamanyo = null;
			if (pagination.getLimit() != null)
				paginaTamanyo = pagination.getLimit().intValue();
			
			String orden = null;
			if (datos.getDatos().getOrden() != null)
				orden = datos.getDatos().getOrden();
			
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*), NVL(SUM(NUM_HUECOS),0) FROM (";
			String select = "SELECT ";
			String campos = "CA.COD_V_CARGA, CA.COD_N_TIPO_CARGA, TC.TXT_NOMBRE_TIPO_CARGA, CA.COD_N_TIPO_SUMINISTRO, TS.TXT_NOMBRE_TIPO_SUMINISTRO, CA.COD_N_PROVEEDOR, PR.TXT_RAZON_SOCIAL, " +
							"CA.COD_V_ALMACEN_ORIGEN, CA.COD_V_CENTRO_DESTINO, TO_CHAR(CA.FEC_DT_SERVICIO,'DD/MM/YYYY'), TO_CHAR(CA.FEC_D_ENTREGA,'DD/MM/YYYY'), TO_CHAR(CA.FEC_DT_CREACION,'DD/MM/YYYY'), " +
							"CA.NUM_DIVISIONES, CA.NUM_HUECOS_ORIGEN, CA.NUM_HUECOS, CA.NUM_HUECOS_RESTANTES, CA.NUM_PESO_TOTAL, CA.NUM_PESO_RESTANTE, CA.NUM_VOLUMEN_TOTAL, CA.COD_N_CATEGORIA, CC.TXT_NOMBRE_CATEGORIA, " +
							"CA.MCA_CONTIENE_LPC, CA.COD_N_ESTADO, EC.TXT_NOMBRE_ESTADO, CA.MCA_PEDIDOS_SIN_VALIDAR, CA.COD_V_APLICACION_ORIGEN, CA.COD_V_USUARIO_CREACION, "+ 
							"TO_CHAR(CA.FEC_DT_VALIDACION,'DD/MM/YYYY HH24:MI'), CA.COD_V_USUARIO_VALIDACION ";
			String from = "FROM D_CARGA CA " +
					"LEFT JOIN D_TIPO_CARGA TC ON (TC.COD_N_TIPO_CARGA = CA.COD_N_TIPO_CARGA) " +
					"LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) " +
					"LEFT JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR = CA.COD_N_PROVEEDOR) " +
					"LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA = CA.COD_N_CATEGORIA) " +
					"LEFT JOIN D_ESTADO_CARGA EC ON (EC.COD_N_ESTADO = CA.COD_N_ESTADO) ";
			String where = "WHERE 1 = 1 ";
			String order = "";
			
			if (codigoCarga != null) where += "AND CA.COD_V_CARGA = ?codigoCarga ";		
			if (codigoPedido != null) where += "AND (CA.COD_V_CARGA, CA.COD_V_ALMACEN_ORIGEN) IN (SELECT CP.COD_V_CARGA, CP.COD_V_ALMACEN_ORIGEN FROM S_CARGA_PEDIDO CP WHERE CP.COD_V_PEDIDO = ?codigoPedido) ";		
			if (codigoProveedor != null) where += "AND CA.COD_N_PROVEEDOR = ?codigoProveedor ";		
			if (codigoTipoCarga != null) where += "AND CA.COD_N_TIPO_CARGA = ?codigoTipoCarga ";		
			if (codigoSuministro != null) where += "AND CA.COD_N_TIPO_SUMINISTRO = ?codigoSuministro ";
			if (fechaServicio != null) where += "AND CA.FEC_DT_SERVICIO = TO_DATE (?fechaServicio,'DD/MM/YYYY') ";
			if (fechaEntrega != null) where += "AND CA.FEC_D_ENTREGA = TO_DATE (?fechaEntrega,'DD/MM/YYYY') ";
			if (fechaIntroduccion != null) where += "AND CA.FEC_DT_CREACION = TO_DATE (?fechaIntroduccion,'DD/MM/YYYY') ";
			if (codigoBloqueOrigen != null) where += "AND CA.COD_V_ALMACEN_ORIGEN IN (SELECT COD_V_CENTRO FROM S_BLOQUE_LOGISTICO_CENTRO_R WHERE COD_N_BLOQUE_LOGISTICO = ?codigoBloqueOrigen) ";
			if (codigoCentroOrigen != null) where += "AND CA.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen ";
			if (codigoCentroDestino != null) where += "AND CA.COD_V_CENTRO_DESTINO = ?codigoCentroDestino ";
			if (usuarioCreacion != null) where += "AND CA.COD_V_USUARIO_CREACION = ?usuarioCreacion ";
			if (mcaPedidosSinValidar != null) where += "AND CA.MCA_PEDIDOS_SIN_VALIDAR = ?mcaPedidosSinValidar ";
			if (aplicacionOrigen != null) where += "AND CA.COD_V_APLICACION_ORIGEN = ?aplicacionOrigen ";
			if (estados != null) {
				where += "AND CA.COD_N_ESTADO IN (";
				for (int i = 0; i < estados.size(); i++) {
					if (estados.size()-1 == i) where += ""+estados.get(i).getCodigoEstado()+") ";
					else where += ""+estados.get(i).getCodigoEstado()+", ";
				}
			}
				
			String countFin = ")";
			
			if (orden.equals("-codigoCarga"))
				order += "ORDER BY CA.COD_V_CARGA DESC";
			else if (orden.equals("+codigoCarga"))
				order += "ORDER BY CA.COD_V_CARGA ASC";
			else if (orden.equals("-codigoTipoCarga"))
				order += "ORDER BY CA.COD_N_TIPO_CARGA DESC";
			else if (orden.equals("+codigoTipoCarga"))
				order += "ORDER BY CA.COD_N_TIPO_CARGA ASC";
			else if (orden.equals("-nombreTipoCarga"))
				order += "ORDER BY TC.TXT_NOMBRE_TIPO_CARGA DESC";
			else if (orden.equals("+nombreTipoCarga"))
				order += "ORDER BY TC.TXT_NOMBRE_TIPO_CARGA ASC";
			else if (orden.equals("-nombreSuministro"))
				order += "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO DESC";
			else if (orden.equals("+nombreSuministro"))
				order += "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO ASC";
			else if (orden.equals("-codigoTipoSuministro"))
				order += "ORDER BY CA.COD_N_TIPO_SUMINISTRO DESC";
			else if (orden.equals("+codigoTipoSuministro"))
				order += "ORDER BY CA.COD_N_TIPO_SUMINISTRO ASC";		
			else if (orden.equals("-nombreTipoSuministro") || orden.equals("-codigoSuministro"))
				order += "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO DESC";
			else if (orden.equals("+nombreTipoSuministro")  || orden.equals("+codigoSuministro"))
				order += "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO ASC";
			else if (orden.equals("-codigoProveedor"))
				order += "ORDER BY CA.COD_N_PROVEEDOR DESC";
			else if (orden.equals("+codigoProveedor"))
				order += "ORDER BY CA.COD_N_PROVEEDOR ASC";
			else if (orden.equals("-nombreProveedor"))
				order += "ORDER BY PR.TXT_RAZON_SOCIAL DESC";
			else if (orden.equals("+nombreProveedor"))
				order += "ORDER BY PR.TXT_RAZON_SOCIAL ASC";
			else if (orden.equals("-codigoCentroOrigen"))
				order += "ORDER BY CA.COD_V_ALMACEN_ORIGEN DESC";
			else if (orden.equals("+codigoCentroOrigen"))
				order += "ORDER BY CA.COD_V_ALMACEN_ORIGEN ASC";
			else if (orden.equals("-codigoCentroDestino"))
				order += "ORDER BY CA.COD_V_CENTRO_DESTINO DESC";
			else if (orden.equals("+codigoCentroDestino"))
				order += "ORDER BY CA.COD_V_CENTRO_DESTINO ASC";
			else if (orden.equals("-fechaEntrega"))
				order += "ORDER BY CA.FEC_D_ENTREGA DESC";
			else if (orden.equals("+fechaEntrega"))
				order += "ORDER BY CA.FEC_D_ENTREGA ASC";
			else if (orden.equals("-fechaServicio"))
				order += "ORDER BY CA.FEC_DT_SERVICIO DESC";
			else if (orden.equals("+fechaServicio"))
				order += "ORDER BY CA.FEC_DT_SERVICIO ASC";	
			else if (orden.equals("-fechaSIntroduccion"))
				order += "ORDER BY CA.FEC_DT_CREACION DESC";
			else if (orden.equals("+fechaSIntroduccion"))
				order += "ORDER BY CA.FEC_DT_CREACION ASC";			
			else if (orden.equals("-numeroDivisiones"))
				order += "ORDER BY CA.NUM_DIVISIONES DESC";
			else if (orden.equals("+numeroDivisiones"))
				order += "ORDER BY CA.NUM_DIVISIONES ASC";
			else if (orden.equals("-numeroHuecosOrigen"))
				order += "ORDER BY CA.NUM_HUECOS_ORIGEN DESC";
			else if (orden.equals("+numeroHuecosOrigen"))
				order += "ORDER BY CA.NUM_HUECOS_ORIGEN ASC";
			else if (orden.equals("-numeroHuecosRestantes"))
				order += "ORDER BY CA.NUM_HUECOS_RESTANTES DESC";
			else if (orden.equals("+numeroHuecosRestantes"))
				order += "ORDER BY CA.NUM_HUECOS_RESTANTES ASC";
			else if (orden.equals("-numeroPesoRestante"))
				order += "ORDER BY CA.NUM_PESO_RESTANTE DESC";
			else if (orden.equals("+numeroPesoRestante"))
				order += "ORDER BY CA.NUM_PESO_RESTANTE ASC";
			else if (orden.equals("-codigoCategoria"))
				order += "ORDER BY CA.COD_N_CATEGORIA DESC";
			else if (orden.equals("+codigoCategoria"))
				order += "ORDER BY CA.COD_N_CATEGORIA ASC";		
			else if (orden.equals("-nombreCategoria"))
				order += "ORDER BY CC.TXT_NOMBRE_CATEGORIA DESC";
			else if (orden.equals("+nombreCategoria"))
				order += "ORDER BY CC.TXT_NOMBRE_CATEGORIA ASC";
			else if (orden.equals("-lpc"))
				order += "ORDER BY CA.MCA_CONTIENE_LPC DESC";
			else if (orden.equals("+lpc"))
				order += "ORDER BY CA.MCA_CONTIENE_LPC ASC";
			else if (orden.equals("-mcaLpC"))
				order += "ORDER BY CA.MCA_CONTIENE_LPC DESC";
			else if (orden.equals("+mcaLpC"))
				order += "ORDER BY CA.MCA_CONTIENE_LPC ASC";
			else if (orden.equals("-codigoEstado"))
				order += "ORDER BY CA.COD_N_ESTADO DESC";
			else if (orden.equals("+codigoEstado"))
				order += "ORDER BY CA.COD_N_ESTADO ASC";		
			else if (orden.equals("-nombreEstado"))
				order += "ORDER BY EC.TXT_NOMBRE_ESTADO DESC";
			else if (orden.equals("+nombreEstado"))	
				order += "ORDER BY EC.TXT_NOMBRE_ESTADO ASC";
			else if (orden.equals("-mcaPedidosSinValidar"))
				order += "ORDER BY CA.MCA_PEDIDOS_SIN_VALIDAR DESC";
			else if (orden.equals("+mcaPedidosSinValidar"))
				order += "ORDER BY CA.MCA_PEDIDOS_SIN_VALIDAR ASC";
			else if (orden.equals("-numeroVolumenTotal"))
				order += "ORDER BY CA.NUM_VOLUMEN_TOTAL DESC";
			else if (orden.equals("+numeroVolumenTotal"))
				order += "ORDER BY CA.NUM_VOLUMEN_TOTAL ASC";
			else if (orden.equals("-fechaValidacion"))
				order += "ORDER BY CA.FEC_DT_VALIDACION DESC";
			else if (orden.equals("+fechaValidacion"))
				order += "ORDER BY CA.FEC_DT_VALIDACION ASC";
			else if (orden.equals("-codigoUsuarioValidacion"))
				order += "ORDER BY CA.COD_V_USUARIO_VALIDACION DESC";
			else if (orden.equals("+codigoUsuarioValidacion"))
				order += "ORDER BY CA.COD_V_USUARIO_VALIDACION ASC";			
			else if (orden.equals("-numeroTotalHuecos"))
				order += "ORDER BY CA.NUM_HUECOS DESC";
			else if (orden.equals("+numeroTotalHuecos"))
				order += "ORDER BY CA.NUM_HUECOS ASC";			
			else if (orden.equals("-numeroTotalPeso"))
				order += "ORDER BY CA.NUM_PESO_TOTAL DESC";
			else if (orden.equals("+numeroTotalPeso"))
				order += "ORDER BY CA.NUM_PESO_TOTAL ASC";
			
			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			if (codigoCarga != null) {
				query.setParameter("codigoCarga", codigoCarga);
				queryCount.setParameter("codigoCarga", codigoCarga);
			}
			
			if (codigoPedido != null) {
				query.setParameter("codigoPedido", codigoPedido);
				queryCount.setParameter("codigoPedido", codigoPedido);
			}
			
			if (codigoProveedor != null) {
				query.setParameter("codigoProveedor", codigoProveedor);
				queryCount.setParameter("codigoProveedor", codigoProveedor);
			}
			
			if (codigoTipoCarga != null) {
				query.setParameter("codigoTipoCarga", codigoTipoCarga);
				queryCount.setParameter("codigoTipoCarga", codigoTipoCarga);
			}
			
			if (codigoSuministro != null) {
				query.setParameter("codigoSuministro", codigoSuministro);
				queryCount.setParameter("codigoSuministro", codigoSuministro);
			}
			
			if (codigoBloqueOrigen != null) {
				query.setParameter("codigoBloqueOrigen", codigoBloqueOrigen);
				queryCount.setParameter("codigoBloqueOrigen", codigoBloqueOrigen);
			}
			
			if (fechaServicio != null) {
				query.setParameter("fechaServicio", fechaServicio);
				queryCount.setParameter("fechaServicio", fechaServicio);
			}
			
			if (fechaEntrega != null) {
				query.setParameter("fechaEntrega", fechaEntrega);
				queryCount.setParameter("fechaEntrega", fechaEntrega);
			}
			
			if (fechaIntroduccion != null) {
				query.setParameter("fechaIntroduccion", fechaIntroduccion);
				queryCount.setParameter("fechaIntroduccion", fechaIntroduccion);
			}
			
			if (codigoCentroOrigen != null) {
				query.setParameter("codigoCentroOrigen", codigoCentroOrigen);
				queryCount.setParameter("codigoCentroOrigen", codigoCentroOrigen);
			}
			
			if (codigoCentroDestino != null) {
				query.setParameter("codigoCentroDestino", codigoCentroDestino);
				queryCount.setParameter("codigoCentroDestino", codigoCentroDestino);
			}
			
			if (usuarioCreacion != null) {
				query.setParameter("usuarioCreacion", usuarioCreacion);
				queryCount.setParameter("usuarioCreacion", usuarioCreacion);
			}
			
			if (mcaPedidosSinValidar != null) {
				query.setParameter("mcaPedidosSinValidar", mcaPedidosSinValidar);
				queryCount.setParameter("mcaPedidosSinValidar", mcaPedidosSinValidar);
			}
			
			if (aplicacionOrigen != null) {
				query.setParameter("aplicacionOrigen", aplicacionOrigen);
				queryCount.setParameter("aplicacionOrigen", aplicacionOrigen);
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
				listaCargas = new ArrayList<>();
				for (Object[] tmp : listado) {
					cargas = new DatosCargasDTO();
					
					cargas.setCodigoCarga(String.valueOf(tmp[0]));
					if (tmp[1] != null) cargas.setCodigoTipoCarga(Integer.parseInt(String.valueOf(tmp[1])));				
					if (tmp[2] != null) cargas.setNombreTipoCarga(String.valueOf(tmp[2]));
					if (tmp[3] != null) cargas.setCodigoTipoSuministro(Integer.parseInt(String.valueOf(tmp[3])));
					if (tmp[4] != null) cargas.setNombreTipoSuministro(String.valueOf(tmp[4]));
					if (tmp[5] != null) cargas.setCodigoProveedor(String.valueOf(tmp[5]));
					if (tmp[6] != null) cargas.setNombreProveedor(String.valueOf(tmp[6]));
					if (tmp[7] != null) cargas.setCodigoCentroOrigen(String.valueOf(tmp[7]));
					if (tmp[8] != null) cargas.setCodigoCentroDestino(String.valueOf(tmp[8]));
					if (tmp[9] != null) cargas.setFechaServicio(String.valueOf(tmp[9]));
					if (tmp[10] != null) cargas.setFechaEntrega(String.valueOf(tmp[10]));
					if (tmp[11] != null) cargas.setFechaIntroduccion(String.valueOf(tmp[11]));
					if (tmp[12] != null) cargas.setNumeroDivisiones(Integer.parseInt(String.valueOf(tmp[12])));
					if (tmp[13] != null) cargas.setNumeroHuecosOrigen(Double.parseDouble(String.valueOf(tmp[13])));
					if (tmp[14] != null) cargas.setNumeroHuecos(Double.parseDouble(String.valueOf(tmp[14])));
					if (tmp[15] != null) cargas.setNumeroHuecosRestantes(Double.parseDouble(String.valueOf(tmp[15])));
					if (tmp[16] != null) cargas.setNumeroPesoTotal(Double.parseDouble(String.valueOf(tmp[16])));
					if (tmp[17] != null) cargas.setNumeroPesoRestante(Double.parseDouble(String.valueOf(tmp[17])));
					if (tmp[18] != null) cargas.setNumeroVolumenTotal(Double.parseDouble(String.valueOf(tmp[18])));
					if (tmp[19] != null) cargas.setCodigoCategoria(Integer.parseInt(String.valueOf(tmp[19])));
					if (tmp[20] != null) cargas.setNombreCategoria(String.valueOf(tmp[20]));
					if (tmp[21] != null) cargas.setLpc(String.valueOf(tmp[21]));
					if (tmp[22] != null) cargas.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[22])));
					if (tmp[23] != null) cargas.setNombreEstado(String.valueOf(tmp[23]));
					if (tmp[24] != null) cargas.setMcaPedidosSinValidar(String.valueOf(tmp[24]));
					if (tmp[25] != null) cargas.setAplicacionOrigen(String.valueOf(tmp[25]));
					if (tmp[26] != null) cargas.setCodigoUsuarioCreacion(String.valueOf(tmp[26]));
					if (tmp[27] != null) cargas.setFechaValidacion(String.valueOf(tmp[27]));
					if (tmp[28] != null) cargas.setCodigoUsuarioValidacion(String.valueOf(tmp[28]));					
					listaCargas.add(cargas);
					
					final StringBuilder sqlPedido = new StringBuilder();
					String selectPedido = "SELECT CP.COD_V_PEDIDO ";
					String fromPedido   = "FROM S_CARGA_PEDIDO CP ";
					String wherePedido =  "WHERE CP.COD_V_CARGA = ?codCarga AND CP.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen";

					sqlPedido.append(selectPedido).append(fromPedido).append(wherePedido);

					final Query queryPedido = getEntityManager().createNativeQuery(sqlPedido.toString());
					queryPedido.setParameter("codCarga", String.valueOf(tmp[0]));
					queryPedido.setParameter("codigoAlmacenOrigen", String.valueOf(tmp[7]));

					@SuppressWarnings("unchecked")
					List<String> listadoPedido = queryPedido.getResultList();

					if (listadoPedido != null && !listadoPedido.isEmpty()) {
						List<PedidoDTO> listaPedidos = new ArrayList<>();
						for (Object tmpPedido : listadoPedido) {
							PedidoDTO pedido = new PedidoDTO();
							if (tmpPedido != null) pedido.setCodigoPedido(String.valueOf(tmpPedido));
							listaPedidos.add(pedido);
						}
						cargas.setPedido(listaPedidos);
					}
				}
			}

			@SuppressWarnings("unchecked")
			List<Object[]> listadoMetadatos = queryCount.getResultList();
			Map<String, String> mapaMetaData = new HashMap<String, String>();

			if (listadoMetadatos != null && !listadoMetadatos.isEmpty()) {

				for (Object[] tmp : listadoMetadatos) {
					mapaMetaData.put("totalItemsCount", String.valueOf(tmp[0]));
					mapaMetaData.put("totalBultos", String.valueOf(tmp[1]));
				}
			}
			result.setMetadatos(mapaMetaData);
			result.setDatos(listaCargas);

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetCargasDAOImpl(GESADUAN)","listarCargas",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
