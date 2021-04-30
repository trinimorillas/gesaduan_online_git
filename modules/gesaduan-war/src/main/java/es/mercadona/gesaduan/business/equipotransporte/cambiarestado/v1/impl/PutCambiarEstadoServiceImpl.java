package es.mercadona.gesaduan.business.equipotransporte.cambiarestado.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.cambiarestado.v1.PutCambiarEstadoService;
import es.mercadona.gesaduan.dao.equipotransporte.cambiarestado.v1.PutCambiarEstadoDAO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.EquipoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.restfull.DatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;

public class PutCambiarEstadoServiceImpl implements PutCambiarEstadoService {

	@Inject
	private PutCambiarEstadoDAO putCambiarEstadoDao;
	
	@Override
	public OutputCambiarEstadoDTO cambiarEstado(InputDatosCambiarEstadoDTO input) {
		
		OutputCambiarEstadoDTO result = new OutputCambiarEstadoDTO();
		DatosCambiarEstadoDTO datos = new DatosCambiarEstadoDTO();
		
		boolean realizarCambio = true;
		
		if (input.getDatos().getCodigoEstado() == 1) {
			List<EquipoDTO> equipos = putCambiarEstadoDao.comprobarEquiposDosierGenerado(input.getDatos().getEquipo());
			if (equipos != null) {
				String resultadoValidacion = "No se puede cambiar a pendiente Equipos asociados a un Dosier.";
				datos.setResultadoValidacion(resultadoValidacion);
				datos.setCambioEstado("N");
				datos.setEquipo(equipos);
				realizarCambio = false;
			}
		} else {
			if ((input.getDatos().getCodigoEstado() == 2 || input.getDatos().getCodigoEstado() == 3) && input.getDatos().getForzarCambio().equals("N")) {
				List<EquipoDTO> equipos = putCambiarEstadoDao.comprobarPedidosSinValidar(input.getDatos().getEquipo());
				if (equipos != null) {
					String resultadoValidacion = "No se deben marcar como facturados o cargados Equipos con Cargas que tienen Pedidos sin validar.";
					datos.setResultadoValidacion(resultadoValidacion);
					datos.setCambioEstado("N");
					datos.setEquipo(equipos);
					realizarCambio = false;
				}
			}
		}

		if (realizarCambio) {
			putCambiarEstadoDao.actualizarEstados(input);
			
			if (input.getDatos().getCodigoEstado() == 1) {
				putCambiarEstadoDao.confirmarPlanEmbarque(input);
				putCambiarEstadoDao.asignarCarga(input);
			} else if (input.getDatos().getCodigoEstado() == 2) {
				putCambiarEstadoDao.facturarPlanEmbarque(input);
				putCambiarEstadoDao.facturarCarga(input);
			} else if (input.getDatos().getCodigoEstado() == 3) {
				putCambiarEstadoDao.cargarPlanEmbarque(input);
				putCambiarEstadoDao.cargarCarga(input);
			}
			
			datos.setCambioEstado("S");
		}
		
		result.setDatos(datos);
		return result;
	}
}
