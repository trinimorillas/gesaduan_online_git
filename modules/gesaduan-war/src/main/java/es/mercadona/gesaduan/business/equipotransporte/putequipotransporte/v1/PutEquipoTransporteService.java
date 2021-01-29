package es.mercadona.gesaduan.business.equipotransporte.putequipotransporte.v1;

import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.restfull.OutputEquipoTransportePutDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface PutEquipoTransporteService {
	public OutputEquipoTransportePutDTO crearEquipoTransporte(InputDatosPutDTO input) throws GesaduanException;
	public OutputEquipoTransportePutDTO modificarEquipoTransporte(InputDatosPutDTO input) throws GesaduanException;
}