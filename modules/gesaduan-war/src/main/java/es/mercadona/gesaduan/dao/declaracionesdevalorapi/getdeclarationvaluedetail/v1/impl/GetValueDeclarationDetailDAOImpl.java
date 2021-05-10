package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1.impl;

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
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1.GetValueDeclarationDetailDAO;
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
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.DataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationCommonDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationHeaderDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationLineDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorJPA;

@SuppressWarnings("unchecked")
public class GetValueDeclarationDetailDAOImpl extends BaseDAO<DeclaracionesDeValorJPA> implements GetValueDeclarationDetailDAO {
	
	@Inject
	private org.slf4j.Logger logger;
	  
	@Inject
	private SecurityService securityService;
	
	@Override
	public void setEntityClass() {
		this.entityClass = DeclaracionesDeValorJPA.class;		
	}
	
	@Override
	public OutputValueDeclarationDetailDTO getValueDeclarationDetail(InputValueDeclarationDetailDTO input, BoPage paginacion) {
		OutputValueDeclarationDetailDTO result = new OutputValueDeclarationDetailDTO();
		
		try {
			final StringBuilder sql = new StringBuilder();
			Long valueDeclarationCode = Long.parseLong(input.getValueDeclarationCode().split("-")[0]);
			Integer valueDeclarationYear = Integer.parseInt(input.getValueDeclarationCode().split("-")[1]);
			Integer valueDeclarationVersion = Integer.parseInt(input.getValueDeclarationCode().split("-")[2]);
			
			sql.append("SELECT DVC.MCA_ULTIMA_VIGENTE, DVC.MCA_DV_CORRECTA, DVC.MCA_ENVIO, ");
			sql.append("TO_CHAR(DVC.FEC_DT_DESCARGA, 'DD/MM/YYYY HH24:MI'), TO_CHAR(DVC.FEC_DT_DESCARGA_EXPORTADOR, 'DD/MM/YYYY HH24:MI'), ");
			sql.append("TO_CHAR(DVC.FEC_DT_DESCARGA_IMPORTADOR, 'DD/MM/YYYY HH24:MI'), DVC.MCA_CARGA_AUTOMATICA, ");
			sql.append("DVC.COD_N_DECLARACION_VALOR, DVC.NUM_ANYO, DVC.COD_N_VERSION, DVC.NUM_DOSIER, ");
			sql.append("DVC.NUM_ANYO_DOSIER, TO_CHAR(DVC.FEC_DT_CREACION, 'DD/MM/YYYY HH24:MI'), DVC.COD_V_EXPEDICION, DVC.TXT_CONDICIONES_ENTREGA, DVC.FEC_D_ALBARAN, DVC.FEC_D_ENVIO, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, DVC.COD_N_BLOQUE_LOGISTICO, DVC.COD_N_PROVEEDOR) COD_ORIGEN, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, BL.TXT_NOMBRE, P.TXT_RAZON_SOCIAL) NOMBRE_ORIGEN, DVC.COD_N_PROVINCIA_CARGA COD_PROVINCIA_ORIGEN, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, 'BLOQUE', 'PROVEEDOR') TIPO_ORIGEN, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, DVC.COD_V_ALMACEN, DVC.COD_V_ALMACEN) COD_DESTINO, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, PU.TXT_NOMBRE_PUERTO, CENTRO.TXT_NOMBRE_LARGO) NOMBRE_DESTINO, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, 'PUERTO', 'ALMACEN') TIPO_DESTINO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DVC ");
			sql.append("LEFT JOIN D_PROVEEDOR_R P ON P.COD_N_PROVEEDOR = DVC.COD_N_PROVEEDOR ");
			sql.append("LEFT JOIN D_CENTRO_R CENTRO ON CENTRO.COD_V_CENTRO = DVC.COD_V_ALMACEN ");
			sql.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON BL.COD_N_BLOQUE_LOGISTICO = DVC.COD_N_BLOQUE_LOGISTICO ");
			sql.append("LEFT JOIN D_PUERTO PU ON PU.COD_N_PUERTO = DVC.COD_N_PUERTO_DESEMBARQUE ");
			sql.append("WHERE DVC.COD_N_DECLARACION_VALOR = ?valueDeclarationCode ");
			sql.append("AND DVC.NUM_ANYO = ?valueDeclarationYear AND DVC.COD_N_VERSION = ?valueDeclarationVersion");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("valueDeclarationCode", valueDeclarationCode);
			query.setParameter("valueDeclarationYear", valueDeclarationYear);
			query.setParameter("valueDeclarationVersion", valueDeclarationVersion);
			query.setParameter("locale" , input.getLocaleId());
			
			List<Object[]> listado = query.getResultList();
			
			DataDTO data = new DataDTO();			
			ValueDeclarationCommonDataDTO commonData = new ValueDeclarationCommonDataDTO();
			ValueDeclarationHeaderDTO header = new ValueDeclarationHeaderDTO();
			
			if (listado != null && !listado.isEmpty()) {				
				for (Object[] tmp : listado) {
					if ("S".equals(String.valueOf(tmp[0]))) {
						commonData.setLastCurrent(true);
					} else {
						commonData.setLastCurrent(false);
					}
					if ("S".equals(String.valueOf(tmp[1]))) {
						commonData.setValueDeclarationOk(true);
					} else {
						commonData.setValueDeclarationOk(false);
					}
					if ("S".equals(String.valueOf(tmp[2]))) {
						commonData.setNotified(true);
					} else {
						commonData.setNotified(false);
					}
					if (tmp[3] != null) {
						commonData.setValueDeclarationDownloadDate(String.valueOf(tmp[3]));
					}
					if (tmp[4] != null) {
						commonData.setExportDownloadDate(String.valueOf(tmp[4]));
					}
					if (tmp[5] != null) {
						commonData.setImportDownloadDate(String.valueOf(tmp[5]));
					}
					if ("S".equals(String.valueOf(tmp[6]))) {
						commonData.setManualLoading(false);
					} else {
						commonData.setManualLoading(true);
					}
					header.setValueDeclarationNumber(String.valueOf(tmp[7]));
					header.setValueDeclarationYear(String.valueOf(tmp[8]));
					header.setValueDeclarationVersion(String.valueOf(tmp[9]));
					if (tmp[10] != null) {
						header.setDossierNumber(String.valueOf(tmp[10]));
					}
					if (tmp[11] != null) {
						header.setDossierYear(String.valueOf(tmp[11]));
					}
					if (tmp[12] != null) {
						header.setValueDeclarationDate(String.valueOf(tmp[12]));
					}
					
							[data][valueDeclarationHeader] dossierNumber= DVC.NUM_DOSIER
							[data][valueDeclarationHeader] dossierYear= DVC.NUM_ANYO_DOSIER
							[data][valueDeclarationHeader] valueDeclarationDate= DVC.FEC_DT_CREACION
							[data][valueDeclarationHeader] totalAmount= totalImporte (Suma de los DVL.NUM_IMPORTE_TOTAL de todos los registros)
							[data][valueDeclarationHeader] totalAmountCurrency= 'EUR'
							[data][valueDeclarationHeader] dispatchId= DVC.COD_V_EXPEDICION
							[data][valueDeclarationHeader] incoterm= DVC.TXT_CONDICIONES_ENTREGA
							[data][valueDeclarationHeader] deliveryNoteDate= DVC.FEC_D_ALBARAN
							[data][valueDeclarationHeader] dispatchDate= DVC.FEC_D_ENVIO
					
					
					} //CIERRA SI EL MERCA ES DISTINTO DE NULL
					
				} //CIERRA EL FOR PRINCIPAL
				
			
				resultados.getCabecera().getDatosDeclaracion().setTotalImporte(totalImporte);
				
				resultados.setLineas(lineasDV);
				dv.setDeclaracionDeValor(resultados);
				result.setDatos(dv);			
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
	public List<DatosHistorico> obtenerHistoricoDV(String declarationValue, Integer declarationYear) {
		List<DatosHistorico> result = new ArrayList<>();
		
		try {
			final StringBuilder sqlDV = new StringBuilder();
			
			sqlDV.append("SELECT EVENTO, TO_CHAR(FECHA,'DD/MM/YYYY HH24:MI') AS FECHA, VERSION, VIGENTE, CORRECTA  ");
			sqlDV.append("FROM ");
			sqlDV.append("(SELECT EVENTO, FECHA, VERSION, VIGENTE, CORRECTA, ");
			sqlDV.append("ROW_NUMBER() OVER(PARTITION BY EVENTO,FECHA ORDER BY VERSION) PRIMERA ");
			sqlDV.append("FROM ( ");
			sqlDV.append("SELECT DISTINCT 'Generación factura - Versión '||DV.COD_N_VERSION AS EVENTO, DV.FEC_DT_CREACION AS FECHA, DV.COD_N_VERSION AS VERSION, DV.MCA_ULTIMA_VIGENTE AS VIGENTE, DV.MCA_DV_CORRECTA AS CORRECTA   ");
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
