package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvsumario.v1.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;


import javax.persistence.Query;



import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvsumario.v1.GetDVSumarioDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.InputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.DatosDvDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.OutputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.ProveedoresDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorJPA;


public class GetDVSumarioDAOImpl extends BaseDAO<DeclaracionesDeValorJPA> implements GetDVSumarioDAO{
	
	
	@Override
	public void setEntityClass() {
		this.entityClass = DeclaracionesDeValorJPA.class;
		
	}
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public OutputDeclaracionesDeValorDTO obtenerDeclaraciones(InputDeclaracionesDeValorDTO input, BoPage paginacion) {
				
		List<DatosDvDTO> resultList = new ArrayList<>();
		OutputDeclaracionesDeValorDTO result = new OutputDeclaracionesDeValorDTO();
		
		final StringBuilder sql = new StringBuilder();
		final StringBuilder sqlCount = new StringBuilder();
		
		Integer paginaInicio = null; 		
		if(paginacion.getPage() != null) {
			paginaInicio = paginacion.getPage().intValue();
		}
		
		Integer paginaTamanyo = null; 		
		if(paginacion.getLimit() != null) {
			paginaTamanyo = paginacion.getLimit().intValue();
		}
		
		sql.append(" SELECT distinct DV.MCA_DV_CORRECTA, ");
		sql.append(" DV.NUM_ANYO, ");
		sql.append(" DV.COD_N_DECLARACION_VALOR, ");
		sql.append(" DV.MCA_FACTURA_DV, ");
		sql.append(" DV.FEC_D_ALBARAN, ");
		sql.append(" P.COD_N_LEGACY_PROVEEDOR, ");
		sql.append(" P.TXT_RAZON_SOCIAL, ");
		sql.append(" DV.COD_N_PROVEEDOR, ");
		sql.append(" DV.COD_V_PEDIDO, ");
		sql.append(" DV.FEC_DT_CREACION, "); 
		sql.append(" DV.MCA_DESCARGA, ");
		sql.append(" DV.FEC_DT_DESCARGA,  ");
		sql.append(" DV.MCA_CARGA_AUTOMATICA, ");
		sql.append(" DV.COD_N_VERSION, ");
		sql.append(" nvl((SELECT SUM(nvl(NUM_IMPORTE_TOTAL,0.0)) FROM O_DECLARACION_VALOR_LIN LIN ");
		sql.append(" WHERE lin.cod_n_declaracion_valor = dv.cod_n_declaracion_valor and lin.cod_n_version = dv.cod_n_version and lin.num_anyo = dv.num_anyo),0.0) AS TOTALFactura, ");
		sql.append(" provincia.txt_nombre, ");   
		sql.append(" P.FEC_D_ACTIVACION ");

		sql.append(" FROM O_DECLARACION_VALOR_CAB DV ");
		sql.append(" left JOIN D_PROVEEDOR_R P ON P.COD_N_PROVEEDOR = DV.COD_N_PROVEEDOR ");
		sql.append(" left JOIN D_CENTRO_R C ON DV.COD_V_ALMACEN = C.COD_V_CENTRO ");
		sql.append(" left join d_provincia_r provincia on p.cod_v_provincia = provincia.cod_n_provincia ");
		
		if(input.getCodigoAgencia() != null) {
			sql.append(" JOIN S_RELACION_PROVEEDOR_R RPP ON RPP.COD_N_PROVEEDOR = P.COD_N_PROVEEDOR ");
			sql.append(" JOIN D_PROVEEDOR_R AGENCIA ON AGENCIA.COD_N_PROVEEDOR = rpp.cod_n_agencia_aduana ");
			sql.append(" and (rpp.fec_d_fin IS NULL OR trunc(rpp.fec_d_fin) >= trunc(sysdate)) ");			 
		}
		
		sql.append(" WHERE 1 = 1");
		
		/*
		 * SE ELIMINA FILTRO PARA LAS DECLARACIONES PENDIENTES DE VALIDAR
		 * 
		if(input.getEstadoDeclaracion().equals("VP")) {
			sql.append(" AND NOT EXISTS (SELECT OEC.COD_V_EXPEDICION"
				+ " FROM O_EXPEDICION_CAB OEC"
				+ " WHERE DV.COD_V_EXPEDICION = OEC.COD_V_EXPEDICION) ");
		}
		*/
			
		sql.append(" AND DV.MCA_ULTIMA_VIGENTE = ?ultimaVigente ");
		
		if(input.getCodigoAgencia() != null) {
			sql.append(" AND (agencia.cod_n_legacy_proveedor = ?agencia or agencia.cod_n_proveedor = ?agencia) ");				 
		}
		
		
	
		
		sqlCount.append(" SELECT COUNT(distinct concat(dv.cod_n_declaracion_valor,dv.cod_n_version)) FROM O_DECLARACION_VALOR_CAB DV ");
		sqlCount.append(" left JOIN D_PROVEEDOR_R P ON P.COD_N_PROVEEDOR = DV.COD_N_PROVEEDOR ");
		sqlCount.append(" left JOIN D_CENTRO_R C ON DV.COD_V_ALMACEN = C.COD_V_CENTRO ");
		sqlCount.append(" left join d_provincia_r provincia on p.cod_v_provincia = provincia.cod_n_provincia ");
		
		if(input.getCodigoAgencia() != null) {
			sqlCount.append(" JOIN S_RELACION_PROVEEDOR_R RPP ON RPP.COD_N_PROVEEDOR = P.COD_N_PROVEEDOR ");
			sqlCount.append(" JOIN D_PROVEEDOR_R AGENCIA ON AGENCIA.COD_N_PROVEEDOR = rpp.cod_n_agencia_aduana ");
			sqlCount.append(" and (rpp.fec_d_fin IS NULL OR rpp.fec_d_fin >= trunc(sysdate)) ");			 
		}
	
		sqlCount.append(" WHERE 1 = 1 ");
		
		/*
		 * SE ELIMINA FILTRO PARA LAS DECLARACIONES PENDIENTES DE VALIDAR
		 * 
		if(input.getEstadoDeclaracion().equals("VP")) {
			sqlCount.append(" AND NOT EXISTS (SELECT OEC.COD_V_EXPEDICION"
					+ " FROM O_EXPEDICION_CAB OEC"
					+ " WHERE DV.COD_V_EXPEDICION = OEC.COD_V_EXPEDICION) ");
			}
		*/
		
		sqlCount.append(" AND DV.MCA_ULTIMA_VIGENTE = ?ultimaVigente ");
		
		if(input.getCodigoAgencia() != null) {
			sqlCount.append(" AND (agencia.cod_n_legacy_proveedor = ?agencia or agencia.cod_n_proveedor = ?agencia) ");				 
		}
		
		
		Integer anyo = null;
		if(input.getAnyo() != null) {
			anyo = input.getAnyo();
			sql.append(" AND DV.NUM_ANYO = ?anyo ");
			sqlCount.append(" AND DV.NUM_ANYO = ?anyo ");
		}
		
		Integer numeroDeclaracion = null;
		if(input.getNumeroDeclaracion() != null) {
			numeroDeclaracion = input.getNumeroDeclaracion();
			sql.append(" AND DV.COD_N_DECLARACION_VALOR = ?numeroDeclaracion ");
			sqlCount.append(" AND DV.COD_N_DECLARACION_VALOR = ?numeroDeclaracion ");
		}
		
		String codigoPedido = null;
		if(input.getCodigoPedido() != null) {
			codigoPedido = input.getCodigoPedido();
			sql.append(" AND DV.COD_V_PEDIDO = ?codigoPedido ");
			sqlCount.append(" AND DV.COD_V_PEDIDO = ?codigoPedido ");
		}
		
		String codigoProveedor = null;
		if(input.getCodigoProveedor() != null) {
			codigoProveedor = input.getCodigoProveedor();
			sql.append(" AND P.COD_N_LEGACY_PROVEEDOR = ?codigoProveedor ");
			sqlCount.append(" AND P.COD_N_LEGACY_PROVEEDOR = ?codigoProveedor ");
		}
		
		String nombreProveedor = null;
		if(input.getNombreProveedor() != null) {
			nombreProveedor = input.getNombreProveedor();
			sql.append(" AND UPPER(P.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?nombreProveedor) ||'%') ");
			sqlCount.append(" AND UPPER(P.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?nombreProveedor) ||'%') ");
		}
		
		
		
		String codigoAlmacen = null;
		if(input.getCodigoAlmacen() != null) {
			codigoAlmacen = input.getCodigoAlmacen();
			sql.append(" AND TO_NUMBER(DV.COD_V_ALMACEN) = TO_NUMBER(?codigoAlmacen) ");
			sqlCount.append(" AND TO_NUMBER(DV.COD_V_ALMACEN) = TO_NUMBER(?codigoAlmacen) ");
		}
		
		
		String nombreAlmacen = null;
		if(input.getNombreAlmacen() != null) {
			nombreAlmacen = input.getNombreAlmacen();
			sql.append(" AND UPPER(C.TXT_NOMBRE_LARGO) LIKE ('%'|| UPPER(?nombreAlmacen) ||'%') ");
			sqlCount.append(" AND UPPER(C.TXT_NOMBRE_LARGO) LIKE ('%'|| UPPER(?nombreAlmacen) ||'%') ");
		}
		
		String tipoDeclaracion = "T";
		if(input.getTipoDeclaracion() != null && !input.getTipoDeclaracion().equals("T")) {
			tipoDeclaracion = input.getTipoDeclaracion();
				sql.append(" AND DV.MCA_FACTURA_DV = ?tipoDeclaracion ");
				sqlCount.append(" AND DV.MCA_FACTURA_DV = ?tipoDeclaracion ");
		}
		
		String estadoDeclaracion = "TD";
		if(input.getEstadoDeclaracion() != null && !input.getEstadoDeclaracion().equals("TD")) {
			estadoDeclaracion = input.getEstadoDeclaracion();
			if(estadoDeclaracion.equals("CM")) {
				sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sql.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");	
				/* sql.append(" AND DV.SPC_FICHERO_PDF IS NULL "); */
				
				sqlCount.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sqlCount.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
				/* sqlCount.append(" AND DV.SPC_FICHERO_PDF IS NULL "); */
				
			}
			if(estadoDeclaracion.equals("VP")) {
				/*
				 * sql.append(" AND DV.MCA_DV_CORRECTA = ?estadoDeclaracion AND DV.SPC_FICHERO_PDF IS NULL ");
				 * sqlCount.append(" AND DV.MCA_DV_CORRECTA = ?estadoDeclaracion AND DV.SPC_FICHERO_PDF IS NULL ");
				 */
				
				sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sql.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
				sqlCount.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sqlCount.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");		
			}
			if(estadoDeclaracion.equals("VI")) {
				sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sql.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
				sqlCount.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sqlCount.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
			}
			if(estadoDeclaracion.equals("VC")) {
				/*sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");*/
				sql.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
				/*sqlCount.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");*/
				sqlCount.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
			}
			if(estadoDeclaracion.equals("VO")) {
				sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sql.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
				sqlCount.append(" AND DV.MCA_CARGA_AUTOMATICA = ?cargaAuto ");
				sqlCount.append(" AND DV.MCA_DV_CORRECTA = ?esCorrecta ");
			}
		}
		
		String estadoDescarga = "T";
		if(input.getEstadoDescarga() != null && !input.getEstadoDescarga().equals("T")) {
			estadoDescarga = input.getEstadoDescarga();
				sql.append(" AND DV.MCA_DESCARGA = ?estadoDescarga ");
				sqlCount.append(" AND DV.MCA_DESCARGA = ?estadoDescarga ");
		}
		
		
		String tipoFechaBusqueda = null;
		
		
		Date fechaDesde = null;
		Date fechaHasta = null;
		
		if(input.getTipoFechaFiltro() != null) {			
			tipoFechaBusqueda = input.getTipoFechaFiltro();
			
			if(tipoFechaBusqueda.equals("FE")) {
				if(input.getFechaDesde() != null) {
					fechaDesde = input.getFechaDesde();
					sql.append(" AND TRUNC(FEC_D_ALBARAN) >= TO_DATE(?fechaDesde, 'DD/MM/YYYY') ");
					sqlCount.append(" AND TRUNC(FEC_D_ALBARAN) >= TO_DATE(?fechaDesde, 'DD/MM/YYYY') ");
				}
				if(input.getFechaHasta() != null) {
					fechaHasta = input.getFechaHasta();
					sql.append(" AND TRUNC(FEC_D_ALBARAN) <= TO_DATE(?fechaHasta, 'DD/MM/YYYY') ");
					sqlCount.append(" AND TRUNC(FEC_D_ALBARAN) <= TO_DATE(?fechaHasta, 'DD/MM/YYYY') ");
				}
			}
			
			if(tipoFechaBusqueda.equals("FV")) {
				if(input.getFechaDesde() != null) {
					fechaDesde = input.getFechaDesde();
					sql.append(" AND TRUNC(FEC_DT_CREACION) >= TO_DATE(?fechaDesde, 'DD/MM/YYYY') ");
					sqlCount.append(" AND TRUNC(FEC_DT_CREACION) >= TO_DATE(?fechaDesde, 'DD/MM/YYYY') ");
				}
				if(input.getFechaHasta() != null) {
					fechaHasta = input.getFechaHasta();
					sql.append(" AND TRUNC(FEC_DT_CREACION) <= TO_DATE(?fechaHasta, 'DD/MM/YYYY') ");
					sqlCount.append(" AND TRUNC(FEC_DT_CREACION) <= TO_DATE(?fechaHasta, 'DD/MM/YYYY') ");
				}
			}
			
			if(tipoFechaBusqueda.equals("FD")) {
				if(input.getFechaDesde() != null) {
					fechaDesde = input.getFechaDesde();
					sql.append(" AND TRUNC(FEC_DT_DESCARGA) >= TO_DATE(?fechaDesde, 'DD/MM/YYYY') ");
					sqlCount.append(" AND TRUNC(FEC_DT_DESCARGA) >= TO_DATE(?fechaDesde, 'DD/MM/YYYY') ");
				}
				if(input.getFechaHasta() != null) {
					fechaHasta = input.getFechaHasta();
					sql.append(" AND TRUNC(FEC_DT_DESCARGA) <= TO_DATE(?fechaHasta, 'DD/MM/YYYY') ");
					sqlCount.append(" AND TRUNC(FEC_DT_DESCARGA) <= TO_DATE(?fechaHasta, 'DD/MM/YYYY') ");
				}				
			}
			
		}
		
		String orden = input.getOrden();
		if(orden != null) {
			if(orden.equals("+anyo")) {
				sql.append(" ORDER BY NUM_ANYO ASC ");
			}
			if(orden.equals("-anyo")) {
				sql.append(" ORDER BY NUM_ANYO DESC ");
			}
			if(orden.equals("+numeroDeclaracion")) {
				sql.append(" ORDER BY COD_N_DECLARACION_VALOR ASC");
			}
			if(orden.equals("-numeroDeclaracion")) {
				sql.append(" ORDER BY COD_N_DECLARACION_VALOR DESC");
			}
			if(orden.equals("+tipoDeclaracion")) {
				sql.append(" ORDER BY MCA_FACTURA_DV ASC");
			}
			if(orden.equals("-tipoDeclaracion")) {
				sql.append(" ORDER BY MCA_FACTURA_DV DESC");
			}
			if(orden.equals("+fechaExpedicion")) {
				sql.append(" ORDER BY FEC_D_ALBARAN ASC");
			}
			if(orden.equals("-fechaExpedicion")) {
				sql.append(" ORDER BY FEC_D_ALBARAN DESC");
			}
			if(orden.equals("+fechaDeclaracion")) {
				sql.append(" ORDER BY FEC_DT_CREACION ASC");
			}
			if(orden.equals("-fechaDeclaracion")) {
				sql.append(" ORDER BY FEC_DT_CREACION DESC");
			}
			if(orden.equals("+fechaDescarga")) {
				sql.append(" ORDER BY FEC_DT_DESCARGA ASC");
			}
			if(orden.equals("-fechaDescarga")) {
				sql.append(" ORDER BY FEC_DT_DESCARGA DESC");
			}
			if(orden.equals("+codigoProveedor")) {
				sql.append(" ORDER BY COD_N_PROVEEDOR ASC");
			}
			if(orden.equals("-codigoProveedor")) {
				sql.append(" ORDER BY COD_N_PROVEEDOR DESC");
			}
			if(orden.equals("+nombreProveedor")) {
				sql.append(" ORDER BY TXT_RAZON_SOCIAL ASC");
			}
			if(orden.equals("-nombreProveedor")) {
				sql.append(" ORDER BY TXT_RAZON_SOCIAL DESC");
			}
			if(orden.equals("+numeroPedido")) {
				sql.append(" ORDER BY COD_V_PEDIDO ASC");
			}
			if(orden.equals("-numeroPedido")) {
				sql.append(" ORDER BY COD_V_PEDIDO DESC");
			}
		}
		
		
		final Query query = getEntityManager().createNativeQuery(sql.toString());
		final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
		
		
		if(anyo != null) {
			query.setParameter("anyo", anyo);
			queryCount.setParameter("anyo", anyo);
		}
		
		if(numeroDeclaracion != null) {
			query.setParameter("numeroDeclaracion", numeroDeclaracion);
			queryCount.setParameter("numeroDeclaracion", numeroDeclaracion);
		}
		
		if(codigoPedido != null) {
			query.setParameter("codigoPedido", codigoPedido);
			queryCount.setParameter("codigoPedido", codigoPedido);
		}
		
		if(codigoProveedor != null) {
			query.setParameter("codigoProveedor", codigoProveedor);
			queryCount.setParameter("codigoProveedor", codigoProveedor);
		}
		
		if(nombreProveedor != null) {
			query.setParameter("nombreProveedor", nombreProveedor);
			queryCount.setParameter("nombreProveedor", nombreProveedor);
		}
		
		
		if(codigoAlmacen != null) {
			query.setParameter("codigoAlmacen", codigoAlmacen);
			queryCount.setParameter("codigoAlmacen", codigoAlmacen);
		}
		
		if(nombreAlmacen != null) {
			query.setParameter("nombreAlmacen", nombreAlmacen);
			queryCount.setParameter("nombreAlmacen", nombreAlmacen);
		}
		
		
		if(input.getTipoDeclaracion() != null && !input.getTipoDeclaracion().equals("T")) {
			query.setParameter("tipoDeclaracion", tipoDeclaracion);
			queryCount.setParameter("tipoDeclaracion", tipoDeclaracion);
		}
		
		
		if(estadoDeclaracion.equals("CM")){
			query.setParameter("cargaAuto", new String("N"));
			query.setParameter("esCorrecta", new String("S"));
			queryCount.setParameter("cargaAuto", new String("N"));
			queryCount.setParameter("esCorrecta", new String("S"));
		}
		if(estadoDeclaracion.equals("VI")){
			query.setParameter("cargaAuto", new String("S"));
			query.setParameter("esCorrecta", new String("N"));
			queryCount.setParameter("cargaAuto", new String("S"));
			queryCount.setParameter("esCorrecta", new String("N"));
		}
	
		if(estadoDeclaracion.equals("VP")){
			query.setParameter("cargaAuto", new String("N"));
			query.setParameter("esCorrecta", new String("N"));
			queryCount.setParameter("cargaAuto", new String("N"));
			queryCount.setParameter("esCorrecta", new String("N"));
		}
		
		if(estadoDeclaracion.equals("VC")){
			/* El filtro VC es usado por PROV para recuperar todas las DV correctas independientemente de si son cargas manuales o automáticas. */
			query.setParameter("esCorrecta", new String("S"));
			queryCount.setParameter("esCorrecta", new String("S"));
		}
		if(estadoDeclaracion.equals("VO")){
			query.setParameter("cargaAuto", new String("S"));
			query.setParameter("esCorrecta", new String("S"));
			queryCount.setParameter("cargaAuto", new String("S"));
			queryCount.setParameter("esCorrecta", new String("S"));
		}
		
		if(estadoDescarga.equals("D")){			
			query.setParameter("estadoDescarga", new String("S"));
			queryCount.setParameter("estadoDescarga", new String("S"));
		}
		
		if(estadoDescarga.equals("N")){			
			query.setParameter("estadoDescarga", new String("N"));
			queryCount.setParameter("estadoDescarga", new String("N"));
		}
		
	
		String dateMask = "dd/MM/YYYY";
		SimpleDateFormat sdf = new SimpleDateFormat(dateMask);
		if(fechaDesde != null && fechaHasta != null) {
			
			/*02042020 Se igualan las fechas para la busqueda del mismo día*/
			if(fechaDesde.equals(fechaHasta)) {
				   
		        Calendar calDesde = Calendar.getInstance();  
		        calDesde.setTime(fechaDesde);  
		        calDesde.set(Calendar.HOUR_OF_DAY , 0);  
		        calDesde.set(Calendar.MINUTE, 0);  
		        calDesde.set(Calendar.SECOND, 0);  
		        calDesde.set(Calendar.MILLISECOND, 0);      
		        fechaDesde = calDesde.getTime(); 
		        		   
		        Calendar calHasta = Calendar.getInstance(); 
		        calHasta.setTime(fechaDesde); 
		        fechaHasta = calHasta.getTime(); 
			}
		}
		
		if(fechaDesde != null) {
			String fDesde = sdf.format(fechaDesde);
			query.setParameter("fechaDesde", fDesde);
			queryCount.setParameter("fechaDesde", fDesde);
		}
		
		if(fechaHasta != null) {
			String fHasta = sdf.format(fechaHasta);			
			query.setParameter("fechaHasta", fHasta);
			queryCount.setParameter("fechaHasta", fHasta);
		}
		
		if(input.getCodigoAgencia() != null) {
			query.setParameter("agencia", input.getCodigoAgencia());
			queryCount.setParameter("agencia", input.getCodigoAgencia());
		}
		
		query.setParameter("ultimaVigente", "S");
		queryCount.setParameter("ultimaVigente", "S");
		
		
		if(paginaInicio != null) {
			if(paginaTamanyo != null) {
				paginaInicio = (paginaInicio * paginaTamanyo) - paginaTamanyo;
			}else {
				paginaInicio = (paginaInicio * 10) - 10;
			}
		}
					
		query.setFirstResult(paginaInicio);
		query.setMaxResults(paginaTamanyo);
		
		
		/**
		 * Contamos el total de resultados para la paginación.
		 * */
		
		Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));
		
		Map<String, String> mapaMetaData = new HashMap<String, String>();
		mapaMetaData.put("totalItemsCount", totalResults.toString());
				
		
		List<Object[]> listado = query.getResultList();
		
		
		if(listado != null && !listado.isEmpty()) {
			
			for (Object[] tmp : listado) {
				
				DatosDvDTO decDeValor = new DatosDvDTO();
				ProveedoresDTO decValorProv = new ProveedoresDTO();
				
				String patternOutputDateTime = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
				String patternOutputDate = "yyy-MM-dd";
				SimpleDateFormat formatDateTime = new SimpleDateFormat(patternOutputDateTime);
				SimpleDateFormat formatDate = new SimpleDateFormat(patternOutputDate);
				Date fechaOutput = null;
								
				if(tmp[0] != null && tmp[0].equals("S")) {
					decDeValor.setEsDeclaracionCorrecta(true);
				}else {
					decDeValor.setEsDeclaracionCorrecta(false);
				}
				
				decDeValor.setAnyo(Integer.parseInt(String.valueOf(tmp[1])));
				decDeValor.setNumero(Integer.parseInt(String.valueOf(tmp[2])));
				decDeValor.setVersion(Integer.parseInt(String.valueOf(tmp[13])));
				
				decDeValor.setImporteTotal(Double.parseDouble(String.valueOf(tmp[14])));
				decDeValor.setEstado(calculaEstado(tmp[12].toString(),tmp[0].toString(),estadoDeclaracion));
				
				if(tmp[3] != null && tmp[3].equals("F")) {
					decDeValor.setEsFactura(true);
				}else {
					decDeValor.setEsFactura(false);
				}
				
				if(tmp[4] != null) {
					fechaOutput = (Date) tmp[4];
					String dateToString = formatDate.format(fechaOutput);
					decDeValor.setFechaExpedicion(dateToString);
				}else {
					decDeValor.setFechaExpedicion(String.valueOf(tmp[4]));	
				}
				
				decValorProv.setCodigoPublico(String.valueOf(tmp[5]));
				decValorProv.setNombre(String.valueOf(tmp[6]));
				decValorProv.setCodigo(String.valueOf(tmp[7]));
				decValorProv.setProvincia(String.valueOf(tmp[15]));
				
				if(tmp[16] != null) {
					fechaOutput = (Date) tmp[16];
					String dateToString = formatDateTime.format(fechaOutput);
					decDeValor.setFechaGeneracion(dateToString);
				}else {
					decValorProv.setFechaActivacion(String.valueOf(tmp[16]));
				}
				
				
				decDeValor.setProveedor(decValorProv);
				decDeValor.setNumeroPedido(String.valueOf(tmp[8]));
				
				if(tmp[9] != null) {
					fechaOutput = (Date) tmp[9];
					String dateToString = formatDateTime.format(fechaOutput);
					decDeValor.setFechaGeneracion(dateToString);
				}else {
					decDeValor.setFechaGeneracion(String.valueOf(tmp[9]));
				}
				
				if(tmp[10] != null && tmp[10].equals("S")) {
					decDeValor.setEstaDescargada(true);
				}else {
					decDeValor.setEstaDescargada(false);
				}
				
				if(tmp[11] != null) {
					fechaOutput = (Date) tmp[11];
					String dateToString = formatDateTime.format(fechaOutput);
					decDeValor.setFechaDescargaDocumento(dateToString);
				}else {
					decDeValor.setFechaDescargaDocumento(String.valueOf(tmp[11]));
				}
				
				if(tmp[12] != null && tmp[12].equals("S")) {
					decDeValor.setEsCargaAutomatica(true);
				}else {
					decDeValor.setEsCargaAutomatica(false);
				}
				
				resultList.add(decDeValor);
			}
		}
		
		result.setMetadatos(mapaMetaData);
		result.setDatos(resultList);
		
		return result;
	}


	private String calculaEstado(String mcaCargaAuto, String mcaDVCorrecta, String filtroEstado) {
		
		String resultado = null;
		
		try {
			
			if(filtroEstado.equals("TD") || filtroEstado ==  null) {
			
				if(mcaCargaAuto.equals("N")) {
					if(mcaDVCorrecta.equals("S")) {
						resultado = "MANUAL";
					}else if(mcaDVCorrecta.equals("N")) {
						resultado = "PENDIENTE";
					}
				}else if(mcaCargaAuto.equals("S")) {
					if(mcaDVCorrecta.equals("S")) {
						resultado = "OK";
					}else if(mcaDVCorrecta.equals("N")) {
						resultado = "KO";
					}
				}
			}else {
				if(filtroEstado.equals("CM")) {
					resultado = "MANUAL";
				}
				else if(filtroEstado.equals("VP")) {
					resultado = "PENDIENTE";
				}
				else if(filtroEstado.equals("VI")) {
					resultado = "KO";
				}
				else if(filtroEstado.equals("VO")) {
					resultado = "OK";
				}
				else if(filtroEstado.equals("VC")) {
					resultado = "OK";
				}
				else {
					
				}
			}
		} catch (Exception e) {
			
		}
		
		return resultado;
	}
	
}
