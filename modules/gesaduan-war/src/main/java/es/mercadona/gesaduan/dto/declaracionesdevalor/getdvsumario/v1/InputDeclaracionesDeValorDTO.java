package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1;

import java.io.Serializable;
import java.util.Date;


import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputDeclaracionesDeValorDTO extends AbstractDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String locale;
	private String codigoUsuario;
	private String codigoAgencia;
	private Integer anyo;
	private Integer numeroDeclaracion;
	private String codigoPedido;
	private String codigoProveedor ;
	private String nombreProveedor;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private String tipoDeclaracion;
	private String estadoDeclaracion;
	private String estadoDescarga;
	private String tipoFechaFiltro;
	private Date fechaDesde;
	private Date fechaHasta;
	private String orden;
	
	@Override
	public String toString() {
		return "InputDeclaracionesDeValorDTO [anyo=" + anyo + ", numeroDeclaracion=" + numeroDeclaracion
				+ ", codigoPedido=" + codigoPedido + ", numeroProveedor=" + codigoProveedor + ", nombreProveedor="
				+ nombreProveedor + ", codigoAlmacen=" + codigoAlmacen + ", nombreAlmacen=" + nombreAlmacen
				+ ", tipoDeclaracion=" + tipoDeclaracion + ", estadoDeclaracion=" + estadoDeclaracion
				+ ", tipoFechaBusqueda=" + tipoFechaFiltro + ", fechaDesde=" + fechaDesde + ", fechaHasta="
				+ fechaHasta + "]";
	}
	
	
	
	
	
	public String getLocale() {
		return locale;
	}





	public void setLocale(String locale) {
		this.locale = locale;
	}





	public String getCodigoUsuario() {
		return codigoUsuario;
	}





	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}


	


	public String getCodigoAgencia() {
		return codigoAgencia;
	}





	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}





	public String getNombreProveedor() {
		return nombreProveedor;
	}



	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}



	public Integer getAnyo() {
		return anyo;
	}
	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}
	public Integer getNumeroDeclaracion() {
		return numeroDeclaracion;
	}
	public void setNumeroDeclaracion(Integer numeroDeclaracion) {
		this.numeroDeclaracion = numeroDeclaracion;
	}
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public String getCodigoProveedor() {
		return codigoProveedor;
	}
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}
	public String getTipoDeclaracion() {
		return tipoDeclaracion;
	}
	public void setTipoDeclaracion(String tipoDeclaracion) {
		this.tipoDeclaracion = tipoDeclaracion;
	}
	public String getEstadoDeclaracion() {
		return estadoDeclaracion;
	}
	public void setEstadoDeclaracion(String estadoDeclaracion) {
		this.estadoDeclaracion = estadoDeclaracion;
	}
	public String getTipoFechaFiltro() {
		return tipoFechaFiltro;
	}
	public void setTipoFechaFiltro(String tipoFechaFiltro) {
		this.tipoFechaFiltro = tipoFechaFiltro;
	}
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	public Date getFechaHasta() {
		return fechaHasta;
	}
	
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public String getEstadoDescarga() {
		return estadoDescarga;
	}

	public void setEstadoDescarga(String estadoDescarga) {
		this.estadoDescarga = estadoDescarga;
	}





	public String getOrden() {
		return orden;
	}





	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	

	
	
	
    
    

}
