package es.mercadona.gesaduan.dto.cargas.cambiarestado.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputCambiarEstadoCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CambiarEstadoCargaDTO> carga;
	
	public List<CambiarEstadoCargaDTO> getCarga() {
		return carga;
	}
	
	public void setCarga(List<CambiarEstadoCargaDTO> carga) {
		this.carga = carga;
	}

}