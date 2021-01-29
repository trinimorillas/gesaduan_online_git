package es.mercadona.gesaduan.jpa.reas.deletereas.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "S_TARIC_REA")
public class ReasTaricsDeleteJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_V_REA")
	private String codigoRea;
	
	@Column(name = "COD_N_TARIC")
	private Long codigoTaric;

	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_INICIO", nullable = false)
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_FIN")
	private Date fechaFin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_D_CREACION", insertable=false)
	private Date fechaCreacionTaricRea;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_D_MODIFICACION", insertable=false)
	private Date fechaModificacionTaricRea;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacionTaricRea;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String codigoCreacionTaricRea;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String codigoModificacionTaricRea;
	
}
