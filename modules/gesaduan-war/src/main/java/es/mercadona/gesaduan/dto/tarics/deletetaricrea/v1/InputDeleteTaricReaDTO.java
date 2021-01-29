package es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDeleteTaricReaDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoTaric;
	private String codigoRea;
	private String fechaInicio;
	
	private InputDeleteMetadatosDTO metadatos;
	private InputPutDatosDTO datos;

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

	public InputDeleteMetadatosDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputDeleteMetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public InputPutDatosDTO getDatos() {
		return datos;
	}

	public void setDatos(InputPutDatosDTO datos) {
		this.datos = datos;
	}
	
	

}
