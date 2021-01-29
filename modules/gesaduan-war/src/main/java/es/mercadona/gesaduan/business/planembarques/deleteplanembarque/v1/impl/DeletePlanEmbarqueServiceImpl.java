package es.mercadona.gesaduan.business.planembarques.deleteplanembarque.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.planembarques.deleteplanembarque.v1.DeletePlanEmbarqueService;
import es.mercadona.gesaduan.dao.planembarques.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dao.planembarques.deleteplanembarque.v1.DeletePlanEmbarqueDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputDatosDeletePlanEmbarqueDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class DeletePlanEmbarqueServiceImpl implements DeletePlanEmbarqueService {
	
	@Inject
	private DeletePlanEmbarqueDAO deletePlanEmbarqueDao;
	
	@Inject
	private CambiarEstadoDAO cambiarEstadoDao;

	@Override
	public void deletePlanEmbarque(InputDatosDeletePlanEmbarqueDTO datos) throws GesaduanException {		
		if (cambiarEstadoDao.getEstadoActual(datos.getDatos().getCodigoEmbarque()) != 1) {
			throw new GesaduanException(EnumGesaduanException.ESTADO_NO_EN_CURSO);
		} else {
			actualizarDatosCarga(datos.getDatos().getCodigoEmbarque());
			List<CargaDTO> listaCarga = deletePlanEmbarqueDao.getCargasPlanEmbarque(datos.getDatos().getCodigoEmbarque());
			deletePlanEmbarqueDao.eliminarRelacionContenedorEquipo(datos);
			eliminarRelacionCargaEquipo(datos.getDatos().getCodigoEmbarque());
			deletePlanEmbarqueDao.eliminarCargasAbortadas(listaCarga);
			reordenarDivisiones();			
			deletePlanEmbarqueDao.borrarPlanEmbarque(datos.getDatos());		
		}		
	}
	
	@Override
	public void actualizarDatosCarga(Long codigoEmbarque) {
		deletePlanEmbarqueDao.actualizarDatosCarga(codigoEmbarque);
	}
	
	@Override
	public void eliminarRelacionCargaEquipo(Long codigoEmbarque) {
		deletePlanEmbarqueDao.eliminarRelacionCargaEquipo(codigoEmbarque);
	}
	
	@Override
	public void reordenarDivisiones() {
		deletePlanEmbarqueDao.reordenarDivisiones();
	}

}
