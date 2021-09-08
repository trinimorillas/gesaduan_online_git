package es.mercadona.gesaduan.business.declaracionesdevalorapi.putreturns.v2.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.putreturns.v2.PutReturnsService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putreturns.v2.PutReturnsDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.InputPutReturnsDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.ItemListDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.restfull.OutputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.restfull.OutputPutReturnsDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.DeclarationLineJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.EnvaseDeclaracionJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.ValueDeclarationJPA;

public class PutReturnsServiceImpl implements PutReturnsService {
	
	@Inject
	private PutReturnsDAO putReturnsDAO;

	@Inject
	private org.slf4j.Logger logger;
	
	private static final String LOG_FILE = "PutReturnsServiceImpl(GESADUAN)";
	private static final String UNITARIO = "Unitario";
	private static final String APP = "GESADUAN";

	@Transactional
	@Override
	public OutputPutReturnsDTO putReturns (InputPutReturnsDTO input) {
		OutputPutReturnsDTO result = new OutputPutReturnsDTO();
		ValueDeclarationJPA factura = new ValueDeclarationJPA();
		OutputDataDTO data = new OutputDataDTO();
		List<DeclarationLineJPA> listaLineas = new ArrayList<>();
		List<EnvaseDeclaracionJPA> listaEnvases = new ArrayList<>();

		try {
			/*
			 * CABECERA 
			 */
			factura.setValueDeclarationNumber(putReturnsDAO.getDeclarationNumber());
			factura.setValueDeclarationYear(Calendar.getInstance().get(Calendar.YEAR));
			factura.setValueDeclarationVersion(1);
			factura.setReturnNumber(input.getData().getHeader().getReturnIds().getReturnNumber());
			factura.setReturnYear(input.getData().getHeader().getReturnIds().getReturnYear());
			factura.setExpeditionDate(toDate(input.getData().getHeader().getReturnDate()));
			factura.setTargetId(input.getData().getHeader().getTarget().getId());
			factura.setSourceId(input.getData().getHeader().getSource().getId());
			factura.setSourceRegionId(putReturnsDAO.getProvincia(input.getData().getHeader().getSource().getRegionId()));
			factura.setDeliveryConditions("EXW");
			factura.setMcaFactura("F");
			factura.setMcaLastCurrent("S");
			factura.setMcaAutomaticLoading("S");
			factura.setMcaRightDV("N");
			factura.setMcaShipping("N");
			factura.setMcaDownload("N");
			factura.setReturnDate(toDate(input.getData().getHeader().getReturnDate()));
			factura.setInvoiceType("DEV");
			factura.setCreationDate(new Date());
			factura.setRecordCreationDate(new Date());
			factura.setApp(APP);
			factura.setUserId(input.getMetadata().getUserId());
			
			/*
			 * Líneas
			 */
			List<ItemListDTO> listado = input.getData().getItemList();

			if (listado != null && !listado.isEmpty()) {
				for (ItemListDTO tmp : listado) {
					DeclarationLineJPA lineaProducto = new DeclarationLineJPA();
					EnvaseDeclaracionJPA lineaEnvase = new EnvaseDeclaracionJPA();
					
					if (tmp.getItemTypeId().equals("PR")) {
						lineaProducto.setValueDeclaration(factura);
						lineaProducto.setItemId(Integer.parseInt(tmp.getItemId()));
						lineaProducto.setNamePackage(UNITARIO);
						putReturnsDAO.getValoresProducto(factura, lineaProducto, tmp);
						lineaProducto.setPackageQuantity(tmp.getPackageQuantity());
						lineaProducto.setNetWeight(tmp.getNetWeight());
						lineaProducto.setGrossWeight(tmp.getGrossWeight());
						lineaProducto.setQuantity(tmp.getQuantity());
						lineaProducto.setUnitPrice(tmp.getUnitPrice());
						lineaProducto.setTotalAmount(tmp.getTotalAmount());
						lineaProducto.setMcaError("N");
						lineaProducto.setCreationDate(new Date());
						lineaProducto.setApp(APP);
						lineaProducto.setUserId(input.getMetadata().getUserId());
						listaLineas.add(lineaProducto);
					}
					
					if (tmp.getItemTypeId().equals("EN")) {					
						lineaEnvase.setValueDeclaration(factura);
						lineaEnvase.setItemId(tmp.getItemId());
						lineaEnvase.setPackageName(UNITARIO);
						lineaEnvase.setPackageQuantity(tmp.getPackageQuantity());
						lineaEnvase.setQuantity(tmp.getQuantity());
						lineaEnvase.setQuantityUnit(tmp.getQuantityUnit());
						lineaEnvase.setNetWeight(tmp.getNetWeight());
						lineaEnvase.setNetWeightUnit(tmp.getNetWeightUnit());
						lineaEnvase.setGrossWeight(tmp.getGrossWeight());
						lineaEnvase.setGrossWeightUnit(tmp.getGrossWeightUnit());
						lineaEnvase.setUnitPrice(tmp.getUnitPrice());
						lineaEnvase.setUnitPriceCurrency(tmp.getUnitPriceCurrency());
						lineaEnvase.setTotalAmount(tmp.getTotalAmount());
						lineaEnvase.setTotalAmountCurrency(tmp.getTotalAmountCurrency());
						lineaEnvase.setItemTaric(putReturnsDAO.getTaric(tmp.getItemId(), factura));
						lineaEnvase.setMcaError("N");
						lineaEnvase.setCreationDate(new Date());
						lineaEnvase.setApp(APP);
						lineaEnvase.setUserId(input.getMetadata().getUserId());
						listaEnvases.add(lineaEnvase);
					}
				}
			}
			
			factura.setProductLines(listaLineas);
			factura.setItemLines(listaEnvases);
			
			putReturnsDAO.putReturns(factura);
			
			if (!putReturnsDAO.existeAlerta(factura)) {
				putReturnsDAO.generarAlerta("53", "COD_V_ENVASE", factura);
				putReturnsDAO.actualizarCabecera(factura, "S");
			}
			
			data.setValueDeclarationNumber(factura.getValueDeclarationNumber());
			data.setValueDeclarationYear(factura.getValueDeclarationYear());
			data.setValueDeclarationVersion(factura.getValueDeclarationVersion());

			result.setDatos(data);
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "putReturns", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		
		return result;	
	}
	
	private Date toDate(String fecha) {
		Date fechaEnvio = null;
		if (fecha != null) {
			try {
				fechaEnvio = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
			} catch (Exception e) {
				this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "toDate", e.getClass().getSimpleName(), e.getMessage());					
			}
		}
		
		return fechaEnvio;
	}

}