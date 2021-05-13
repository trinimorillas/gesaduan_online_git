package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DVInsertPKDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numeroDecalaracion;
	private Integer anyo;
	private Integer version;

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getNumeroDecalaracion() {
		return numeroDecalaracion;
	}

	public void setNumeroDecalaracion(Integer numeroDecalaracion) {
		this.numeroDecalaracion = numeroDecalaracion;
	}

}