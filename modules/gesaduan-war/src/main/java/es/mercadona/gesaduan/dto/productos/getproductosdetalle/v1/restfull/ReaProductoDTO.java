package es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class ReaProductoDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    
    private String codigo;
	private String codigoTaric;
	private Long numeroProductos;
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
	public Long getNumeroProductos() {
		return numeroProductos;
	}
	public void setNumeroProductos(Long numeroProductos) {
		this.numeroProductos = numeroProductos;
	}

	
}
