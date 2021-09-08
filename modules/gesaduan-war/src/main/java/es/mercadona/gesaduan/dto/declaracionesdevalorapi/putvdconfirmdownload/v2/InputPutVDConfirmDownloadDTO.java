package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutVDConfirmDownloadDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private MetadataDTO metadatos;
	private InputDataDTO data;

	public MetadataDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(MetadataDTO metadatos) {
		this.metadatos = metadatos;
	}

	public InputDataDTO getData() {
		return data;
	}

	public void setData(InputDataDTO data) {
		this.data = data;
	}

}
