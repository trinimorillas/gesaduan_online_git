package es.mercadona.gesaduan.business.dosier.getdosierdetalle.v1;

import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.OutputDosierDetalleDTO;

public interface GetDosierDetalleService {
	
	public OutputDosierDetalleDTO getDosierDetalle(InputDatosDetalleDTO input);
	
}
