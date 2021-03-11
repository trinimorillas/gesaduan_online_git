package es.mercadona.gesaduan.business.proveedores.putpuertoagencia.v1;

import es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1.InputDatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface PutPuertoAgenciaService {
	
	public void putPuertoAgencia(InputDatosPuertoAgenciaDTO datos) throws GesaduanException;

}