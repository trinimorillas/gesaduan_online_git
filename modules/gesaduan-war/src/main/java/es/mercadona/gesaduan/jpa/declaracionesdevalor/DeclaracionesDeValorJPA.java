package es.mercadona.gesaduan.jpa.declaracionesdevalor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import javax.persistence.IdClass;


@Entity
@Table(name = "O_DECLARACION_VALOR_CAB")
@IdClass(DeclaracionesDeValorPK.class)
@Cacheable(false)
public class DeclaracionesDeValorJPA implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_N_DECLARACION_VALOR")  
	private Integer codDeclaracionValor;
	
	@Id  
	@Column(name="NUM_ANYO")  
	private Integer anyo;
	
	@Id
	@Column(name="COD_N_VERSION")  
	private Integer version;
	
	
	@Column(name="FEC_D_ALBARAN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAlbaran;
		
	@Column(name="FEC_DT_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;

	@Column(name="FEC_DT_DESCARGA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecDescarga;
	
	
	public Integer getCodDeclaracionValor() {
		return codDeclaracionValor;
	}

	public void setCodDeclaracionValor(Integer codDeclaracionValor) {
		this.codDeclaracionValor = codDeclaracionValor;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getFecAlbaran() {
		return fecAlbaran;
	}

	public void setFecAlbaran(Date fecAlbaran) {
		this.fecAlbaran = fecAlbaran;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public Date getFecDescarga() {
		return fecDescarga;
	}

	public void setFecDescarga(Date fecDescarga) {
		this.fecDescarga = fecDescarga;
	}

}
