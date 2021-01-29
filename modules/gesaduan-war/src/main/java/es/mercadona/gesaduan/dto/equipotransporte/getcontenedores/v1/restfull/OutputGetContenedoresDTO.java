package es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputGetContenedoresDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private DatosGetCargasContenedoresDTO datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosGetCargasContenedoresDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosGetCargasContenedoresDTO datos) {
		this.datos = datos;
	}

}
