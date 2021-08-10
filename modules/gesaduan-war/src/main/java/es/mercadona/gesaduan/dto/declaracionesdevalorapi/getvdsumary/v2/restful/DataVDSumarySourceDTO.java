package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataVDSumarySourceDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String  publicId;
	private String  name;
	private String  regionId;
	private String  typeId;
	
	/**
	 * @return the id
	 */
	public String getPublicId() {
		return publicId;
	}
	/**
	 * @param id the id to set
	 */
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the regionId
	 */
	public String getRegionId() {
		return regionId;
	}
	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	/**
	 * @return the typeId
	 */
	public String getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}	


	
}
