package es.mercadona.gesaduan.dto.tipocarga.gettipocarga.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputTipoCargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosTipoCargaDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosTipoCargaDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosTipoCargaDTO> datos) {
		this.datos = datos;
	}

}