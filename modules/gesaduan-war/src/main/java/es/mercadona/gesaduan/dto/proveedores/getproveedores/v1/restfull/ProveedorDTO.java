package es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull;

import es.mercadona.gesaduan.dto.proveedores.AbstractDTO;

public class ProveedorDTO extends AbstractDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPublico;
	private String nombre;
	private String provincia;
	private String codigo;
	private String fechaActivacion;
	
	public String getCodigoPublico() {
		return codigoPublico;
	}
	public void setCodigoPublico(String codigoPublico) {
		this.codigoPublico = codigoPublico;
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
	public String getCodigoLegacy() {
		return codigo;
	}
	public void setCodigoLegacy(String codigoLegacy) {
		this.codigo = codigoLegacy;
	}
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	
	
	

	
	

}
