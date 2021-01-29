package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class ProveedoresDTO extends AbstractDTO{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codigoPublico;
	private String codigo;
	private String nombre;
	private String provincia;
	private String fechaActivacion;
	
	
	
	
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoPublico() {
		return codigoPublico;
	}
	public void setCodigoPublico(String codigoPublico) {
		this.codigoPublico = codigoPublico;
	}
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	

	
	
	
}
