package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class TargetDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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

}
