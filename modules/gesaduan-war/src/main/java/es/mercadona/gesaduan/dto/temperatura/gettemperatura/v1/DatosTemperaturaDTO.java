package es.mercadona.gesaduan.dto.temperatura.gettemperatura.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosTemperaturaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoTemperatura;
	private String valorTemperatura;
	
	public Integer getCodigoTemperatura() {
		return codigoTemperatura;
	}
	
	public void setCodigoTemperatura(Integer codigoTemperatura) {
		this.codigoTemperatura = codigoTemperatura;
	}
	
	public String getValorTemperatura() {
		return valorTemperatura;
	}
	
	public void setValorTemperatura(String valorTemperatura) {
		this.valorTemperatura = valorTemperatura;
	}	
	
}