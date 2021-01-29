package es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DeclaracionesDeValorEstadoDescargaDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigoDeclaracion;
	private Integer anyo;
	private Integer version;
	
	
	public Integer getCodigoDeclaracion() {
		return codigoDeclaracion;
	}
	public void setCodigoDeclaracion(Integer codigoDeclaracion) {
		this.codigoDeclaracion = codigoDeclaracion;
	}
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

	


	
	
	

}
