package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class EquipoDTO extends AbstractDTO{
	
	private static final long serialVersionUID = 1L;	
	
    private Long codigoEquipo;
	private String matricula;
	
	/**
	 * @return the codigoEquipo
	 */
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	/**
	 * @param codigoEquipo the codigoEquipo to set
	 */
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	

}
