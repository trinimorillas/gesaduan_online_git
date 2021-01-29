package es.mercadona.gesaduan.business.proveedores.getproveedores.v1.impl;

import javax.inject.Inject;


import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.proveedores.getproveedores.v1.GetProveedoresService;
import es.mercadona.gesaduan.dao.proveedores.getproveedores.v1.GetProveedoresDAO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.InputGetProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull.OutputGetProveedoresDTO;

public class GetProveedoresServiceImpl implements GetProveedoresService {

	@Inject
	private GetProveedoresDAO getProveedoresDAO;

	@Override
	public OutputGetProveedoresDTO getProveedoresList(InputGetProveedoresDTO bodata, BoPage paginacion) {

		OutputGetProveedoresDTO result = getProveedoresDAO.obtenerProveedores(bodata, paginacion);

		return result;

	}

}
