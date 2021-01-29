package es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private String codigoCarga;
    private String codigoCentroOrigen;
	private Integer numeroDivision;
	private Double numeroHuecosOcupados;
	private Double numeroPesoOcupado;
	
	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}

	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}

	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}

	public Integer getNumeroDivision() {
		return numeroDivision;
	}

	public void setNumeroDivision(Integer numeroDivision) {
		this.numeroDivision = numeroDivision;
	}

	public Double getNumeroHuecosOcupados() {
		return numeroHuecosOcupados;
	}

	public void setNumeroHuecosOcupados(Double numeroHuecosOcupados) {
		this.numeroHuecosOcupados = numeroHuecosOcupados;
	}

	public Double getNumeroPesoOcupado() {
		return numeroPesoOcupado;
	}

	public void setNumeroPesoOcupado(Double numeroPesoOcupado) {
		this.numeroPesoOcupado = numeroPesoOcupado;
	}
	
}
