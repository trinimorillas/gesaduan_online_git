package es.mercadona.gesaduan.jpa.dosier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.mercadona.gesaduan.jpa.cargas.v1.CargasPkJPA;

@Entity
@Table(name = "S_DOSIER_EQUIPO")
public class DosierEquipoJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NUM_DOSIER")
	private Long numDosier;
	
	@Id
	@Column(name="NUM_ANYO")
	private Integer anyoDosier;	
	
	@Id
	@Column(name = "COD_N_EQUIPO")	
	private Long codigoEquipo;
	
	@Id
	@Column(name = "TXT_MATRICULA")	
	private String matricula;
		
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_DT_CREACION", insertable = false)
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_DT_MODIFICACION", insertable = false)
	private Date fechaModificacion;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacion;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String usuarioModificacion;		
	
	/**
	 * @return the numDosier
	 */
	public Long getNumDosier() {
		return numDosier;
	}
	/**
	 * @param numDosier the numDosier to set
	 */
	public void setNumDosier(Long numDosier) {
		this.numDosier = numDosier;
	}
	/**
	 * @return the anyoDosier
	 */
	public Integer getAnyoDosier() {
		return anyoDosier;
	}
	/**
	 * @param anyoDosier the anyoDosier to set
	 */
	public void setAnyoDosier(Integer anyoDosier) {
		this.anyoDosier = anyoDosier;
	}
	/**
	 * @return the codigoEquipo
	 */
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	/**
	 * @param codigoEquipo the codigoEquipo to set
	 */
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @return the codigoAplicacion
	 */
	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}
	/**
	 * @param codigoAplicacion the codigoAplicacion to set
	 */
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	/**
	 * @return the usuarioModificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	/**
	 * @param usuarioModificacion the usuarioModificacion to set
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	
	
}
