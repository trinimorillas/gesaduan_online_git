package es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosEquipoCargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoEquipo;
	private List<CargaDTO> carga;
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public List<CargaDTO> getCarga() {
		return carga;
	}

	public void setCarga(List<CargaDTO> carga) {
		this.carga = carga;
	}

}