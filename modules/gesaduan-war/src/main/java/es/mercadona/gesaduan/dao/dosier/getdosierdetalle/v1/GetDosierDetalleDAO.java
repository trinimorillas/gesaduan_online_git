package es.mercadona.gesaduan.dao.dosier.getdosierdetalle.v1;

import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.OutputDosierDetalleDTO;

public interface GetDosierDetalleDAO {
	
	public OutputDosierDetalleDTO consultarDosier(InputDatosDetalleDTO input);
	
}
