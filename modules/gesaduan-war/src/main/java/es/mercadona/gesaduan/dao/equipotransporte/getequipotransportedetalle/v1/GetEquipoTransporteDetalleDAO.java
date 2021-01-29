package es.mercadona.gesaduan.dao.equipotransporte.getequipotransportedetalle.v1;

import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.InputEquipoTransporteDetalleDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.OutputEquipoTransporteDetalleDTO;

public interface GetEquipoTransporteDetalleDAO {
	
	public OutputEquipoTransporteDetalleDTO consultarEquipoTransporte(InputEquipoTransporteDetalleDTO input);
	
}
