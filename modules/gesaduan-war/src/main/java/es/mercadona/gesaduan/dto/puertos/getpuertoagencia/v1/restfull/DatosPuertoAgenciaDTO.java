package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPuertoAgenciaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    
    private List<PuertoDTO> puertos;

	/**
	 * @return the puertos
	 */
	public List<PuertoDTO> getPuertos() {
		return puertos;
	}

	/**
	 * @param puertos the puertos to set
	 */
	public void setPuertos(List<PuertoDTO> puertos) {
		this.puertos = puertos;
	}

}
