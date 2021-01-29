package es.mercadona.gesaduan.dao.equipotransporte.putequipotransporte.v1;

import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.restfull.OutputEquipoTransportePutDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public interface PutEquipoTransporteDAO {	
	public OutputEquipoTransportePutDTO crearEquipoTransporte(EquipoTransporteJPA input);
	public OutputEquipoTransportePutDTO modificarEquipoTransporte(EquipoTransporteJPA input);
	public void actualizarNumeroEquipos(Long codigoEmbarque);
}
