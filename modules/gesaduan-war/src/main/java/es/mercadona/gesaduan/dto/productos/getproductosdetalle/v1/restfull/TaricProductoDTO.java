package es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class TaricProductoDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String capitulo;
    private String partidaSA;
    private String subPartidaSA;
    private String subPartidaNC;
    private String aperturaTARIC;
    private Long numeroREAs;
    private Long numeroProductos;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(String capitulo) {
		this.capitulo = capitulo;
	}
	public String getPartidaSA() {
		return partidaSA;
	}
	public void setPartidaSA(String partidaSA) {
		this.partidaSA = partidaSA;
	}
	public String getSubPartidaSA() {
		return subPartidaSA;
	}
	public void setSubPartidaSA(String subPartidaSA) {
		this.subPartidaSA = subPartidaSA;
	}
	public String getSubPartidaNC() {
		return subPartidaNC;
	}
	public void setSubPartidaNC(String subPartidaNC) {
		this.subPartidaNC = subPartidaNC;
	}
	public String getAperturaTARIC() {
		return aperturaTARIC;
	}
	public void setAperturaTARIC(String aperturaTARIC) {
		this.aperturaTARIC = aperturaTARIC;
	}
	public Long getNumeroREAs() {
		return numeroREAs;
	}
	public void setNumeroREAs(Long numeroREAs) {
		this.numeroREAs = numeroREAs;
	}
	public Long getNumeroProductos() {
		return numeroProductos;
	}
	public void setNumeroProductos(Long numeroProductos) {
		this.numeroProductos = numeroProductos;
	}
    
    
    
	
	

}
