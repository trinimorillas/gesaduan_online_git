package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputVDSumaryDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<String,String> metadata;
	private List<DataVDSumaryDTO> data;
	
	/**
	 * @return the metadata
	 */
	public Map<String, String> getMetadata() {
		return metadata;
	}
	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	/**
	 * @return the data
	 */
	public List<DataVDSumaryDTO> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<DataVDSumaryDTO> data) {
		this.data = data;
	}
	

	
	
}
