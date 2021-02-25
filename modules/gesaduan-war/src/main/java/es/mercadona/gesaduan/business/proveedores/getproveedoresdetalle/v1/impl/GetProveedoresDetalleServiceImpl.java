package es.mercadona.gesaduan.business.proveedores.getproveedoresdetalle.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.proveedores.getproveedoresdetalle.v1.GetProveedoresDetalleService;
import es.mercadona.gesaduan.dao.proveedores.getproveedoresdetalle.v1.GetProveedoresDetalleDAO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.InputProveedoresDetalleDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.OutputProveedoresDetalleDTO;

public class GetProveedoresDetalleServiceImpl implements GetProveedoresDetalleService{

	@Inject
	private GetProveedoresDetalleDAO getProveedoresDetalleDao;
	
	@Override
	public OutputProveedoresDetalleDTO getProveedoresDetalle(InputProveedoresDetalleDTO input) {
		
		OutputProveedoresDetalleDTO result = getProveedoresDetalleDao.getProveedoresDetalle(input);
		
		return result;
	}

	@Override
	public String getCodigoProveedorSap(String codigoProveedor) {
		
		return getProveedoresDetalleDao.getCodigoProveedorSap(codigoProveedor);
		
	}

}
