package es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CargaOutputDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String codigoCarga;
	private String resultadoOK;
	private String mensajeResultado;
	
	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}

	public String getResultadoOK() {
		return resultadoOK;
	}

	public void setResultadoOK(String resultadoOK) {
		this.resultadoOK = resultadoOK;
	}

	public String getMensajeResultado() {
		return mensajeResultado;
	}

	public void setMensajeResultado(String mensajeResultado) {
		this.mensajeResultado = mensajeResultado;
	}

}
