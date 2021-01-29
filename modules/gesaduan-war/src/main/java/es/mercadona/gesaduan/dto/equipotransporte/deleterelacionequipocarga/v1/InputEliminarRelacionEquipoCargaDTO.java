package es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputEliminarRelacionEquipoCargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoEquipo;
	private List<EliminarCargaDTO> carga;
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public List<EliminarCargaDTO> getCarga() {
		return carga;
	}

	public void setCarga(List<EliminarCargaDTO> carga) {
		this.carga = carga;
	}

}
