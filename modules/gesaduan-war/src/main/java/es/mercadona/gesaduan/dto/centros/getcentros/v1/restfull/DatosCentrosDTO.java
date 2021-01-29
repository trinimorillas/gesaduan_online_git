package es.mercadona.gesaduan.dto.centros.getcentros.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosCentrosDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private String codigoCentro;
	private String nombreLargo;
	
	public String getCodigoCentro() {
		return codigoCentro;
	}
	
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	
}