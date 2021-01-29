package es.mercadona.gesaduan.dao.equipotransporte.getequipostransporte.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.InputDatosEquipoTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.restfull.OutputEquiposTransporteDTO;

public interface GetEquiposTransporteDAO {
	
	public OutputEquiposTransporteDTO listarEquiposTransporte(InputDatosEquipoTransporteDTO input, BoPage pagination);
	
}
