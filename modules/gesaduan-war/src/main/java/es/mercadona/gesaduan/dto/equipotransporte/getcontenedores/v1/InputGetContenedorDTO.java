package es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputGetContenedorDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Long codigoEquipo;
  	private String codigoCentroOrigen;
  	private String codigoCentroDestino;
  	private String mcaFacturado;
  	private String mcaCargasDivididas;
  	private Long codigoSuministro;
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}

	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}

	public String getCodigoCentroDestino() {
		return codigoCentroDestino;
	}

	public void setCodigoCentroDestino(String codigoCentroDestino) {
		this.codigoCentroDestino = codigoCentroDestino;
	}

	public String getMcaFacturado() {
		return mcaFacturado;
	}

	public void setMcaFacturado(String mcaFacturado) {
		this.mcaFacturado = mcaFacturado;
	}

	public String getMcaCargasDivididas() {
		return mcaCargasDivididas;
	}

	public void setMcaCargasDivididas(String mcaCargasDivididas) {
		this.mcaCargasDivididas = mcaCargasDivididas;
	}

	public Long getCodigoSuministro() {
		return codigoSuministro;
	}

	public void setCodigoSuministro(Long codigoSuministro) {
		this.codigoSuministro = codigoSuministro;
	}

}
