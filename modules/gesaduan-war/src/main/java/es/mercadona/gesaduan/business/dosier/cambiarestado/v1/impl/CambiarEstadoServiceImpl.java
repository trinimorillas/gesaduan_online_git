package es.mercadona.gesaduan.business.dosier.cambiarestado.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.dosier.cambiarestado.v1.CambiarEstadoService;
import es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;

public class CambiarEstadoServiceImpl implements CambiarEstadoService {

	@Inject
	private CambiarEstadoDAO cambiarEstadoDao;
	
	@Override
	public OutputCambiarEstadoDTO cambiarEstado(InputDatosCambiarEstadoDTO input) throws GesaduanException {
		
		DosierJPA dosierJPA = new DosierJPA();
		
		dosierJPA.setNumDosier(input.getDatos().getNumDosier());
		dosierJPA.setAnyoDosier(input.getDatos().getAnyoDosier());
		dosierJPA.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario());		
		
		// actualiza contenedores asociados al dosier
		cambiarEstadoDao.actualizaContenedores(dosierJPA);
		
		// actualiza el estado de la documentacion equipos
		cambiarEstadoDao.actualizaEquipos(dosierJPA);
		
		// elimina asociación con los equipos
		cambiarEstadoDao.eliminaRelacionEquipo(dosierJPA);
		
		// cambia el estado del dosier
		return cambiarEstadoDao.cambiarEstado(dosierJPA);
	}

}
