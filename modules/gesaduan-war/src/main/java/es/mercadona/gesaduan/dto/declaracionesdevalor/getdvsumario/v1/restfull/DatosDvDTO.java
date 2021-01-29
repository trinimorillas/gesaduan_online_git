package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosDvDTO extends AbstractDTO{

	private static final long serialVersionUID = 1L;
	
	private boolean esDeclaracionCorrecta;
	private boolean esCargaAutomatica;
	
	private Integer anyo;
	private Integer numero;
	private Integer version;
	
	
	private boolean esFactura;
	private Double importeTotal;
	
	private String estado;
	
	private String fechaExpedicion;
	
	private ProveedoresDTO proveedor;
	
	private String numeroPedido;
	
	
	private String fechaGeneracion;
	private boolean estaDescargada;
	private String fechaDescargaDocumento;
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isEsDeclaracionCorrecta() {
		return esDeclaracionCorrecta;
	}
	public void setEsDeclaracionCorrecta(boolean esDeclaracionCorrecta) {
		this.esDeclaracionCorrecta = esDeclaracionCorrecta;
	}
	public boolean isEsCargaAutomatica() {
		return esCargaAutomatica;
	}
	public void setEsCargaAutomatica(boolean esCargaAutomatica) {
		this.esCargaAutomatica = esCargaAutomatica;
	}
	public Integer getAnyo() {
		return anyo;
	}
	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public boolean isEsFactura() {
		return esFactura;
	}
	public void setEsFactura(boolean esFactura) {
		this.esFactura = esFactura;
	}
	public Double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	public ProveedoresDTO getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedoresDTO proveedor) {
		this.proveedor = proveedor;
	}
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	public boolean isEstaDescargada() {
		return estaDescargada;
	}
	public void setEstaDescargada(boolean estaDescargada) {
		this.estaDescargada = estaDescargada;
	}
	public String getFechaDescargaDocumento() {
		return fechaDescargaDocumento;
	}
	public void setFechaDescargaDocumento(String fechaDescargaDocumento) {
		this.fechaDescargaDocumento = fechaDescargaDocumento;
	}


	
	
}
