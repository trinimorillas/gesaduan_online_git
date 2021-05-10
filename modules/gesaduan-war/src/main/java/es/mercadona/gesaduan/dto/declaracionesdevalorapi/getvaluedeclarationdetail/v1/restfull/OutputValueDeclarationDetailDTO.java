package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputValueDeclarationDetailDTO extends AbstractDTO {
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