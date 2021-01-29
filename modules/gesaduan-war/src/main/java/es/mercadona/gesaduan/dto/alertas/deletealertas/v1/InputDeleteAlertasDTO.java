package es.mercadona.gesaduan.dto.alertas.deletealertas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDeleteAlertasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String alerta;
	private String elemento;
	private String expedicion;
	private String fechaAlbaran;
	
	public String getAlerta() {
		return alerta;
	}
	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}
	public String getElemento() {
		return elemento;
	}
	public void setElemento(String elemento) {
		this.elemento = elemento;
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
	
	

}
