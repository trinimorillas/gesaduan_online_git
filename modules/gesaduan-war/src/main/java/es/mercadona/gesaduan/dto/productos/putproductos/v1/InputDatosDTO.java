package es.mercadona.gesaduan.dto.productos.putproductos.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private String codigo;
	private String denominacionAlt;
	private String formatoVentaAlt;
	private String codigoTaric;
	private String codigoRea;
	private String nuevoCodigoRea;
	private String nuevoCodigoTaric;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getNuevoCodigoRea() {
		return nuevoCodigoRea;
	}
	public void setNuevoCodigoRea(String nuevoCodigoRea) {
		this.nuevoCodigoRea = nuevoCodigoRea;
	}
	public String getNuevoCodigoTaric() {
		return nuevoCodigoTaric;
	}
	public void setNuevoCodigoTaric(String nuevoCodigoTaric) {
		this.nuevoCodigoTaric = nuevoCodigoTaric;
	}
	


	
	

}
