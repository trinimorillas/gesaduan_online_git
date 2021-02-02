package es.mercadona.gesaduan.business.productos.putproductos.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.productos.putproductos.v1.PutProductosService;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.ActualizarProductosDAO;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.AsignarRelacionReaDAO;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.AsignarRelacionTaricDAO;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.ResolverAlertasDAO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.DatosProductosDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.InputMetadatosDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.InputPutProductosDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.restfull.OutputPutProductosDTO;

public class PutProductosServiceImpl implements PutProductosService{
	
	@Inject
	private ActualizarProductosDAO putProductosDao;

	@Inject
	private AsignarRelacionReaDAO asignarRelacionReaDao;
	
	@Inject
	private AsignarRelacionTaricDAO asignarRelacionTaricDao;
	
	@Inject
	private ResolverAlertasDAO resolverAlertasDao;
	
	@Override
	public OutputPutProductosDTO actualizarProducto(Long codigoProducto, InputPutProductosDTO input) {
				
		OutputPutProductosDTO response = new OutputPutProductosDTO();
		DatosProductosDTO datosProductos = new DatosProductosDTO();
		
		String descripcionAlt = null, descripcionFormato = null, nuevoCodigoRea = null;
		Long nuevoCodigoTaric = null;
		Long codigoTaric = (input.getDatos().getCodigoTaric() != null) ? Long.parseLong(input.getDatos().getCodigoTaric()) : null;
		String codigoRea = (input.getDatos().getCodigoRea() != null) ? input.getDatos().getCodigoRea() : null;
		String codigoUsuario = input.getMetadatos().getCodigoUsuario().toUpperCase();
		
		if(input.getDatos().getDenominacionAlt() != null) {
			descripcionAlt = input.getDatos().getDenominacionAlt();
			datosProductos.setDenominacionAlt(descripcionAlt);
		}
		
		if(input.getDatos().getFormatoVentaAlt() != null) {
			descripcionFormato = input.getDatos().getFormatoVentaAlt();
			datosProductos.setFormatoVentaAlt(descripcionFormato);
		}
		
		if(input.getDatos().getNuevoCodigoTaric() != null && !input.getDatos().getNuevoCodigoTaric().isEmpty()) {
			nuevoCodigoTaric = Long.parseLong(input.getDatos().getNuevoCodigoTaric());
			datosProductos.setCodigoTaric(String.valueOf(nuevoCodigoTaric));
		}
		
		if(input.getDatos().getNuevoCodigoRea() != null && !input.getDatos().getNuevoCodigoRea().isEmpty()) {
			nuevoCodigoRea = input.getDatos().getNuevoCodigoRea();
			datosProductos.setCodigoRea(nuevoCodigoRea);
		}
		
		if(descripcionAlt != null || descripcionFormato != null) {
			response = putProductosDao.actualizarProductos(codigoProducto, descripcionAlt, descripcionFormato, codigoUsuario);
		}
		
		if(input.getDatos().getNuevoCodigoTaric() != null) {
			asignarRelacionTaricDao.asigarTaricProducto(codigoTaric, codigoProducto, nuevoCodigoTaric, codigoUsuario);
			
			asignarRelacionReaDao.desasociarReaProducto(codigoProducto, codigoUsuario);
		}
		
		if (input.getDatos().getNuevoCodigoTaric() != null) {
			resolverAlertasDao.resolverAlertas(input);
		}
		
		if(input.getDatos().getNuevoCodigoRea() != null) {
			asignarRelacionReaDao.asignarReaProducto(codigoRea, codigoProducto, nuevoCodigoRea, codigoUsuario);
		}
		
		response.setDatos(datosProductos);
		
		return response;
	}
}
