package es.mercadona.gesaduan.business.cargas.getpedidoscarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.cargas.getpedidoscarga.v1.GetPedidosCargaService;
import es.mercadona.gesaduan.dao.cargas.getpedidoscarga.v1.GetPedidosCargaDAO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.InputDatosPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.restfull.OutputPedidosCargaDTO;

public class GetPedidosCargaServiceImpl implements GetPedidosCargaService {
	@Inject
	private GetPedidosCargaDAO getPedidosCargaDao;
	
	@Override
	public OutputPedidosCargaDTO listarPedidosCarga(InputDatosPedidosCargaDTO input) {		
		return getPedidosCargaDao.listarPedidosCarga(input);
	}
}