package es.mercadona.gesaduan.business.equipotransporte.deleteequipotransporte.v1;

import es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1.InputDatosBorrarEquipoTransporteDTO;
import es.mercadona.gesaduan.exception.GesaduanException;


public interface DeleteEquipoTransporteService {	
	public void deleteEquipoTransporte(InputDatosBorrarEquipoTransporteDTO datos) throws GesaduanException;
}
