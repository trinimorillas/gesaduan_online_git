package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPutFacturaConfirmaDescargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<?, ?> metadatos;
	private OutputDatosDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public OutputDatosDTO getDatos() {
		return datos;
	}

	public void setDatos(OutputDatosDTO datos) {
		this.datos = datos;
	}

}