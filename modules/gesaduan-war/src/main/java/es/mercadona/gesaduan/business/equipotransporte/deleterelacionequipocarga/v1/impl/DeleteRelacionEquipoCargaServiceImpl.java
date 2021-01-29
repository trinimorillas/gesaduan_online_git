package es.mercadona.gesaduan.business.equipotransporte.deleterelacionequipocarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.deleterelacionequipocarga.v1.DeleteRelacionEquipoCargaService;
import es.mercadona.gesaduan.dao.equipotransporte.deleteequipotransporte.v1.DeleteEquipoTransporteDAO;
import es.mercadona.gesaduan.dao.equipotransporte.deleterelacionequipocarga.v1.DeleteRelacionEquipoCargaDAO;
import es.mercadona.gesaduan.dao.planembarques.deleteplanembarque.v1.DeletePlanEmbarqueDAO;
import es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1.InputDatosEliminarRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class DeleteRelacionEquipoCargaServiceImpl implements DeleteRelacionEquipoCargaService {

	@Inject
	private DeleteRelacionEquipoCargaDAO deleteRelacionEquipoCargaDao;
	
	@Inject
	private DeleteEquipoTransporteDAO deleteEquipoTransporteDao;
	
	@Inject
	private DeletePlanEmbarqueDAO deletePlanEmbarqueDao;
	
	@Override
	public void deleteRelacionEquipoCarga(InputDatosEliminarRelacionEquipoCargaDTO input) throws GesaduanException {
		if (deleteEquipoTransporteDao.getEstadoActual(input.getDatos().getCodigoEquipo()) != 1)
			throw new GesaduanException(EnumGesaduanException.ESTADO_NO_EN_CURSO);
		deleteRelacionEquipoCargaDao.actualizarCargas(input);
		deleteRelacionEquipoCargaDao.eliminarRelacionContenedorEquipo(input);
		deleteRelacionEquipoCargaDao.deleteRelacionEquipoCarga(input);
		deletePlanEmbarqueDao.reordenarDivisiones();
		deleteRelacionEquipoCargaDao.deleteCargasAbortadas(input);		
		deleteRelacionEquipoCargaDao.actualizarOcupacion(input.getDatos().getCodigoEquipo());
	}

}