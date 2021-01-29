package es.mercadona.gesaduan.dto.puertos.getpuertos.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPuertosDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoPuerto;
	private String nombrePuerto;
	
	public Integer getCodigoPuerto() {
		return codigoPuerto;
	}
	
	public void setCodigoPuerto(Integer codigoPuerto) {
		this.codigoPuerto = codigoPuerto;
	}
	
	public String getNombrePuerto() {
		return nombrePuerto;
	}
	
	public void setNombrePuerto(String nombrePuerto) {
		this.nombrePuerto = nombrePuerto;
	}	

}