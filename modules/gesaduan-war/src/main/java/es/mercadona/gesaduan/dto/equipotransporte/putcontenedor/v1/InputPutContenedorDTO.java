package es.mercadona.gesaduan.dto.equipotransporte.putcontenedor.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.ContenedorDTO;

public class InputPutContenedorDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Long codigoEquipo;
  	private List<ContenedorDTO> contenedor;
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public List<ContenedorDTO> getContenedor() {
		return contenedor;
	}

	public void setContenedor(List<ContenedorDTO> contenedor) {
		this.contenedor = contenedor;
	}

}
