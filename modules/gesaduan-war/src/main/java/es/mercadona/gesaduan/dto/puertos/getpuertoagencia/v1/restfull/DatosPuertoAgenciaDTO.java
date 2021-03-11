package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPuertoAgenciaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    
    private List<PuertoDTO> puerto;

	/**
	 * @return the puertos
	 */
	public List<PuertoDTO> getPuerto() {
		return puerto;
	}

	/**
	 * @param puertos the puertos to set
	 */
	public void setPuerto(List<PuertoDTO> puerto) {
		this.puerto = puerto;
	}

}
