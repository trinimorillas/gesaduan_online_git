package es.mercadona.gesaduan.business.equipotransporte.movercargas.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.movercargas.v1.PutMoverCargasService;
import es.mercadona.gesaduan.business.planembarques.deleteplanembarque.v1.DeletePlanEmbarqueService;
import es.mercadona.gesaduan.dao.equipotransporte.movercargas.v1.PutMoverCargasDAO;
import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.CargasDTO;
import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.InputDatosMoverCargasDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class PutMoverCargasServiceImpl implements PutMoverCargasService {

	@Inject
	private PutMoverCargasDAO putMoverCargasDao;
	
	@Inject
	private DeletePlanEmbarqueService deletePlanEmbarqueService; 
	
	@Override
	public void moverCargas(InputDatosMoverCargasDTO datos) throws GesaduanException {
		if (putMoverCargasDao.comprobarEquipoCargado(datos.getDatos().getCodigoEquipoOrigen()) == 0)
			throw new GesaduanException(EnumGesaduanException.EQUIPO_ORIGEN_CARGADO);
		if (putMoverCargasDao.comprobarEquipoCargado(datos.getDatos().getCodigoEquipoDestino()) == 0)
			throw new GesaduanException(EnumGesaduanException.EQUIPO_DESTINO_CARGADO);
		if (putMoverCargasDao.comprobarCargaContenedorFacturado(datos) > 0)
			throw new GesaduanException(EnumGesaduanException.CARGA_CONTENEDOR_FACTURADO);
		
		List<CargasDTO> cargas = datos.getDatos().getCargas();
		for (CargasDTO carga : cargas) {
			if (putMoverCargasDao.comprobarRelacion(datos, carga) != 0) {
				putMoverCargasDao.actualizarRelacion(datos, carga);
				putMoverCargasDao.actualizarDivisiones(datos, carga);
				putMoverCargasDao.actualizarOcupacionEquipoOrigen(datos, carga);
				putMoverCargasDao.actualizarOcupacionEquipoDestino(datos, carga);
				putMoverCargasDao.borrarRelacionOrigen(datos.getDatos().getCodigoEquipoOrigen(), carga);
				deletePlanEmbarqueService.reordenarDivisiones();
			} else {
				putMoverCargasDao.actualizarOcupacionEquipoOrigen(datos, carga);
				putMoverCargasDao.actualizarOcupacionEquipoDestino(datos, carga);
				putMoverCargasDao.cambiarRelacion(datos, carga);				
			}
		}
		
		
	}
}
