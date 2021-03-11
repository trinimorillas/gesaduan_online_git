package es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class PuertoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private Long codigoPuerto;
	private String mcaPreferente;

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
	 * @return the mcaPreferente
	 */
	public String getMcaPreferente() {
		return mcaPreferente;
	}

	/**
	 * @param mcaPreferente the mcaPreferente to set
	 */
	public void setMcaPreferente(String mcaPreferente) {
		this.mcaPreferente = mcaPreferente;
	}

}
