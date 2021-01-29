package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosPedidoDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fecha;
	private String codigo;
	
	private ProveedorDTO proveedor;
	private ProvinciaDeCargaDTO provinciaDeCarga;
	
	private String codigoExpedicion;
	private String condicionesEntrega;
	private String fechaAlbaran;
	private String fechaEnvio;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public ProveedorDTO getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}
	public ProvinciaDeCargaDTO getProvinciaDeCarga() {
		return provinciaDeCarga;
	}
	public void setProvinciaDeCarga(ProvinciaDeCargaDTO provinciaDeCarga) {
		this.provinciaDeCarga = provinciaDeCarga;
	}
	public String getCodigoExpedicion() {
		return codigoExpedicion;
	}
	public void setCodigoExpedicion(String codigoExpedicion) {
		this.codigoExpedicion = codigoExpedicion;
	}
	public String getCondicionesEntrega() {
		return condicionesEntrega;
	}
	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
	}
	public String getFechaAlbaran() {
		return fechaAlbaran;
	}
	public void setFechaAlbaran(String fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
	


}
