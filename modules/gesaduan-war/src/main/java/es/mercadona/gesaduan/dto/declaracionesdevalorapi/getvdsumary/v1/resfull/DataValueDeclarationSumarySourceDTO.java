package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataValueDeclarationSumarySourceDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String  id;
	private String  name;
	private String  regionId;
	private String  typeId;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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