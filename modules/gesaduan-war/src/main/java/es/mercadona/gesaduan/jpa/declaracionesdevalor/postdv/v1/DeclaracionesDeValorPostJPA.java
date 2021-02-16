package es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "O_DECLARACION_VALOR_CAB")
@IdClass(DeclaracionesDeValorPostPK.class)
@Cacheable(false)
public class DeclaracionesDeValorPostJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_N_DECLARACION_VALOR")
	@SequenceGenerator(name="ADM_GESADUAN",sequenceName="DECLARACION_VALOR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_GESADUAN")
	private Integer codDeclaracionValor;
	
	@Id  
	@Column(name="NUM_ANYO")  
	private Integer anyo;
	
	@Id
	@Column(name="COD_N_VERSION")  
	private Integer version;
	
	@OneToMany(
			mappedBy = "codigoDv",
			cascade = {CascadeType.ALL}, 
			fetch = FetchType.LAZY			
			)
	private List<LineaDeclaracionJPA> lineasProductos;
			
	@Column(name = "COD_V_EXPEDICION")
	private String expedicion;
	
	@Column(name = "COD_V_PEDIDO")
	private String pedido;
	
	@Column(name = "COD_N_PROVEEDOR")
	private String proveedor;
	
	@Column (name = "COD_N_PROVINCIA_CARGA")
	private Integer provinciaCarga;
	
	@Column (name = "COD_V_ALMACEN")
	private String codAlmacen;
	
	@Column (name = "TXT_CONDICIONES_ENTREGA")
	private String condicionesEntrega;
	
	@Column (name = "MCA_FACTURA_DV")
	private String mcaFactura;
	
	@Column (name = "MCA_ULTIMA_VIGENTE")
	private String mcaUltimaVigente;
	
	@Column (name = "MCA_CARGA_AUTOMATICA")
	private String mcaCargaAuto;
	
	@Column (name = "MCA_DV_CORRECTA")
	private String mcaDvCorrecta;
	
	@Column (name = "MCA_ENVIO")
	private String mcaEnvio;
	
	@Column (name = "MCA_DESCARGA")
	private String mcaDescarga;
	
	@Column (name = "FEC_D_ALBARAN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlbaran;
	
	@Column (name = "FEC_D_ENVIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEnvio;
	
	@Column (name = "FEC_DT_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Column (name = "FEC_DT_DESCARGA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDescarga;
	
	@Column (name = "FEC_D_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacionRegistro;
	
	@Column (name = "FEC_D_MODIFICACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacionRegistro;
	
	@Column (name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column (name = "COD_V_USR_MODIFICACION")
	private String usuarioEdit;
	
	@Column (name = "COD_V_APLICACION")
	private String app;

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

	
	public List<LineaDeclaracionJPA> getLineasProductos() {
		return lineasProductos;
	}

	public void setLineasProductos(List<LineaDeclaracionJPA> lineasProductos) {
		this.lineasProductos = lineasProductos;
	}

	public String getExpedicion() {
		return expedicion;
	}

	public void setExpedicion(String expedicion) {
		this.expedicion = expedicion;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getProvinciaCarga() {
		return provinciaCarga;
	}

	public void setProvinciaCarga(Integer provinciaCarga) {
		this.provinciaCarga = provinciaCarga;
	}

	public String getCodAlmacen() {
		return codAlmacen;
	}

	public void setCodAlmacen(String codAlmacen) {
		this.codAlmacen = codAlmacen;
	}

	public String getCondicionesEntrega() {
		return condicionesEntrega;
	}

	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
	}

	public String getMcaFactura() {
		return mcaFactura;
	}

	public void setMcaFactura(String mcaFactura) {
		this.mcaFactura = mcaFactura;
	}

	public String getMcaUltimaVigente() {
		return mcaUltimaVigente;
	}

	public void setMcaUltimaVigente(String mcaUltimaVigente) {
		this.mcaUltimaVigente = mcaUltimaVigente;
	}

	public String getMcaCargaAuto() {
		return mcaCargaAuto;
	}

	public void setMcaCargaAuto(String mcaCargaAuto) {
		this.mcaCargaAuto = mcaCargaAuto;
	}

	public String getMcaDvCorrecta() {
		return mcaDvCorrecta;
	}

	public void setMcaDvCorrecta(String mcaDvCorrecta) {
		this.mcaDvCorrecta = mcaDvCorrecta;
	}

	public String getMcaEnvio() {
		return mcaEnvio;
	}

	public void setMcaEnvio(String mcaEnvio) {
		this.mcaEnvio = mcaEnvio;
	}

	public String getMcaDescarga() {
		return mcaDescarga;
	}

	public void setMcaDescarga(String mcaDescarga) {
		this.mcaDescarga = mcaDescarga;
	}

	public Date getFechaAlbaran() {
		return fechaAlbaran;
	}

	public void setFechaAlbaran(Date fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaDescarga() {
		return fechaDescarga;
	}

	public void setFechaDescarga(Date fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}

	public Date getFechaCreacionRegistro() {
		return fechaCreacionRegistro;
	}

	public void setFechaCreacionRegistro(Date fechaCreacionRegistro) {
		this.fechaCreacionRegistro = fechaCreacionRegistro;
	}

	public Date getFechaModificacionRegistro() {
		return fechaModificacionRegistro;
	}

	public void setFechaModificacionRegistro(Date fechaModificacionRegistro) {
		this.fechaModificacionRegistro = fechaModificacionRegistro;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioEdit() {
		return usuarioEdit;
	}

	public void setUsuarioEdit(String usuarioEdit) {
		this.usuarioEdit = usuarioEdit;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}
	
	

	
	

}
