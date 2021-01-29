package es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputDatosComunesDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	MetadatosDTO metadatos;
	EstaDescargadoDTO datos;


	public MetadatosDTO getMetadatos() {
		return metadatos;
	}


	public void setMetadatos(MetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}


	public EstaDescargadoDTO getDatos() {
		return datos;
	}


	public void setDatos(EstaDescargadoDTO datos) {
		this.datos = datos;
	}


	
	
	
}
