package es.mercadona.gesaduan.dto.proveedores.getproveedores.v1;

import es.mercadona.gesaduan.dto.proveedores.AbstractDTO;

public class InputGetProveedoresDTO extends AbstractDTO{

	
	private static final long serialVersionUID = 1L;

	
	String locale;
    boolean estaActivo;
    boolean esAgencia;
    Integer codigoProveedorLegacy;
    Long codigoProveedorPublico;
    String nombreProveedor;
    String mcaNaviera;
    String mcaTransportista;
    String mcaProducto;    
    Integer paginaInicio;
    Integer paginaTamanyo;
    String orden;    
    
	public boolean isEsAgencia() {
		return esAgencia;
	}

	public void setEsAgencia(boolean esAgencia) {
		this.esAgencia = esAgencia;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	public Integer getCodigoProveedorLegacy() {
		return codigoProveedorLegacy;
	}

	public void setCodigoProveedorLegacy(Integer codigoProveedorLegacy) {
		this.codigoProveedorLegacy = codigoProveedorLegacy;
	}

	public Long getCodigoProveedorPublico() {
		return codigoProveedorPublico;
	}

	public void setCodigoProveedorPublico(Long codigoProveedorPublico) {
		this.codigoProveedorPublico = codigoProveedorPublico;
	}

	public Integer getPaginaInicio() {
		return paginaInicio;
	}

	public void setPaginaInicio(Integer paginaInicio) {
		this.paginaInicio = paginaInicio;
	}

	public Integer getPaginaTamanyo() {
		return paginaTamanyo;
	}

	public void setPaginaTamanyo(Integer paginaTamanyo) {
		this.paginaTamanyo = paginaTamanyo;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getMcaNaviera() {
		return mcaNaviera;
	}

	public void setMcaNaviera(String mcaNaviera) {
		this.mcaNaviera = mcaNaviera;
	}

	public String getMcaTransportista() {
		return mcaTransportista;
	}

	public void setMcaTransportista(String mcaTransportista) {
		this.mcaTransportista = mcaTransportista;
	}

	public String getMcaProducto() {
		return mcaProducto;
	}

	public void setMcaProducto(String mcaProducto) {
		this.mcaProducto = mcaProducto;
	}
	
	
}
