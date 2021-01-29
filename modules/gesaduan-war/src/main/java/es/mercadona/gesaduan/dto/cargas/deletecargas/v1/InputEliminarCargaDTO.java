package es.mercadona.gesaduan.dto.cargas.deletecargas.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputEliminarCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<EliminarCargaDTO> carga;
	
	public List<EliminarCargaDTO> getCarga() {
		return carga;
	}
	
	public void setCarga(List<EliminarCargaDTO> carga) {
		this.carga = carga;
	}

}
