package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutDosierConfirmaDescargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private MetadatosDTO metadatos;
	private InputDatosDTO datos;

	public MetadatosDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(MetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}

	public InputDatosDTO getDatos() {
		return datos;
	}

	public void setDatos(InputDatosDTO datos) {
		this.datos = datos;
	}

}