package es.mercadona.gesaduan.dao.equipotransporte.getequipotransportedetalle.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.equipotransporte.getequipotransportedetalle.v1.GetEquipoTransporteDetalleDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.InputEquipoTransporteDetalleDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.ContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.DatosEquipoTransporteDetalleDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.OutputEquipoTransporteDetalleDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.PedidoDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public class GetEquipoTransporteDetalleDAOImpl extends BaseDAO<EquipoTransporteJPA> implements GetEquipoTransporteDetalleDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = EquipoTransporteJPA.class;		
	}
	
	@Override
	public OutputEquipoTransporteDetalleDTO consultarEquipoTransporte(InputEquipoTransporteDetalleDTO input) {
		
		OutputEquipoTransporteDetalleDTO result = new OutputEquipoTransporteDetalleDTO();
		DatosEquipoTransporteDetalleDTO equipoTransporte = null;
		
		try {		
		
			Long codigoEquipo = input.getCodigoEquipo();
			
			final StringBuilder sql = new StringBuilder();
	
			String select = "SELECT DISTINCT ";		
			String campos = "ET.COD_N_EQUIPO, ET.COD_N_EMBARQUE, ET.TXT_MATRICULA, PR.COD_N_PROVEEDOR, PR.TXT_RAZON_SOCIAL, T.COD_N_TEMPERATURA, " +
					"T.TXT_TEMPERATURA, ET.NUM_CAPACIDAD, ET.NUM_OCUPACION, ET.COD_N_ESTADO, EE.TXT_NOMBRE_ESTADO, TO_CHAR(ET.FEC_DT_CARGA,'DD/MM/YYYY'), " +
					"TO_CHAR(ET.FEC_DT_CARGA, 'HH24:MI:SS'), ET.TXT_OBSERVACIONES, ET.COD_V_USUARIO_CREACION, ET.COD_N_ESTADO_DOCUMENTACION, EDE.TXT_NOMBRE_ESTADO, " +
					"CASE WHEN (SELECT COUNT(*) FROM O_CONTENEDOR_EXPEDIDO CE WHERE CE.COD_N_EQUIPO = ?codigoEquipo) > 0 THEN 'S' ELSE 'N' END ";
			String from = "FROM D_EQUIPO_TRANSPORTE ET " +
					"INNER JOIN D_ESTADO_EQUIPO EE ON (EE.COD_N_ESTADO = ET.COD_N_ESTADO) " +
					"LEFT JOIN D_PROVEEDOR_R PR ON (PR .COD_N_PROVEEDOR = ET.COD_N_PROVEEDOR) " +
					"LEFT JOIN D_TEMPERATURA T ON (T.COD_N_TEMPERATURA = ET.COD_N_TEMPERATURA) " +
					"LEFT JOIN S_EQUIPO_CARGA EC ON (EC .COD_N_EQUIPO = ET.COD_N_EQUIPO) " +
					"LEFT JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC .COD_V_CARGA AND  CA .COD_V_ALMACEN_ORIGEN = EC .COD_V_ALMACEN_ORIGEN) " +
					"LEFT JOIN D_TIPO_CARGA TC ON (TC.COD_N_TIPO_CARGA= CA .COD_N_TIPO_CARGA) " +
					"LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA .COD_N_TIPO_SUMINISTRO) " +
					"LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA= CA .COD_N_CATEGORIA) " +
					"LEFT JOIN D_PROVEEDOR_R PP ON (PP.COD_N_PROVEEDOR = CA .COD_N_PROVEEDOR) " +
					"LEFT JOIN D_ESTADO_CARGA ESC ON (ESC.COD_N_ESTADO = CA .COD_N_ESTADO) " +
					"LEFT JOIN D_ESTADO_DOCUMENTACION_EQUIPO EDE ON (EDE.COD_N_ESTADO = ET.COD_N_ESTADO_DOCUMENTACION) ";
			String where = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo ";
			
			sql.append(select).append(campos).append(from).append(where);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("codigoEquipo", codigoEquipo);
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();		
			List<CargaDTO> listaCarga = null;
			List<ContenedorDTO> listaContenedores = null;
	
			if (listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {				
					equipoTransporte = new DatosEquipoTransporteDetalleDTO();
					equipoTransporte.setCodigoEquipo(Long.parseLong(String.valueOf(tmp[0])));
					equipoTransporte.setCodigoEmbarque(Long.parseLong(String.valueOf(tmp[1])));
					equipoTransporte.setMatricula(String.valueOf(tmp[2]));
					if (tmp[3] != null) equipoTransporte.setCodigoTransportista(String.valueOf(tmp[3]));
					if (tmp[4] != null) equipoTransporte.setNombreTransportista(String.valueOf(tmp[4]));
					if (tmp[5] != null) equipoTransporte.setCodigoTemperatura(Integer.parseInt(String.valueOf(tmp[5])));
					if (tmp[6] != null) equipoTransporte.setValorTemperatura(String.valueOf(tmp[6]));
					equipoTransporte.setCapacidad(Double.parseDouble(String.valueOf(tmp[7])));
					if (tmp[8] != null) equipoTransporte.setOcupacion(Double.parseDouble(String.valueOf(tmp[8])));
					equipoTransporte.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[9])));
					equipoTransporte.setNombreEstado(String.valueOf(tmp[10]));
					if (tmp[11] != null) equipoTransporte.setDiaCarga(String.valueOf(tmp[11]));
					if (tmp[12] != null) equipoTransporte.setHoraCarga(String.valueOf(tmp[12]));
					if (tmp[13] != null) equipoTransporte.setObservaciones(String.valueOf(tmp[13]));
					if (tmp[14] != null) equipoTransporte.setCodigoUsuarioCreacion(String.valueOf(tmp[14]));
					if (tmp[15] != null) equipoTransporte.setCodigoEstadoDocumentacion(Integer.parseInt(String.valueOf(tmp[15])));
					if (tmp[16] != null) equipoTransporte.setNombreEstadoDocumentacion(String.valueOf(tmp[16]));
					if (tmp[17] != null) equipoTransporte.setMcaExistenContenedoresFacturados(String.valueOf(tmp[17]));
					listaCarga = new ArrayList<>();
					listaCarga = getCargas(equipoTransporte.getCodigoEquipo(), input.getOrden(), input.getMcaIncluyeContenedores());
					listaContenedores = new ArrayList<>();
					if (input.getMcaIncluyeContenedores().equals("S")) listaContenedores = getContenedores(codigoEquipo, input.getOrden(), input.getMcaContenedorSinDosier());

					equipoTransporte.setCarga(listaCarga);
					equipoTransporte.setContenedor(listaContenedores);
				}			
					
			}

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalCargas", (listaCarga != null) ? String.valueOf(listaCarga.size()) : "0");
			mapaMetaData.put("totalContenedores", (listaContenedores != null) ? String.valueOf(listaContenedores.size()) : "0");

			result.setDatos(equipoTransporte);
			result.setMetadatos(mapaMetaData);
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetEquipoTransporteDetalleDAOImpl(GESADUAN)","consultarEquipoTransporte",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		
		return result;
	}
	
	private List<CargaDTO> getCargas(Long codigoEquipo, String orden, String hayContenedores) {		
		List<CargaDTO> listaCarga = null;		
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT * FROM (SELECT DISTINCT ";		
			String campos = "CA.COD_V_CARGA, TC.COD_N_TIPO_CARGA, TC.TXT_NOMBRE_TIPO_CARGA, TS.COD_N_TIPO_SUMINISTRO, TS.TXT_NOMBRE_TIPO_SUMINISTRO, " +
					"CC.COD_N_CATEGORIA, CC.TXT_NOMBRE_CATEGORIA, PP.COD_N_PROVEEDOR, PP.TXT_RAZON_SOCIAL, CA.COD_V_ALMACEN_ORIGEN, CA.COD_V_CENTRO_DESTINO, " +
					"TO_CHAR(CA.FEC_D_ENTREGA, 'DD/MM/YYYY') AS FECHAENTREGA, TO_CHAR(CA.FEC_DT_SERVICIO, 'DD/MM/YYYY'), ESC.COD_N_ESTADO, ESC.TXT_NOMBRE_ESTADO, " +
					"EC.NUM_HUECO_OCUPADO, EC.NUM_PESO_OCUPADO, EC.NUM_DIVISION, CA.MCA_CONTIENE_LPC, CA.MCA_PEDIDOS_SIN_VALIDAR ";
			String from = "FROM D_EQUIPO_TRANSPORTE ET " +
					"INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) " +
					"INNER JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) " +
					"LEFT JOIN D_TIPO_CARGA TC ON (TC.COD_N_TIPO_CARGA = CA .COD_N_TIPO_CARGA) " +
					"LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) " +
					"LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA = CA.COD_N_CATEGORIA) " +
					"LEFT JOIN D_PROVEEDOR_R PP ON (PP.COD_N_PROVEEDOR = CA.COD_N_PROVEEDOR) " +
					"INNER JOIN D_ESTADO_CARGA ESC ON (ESC.COD_N_ESTADO = CA.COD_N_ESTADO) ";
			String where = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo) ";
			
			String order = "";
			String countFin = ")";
			
			if (orden.equals("-codigoCarga"))
				order += "ORDER BY COD_V_CARGA DESC";
			else if (orden.equals("+codigoCarga"))
				order += "ORDER BY COD_V_CARGA ASC";
			else if (orden.equals("-codigoTipoCarga"))
				order += "ORDER BY COD_N_TIPO_CARGA DESC";
			else if (orden.equals("+codigoTipoCarga"))
				order += "ORDER BY COD_N_TIPO_CARGA ASC";
			else if (orden.equals("-nombreSuministro"))
				order += "ORDER BY TXT_NOMBRE_TIPO_SUMINISTRO DESC";
			else if (orden.equals("+nombreSuministro"))
				order += "ORDER BY TXT_NOMBRE_TIPO_SUMINISTRO ASC";
			else if (orden.equals("-nombreProveedor"))
				order += "ORDER BY TXT_RAZON_SOCIAL DESC";
			else if (orden.equals("+nombreProveedor"))
				order += "ORDER BY TXT_RAZON_SOCIAL ASC";
			else if (orden.equals("-codigoAlmacenOrigen"))
				order += "ORDER BY COD_V_ALMACEN_ORIGEN DESC";
			else if (orden.equals("+codigoAlmacenOrigen"))
				order += "ORDER BY COD_V_ALMACEN_ORIGEN ASC";
			else if (orden.equals("-codigoCentroDestino"))
				order += "ORDER BY COD_V_CENTRO_DESTINO DESC";
			else if (orden.equals("+codigoCentroDestino"))
				order += "ORDER BY COD_V_CENTRO_DESTINO ASC";
			else if (orden.equals("-fechaEntrega"))
				order += "ORDER BY TO_DATE(FECHAENTREGA, 'DD/MM/YYYY') DESC";
			else if (orden.equals("+fechaEntrega"))
				order += "ORDER BY TO_DATE(FECHAENTREGA, 'DD/MM/YYYY') ASC";			
			else if (orden.equals("-nombreCategoria"))
				order += "ORDER BY TXT_NOMBRE_CATEGORIA DESC";
			else if (orden.equals("+nombreCategoria"))
				order += "ORDER BY TXT_NOMBRE_CATEGORIA ASC";
			else if (orden.equals("-marcaLpC"))
				order += "ORDER BY MCA_CONTIENE_LPC DESC";
			else if (orden.equals("+marcaLpC"))
				order += "ORDER BY MCA_CONTIENE_LPC ASC";
			else if (orden.equals("-numeroDivision"))
				order += "ORDER BY NUM_DIVISION DESC";
			else if (orden.equals("+numeroDivision"))
				order += "ORDER BY NUM_DIVISION ASC";
			else if (orden.equals("-numeroHuecoOcupado"))
				order += "ORDER BY NUM_HUECO_OCUPADO DESC";
			else if (orden.equals("+numeroHuecoOcupado"))
				order += "ORDER BY NUM_HUECO_OCUPADO ASC";
			else if (orden.equals("-numeroPesoOcupado"))
				order += "ORDER BY NUM_PESO_OCUPADO DESC";
			else if (orden.equals("+numeroPesoOcupado"))
				order += "ORDER BY NUM_PESO_OCUPADO ASC";
			
			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			if (codigoEquipo != null) {
				query.setParameter("codigoEquipo", codigoEquipo);
				queryCount.setParameter("codigoEquipo", codigoEquipo);
			}
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				listaCarga = new ArrayList<>(); 
				for (Object[] tmp : listado) {		
					CargaDTO carga = new CargaDTO();
					carga.setCodigoCarga(String.valueOf(tmp[0]));
					if (tmp[1] != null) carga.setCodigoTipoCarga(Integer.parseInt(String.valueOf(tmp[1])));
					if (tmp[2] != null) carga.setNombreTipoCarga(String.valueOf(tmp[2]));
					if (tmp[3] != null) carga.setCodigoSuministro(Integer.parseInt(String.valueOf(tmp[3])));
					if (tmp[4] != null) carga.setNombreSuministro(String.valueOf(tmp[4]));
					if (tmp[5] != null) carga.setCodigoCategoria(Integer.parseInt(String.valueOf(tmp[5])));
					if (tmp[6] != null) carga.setNombreCategoria(String.valueOf(tmp[6]));
					if (tmp[7] != null) carga.setCodigoProveedor(String.valueOf(tmp[7]));
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
					carga.setPedido(getPedidos(String.valueOf(tmp[0]), String.valueOf(tmp[9])));
					listaCarga.add(carga);
				}
			}
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetEquipoTransporteDetalleDAOImpl(GESADUAN)","getCargas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return listaCarga;
	}
	
	private List<ContenedorDTO> getContenedores(Long codigoEquipo, String orden, String mcaContenedorSinDosier) {
		List<ContenedorDTO> listaContenedor = null;
		final StringBuilder sql = new StringBuilder();
		final StringBuilder sqlCount = new StringBuilder();
		
		try {
			String count  = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";
			String campos = "DISTINCT CA.COD_V_CARGA, CE.NUM_CONTENEDOR, TS.COD_N_TIPO_SUMINISTRO, TS.TXT_NOMBRE_TIPO_SUMINISTRO, PP.COD_N_PROVEEDOR, PP.TXT_RAZON_SOCIAL, CA.COD_V_ALMACEN_ORIGEN, " +
							"CA.COD_V_CENTRO_DESTINO, TO_CHAR(CE.FEC_DT_EXPEDICION, 'DD/MM/YYYY HH24:MI:SS'), TO_CHAR(CA.FEC_D_ENTREGA, 'DD/MM/YYYY'), TO_CHAR(CA.FEC_DT_SERVICIO, 'DD/MM/YYYY'), CC.COD_N_CATEGORIA, CC.TXT_NOMBRE_CATEGORIA, " +
							"CA.MCA_CONTIENE_LPC, CE.NUM_DOSIER, CE.NUM_ANYO, CA.COD_N_TIPO_CARGA ";
			String from   = "FROM D_EQUIPO_TRANSPORTE ET " +
							"INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) " +
							"INNER JOIN D_CARGA CA ON ( " +
							"CA.COD_V_CARGA = EC.COD_V_CARGA " +
							"AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) " +
							"INNER JOIN O_CONTENEDOR_EXPEDIDO CE ON (CE.COD_N_EQUIPO = ET.COD_N_EQUIPO AND CE.COD_V_ALMACEN = EC.COD_V_ALMACEN_ORIGEN AND CE.COD_V_CARGA = EC.COD_V_CARGA) " +
							"LEFT JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = CA.COD_N_TIPO_SUMINISTRO) " +
							"LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA = CA.COD_N_CATEGORIA) " +
							"LEFT JOIN D_PROVEEDOR_R PP ON (PP.COD_N_PROVEEDOR = CA.COD_N_PROVEEDOR) ";
			
			if (mcaContenedorSinDosier != null && mcaContenedorSinDosier.equals("S")) from += "LEFT JOIN S_DOSIER_EQUIPO DE ON (DE.COD_N_EQUIPO = ET.COD_N_EQUIPO) ";
			
			String where = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo "; 
			
			if (mcaContenedorSinDosier != null && mcaContenedorSinDosier.equals("S")) where += "AND CE.NUM_DOSIER IS NULL ";
			
			String order = "";
			String countFin = ")";
			
			if (orden.equals("-codigoCarga"))
				order += "ORDER BY CA.COD_V_CARGA DESC";
			else if (orden.equals("+codigoCarga"))
				order += "ORDER BY CA.COD_V_CARGA ASC";			
			if (orden.equals("-numeroContenedor"))
				order += "ORDER BY CE.NUM_CONTENEDOR DESC";
			else if (orden.equals("+numeroContenedor"))
				order += "ORDER BY CE.NUM_CONTENEDOR ASC";			
			if (orden.equals("-nombreSuministro"))
				order += "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO DESC";
			else if (orden.equals("+nombreSuministro"))
				order += "ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO ASC";			
			if (orden.equals("-nombreProveedor"))
				order += "ORDER BY PP.TXT_RAZON_SOCIAL DESC";
			else if (orden.equals("+nombreProveedor"))
				order += "ORDER BY PP.TXT_RAZON_SOCIAL ASC";			
			if (orden.equals("-codigoAlmacenOrigen"))
				order += "ORDER BY CA.COD_V_ALMACEN_ORIGEN DESC";
			else if (orden.equals("+codigoAlmacenOrigen"))
				order += "ORDER BY CA.COD_V_ALMACEN_ORIGEN ASC";			
			if (orden.equals("-codigoCentroDestino"))
				order += "ORDER BY CA.COD_V_CENTRO_DESTINO DESC";
			else if (orden.equals("+codigoCentroDestino"))
				order += "ORDER BY CA.COD_V_CENTRO_DESTINO ASC";			
			if (orden.equals("-fechaEntrega"))
				order += "ORDER BY CA.FEC_DT_SERVICIO DESC";
			else if (orden.equals("+fechaEntrega"))
				order += "ORDER BY CA.FEC_DT_SERVICIO ASC";			
			if (orden.equals("-fechaExpedicion"))
				order += "ORDER BY CE.FEC_DT_EXPEDICION DESC";
			else if (orden.equals("+fechaExpedicion"))
				order += "ORDER BY CE.FEC_DT_EXPEDICION ASC";			
			if (orden.equals("-nombreCategoria"))
				order += "ORDER BY CC.TXT_NOMBRE_CATEGORIA DESC";
			else if (orden.equals("+nombreCategoria"))
				order += "ORDER BY CC.TXT_NOMBRE_CATEGORIA ASC";			
			if (orden.equals("-marcaLpC"))
				order += "ORDER BY CA.MCA_CONTIENE_LPC DESC";
			else if (orden.equals("+marcaLpC"))
				order += "ORDER BY CA.MCA_CONTIENE_LPC ASC";			
			if (orden.equals("-numDosier"))
				order += "ORDER BY CE.NUM_DOSIER DESC";
			else if (orden.equals("+numDosier"))
				order += "ORDER BY CE.NUM_DOSIER ASC";
			
			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			if (codigoEquipo != null) {
				query.setParameter("codigoEquipo", codigoEquipo);
				queryCount.setParameter("codigoEquipo", codigoEquipo);
			}		
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				listaContenedor = new ArrayList<>(); 
				for (Object[] tmp : listado) {		
					ContenedorDTO contenedor = new ContenedorDTO();
					
					if (tmp[0] != null) contenedor.setCodigoCarga(String.valueOf(tmp[0]));
					if (tmp[1] != null) contenedor.setNumContenedor(Long.parseLong(String.valueOf(tmp[1])));
					if (tmp[2] != null) contenedor.setCodigoSuministro(Integer.parseInt(String.valueOf(tmp[2])));
					if (tmp[3] != null) contenedor.setNombreSuministro(String.valueOf(tmp[3]));
					if (tmp[4] != null) contenedor.setCodigoProveedor(Long.parseLong(String.valueOf(tmp[4])));
					if (tmp[5] != null) contenedor.setNombreProveedor(String.valueOf(tmp[5]));
					if (tmp[6] != null) contenedor.setCodigoCentroOrigen(String.valueOf(tmp[6]));
					if (tmp[7] != null) contenedor.setCodigoCentroDestino(String.valueOf(tmp[7]));
					if (tmp[8] != null) contenedor.setFechaExpedicion(String.valueOf(tmp[8]));
					if (tmp[9] != null) contenedor.setFechaEntrega(String.valueOf(tmp[9]));
					if (tmp[10] != null) contenedor.setFechaServicio(String.valueOf(tmp[10]));					
					if (tmp[11] != null) contenedor.setCodigoCategoria(Integer.parseInt(String.valueOf(tmp[11])));
					if (tmp[12] != null) contenedor.setNombreCategoria(String.valueOf(tmp[12]));
					if (tmp[13] != null) contenedor.setMarcaLpC(String.valueOf(tmp[13]));
					if (tmp[14] != null) contenedor.setNumDosier(Integer.parseInt(String.valueOf(tmp[14])));
					if (tmp[15] != null) contenedor.setAnyoDosier(Integer.parseInt(String.valueOf(tmp[15])));
					if (tmp[16] != null) contenedor.setCodigoTipoCarga(Integer.parseInt(String.valueOf(tmp[16])));
					contenedor.setPedido(getPedidos(String.valueOf(tmp[0]), String.valueOf(tmp[6])));
					listaContenedor.add(contenedor);
				}
			}		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetEquipoTransporteDetalleDAOImpl(GESADUAN)","getContenedores",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return listaContenedor;
	}
	
	private List<PedidoDTO> getPedidos(String codigoCarga, String codigoAlmacenOrigen) {
		final StringBuilder sqlPedido = new StringBuilder();
		List<PedidoDTO> listaPedidos = null;
		
		String selectPedido = "SELECT CP.COD_V_PEDIDO ";
		String fromPedido   = "FROM S_CARGA_PEDIDO CP ";
		String wherePedido =  "WHERE CP.COD_V_CARGA = ?codigoCarga AND CP.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen";

		sqlPedido.append(selectPedido).append(fromPedido).append(wherePedido);

		final Query queryPedido = getEntityManager().createNativeQuery(sqlPedido.toString());
		queryPedido.setParameter("codigoCarga", codigoCarga);
		queryPedido.setParameter("codigoAlmacenOrigen", codigoAlmacenOrigen);

		@SuppressWarnings("unchecked")
		List<String> listadoPedido = queryPedido.getResultList();

		if (listadoPedido != null && !listadoPedido.isEmpty()) {
			listaPedidos = new ArrayList<>();
			for (Object tmpPedido : listadoPedido) {
				PedidoDTO pedido = new PedidoDTO();
				if (tmpPedido != null) pedido.setCodigoPedido(String.valueOf(tmpPedido));
				listaPedidos.add(pedido);
			}
		}
		
		return listaPedidos;
	}

}