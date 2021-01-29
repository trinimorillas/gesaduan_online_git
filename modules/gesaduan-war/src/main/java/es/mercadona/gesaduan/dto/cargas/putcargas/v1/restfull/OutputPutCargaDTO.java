package es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPutCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private DatosPutCargaDTO datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosPutCargaDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosPutCargaDTO datos) {
		this.datos = datos;
	}

}