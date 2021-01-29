package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputPostDeclaracionesDeValorDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	MetadatosDTO metadatos;
	DatosGeneralDTO datos;


	public MetadatosDTO getMetadatos() {
		return metadatos;
	}


	public void setMetadatos(MetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}


	public DatosGeneralDTO getDatos() {
		return datos;
	}


	public void setDatos(DatosGeneralDTO datos) {
		this.datos = datos;
	}
	
	
	

}
