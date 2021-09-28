package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputDeclaracionesDeValorDocLinDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoDeclaracion;
	private String anyoDeclaracion;
	private String versionDeclaracion;
	private String codigoProducto;
	private String nombreProducto;	
	private String tipoItem;	
	private String marca;
	private String codigoTaric;
	private String tipoLinea;
	private String codigoRea;
	private String paisOrigen;
	private String lpc;
	private String numeroBultos;
	private String tipoBultos;
	private String pesoBruto;
	private String pesoNeto;
	private String cantidad;
	private String volumen;
	private String alcohol;
	private String plato;
	private String precio;
	private String importe;
	private boolean procesada;
	
	/**
	 * @return the codigoDeclaracion
	 */
	public String getCodigoDeclaracion() {
		return codigoDeclaracion;
	}
	/**
	 * @param codigoDeclaracion the codigoDeclaracion to set
	 */
	public void setCodigoDeclaracion(String codigoDeclaracion) {
		this.codigoDeclaracion = codigoDeclaracion;
	}
	/**
	 * @return the anyoDeclaracion
	 */
	public String getAnyoDeclaracion() {
		return anyoDeclaracion;
	}
	/**
	 * @param anyoDeclaracion the anyoDeclaracion to set
	 */
	public void setAnyoDeclaracion(String anyoDeclaracion) {
		this.anyoDeclaracion = anyoDeclaracion;
	}
	/**
	 * @return the versionDeclaracion
	 */
	public String getVersionDeclaracion() {
		return versionDeclaracion;
	}
	/**
	 * @param versionDeclaracion the versionDeclaracion to set
	 */
	public void setVersionDeclaracion(String versionDeclaracion) {
		this.versionDeclaracion = versionDeclaracion;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}
	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	/**
	 * @return the tipoItem
	 */
	public String getTipoItem() {
		return tipoItem;
	}
	/**
	 * @param tipoItem the tipoItem to set
	 */
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the codigoTaric
	 */
	public String getCodigoTaric() {
		return codigoTaric;
	}
	/**
	 * @param codigoTaric the codigoTaric to set
	 */
	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
	}
	/**
	 * @return the tipoLinea
	 */
	public String getTipoLinea() {
		return tipoLinea;
	}
	/**
	 * @param tipoLinea the tipoLinea to set
	 */
	public void setTipoLinea(String tipoLinea) {
		this.tipoLinea = tipoLinea;
	}
	/**
	 * @return the codigoRea
	 */
	public String getCodigoRea() {
		return codigoRea;
	}
	/**
	 * @param codigoRea the codigoRea to set
	 */
	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}
	/**
	 * @return the paisOrigen
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}
	/**
	 * @param paisOrigen the paisOrigen to set
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	/**
	 * @return the lpc
	 */
	public String getLpc() {
		return lpc;
	}
	/**
	 * @param lpc the lpc to set
	 */
	public void setLpc(String lpc) {
		this.lpc = lpc;
	}
	/**
	 * @return the numeroBultos
	 */
	public String getNumeroBultos() {
		return numeroBultos;
	}
	/**
	 * @param numeroBultos the numeroBultos to set
	 */
	public void setNumeroBultos(String numeroBultos) {
		this.numeroBultos = numeroBultos;
	}
	/**
	 * @return the tipoBultos
	 */
	public String getTipoBultos() {
		return tipoBultos;
	}
	/**
	 * @param tipoBultos the tipoBultos to set
	 */
	public void setTipoBultos(String tipoBultos) {
		this.tipoBultos = tipoBultos;
	}
	/**
	 * @return the pesoBruto
	 */
	public String getPesoBruto() {
		return pesoBruto;
	}
	/**
	 * @param pesoBruto the pesoBruto to set
	 */
	public void setPesoBruto(String pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	/**
	 * @return the pesoNeto
	 */
	public String getPesoNeto() {
		return pesoNeto;
	}
	/**
	 * @param pesoNeto the pesoNeto to set
	 */
	public void setPesoNeto(String pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the volumen
	 */
	public String getVolumen() {
		return volumen;
	}
	/**
	 * @param volumen the volumen to set
	 */
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	/**
	 * @return the alcohol
	 */
	public String getAlcohol() {
		return alcohol;
	}
	/**
	 * @param alcohol the alcohol to set
	 */
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	/**
	 * @return the plato
	 */
	public String getPlato() {
		return plato;
	}
	/**
	 * @param plato the plato to set
	 */
	public void setPlato(String plato) {
		this.plato = plato;
	}
	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	/**
	 * @return the procesada
	 */
	public boolean isProcesada() {
		return procesada;
	}
	/**
	 * @param procesada the procesada to set
	 */
	public void setProcesada(boolean procesada) {
		this.procesada = procesada;
	}

	
	
}
