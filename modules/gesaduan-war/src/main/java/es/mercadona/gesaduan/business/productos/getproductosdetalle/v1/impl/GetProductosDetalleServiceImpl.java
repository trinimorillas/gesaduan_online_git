package es.mercadona.gesaduan.business.productos.getproductosdetalle.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.productos.getproductosdetalle.v1.GetProductosDetalleService;
import es.mercadona.gesaduan.dao.productos.getproductosdetalle.v1.GetProductosDetalleDAO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.InputProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.OutputProductosDetalleDTO;

public class GetProductosDetalleServiceImpl implements GetProductosDetalleService{

	@Inject
	private GetProductosDetalleDAO getProductosDetalleDao;
	
	@Override
	public OutputProductosDetalleDTO getProductosDetalle(InputProductosDetalleDTO input) {		
		return getProductosDetalleDao.getProductosDetalle(input);
	}
	
	

}
