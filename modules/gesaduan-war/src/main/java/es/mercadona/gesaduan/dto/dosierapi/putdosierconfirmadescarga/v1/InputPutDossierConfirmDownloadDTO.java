package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutDossierConfirmDownloadDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	//private MetadataDTO metadata;
	private InputDataDTO data;

	/*
	public MetadataDTO getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataDTO metadata) {
		this.metadata = metadata;
	}
	*/

	public InputDataDTO getData() {
		return data;
	}

	public void setData(InputDataDTO data) {
		this.data = data;
	}

}