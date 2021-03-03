package es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputCambiarEstadoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;		
	private DatosCambiarEstadoDTO datos;	
		
	public DatosCambiarEstadoDTO getDatos() {
		return datos;
	}
	
	public void setDatos(DatosCambiarEstadoDTO datos) {
		this.datos = datos;
	}

}
