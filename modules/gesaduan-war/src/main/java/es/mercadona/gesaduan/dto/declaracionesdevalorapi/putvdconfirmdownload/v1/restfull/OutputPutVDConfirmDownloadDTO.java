package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPutVDConfirmDownloadDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String,String> metadatos;
	private OutputDataDTO datos;

	public Map<String, String> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<String,String> metadatos) {
		this.metadatos = metadatos;
	}

	public OutputDataDTO getDatos() {
		return datos;
	}

	public void setDatos(OutputDataDTO datos) {
		this.datos = datos;
	}

}