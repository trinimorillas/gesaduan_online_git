package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputPutVDDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private DVInsertPKDTO datos;
	private MetadataDTO metadata;

	/**
	 * @return the datos
	 */
	public DVInsertPKDTO getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DVInsertPKDTO datos) {
		this.datos = datos;
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
