package es.mercadona.gesaduan.business.equipotransporte.putrelacionequipocarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.putrelacionequipocarga.v1.PutRelacionEquipoCargaService;
import es.mercadona.gesaduan.dao.equipotransporte.deleteequipotransporte.v1.DeleteEquipoTransporteDAO;
import es.mercadona.gesaduan.dao.equipotransporte.putrelacionequipocarga.v1.PutRelacionEquipoCargaDAO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.CargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.InputDatosCrearRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class PutRelacionEquipoCargaServiceImpl implements PutRelacionEquipoCargaService {

	@Inject
	private PutRelacionEquipoCargaDAO putRelacionEquipoCargaDao;
	
	@Inject
	private DeleteEquipoTransporteDAO deleteEquipoTransporteDao;
	
	@Override
	public void putRelacionEquipoCarga(InputDatosCrearRelacionEquipoCargaDTO input) throws GesaduanException {
		if (deleteEquipoTransporteDao.getEstadoActual(input.getDatos().getCodigoEquipo()) != 1)
			throw new GesaduanException(EnumGesaduanException.ESTADO_NO_EN_CURSO);
		if (putRelacionEquipoCargaDao.cargasNoProcesadas(input) > 0)
			throw new GesaduanException(EnumGesaduanException.CARGA_NO_PROCESADA);
		
		for (CargaDTO carga : input.getDatos().getCarga()) {
			if (putRelacionEquipoCargaDao.comprobarRelaciones(input, carga) != 0) {
				putRelacionEquipoCargaDao.actualizarRelacion(input, carga);
				putRelacionEquipoCargaDao.actualizarDivisionesCarga(input, carga);
				putRelacionEquipoCargaDao.actualizarDivisionesRelacion(input, carga);
				putRelacionEquipoCargaDao.actualizarOcupacion(input.getDatos().getCodigoEquipo());
			}
			else {
				putRelacionEquipoCargaDao.putRelacionEquipoCarga(input, carga);
				putRelacionEquipoCargaDao.actualizarCarga(input, carga);
				putRelacionEquipoCargaDao.actualizarOcupacion(input.getDatos().getCodigoEquipo());
			}
		}		
	}
}