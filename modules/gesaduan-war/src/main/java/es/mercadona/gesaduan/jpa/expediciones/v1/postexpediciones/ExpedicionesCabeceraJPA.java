package es.mercadona.gesaduan.jpa.expediciones.v1.postexpediciones;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "O_EXPEDICION_CAB")
@IdClass(ExpedicionesCabeceraPK.class)
public class ExpedicionesCabeceraJPA implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_V_EXPEDICION")
	private String codVExpedicion;
	
	@Id
	@Column (name = "FEC_D_ALBARAN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlbaran;

	@Column(name="COD_V_PEDIDO")
	private String codVPedido;
	
	@Column(name="COD_V_USUARIO_CREACION")
	private String creationUser;
	
	@Column(name="FEC_D_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name="MCA_DV_GENERADA")
	private String mcaDVGenerada;
	
	@Column(name="TXT_CONDICIONES_ENTREGA")
	private String condicionesEntrega;
	
	@Column(name="COD_V_USUARIO_MODIFICACION")
	private String editUser;
	
	@Column(name="COD_V_APLICACION")
	private String codAplicacion;
	
	@Column(name="FEC_D_MODIFICACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date editDate;

	
	
	public String getCondicionesEntrega() {
		return condicionesEntrega;
	}

	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
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

	public String getCodVPedido() {
		return codVPedido;
	}

	public void setCodVPedido(String codVPedido) {
		this.codVPedido = codVPedido;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getMcaDVGenerada() {
		return mcaDVGenerada;
	}

	public void setMcaDVGenerada(String mcaDVGenerada) {
		this.mcaDVGenerada = mcaDVGenerada;
	}

	public String getEditUser() {
		return editUser;
	}

	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}

	public String getCodAplicacion() {
		return codAplicacion;
	}

	public void setCodAplicacion(String codAplicacion) {
		this.codAplicacion = codAplicacion;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	
	
}
