package es.mercadona.gesaduan.dto.alertas.putalertas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutAlertasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MetadatosAlertasDTO metadatos;
	private DatosAlertasDTO datos;
	
	public MetadatosAlertasDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(MetadatosAlertasDTO metadatos) {
		this.metadatos = metadatos;
	}
	public DatosAlertasDTO getDatos() {
		return datos;
	}
	public void setDatos(DatosAlertasDTO datos) {
		this.datos = datos;
	}
	
	

}
