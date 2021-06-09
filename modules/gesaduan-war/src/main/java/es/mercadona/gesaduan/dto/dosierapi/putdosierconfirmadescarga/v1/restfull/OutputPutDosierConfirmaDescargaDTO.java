package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPutDosierConfirmaDescargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String,String> metadatos;
	private OutputDatosDTO datos;

	public Map<String,String> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<String,String> metadatos) {
		this.metadatos = metadatos;
	}

	public OutputDatosDTO getDatos() {
		return datos;
	}

	public void setDatos(OutputDatosDTO datos) {
		this.datos = datos;
	}

}