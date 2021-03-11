package es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosProveedoresDetalleDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPublico;
	private String nombre;
	private String provincia;
	private String codigo;
	private String fechaActivacion;
	
	DireccionFiscalDTO direccionFiscal;
	
	List<PersonasContactoDTO> personasContacto;
	private List<PuertoDTO> puerto;
	

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

	public DireccionFiscalDTO getDireccionFiscal() {
		return direccionFiscal;
	}

	public void setDireccionFiscal(DireccionFiscalDTO direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	public List<PersonasContactoDTO> getPersonasContacto() {
		return personasContacto;
	}

	public void setPersonasContacto(List<PersonasContactoDTO> personasContacto) {
		this.personasContacto = personasContacto;
	}

	/**
	 * @return the puerto
	 */
	public List<PuertoDTO> getPuerto() {
		return puerto;
	}

	/**
	 * @param puerto the puerto to set
	 */
	public void setPuerto(List<PuertoDTO> puerto) {
		this.puerto = puerto;
	}	
	
}
