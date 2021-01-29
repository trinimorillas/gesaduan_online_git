package es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.mercadona.gesaduan.dto.expediciones.AbstractDTO;

public class ExpedicionDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean esCorrecta;
		
	private List<String> lineas;
	
	private String codVExpedicion;
	private Date fechaAlbaran;
	
	private String codPedido;
	private String usuario;
	
	private Date fechaCreacion;
	private String codAplicación;
	
	private String mcaDVGenerada;
	private String condicionesEntrega;

	
	
	public String getCondicionesEntrega() {
		return condicionesEntrega;
	}

	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
	}

	public boolean isEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	public List<String> getLineas() {
		return lineas;
	}

	public void setLineas(List<String> lineas) {
		this.lineas = lineas;
	}

	public String getCodVExpedicion() {
		return codVExpedicion;
	}

	public void setCodVExpedicion(String codVExpedicion) {
		this.codVExpedicion = codVExpedicion;
	}

	public Date getFechaAlbaran() {
		return fechaAlbaran;
	}

	public void setFechaAlbaran(Date fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}

	public String getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCodAplicación() {
		return codAplicación;
	}

	public void setCodAplicación(String codAplicación) {
		this.codAplicación = codAplicación;
	}

	public String getMcaDVGenerada() {
		return mcaDVGenerada;
	}

	public void setMcaDVGenerada(String mcaDVGenerada) {
		this.mcaDVGenerada = mcaDVGenerada;
	}
	
	
	
	
}
