package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;


import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosDeclaracionDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer codigo;
	private String fecha;
	private Integer anyo;
	private Integer version;
	private Double totalImporte;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getAnyo() {
		return anyo;
	}
	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Double getTotalImporte() {
		return totalImporte;
	}
	public void setTotalImporte(Double totalImporte) {
		this.totalImporte = totalImporte;
	}
	
	

	
	

	
}
