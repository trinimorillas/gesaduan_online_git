package es.mercadona.gesaduan.business.productos.getproductos.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.productos.getproductos.v1.GetProductosService;
import es.mercadona.gesaduan.dao.productos.getproductos.v1.GetProductosDAO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.InputGetProductoDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.OutputProductosDTO;

public class GetProductosServiceImpl implements GetProductosService {
	
	@Inject
	private GetProductosDAO getProductosDAO;

	@Override
	public OutputProductosDTO getProductos(InputGetProductoDTO input, BoPage pagination) {
		
		OutputProductosDTO result = getProductosDAO.getProductos(input, pagination);		
		return result;
		
	}
	
}
