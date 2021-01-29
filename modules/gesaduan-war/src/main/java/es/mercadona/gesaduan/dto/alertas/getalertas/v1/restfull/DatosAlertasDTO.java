package es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosAlertasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String codigo;
	private Boolean estaActiva;
	private String fechaAlerta;
	private String elemento;
	private String expedicion;
	private String fechaAlbaran;
	private String resolucion;
	private Boolean tieneEnlace;
	private Boolean envioSMS;
	private Boolean envioEmail;
	private String codigoElemento;
	
	private Boolean estaResuelta;
	
	private String descripcionAlerta;
	
	private String codigoProveedorLegacy;	
	private String codigoProveedor;
	private String nombreProveedor;
	
	DeclaracionDeValorAlertasDTO declaracionDeValor;
	List<ReceptoresAlertasDTO> receptores;
	
	
	
	public Boolean getEstaResuelta() {
		return estaResuelta;
	}
	public void setEstaResuelta(Boolean estaResuelta) {
		this.estaResuelta = estaResuelta;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Boolean getEstaActiva() {
		return estaActiva;
	}
	public void setEstaActiva(Boolean estaActiva) {
		this.estaActiva = estaActiva;
	}
	public String getFechaAlerta() {
		return fechaAlerta;
	}
	public void setFechaAlerta(String fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}
	public String getElemento() {
		return elemento;
	}
	public void setElemento(String elemento) {
		this.elemento = elemento;
	}
	public String getResolucion() {
		return resolucion;
	}
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
	public Boolean getTieneEnlace() {
		return tieneEnlace;
	}
	public void setTieneEnlace(Boolean tieneEnlace) {
		this.tieneEnlace = tieneEnlace;
	}
	public Boolean getEnvioSMS() {
		return envioSMS;
	}
	public void setEnvioSMS(Boolean envioSMS) {
		this.envioSMS = envioSMS;
	}
	public Boolean getEnvioEmail() {
		return envioEmail;
	}
	public void setEnvioEmail(Boolean envioEmail) {
		this.envioEmail = envioEmail;
	}
	public DeclaracionDeValorAlertasDTO getDeclaracionDeValor() {
		return declaracionDeValor;
	}
	public void setDeclaracionDeValor(DeclaracionDeValorAlertasDTO declaracionDeValor) {
		this.declaracionDeValor = declaracionDeValor;
	}
	public List<ReceptoresAlertasDTO> getReceptores() {
		return receptores;
	}
	public void setReceptores(List<ReceptoresAlertasDTO> receptores) {
		this.receptores = receptores;
	}
	public String getDescripcionAlerta() {
		return descripcionAlerta;
	}
	public void setDescripcionAlerta(String descripcionAlerta) {
		this.descripcionAlerta = descripcionAlerta;
	}

	public String getCodigoProveedorLegacy() {
		return codigoProveedorLegacy;
	}
	public void setCodigoProveedorLegacy(String codigoProveedorLegacy) {
		this.codigoProveedorLegacy = codigoProveedorLegacy;
	}
	public String getCodigoProveedor() {
		return codigoProveedor;
	}
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getExpedicion() {
		return expedicion;
	}
	public void setExpedicion(String expedicion) {
		this.expedicion = expedicion;
	}
	public String getFechaAlbaran() {
		return fechaAlbaran;
	}
	public void setFechaAlbaran(String fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}
	public String getCodigoElemento() {
		return codigoElemento;
	}
	public void setCodigoElemento(String codigoElemento) {
		this.codigoElemento = codigoElemento;
	}
	
	
	
	
}
