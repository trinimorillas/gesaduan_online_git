package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPlanEmbarqueDetalleDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    
    private Long codigoEmbarque;
	private String fechaEmbarque;
	private Integer codigoBloqueOrigen;
	private String nombreBloqueOrigen;
	private Integer codigoPuertoEmbarque;
	private String nombrePuertoEmbarque;
	private Integer codigoPuertoDesembarque;
	private String nombrePuertoDesembarque;
	private String codigoNaviera;
	private String nombreNaviera;
	private Integer numeroEquipos;
	private Integer codigoEstado;
	private String nombreEstado;
	private String codigoUsuarioValidacion;
	private List<EquipoDTO> equipo;
	private List<DosierDTO> dosier;	

	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}

	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

	public String getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}

	public Integer getCodigoBloqueOrigen() {
		return codigoBloqueOrigen;
	}

	public void setCodigoBloqueOrigen(Integer codigoBloqueOrigen) {
		this.codigoBloqueOrigen = codigoBloqueOrigen;
	}

	public String getNombreBloqueOrigen() {
		return nombreBloqueOrigen;
	}

	public void setNombreBloqueOrigen(String nombreBloqueOrigen) {
		this.nombreBloqueOrigen = nombreBloqueOrigen;
	}

	public Integer getCodigoPuertoEmbarque() {
		return codigoPuertoEmbarque;
	}

	public void setCodigoPuertoEmbarque(Integer codigoPuertoEmbarque) {
		this.codigoPuertoEmbarque = codigoPuertoEmbarque;
	}

	public String getNombrePuertoEmbarque() {
		return nombrePuertoEmbarque;
	}

	public void setNombrePuertoEmbarque(String nombrePuertoEmbarque) {
		this.nombrePuertoEmbarque = nombrePuertoEmbarque;
	}

	public Integer getCodigoPuertoDesembarque() {
		return codigoPuertoDesembarque;
	}

	public void setCodigoPuertoDesembarque(Integer codigoPuertoDesembarque) {
		this.codigoPuertoDesembarque = codigoPuertoDesembarque;
	}

	public String getNombrePuertoDesembarque() {
		return nombrePuertoDesembarque;
	}

	public void setNombrePuertoDesembarque(String nombrePuertoDesembarque) {
		this.nombrePuertoDesembarque = nombrePuertoDesembarque;
	}

	public String getCodigoNaviera() {
		return codigoNaviera;
	}

	public void setCodigoNaviera(String codigoNaviera) {
		this.codigoNaviera = codigoNaviera;
	}

	public String getNombreNaviera() {
		return nombreNaviera;
	}

	public void setNombreNaviera(String nombreNaviera) {
		this.nombreNaviera = nombreNaviera;
	}

	public Integer getNumeroEquipos() {
		return numeroEquipos;
	}

	public void setNumeroEquipos(Integer numeroEquipos) {
		this.numeroEquipos = numeroEquipos;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getCodigoUsuarioValidacion() {
		return codigoUsuarioValidacion;
	}

	public void setCodigoUsuarioValidacion(String codigoUsuarioValidacion) {
		this.codigoUsuarioValidacion = codigoUsuarioValidacion;
	}

	public List<EquipoDTO> getEquipo() {
		return equipo;
	}

	public void setEquipo(List<EquipoDTO> equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the dosier
	 */
	public List<DosierDTO> getDosier() {
		return dosier;
	}

	/**
	 * @param dosier the dosier to set
	 */
	public void setDosier(List<DosierDTO> dosier) {
		this.dosier = dosier;
	}
	
	

}
