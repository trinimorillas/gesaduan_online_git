package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputPutValueDeclarationDTO extends AbstractDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private DataDTO data;
	private MetadataDTO metadata;

	/**
	 * @return the data
	 */
	public DataDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(DataDTO data) {
		this.data = data;
	}

	/**
	 * @return the metadata
	 */
	public MetadataDTO getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(MetadataDTO metadata) {
		this.metadata = metadata;
	}
}