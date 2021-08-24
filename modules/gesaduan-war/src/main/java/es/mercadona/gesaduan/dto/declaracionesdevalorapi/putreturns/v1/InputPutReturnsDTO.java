package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutReturnsDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private MetadataDTO metadata;
	private InputDataPutReturnsDTO data;

	public MetadataDTO getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataDTO metadata) {
		this.metadata = metadata;
	}

	public InputDataPutReturnsDTO getData() {
		return data;
	}

	public void setData(InputDataPutReturnsDTO data) {
		this.data = data;
	}

}
