package es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class ReasDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private String codigo;
	private String codigoTaric;
	private Long numeroProductos;
	private String fechaInicio;
	
	List<ProductosDTO> productos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoTaric() {
		return codigoTaric;
	}

	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
	}
	

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Long getNumeroProductos() {
		return numeroProductos;
	}

	public void setNumeroProductos(Long numeroProductos) {
		this.numeroProductos = numeroProductos;
	}

	public List<ProductosDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosDTO> productos) {
		this.productos = productos;
	}


	
	

}
