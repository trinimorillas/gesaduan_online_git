package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdetalle.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdetalle.v1.GetDVDetalleService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdetalle.v1.GetDVDetalleDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.InputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.OutputDeclaracionesDeValorDetalleDTO;

public class GetDVDetalleServiceImpl implements GetDVDetalleService {

	@Inject
	private GetDVDetalleDAO getDVDetalleDAO;

	@Override
	public OutputDeclaracionesDeValorDetalleDTO getDeclaracionesDeValorPorCodigoList(
			InputDeclaracionesDeValorDetalleDTO bodata, BoPage paginacion) {

		OutputDeclaracionesDeValorDetalleDTO result = this.getDVDetalleDAO.obtenerDeclaracionesPorCodigo(bodata,
				paginacion);

		Integer codDV = bodata.getCodigoDeclaracion();
		Integer version = bodata.getAnyo();
		
		if(result.getDatos() != null) {
			result.getDatos().getDeclaracionDeValor().setHistorico(this.getDVDetalleDAO.obtenerHistoricoDV(codDV, version));
			
			return result;
		}
		else {
			
			return null;
		}
		
	}

}
