package es.mercadona.gesaduan.business.planembarques.cambiarestado.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.planembarques.cambiarestado.v1.CambiarEstadoService;
import es.mercadona.gesaduan.dao.planembarques.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public class CambiarEstadoServiceImpl implements CambiarEstadoService {

	@Inject
	private CambiarEstadoDAO cambiarEstadoDao;
	
	@Override
	public OutputCambiarEstadoDTO cambiarEstado(InputDatosCambiarEstadoDTO input) throws GesaduanException {
		int codigoEstado = input.getDatos().getCodigoEstado();
		int codigoEstadoActual = cambiarEstadoDao.getEstadoActual(input.getDatos().getCodigoEmbarque());
		int numEquipos = cambiarEstadoDao.getEquipos(input.getDatos().getCodigoEmbarque());
		int numEquiposCargas = cambiarEstadoDao.getEquiposCargas(input.getDatos().getCodigoEmbarque());
		PlanEmbarquesJPA datos = new PlanEmbarquesJPA();
		
		if (codigoEstadoActual == 1) {
			if (codigoEstado != 2) throw new GesaduanException(EnumGesaduanException.ESTADO_INCORRECTO);
		} else if (codigoEstado != 1) throw new GesaduanException(EnumGesaduanException.ESTADO_INCORRECTO);
		
		if (codigoEstado == 2) {
			if (numEquipos == 0) throw new GesaduanException(EnumGesaduanException.PLAN_EMBARQUE_SIN_EQUIPOS);
			if (numEquiposCargas != 0) throw new GesaduanException(EnumGesaduanException.EQUIPO_SIN_CARGAS);
		}
		
		if (codigoEstado == 2) {
			if (cambiarEstadoDao.getEquiposNoCargados(input.getDatos().getCodigoEmbarque()) == 0) codigoEstado = 3;
			datos.setUsuarioValidacion(input.getMetadatos().getCodigoUsuario());
		}		
		
		datos.setCodigoEmbarque(input.getDatos().getCodigoEmbarque());
		datos.setEstado(codigoEstado);
		datos.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		
		OutputCambiarEstadoDTO result = cambiarEstadoDao.cambiarEstado(datos);

		return result;		
	}

}
