package es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class RelacionesProveedoresDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long codigo;
	private String nombre;
	private String provincia;
	private String fechaInicio;
	private String fechaFin;
	
	private String codigoPublico;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getCodigoPublico() {
		return codigoPublico;
	}
	public void setCodigoPublico(String codigoPublico) {
		this.codigoPublico = codigoPublico;
	}

	
	

}
