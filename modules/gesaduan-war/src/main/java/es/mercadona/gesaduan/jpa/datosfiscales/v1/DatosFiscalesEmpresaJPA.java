package es.mercadona.gesaduan.jpa.datosfiscales.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "D_EMPRESA_R")
public class DatosFiscalesEmpresaJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_V_EMPRESA")
	private String codEmpresa;
	
	@Column(name = "TXT_RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name = "NUM_CIF")
	private String cif;
	
	@Column(name = "TXT_DIRECCION")
	private String direccion;
	
	@Column(name = "TXT_POBLACION")
	private String poblacion;
	
	@Column(name = "TXT_MUNICIPIO")
	private String municipio;
	
	@Column(name = "COD_V_MUNICIPIO")
	private String codMunicipio;
	
	@Column(name = "TXT_PROVINCIA")
	private String provincia;
	
	@Column(name = "COD_V_PROVINCIA")
	private String codProvincia;
	
	@Column(name = "TXT_PAIS")
	private String pais;
	
	@Column(name = "COD_V_PAIS")
	private String codPais;
	
	@Column(name = "TXT_CODIGO_POSTAL")
	private String codPostal;
	
	@Column(name = "COD_V_IDIOMA_PREFERENTE")
	private String codIdiomaPreferente;

	
	
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getCodIdiomaPreferente() {
		return codIdiomaPreferente;
	}

	public void setCodIdiomaPreferente(String codIdiomaPreferente) {
		this.codIdiomaPreferente = codIdiomaPreferente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((codEmpresa == null) ? 0 : codEmpresa.hashCode());
		result = prime * result + ((codIdiomaPreferente == null) ? 0 : codIdiomaPreferente.hashCode());
		result = prime * result + ((codMunicipio == null) ? 0 : codMunicipio.hashCode());
		result = prime * result + ((codPais == null) ? 0 : codPais.hashCode());
		result = prime * result + ((codPostal == null) ? 0 : codPostal.hashCode());
		result = prime * result + ((codProvincia == null) ? 0 : codProvincia.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((poblacion == null) ? 0 : poblacion.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((razonSocial == null) ? 0 : razonSocial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatosFiscalesEmpresaJPA other = (DatosFiscalesEmpresaJPA) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (codEmpresa == null) {
			if (other.codEmpresa != null)
				return false;
		} else if (!codEmpresa.equals(other.codEmpresa))
			return false;
		if (codIdiomaPreferente == null) {
			if (other.codIdiomaPreferente != null)
				return false;
		} else if (!codIdiomaPreferente.equals(other.codIdiomaPreferente))
			return false;
		if (codMunicipio == null) {
			if (other.codMunicipio != null)
				return false;
		} else if (!codMunicipio.equals(other.codMunicipio))
			return false;
		if (codPais == null) {
			if (other.codPais != null)
				return false;
		} else if (!codPais.equals(other.codPais))
			return false;
		if (codPostal == null) {
			if (other.codPostal != null)
				return false;
		} else if (!codPostal.equals(other.codPostal))
			return false;
		if (codProvincia == null) {
			if (other.codProvincia != null)
				return false;
		} else if (!codProvincia.equals(other.codProvincia))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (poblacion == null) {
			if (other.poblacion != null)
				return false;
		} else if (!poblacion.equals(other.poblacion))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (razonSocial == null) {
			if (other.razonSocial != null)
				return false;
		} else if (!razonSocial.equals(other.razonSocial))
			return false;
		return true;
	}

	public DatosFiscalesEmpresaJPA(String codEmpresa, String razonSocial, String cif, String direccion,
			String poblacion, String municipio, String codMunicipio, String provincia, String codProvincia, String pais,
			String codPais, String codPostal, String codIdiomaPreferente) {
		super();
		this.codEmpresa = codEmpresa;
		this.razonSocial = razonSocial;
		this.cif = cif;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.municipio = municipio;
		this.codMunicipio = codMunicipio;
		this.provincia = provincia;
		this.codProvincia = codProvincia;
		this.pais = pais;
		this.codPais = codPais;
		this.codPostal = codPostal;
		this.codIdiomaPreferente = codIdiomaPreferente;
	}

	public DatosFiscalesEmpresaJPA() {
		super();
		
	}

	@Override
	public String toString() {
		return "DatosFiscalesEmpresaJPA [codEmpresa=" + codEmpresa + ", razonSocial=" + razonSocial + ", cif=" + cif
				+ ", direccion=" + direccion + ", poblacion=" + poblacion + ", municipio=" + municipio
				+ ", codMunicipio=" + codMunicipio + ", provincia=" + provincia + ", codProvincia=" + codProvincia
				+ ", pais=" + pais + ", codPais=" + codPais + ", codPostal=" + codPostal + ", codIdiomaPreferente="
				+ codIdiomaPreferente + "]";
	}
	
	
	
	
	
}
