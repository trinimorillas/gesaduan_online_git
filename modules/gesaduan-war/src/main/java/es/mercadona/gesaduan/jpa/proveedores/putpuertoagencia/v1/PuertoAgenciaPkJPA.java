package es.mercadona.gesaduan.jpa.proveedores.putpuertoagencia.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PuertoAgenciaPkJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "COD_N_PUERTO")
	private Long codigoPuerto;

	@Column(name = "COD_V_AGENCIA_ADUANA")
	private String codigoAgencia;

	/**
	 * @return the codigoPuerto
	 */
	public Long getCodigoPuerto() {
		return codigoPuerto;
	}

	/**
	 * @param codigoPuerto the codigoPuerto to set
	 */
	public void setCodigoPuerto(Long codigoPuerto) {
		this.codigoPuerto = codigoPuerto;
	}

	/**
	 * @return the codigoAgencia
	 */
	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * @param codigoAgencia the codigoAgencia to set
	 */
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

}
