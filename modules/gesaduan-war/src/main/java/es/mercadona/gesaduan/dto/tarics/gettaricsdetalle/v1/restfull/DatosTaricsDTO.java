package es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosTaricsDTO extends AbstractDTO{

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
	private Integer numeroREAs;
	private Long numeroProductos;
	
	List<ReasDTO> reas;
	
	List<ProductosDTO> productos;
	
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
	public Integer getNumeroREAs() {
		return numeroREAs;
	}
	public void setNumeroREAs(Integer numeroREAs) {
		this.numeroREAs = numeroREAs;
	}
	public Long getNumeroProductos() {
		return numeroProductos;
	}
	public void setNumeroProductos(Long numeroProductos) {
		this.numeroProductos = numeroProductos;
	}
	public List<ReasDTO> getReas() {
		return reas;
	}
	public void setReas(List<ReasDTO> reas) {
		this.reas = reas;
	}
	public List<ProductosDTO> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductosDTO> productos) {
		this.productos = productos;
	}
	
	
	

}
