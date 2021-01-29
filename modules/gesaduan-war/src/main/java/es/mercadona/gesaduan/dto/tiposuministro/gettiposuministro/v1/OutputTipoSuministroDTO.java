package es.mercadona.gesaduan.dto.tiposuministro.gettiposuministro.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputTipoSuministroDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosTipoSuministroDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosTipoSuministroDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosTipoSuministroDTO> datos) {
		this.datos = datos;
	}

}