package es.mercadona.gesaduan.dto.dosier.putdosier.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDosierEquipoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Long codigoEquipo;
	private String matricula;
	private List<InputDosierEquipoContenedorDTO> contenedor;
	
	/**
	 * @return the codigEquipo
	 */
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	/**
	 * @param codigEquipo the codigEquipo to set
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
	/**
	 * @return the contenedor
	 */
	public List<InputDosierEquipoContenedorDTO> getContenedor() {
		return contenedor;
	}
	/**
	 * @param contenedor the contenedor to set
	 */
	public void setContenedor(List<InputDosierEquipoContenedorDTO> contenedor) {
		this.contenedor = contenedor;
	}

	

}
