package es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPutCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CargaOutputDTO> carga;
	
	public List<CargaOutputDTO> getCarga() {
		return carga;
	}
	
	public void setCarga(List<CargaOutputDTO> carga) {
		this.carga = carga;
	}
}