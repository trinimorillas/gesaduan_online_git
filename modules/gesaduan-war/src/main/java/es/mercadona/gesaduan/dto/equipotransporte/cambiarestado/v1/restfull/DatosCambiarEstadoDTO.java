package es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.EquipoDTO;

public class DatosCambiarEstadoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private String cambioEstado;
	private String resultadoValidacion;
	private List<EquipoDTO> equipo;
	
	public String getCambioEstado() {
		return cambioEstado;
	}

	public void setCambioEstado(String cambioEstado) {
		this.cambioEstado = cambioEstado;
	}

	public String getResultadoValidacion() {
		return resultadoValidacion;
	}
	
	public void setResultadoValidacion(String resultadoValidacion) {
		this.resultadoValidacion = resultadoValidacion;
	}
	
	public List<EquipoDTO> getEquipo() {
		return equipo;
	}
	
	public void setEquipo(List<EquipoDTO> equipo) {
		this.equipo = equipo;
	}	

}