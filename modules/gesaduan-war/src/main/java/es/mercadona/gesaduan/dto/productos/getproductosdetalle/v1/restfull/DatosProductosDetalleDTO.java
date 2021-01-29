package es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosProductosDetalleDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private String codigo;
	private String denominacion;
	private String formatoVenta;
	private String unidadMedida;
	private String marca;
	private String denominacionAlt;
	private String formatoVentaAlt;
	private String codigoTaric;
	private String codigoRea;
	private Boolean esListoParaComer;
		
	private List<ProveedoresProductoDTO> proveedores;

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getFormatoVenta() {
		return formatoVenta;
	}

	public void setFormatoVenta(String formatoVenta) {
		this.formatoVenta = formatoVenta;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDenominacionAlt() {
		return denominacionAlt;
	}

	public void setDenominacionAlt(String denominacionAlt) {
		this.denominacionAlt = denominacionAlt;
	}

	public String getFormatoVentaAlt() {
		return formatoVentaAlt;
	}

	public void setFormatoVentaAlt(String formatoVentaAlt) {
		this.formatoVentaAlt = formatoVentaAlt;
	}

	public String getCodigoTaric() {
		return codigoTaric;
	}

	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
	}

	public String getCodigoRea() {
		return codigoRea;
	}

	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}

	public Boolean getEsListoParaComer() {
		return esListoParaComer;
	}

	public void setEsListoParaComer(Boolean esListoParaComer) {
		this.esListoParaComer = esListoParaComer;
	}


	public List<ProveedoresProductoDTO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedoresProductoDTO> proveedores) {
		this.proveedores = proveedores;
	}
	
	

}
