package es.mercadona.gesaduan.business.tipocarga.gettipocarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.tipocarga.gettipocarga.v1.GetTipoCargaService;
import es.mercadona.gesaduan.dao.tipocarga.gettipocarga.v1.GetTipoCargaDAO;
import es.mercadona.gesaduan.dto.tipocarga.gettipocarga.v1.OutputTipoCargaDTO;

public class GetTipoCargaServiceImpl implements GetTipoCargaService {

	@Inject
	private GetTipoCargaDAO getTipoCargaDao;
	
	public OutputTipoCargaDTO listarTipoCarga() {
		
		OutputTipoCargaDTO result = getTipoCargaDao.listarTipoCarga();

		return result;
		
	}

}