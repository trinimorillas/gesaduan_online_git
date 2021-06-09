package es.mercadona.gesaduan.business.dosier.cambiarestado.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.dosier.cambiarestado.v1.CambiarEstadoService;
import es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierPkJPA;

public class CambiarEstadoServiceImpl implements CambiarEstadoService {

	@Inject
	private CambiarEstadoDAO cambiarEstadoDao;

	@Override
	public OutputCambiarEstadoDTO cambiarEstado(InputDatosCambiarEstadoDTO input) throws GesaduanException {

		DosierJPA dosierJPA = new DosierJPA();
		DosierPkJPA dosierPkJPA = new DosierPkJPA();

		dosierPkJPA.setNumDosier(input.getDatos().getNumDosier());
		dosierPkJPA.setAnyoDosier(input.getDatos().getAnyoDosier());
		dosierJPA.setId(dosierPkJPA);
		dosierJPA.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario());

		Boolean tieneErrores = cambiarEstadoDao.tieneErrorDosier(dosierJPA);
		if (!tieneErrores) { // Generar fichero PDF
			cambiarEstadoDao.generarPDF(dosierJPA);
		}
		
		// Borrar alertas y notificaciones
		cambiarEstadoDao.eliminarAlertas(dosierJPA);
		
		// Actualiza contenedores asociados al dosier
		cambiarEstadoDao.actualizaContenedores(dosierJPA);

		// Actualiza el estado de la documentacion equipos
		cambiarEstadoDao.actualizaEquipos(dosierJPA);

		// Elimina asociación con los equipos
		/* cambiarEstadoDao.eliminaRelacionEquipo(dosierJPA); */
		
		if (!tieneErrores) {// Insertar alerta dosier inválido
			cambiarEstadoDao.insertarAlerta(dosierJPA, input.getMetadatos().getCodigoUsuario());
		}

		// Cambia el estado del dosier
		return cambiarEstadoDao.cambiarEstado(dosierJPA);
	}

}