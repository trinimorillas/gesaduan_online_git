package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class EquipoSimpleDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private Long codigoEquipo;
	private String matricula;

	public Long getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
