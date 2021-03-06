package es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosRelacionesProveedoresDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPublico;
	private String nombre;
	private String provincia;
	private String codigo;
	private String fechaActivacion;
	
	List<RelacionesProveedoresDTO> relacionProveedor;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public List<RelacionesProveedoresDTO> getRelacionProveedor() {
		return relacionProveedor;
	}

	public void setRelacionProveedor(List<RelacionesProveedoresDTO> relacionProveedor) {
		this.relacionProveedor = relacionProveedor;
	}
	
	
	
	
	
}
