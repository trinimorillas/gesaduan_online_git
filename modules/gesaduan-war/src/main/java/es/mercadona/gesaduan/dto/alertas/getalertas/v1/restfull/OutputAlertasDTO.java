package es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputAlertasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private List<DatosAlertasDTO> datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosAlertasDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosAlertasDTO> datos) {
		this.datos = datos;
	}
	
	

}
