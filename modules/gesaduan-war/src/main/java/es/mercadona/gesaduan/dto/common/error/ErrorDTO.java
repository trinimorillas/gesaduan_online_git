package es.mercadona.gesaduan.dto.common.error;

import java.util.List;

public class ErrorDTO {

	private String codigo;
	private String descripcion;
	
	private List<?> detalles;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<?> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<?> detalles) {
		this.detalles = detalles;
	}

	
	
	
}
