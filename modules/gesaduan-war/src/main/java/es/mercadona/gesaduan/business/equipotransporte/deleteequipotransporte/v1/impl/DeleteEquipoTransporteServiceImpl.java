package es.mercadona.gesaduan.business.equipotransporte.deleteequipotransporte.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.deleteequipotransporte.v1.DeleteEquipoTransporteService;
import es.mercadona.gesaduan.business.planembarques.deleteplanembarque.v1.DeletePlanEmbarqueService;
import es.mercadona.gesaduan.dao.equipotransporte.deleteequipotransporte.v1.DeleteEquipoTransporteDAO;
import es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1.InputDatosBorrarEquipoTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class DeleteEquipoTransporteServiceImpl implements DeleteEquipoTransporteService {
	
	@Inject
	private DeleteEquipoTransporteDAO deleteEquipoTransporteDao;
	
	@Inject
	private DeletePlanEmbarqueService deletePlanEmbarqueService;

	@Override
	public void deleteEquipoTransporte(InputDatosBorrarEquipoTransporteDTO datos) throws GesaduanException {
		if (deleteEquipoTransporteDao.getEstadoActual(datos.getDatos().getCodigoEquipo()) != 1) throw new GesaduanException(EnumGesaduanException.ESTADO_NO_EN_CURSO);
		else {
			deleteEquipoTransporteDao.actualizarDatosCarga(datos.getDatos().getCodigoEquipo());
			List<CargaDTO> listaCarga = deleteEquipoTransporteDao.getCargasEquipo(datos.getDatos().getCodigoEquipo());
			deleteEquipoTransporteDao.eliminarRelacionContenedorEquipo(datos.getDatos().getCodigoEquipo(), datos.getMetadatos().getCodigoUsuario());
			deleteEquipoTransporteDao.eliminarRelacionCargaEquipo(datos.getDatos().getCodigoEquipo());
			deletePlanEmbarqueService.reordenarDivisiones();
			deleteEquipoTransporteDao.actualizarNumeroEquipos(datos.getDatos().getCodigoEquipo());
			deleteEquipoTransporteDao.eliminarCargasAbortadas(listaCarga);
			deleteEquipoTransporteDao.borrarEquipoTransporte(datos.getDatos());
		}
	}

}
