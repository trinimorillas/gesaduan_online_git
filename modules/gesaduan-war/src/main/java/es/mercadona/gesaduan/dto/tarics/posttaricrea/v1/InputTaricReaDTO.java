package es.mercadona.gesaduan.dto.tarics.posttaricrea.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputTaricReaDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoTaric;
	private String codigoRea;
	private String fechaInicio;
	
	private InputMetadatosDTO metadatos;

	public String getCodigoTaric() {
		return codigoTaric;
	}

	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
	}

	public String getCodigoRea() {
		return codigoRea;
	}

	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}

	public InputMetadatosDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	

}
