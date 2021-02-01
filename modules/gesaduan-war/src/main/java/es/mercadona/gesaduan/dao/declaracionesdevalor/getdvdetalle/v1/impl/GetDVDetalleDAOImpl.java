package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdetalle.v1.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdetalle.v1.GetDVDetalleDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.InputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.CabeceraDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosComunesDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosDeclaracionDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosDestinatarioDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosExportadorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosHistorico;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosPedidoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.LineaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.OutputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.ProductoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.ProveedorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.ProvinciaDeCargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorJPA;

@SuppressWarnings("unchecked")
public class GetDVDetalleDAOImpl extends BaseDAO<DeclaracionesDeValorJPA> implements GetDVDetalleDAO{
	
	@Inject
	private org.slf4j.Logger logger;
	  
	@Inject
	private SecurityService securityService;
	
	@Override
	public void setEntityClass() {
		this.entityClass = DeclaracionesDeValorJPA.class;
		
	}

	
	@Override
	public OutputDeclaracionesDeValorDetalleDTO obtenerDeclaracionesPorCodigo(InputDeclaracionesDeValorDetalleDTO input, BoPage paginacion) {
		
		
		OutputDeclaracionesDeValorDetalleDTO result = new OutputDeclaracionesDeValorDetalleDTO();
		
		try {
			final StringBuilder sql = new StringBuilder();
			
			Double totalImporte = 0.0;
			
			
			Integer codigoDeclaracion = input.getCodigoDeclaracion();
			Integer anyo = input.getAnyo();
			Integer version = input.getVersion();
			
			/*DATOS DECLARACION */
			
			sql.append(" SELECT DVC.COD_N_DECLARACION_VALOR,  ");
			sql.append(" DVC.FEC_DT_CREACION,  ");
			sql.append(" DVC.NUM_ANYO,   ");
			sql.append(" DVC.COD_N_VERSION,  ");
			
			/* DATOS PEDIDO */
			
			sql.append(" PED.FEC_D_PEDIDO,  ");
			sql.append(" DVC.COD_V_PEDIDO,  ");
			sql.append(" P.COD_N_LEGACY_PROVEEDOR,  ");
			sql.append(" P.TXT_RAZON_SOCIAL,  ");
			sql.append(" DVC.COD_N_PROVEEDOR,  ");
			sql.append(" DVC.COD_N_PROVINCIA_CARGA,  ");
			sql.append(" PROV.TXT_NOMBRE,   ");
			sql.append(" DVC.COD_V_EXPEDICION,  ");
			sql.append(" DVC.TXT_CONDICIONES_ENTREGA,  ");
			sql.append(" DVC.FEC_D_ALBARAN,  ");
			sql.append(" DVC.FEC_D_ENVIO,   ");
			
			/* INDICADORES */
			
			sql.append(" DVC.MCA_FACTURA_DV,   ");
			sql.append(" DVC.MCA_ULTIMA_VIGENTE,  ");
			sql.append(" DVC.MCA_DV_CORRECTA,  ");
			sql.append(" DVC.MCA_ENVIO,   ");
			sql.append(" DVC.MCA_DESCARGA,  ");
			sql.append(" DVC.FEC_DT_DESCARGA,  ");
			sql.append(" DVC.MCA_CARGA_AUTOMATICA,  ");
			
			/* DATOS EXPORTADOR */
			
			sql.append(" PED.COD_V_EMPRESA,  ");
			
			/* DATOS DESTINATARIO */
			
			sql.append(" DVC.COD_V_ALMACEN,   ");
			sql.append(" CENTRO.TXT_NOMBRE_LARGO,  ");
			
			/* LINEAS */
			
			sql.append(" DVL.COD_N_MERCA,  ");
			sql.append(" DVL.COD_N_TARIC,  ");
			sql.append(" DVL.COD_V_REA,  ");
			sql.append(" DECODE(prod.TXT_DENOMINA_ALTERNATIVA,NULL,DEN.TXT_DESCRIPCION,prod.TXT_DENOMINA_ALTERNATIVA) AS PRODUCTO,  ");
			sql.append(" DECODE(prod.TXT_FORMATO_ALTERNATIVO,NULL,CONCAT(CONCAT(prod.num_formato_venta, ' '), prod.txt_unidad_medida), prod.TXT_FORMATO_ALTERNATIVO) AS FORMATO,  ");
			sql.append(" DVL.TXT_NOMBRE_BULTO_DV,  ");
			sql.append(" DVL.NUM_BULTOS,  ");
			sql.append(" DVL.NUM_PESO_NETO,  ");
			sql.append(" DVL.NUM_PESO_BRUTO,  ");
			sql.append(" DVL.NUM_VOLUMEN,  ");
			sql.append(" DVL.NUM_CANTIDAD,  ");
			sql.append(" DVL.NUM_PRECIO_UNIDAD,  ");
			sql.append(" DVL.NUM_IMPORTE_TOTAL,  ");
			sql.append(" DVL.NUM_GRADO_ALCOHOL,  ");
			sql.append(" DVL.NUM_GRADO_PLATO,  ");
			sql.append(" DVL.COD_V_PAIS,  ");
			sql.append(" DVL.MCA_PRODUCTO_LPC,  ");
					
			/**
			 * A partir de aqui item 42 para guardar los valores. 
			 */
			sql.append(" DEN.TXT_DESCRIPCION,  ");
			sql.append(" ean.cod_v_ean13,  ");
			sql.append(" prod.txt_marca,  ");
			sql.append(" DECODE(prod.TXT_DENOMINA_ALTERNATIVA,NULL,DEN.TXT_DESCRIPCION,prod.TXT_DENOMINA_ALTERNATIVA) AS PRODUCTO ,  "); // prod.txt_denomina_etiqueta
			sql.append(" DECODE(prod.TXT_FORMATO_ALTERNATIVO,NULL,CONCAT(CONCAT(prod.num_formato_venta, ' '), prod.txt_unidad_medida), prod.TXT_FORMATO_ALTERNATIVO) AS FORMATO  ,  ");
			
			/*
			 * A partir de aqui item 47 para guardar 
			 */

			sql.append(" CASE WHEN SPC_FICHERO_PDF IS NULL THEN 'N' ELSE 'S' END as tienepdf, "  );
			sql.append(" CASE WHEN SPC_FICHERO_CSV IS NULL THEN 'N' ELSE 'S' END as tienecsv  ");
			
			
			sql.append(" FROM O_DECLARACION_VALOR_CAB DVC  ");
			sql.append(" left JOIN D_PROVEEDOR_R P ON P.COD_N_PROVEEDOR = DVC.COD_N_PROVEEDOR  ");
			sql.append(" left JOIN O_PEDIDO_CAB_R PED ON PED.COD_V_PEDIDO = DVC.COD_V_PEDIDO  ");
			
			sql.append(" LEFT JOIN O_DECLARACION_VALOR_LIN DVL ON DVL.COD_N_DECLARACION_VALOR = DVC.COD_N_DECLARACION_VALOR  "
					+ " AND DVL.NUM_ANYO = DVC.NUM_ANYO "
					+ " AND DVL.COD_N_VERSION = DVC.COD_N_VERSION ");
				
			sql.append(" left join D_CENTRO_R CENTRO ON CENTRO.COD_V_CENTRO = DVC.COD_V_ALMACEN  ");
			sql.append(" left join D_PROVINCIA_R PROV ON PROV.COD_N_PROVINCIA = DVC.COD_N_PROVINCIA_CARGA  ");
			sql.append(" left join D_producto_r prod on dvl.cod_n_merca = prod.cod_n_producto  ");
			sql.append(" left join s_denominacion_producto_i18n_r DEN on DVL.COD_N_MERCA = DEN.COD_N_PRODUCTO and DEN.COD_V_LOCALE = ?locale ");
			sql.append(" left join s_ean_producto_proveedor_r EAN on ean.cod_n_producto = dvl.cod_n_merca AND ean.cod_n_legacy_proveedor = dvc.cod_n_proveedor  ");
					
			
			sql.append(" WHERE DVC.COD_N_DECLARACION_VALOR = ?codigoDeclaracion  ");
			sql.append(" AND DVC.NUM_ANYO = ?anyo  ");
			sql.append(" AND DVC.COD_N_VERSION = ?version  ");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			

			query.setParameter("codigoDeclaracion", codigoDeclaracion);
			query.setParameter("anyo", anyo);
			query.setParameter("version", version);
			query.setParameter("locale" , "es_ES");
			
			List<Object[]> listado = query.getResultList();
			
			DatosDTO dv = new DatosDTO();
			DeclaracionesDeValorDTO resultados = new DeclaracionesDeValorDTO();
			
			DatosComunesDTO datosComunes = new DatosComunesDTO();
			CabeceraDTO datosCabecera = new CabeceraDTO();		
			List<ProductoDTO> lineasDV = new ArrayList<ProductoDTO>();
			
			
			if (listado != null && !listado.isEmpty()) {
				
				String patternOutputDateTime = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
				SimpleDateFormat formatDateTime = new SimpleDateFormat(patternOutputDateTime);
				Date fechaOutput = null;
			
				boolean semaforoCab = true;
				
				for (Object[] tmp : listado) {
					
					/* DECLARACIONES CABECERA */
					if(semaforoCab) {
						
						/* DATOS COMUNES */
						
						if(tmp[15] != null && tmp[15].equals("F")) {
							datosComunes.setEsFactura(true);
						}else {
							datosComunes.setEsFactura(false);
						}
						
						if(tmp[16] != null && tmp[16].equals("S")) {
							datosComunes.setEsUltimaVigente(true);
						}else {
							datosComunes.setEsUltimaVigente(false);
						}
						
						if(tmp[17] != null && tmp[17].equals("S")) {
							datosComunes.setEsCorrecta(true);
						}else {
							datosComunes.setEsCorrecta(false);
						}
						
						if(tmp[18] != null && tmp[18].equals("S")) {
							datosComunes.setEstaNotificada(true);
						}else {
							datosComunes.setEstaNotificada(false);
						}
						
						if(tmp[19] != null && tmp[19].equals("S")) {
							datosComunes.setEstaDescargada(true);
						}else {
							datosComunes.setEstaDescargada(false);
						}
						
						if(tmp[20] != null) {
							fechaOutput = (Date) tmp[20];
							String dateToString = formatDateTime.format(fechaOutput);
							datosComunes.setFechaDescargaDV(dateToString);
						}else {
							datosComunes.setFechaDescargaDV(null);
						}
						
						
						if(tmp[21] != null && tmp[21].equals("N")) {
							datosComunes.setEsCargaManual(true);
						}else {
							datosComunes.setEsCargaManual(false);
						}
						
						datosComunes.setTienePdf(tmp[47].equals("S") ? true : false);
						datosComunes.setTieneCsv(tmp[48].equals("S") ? true : false);
						
						resultados.setDatosComunes(datosComunes);
						
						/* FIN DATOS COMUNES */
						
						/* CABECERA */
						
							DatosDeclaracionDTO datosDVgen= new DatosDeclaracionDTO();
							DatosPedidoDTO datosPedido = new DatosPedidoDTO();
							ProvinciaDeCargaDTO datosProvincia = new ProvinciaDeCargaDTO();
							ProveedorDTO proveedor = new ProveedorDTO();
							DatosExportadorDTO datosExp = new DatosExportadorDTO();
							DatosDestinatarioDTO datosDest = new DatosDestinatarioDTO();
											
						/*DATOS DECLARACION */
						
							datosDVgen.setCodigo(Integer.valueOf(tmp[0].toString()));
							
							if(tmp[1] != null) {
								fechaOutput = (Date) tmp[1];
								String dateToString = formatDateTime.format(fechaOutput);
								datosDVgen.setFecha(dateToString);
							}else {
								datosDVgen.setFecha(String.valueOf(tmp[1]));
							}
							
							
							datosDVgen.setAnyo(Integer.valueOf(tmp[2].toString()));
							datosDVgen.setVersion(Integer.parseInt(String.valueOf(tmp[3])));
							
							datosCabecera.setDatosDeclaracion(datosDVgen);
							
							
						
						/* DATOS PEDIDO */
						
							if(tmp[4] != null) {
								fechaOutput = (Date) tmp[4];
								String dateToString = formatDateTime.format(fechaOutput);
								datosPedido.setFecha(dateToString);
							}else {
								datosPedido.setFecha(null);
							}
							
							datosPedido.setCodigo(String.valueOf(tmp[5]));
							proveedor.setCodigoPublico(String.valueOf(tmp[6]));
							proveedor.setNombre(String.valueOf(tmp[7]));
							proveedor.setCodigo(String.valueOf(tmp[8]));
							
							
							datosProvincia.setNombre(tmp[10] != null ? tmp[10].toString() : null);
							datosProvincia.setCodigo(tmp[9] != null ? tmp[9].toString() : null);
							
							
							datosPedido.setCodigoExpedicion(String.valueOf(tmp[11]));
							datosPedido.setCondicionesEntrega(String.valueOf(tmp[12]));
							
							if(tmp[13] != null) {
								fechaOutput = (Date) tmp[13];
								String dateToString = formatDateTime.format(fechaOutput);
								datosPedido.setFechaAlbaran(dateToString);
							}else {
								datosPedido.setFechaAlbaran(null);
							}
							
							if(tmp[14] != null) {
								fechaOutput = (Date) tmp[14];
								String dateToString = formatDateTime.format(fechaOutput);
								datosPedido.setFechaEnvio(dateToString);
							}else {
								datosPedido.setFechaEnvio(null);
							}
							
							
							
							datosPedido.setProveedor(proveedor);
							datosPedido.setProvinciaDeCarga(datosProvincia);
							datosCabecera.setDatosPedido(datosPedido);
						
						
						/* DATOS EXPORTADOR */
							
							datosExp.setCodigo(String.valueOf(tmp[22]));
							datosCabecera.setDatosExportador(datosExp);
							
						/* DATOS DESTINATARIO */
							
							datosDest.setCodigoAlmacen(String.valueOf(tmp[23]));
							datosDest.setNombreAlmacen(String.valueOf(tmp[24]));
							datosCabecera.setDatosDestinatario(datosDest);
										
						
						/* FIN CABECERA */
						
						resultados.setCabecera(datosCabecera);
						
						semaforoCab = false;
					
					
					}//CIERRA EL SEMAFORO CABECERA
									
					/* DECLARACIONES LINEAS */
									
					LineaDTO linea = new LineaDTO();
					
					/* LINEAS */
					/* Se comprueba si el Merca y pk viene informado */
									
					if(tmp[25] != null)
						
					{
						
						
					
						linea.setCodigoPublico(Integer.valueOf(tmp[25].toString()));
						linea.setCodigoTaric(tmp[26] != null ? Long.valueOf(tmp[26].toString()) : null);
						linea.setCodigoRea(tmp[27] != null ? tmp[27].toString() : null);
						linea.setNombreAlternativo(tmp[28] != null ? tmp[28].toString() : null);
						linea.setDescFormatoVentaAlternativo(tmp[29] != null ? tmp[29].toString() : null);
						linea.setNombreTipoBulto(tmp[30] != null ? tmp[30].toString() : null);
											
						linea.setNumeroDeBultos(tmp[31] != null ? Integer.valueOf(tmp[31].toString()) : null);
						
						if(tmp[32] != null) {
							linea.setPesoNetoLinea(Double.parseDouble(String.valueOf(tmp[32])));
						}else {
							linea.setPesoNetoLinea(null);
						}
						
						if(tmp[33] != null) {
							linea.setPesoBrutoLinea(Double.parseDouble(String.valueOf(tmp[33])));
						}else {
							linea.setPesoBrutoLinea(null);
						}
						
						if(tmp[34] != null) {
							linea.setVolumenUnidad(Double.parseDouble(String.valueOf(tmp[34])));
						}else {
							linea.setVolumenUnidad(null);
						}
						
						if(tmp[35] != null) {
							linea.setCantidadFormato(Double.parseDouble(String.valueOf(tmp[35])));
						}else {
							linea.setCantidadFormato(null);
						}
						
						if(tmp[36] != null) {
							linea.setPrecioUnidad(Double.parseDouble(String.valueOf(tmp[36])));
						}else {
							linea.setPrecioUnidad(null);
						}
						
						if(tmp[37] != null) {
							totalImporte += Double.parseDouble(String.valueOf(tmp[37]));
							linea.setImporteTotal(Double.parseDouble(String.valueOf(tmp[37])));
							
						}else {
							linea.setImporteTotal(null);
						}
						
						if(tmp[38] != null) {
							linea.setGradoAlcohol(Double.parseDouble(String.valueOf(tmp[38])));
						}else {
							linea.setGradoAlcohol(null);
						}
						
						
						
						linea.setGradoPlato(tmp[39] != null ? Double.valueOf(tmp[39].toString()) : null);
						
						linea.setPaisOrigen(tmp[40] != null ? (String) tmp[40] : null);
						
						
						
						if(tmp[41] != null && tmp[41].equals("S")) {
							linea.setEsListoParaComer(true);
						}else {
							linea.setEsListoParaComer(false);
						}
						
						
						
						linea.setCodigoEan13(tmp[43] != null ? (String) tmp[43] : null);
						linea.setMarca(tmp[44] != null ? (String) tmp[44] : null);
						linea.setDenominacionProducto(tmp[45] != null ? (String) tmp[45] : null);
						linea.setFormatoVentaEstandar(tmp[46] != null ? (String) tmp[46] : null);
						
						if (linea.getCodigoTaric() == null) {
							final StringBuilder sqlTaric = new StringBuilder();

							String selectTaric = "SELECT ";		
							String camposTaric = "TP.COD_N_TARIC ";			
							String fromTaric   = "FROM S_TARIC_PRODUCTO TP ";
							String whereTaric  = "WHERE TP.COD_N_PRODUCTO = ?codigoPublico";
							
							sqlTaric.append(selectTaric).append(camposTaric).append(fromTaric).append(whereTaric);					
							final Query queryTaric = getEntityManager().createNativeQuery(sqlTaric.toString());							
							queryTaric.setParameter("codigoPublico", linea.getCodigoPublico());

							List<BigDecimal> listadoTaric = queryTaric.getResultList();
							
							if (listadoTaric != null && !listadoTaric.isEmpty()) {								
								for (BigDecimal tmpTaric : listadoTaric) {
									if (tmpTaric != null) linea.setCodigoTaricProducto(Long.parseLong(String.valueOf(tmpTaric)));
								}
							}
						}									
						
						ProductoDTO newProducto = new ProductoDTO();
						newProducto.setProducto(linea);
						
						lineasDV.add(newProducto);
					
					}//CIERRA SI EL MERCA ES DISTINTO DE NULL
					
				}//CIERRA EL FOR PRINCIPAL
				
			
				resultados.getCabecera().getDatosDeclaracion().setTotalImporte(totalImporte);
				
				resultados.setLineas(lineasDV);
				dv.setDeclaracionDeValor(resultados);
				result.setDatos(dv);
				
			}//CIERRA IF SI HAY DATOS
			
		} catch (NumberFormatException nfe) {
			establecerSalidaError(nfe, "obtenerDeclaracionesPorCodigo", "error.NumberFormatException");
			throw new ApplicationException(nfe.getMessage());
			
		} catch (Exception e) {
			establecerSalidaError(e, "obtenerDeclaracionesPorCodigo", "error.Exception");
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	
	
	}//CIERRA LA CLASE

	@Override
	public List<DatosHistorico> obtenerHistoricoDV(Integer codDv, Integer anyo) {
		List<DatosHistorico> result = new ArrayList<>();
		
		try {
			final StringBuilder sqlDV = new StringBuilder();
			
			sqlDV.append("SELECT EVENTO, TO_CHAR(FECHA,'DD/MM/YYYY HH24:MI') AS FECHA, VERSION, VIGENTE, CORRECTA  ");
			sqlDV.append("FROM ");
			sqlDV.append("(SELECT EVENTO, FECHA, VERSION, VIGENTE, CORRECTA, ");
			sqlDV.append("ROW_NUMBER() OVER(PARTITION BY EVENTO,FECHA ORDER BY VERSION) PRIMERA ");
			sqlDV.append("FROM ( ");
			sqlDV.append("SELECT DISTINCT 'Generación DV - Versión '||DV.COD_N_VERSION AS EVENTO, DV.FEC_DT_CREACION AS FECHA, DV.COD_N_VERSION AS VERSION, DV.MCA_ULTIMA_VIGENTE AS VIGENTE, DV.MCA_DV_CORRECTA AS CORRECTA   ");
			sqlDV.append("FROM O_DECLARACION_VALOR_CAB DV   ");
			sqlDV.append("WHERE DV.COD_N_DECLARACION_VALOR = ?codDv   ");
			sqlDV.append("AND DV.NUM_ANYO = ?anyo ");
			sqlDV.append("UNION  ");  
			sqlDV.append("SELECT DISTINCT 'Realización pedido', PED.FEC_D_PEDIDO, DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA  "); 
			sqlDV.append("FROM O_PEDIDO_CAB_R PED  "); 
			sqlDV.append("INNER JOIN O_EXPEDICION_CAB EXP ON (EXP.COD_V_PEDIDO = PED.COD_V_PEDIDO) ");  
			sqlDV.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.COD_V_EXPEDICION = EXP.COD_V_EXPEDICION AND DV.FEC_D_ALBARAN = EXP.FEC_D_ALBARAN) ");  
			sqlDV.append("WHERE DV.COD_N_DECLARACION_VALOR = ?codDv ");  
			sqlDV.append("AND DV.NUM_ANYO = ?anyo ");  
			sqlDV.append("UNION ");  
			sqlDV.append("SELECT DISTINCT 'Expedición pedido', EXP.FEC_D_ALBARAN, DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");  
			sqlDV.append("FROM O_EXPEDICION_CAB EXP ");   
			sqlDV.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.COD_V_EXPEDICION = EXP.COD_V_EXPEDICION AND DV.FEC_D_ALBARAN = EXP.FEC_D_ALBARAN) ");  
			sqlDV.append("WHERE DV.COD_N_DECLARACION_VALOR = ?codDv ");  
			sqlDV.append("AND DV.NUM_ANYO = ?anyo ");  
			sqlDV.append("UNION ");  
			sqlDV.append("SELECT DISTINCT 'Envío notificación',NOTIF.FEC_DT_ENVIO, DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");  
			sqlDV.append("FROM S_NOTIF_ALERTA_EXPED_DV NOTIF ");  
			sqlDV.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.COD_N_DECLARACION_VALOR = NOTIF.COD_N_DECLARACION_VALOR ");   
			sqlDV.append(" AND DV.NUM_ANYO = NOTIF.NUM_ANYO AND DV.COD_N_VERSION = NOTIF.COD_N_VERSION) ");  
			sqlDV.append("WHERE DV.COD_N_DECLARACION_VALOR = ?codDv ");  
			sqlDV.append("AND DV.NUM_ANYO = ?anyo ");  
			sqlDV.append("AND NOTIF.FEC_DT_ENVIO IS NOT NULL ");  
			sqlDV.append("UNION ");  
			sqlDV.append("SELECT DISTINCT 'Descarga DV por el agente de aduanas',DV.FEC_DT_DESCARGA, DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");  
			sqlDV.append("FROM O_DECLARACION_VALOR_CAB DV ");  
			sqlDV.append("WHERE DV.COD_N_DECLARACION_VALOR = ?codDv ");  
			sqlDV.append("AND DV.NUM_ANYO = ?anyo ");  
			sqlDV.append("AND DV.MCA_DESCARGA = 'S' ");  
			sqlDV.append("AND DV.FEC_DT_DESCARGA IS NOT NULL) ");
			sqlDV.append("ORDER BY VERSION,FECHA ASC) ");
			sqlDV.append("WHERE PRIMERA = 1 ");

			final Query queryDV = getEntityManager().createNativeQuery(sqlDV.toString());
			
			queryDV.setParameter("codDv", codDv);
			queryDV.setParameter("anyo", anyo);
			
			List<Object[]> listado = queryDV.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					DatosHistorico dh = new DatosHistorico();
					dh.setEvento(String.valueOf(tmp[0]));
					dh.setFechaEvento(String.valueOf(tmp[1]));
					dh.setVersion(Integer.valueOf(tmp[2].toString()));
							
					if(tmp[3] != null && tmp[3].equals("S")) {
						dh.setEsVigente(true);
					}else {
						dh.setEsVigente(false);
					}
					
					if(tmp[4] != null && tmp[4].equals("S")) {
						dh.setEsCorrecta(true);
					}else {
						dh.setEsCorrecta(false);
					}
					
					result.add(dh);
				}
				
			}
		} catch (NumberFormatException e) {
			establecerSalidaError(e, "obtenerHistoricoDV", "error.NumberFormatException");
			throw new ApplicationException(e.getMessage());
		} catch (Exception e) {
			establecerSalidaError(e, "obtenerHistoricoDV", "error.Exception");
			throw new ApplicationException(e.getMessage());
		}
		
		
		return result;
	}


	private void establecerSalidaError(Exception exception, String metodo, String codError) {

	    String login = this.securityService.getPrincipal().getLogin();
	    
	    this.logger.error("Error ejecutando la clase: GetDVDetalleDAOImpl",
	        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
  }
  
	
	
}
