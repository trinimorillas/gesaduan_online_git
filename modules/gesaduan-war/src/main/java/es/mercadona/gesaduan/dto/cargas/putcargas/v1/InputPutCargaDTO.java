package es.mercadona.gesaduan.dto.cargas.putcargas.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CargaDTO> carga;
	
	public List<CargaDTO> getCarga() {
		return carga;
	}
	
	public void setCarga(List<CargaDTO> carga) {
		this.carga = carga;
	}

}
