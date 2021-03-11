package es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPlanEmbarquesDTO extends AbstractDTO implements Serializable {

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
	private Integer numEquipEstadoDocuCompleta;
	private Integer codigoEstado;
	private String nombreEstado;
	private String codigoUsuarioValidacion;
	/**
	 * @return the codigoEmbarque
	 */
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	/**
	 * @param codigoEmbarque the codigoEmbarque to set
	 */
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}
	/**
	 * @return the fechaEmbarque
	 */
	public String getFechaEmbarque() {
		return fechaEmbarque;
	}
	/**
	 * @param fechaEmbarque the fechaEmbarque to set
	 */
	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	/**
	 * @return the codigoBloqueOrigen
	 */
	public Integer getCodigoBloqueOrigen() {
		return codigoBloqueOrigen;
	}
	/**
	 * @param codigoBloqueOrigen the codigoBloqueOrigen to set
	 */
	public void setCodigoBloqueOrigen(Integer codigoBloqueOrigen) {
		this.codigoBloqueOrigen = codigoBloqueOrigen;
	}
	/**
	 * @return the nombreBloqueOrigen
	 */
	public String getNombreBloqueOrigen() {
		return nombreBloqueOrigen;
	}
	/**
	 * @param nombreBloqueOrigen the nombreBloqueOrigen to set
	 */
	public void setNombreBloqueOrigen(String nombreBloqueOrigen) {
		this.nombreBloqueOrigen = nombreBloqueOrigen;
	}
	/**
	 * @return the codigoPuertoEmbarque
	 */
	public Integer getCodigoPuertoEmbarque() {
		return codigoPuertoEmbarque;
	}
	/**
	 * @param codigoPuertoEmbarque the codigoPuertoEmbarque to set
	 */
	public void setCodigoPuertoEmbarque(Integer codigoPuertoEmbarque) {
		this.codigoPuertoEmbarque = codigoPuertoEmbarque;
	}
	/**
	 * @return the nombrePuertoEmbarque
	 */
	public String getNombrePuertoEmbarque() {
		return nombrePuertoEmbarque;
	}
	/**
	 * @param nombrePuertoEmbarque the nombrePuertoEmbarque to set
	 */
	public void setNombrePuertoEmbarque(String nombrePuertoEmbarque) {
		this.nombrePuertoEmbarque = nombrePuertoEmbarque;
	}
	/**
	 * @return the codigoPuertoDesembarque
	 */
	public Integer getCodigoPuertoDesembarque() {
		return codigoPuertoDesembarque;
	}
	/**
	 * @param codigoPuertoDesembarque the codigoPuertoDesembarque to set
	 */
	public void setCodigoPuertoDesembarque(Integer codigoPuertoDesembarque) {
		this.codigoPuertoDesembarque = codigoPuertoDesembarque;
	}
	/**
	 * @return the nombrePuertoDesembarque
	 */
	public String getNombrePuertoDesembarque() {
		return nombrePuertoDesembarque;
	}
	/**
	 * @param nombrePuertoDesembarque the nombrePuertoDesembarque to set
	 */
	public void setNombrePuertoDesembarque(String nombrePuertoDesembarque) {
		this.nombrePuertoDesembarque = nombrePuertoDesembarque;
	}
	/**
	 * @return the codigoNaviera
	 */
	public String getCodigoNaviera() {
		return codigoNaviera;
	}
	/**
	 * @param codigoNaviera the codigoNaviera to set
	 */
	public void setCodigoNaviera(String codigoNaviera) {
		this.codigoNaviera = codigoNaviera;
	}
	/**
	 * @return the nombreNaviera
	 */
	public String getNombreNaviera() {
		return nombreNaviera;
	}
	/**
	 * @param nombreNaviera the nombreNaviera to set
	 */
	public void setNombreNaviera(String nombreNaviera) {
		this.nombreNaviera = nombreNaviera;
	}
	/**
	 * @return the numeroEquipos
	 */
	public Integer getNumeroEquipos() {
		return numeroEquipos;
	}
	/**
	 * @param numeroEquipos the numeroEquipos to set
	 */
	public void setNumeroEquipos(Integer numeroEquipos) {
		this.numeroEquipos = numeroEquipos;
	}
	/**
	 * @return the numEquipEstadoDocuCompleta
	 */
	public Integer getNumEquipEstadoDocuCompleta() {
		return numEquipEstadoDocuCompleta;
	}
	/**
	 * @param numEquipEstadoDocuCompleta the numEquipEstadoDocuCompleta to set
	 */
	public void setNumEquipEstadoDocuCompleta(Integer numEquipEstadoDocuCompleta) {
		this.numEquipEstadoDocuCompleta = numEquipEstadoDocuCompleta;
	}
	/**
	 * @return the codigoEstado
	 */
	public Integer getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	/**
	 * @return the nombreEstado
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}
	/**
	 * @param nombreEstado the nombreEstado to set
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	/**
	 * @return the codigoUsuarioValidacion
	 */
	public String getCodigoUsuarioValidacion() {
		return codigoUsuarioValidacion;
	}
	/**
	 * @param codigoUsuarioValidacion the codigoUsuarioValidacion to set
	 */
	public void setCodigoUsuarioValidacion(String codigoUsuarioValidacion) {
		this.codigoUsuarioValidacion = codigoUsuarioValidacion;
	}
	


}
