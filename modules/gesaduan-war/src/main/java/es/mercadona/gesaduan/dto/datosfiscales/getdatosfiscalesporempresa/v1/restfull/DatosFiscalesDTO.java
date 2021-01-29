package es.mercadona.gesaduan.dto.datosfiscales.getdatosfiscalesporempresa.v1.restfull;

import es.mercadona.gesaduan.dto.datosfiscales.AbstractDTO;

public class DatosFiscalesDTO extends AbstractDTO{

	private String descRazonSocial;
    private String numCif;
    private String descDireccion;
    private String descCodigoPostal;
    private String descPoblacion;
    private String descProvincia;
    private String descPais;
    private String codigo;
    
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	private static final long serialVersionUID = 1L;
	
	
	
	public String getDescRazonSocial() {
		return descRazonSocial;
	}
	public void setDescRazonSocial(String descRazonSocial) {
		this.descRazonSocial = descRazonSocial;
	}
	public String getNumCif() {
		return numCif;
	}
	public void setNumCif(String numCif) {
		this.numCif = numCif;
	}
	public String getDescDireccion() {
		return descDireccion;
	}
	public void setDescDireccion(String descDireccion) {
		this.descDireccion = descDireccion;
	}
	public String getDescCodigoPostal() {
		return descCodigoPostal;
	}
	public void setDescCodigoPostal(String descCodigoPostal) {
		this.descCodigoPostal = descCodigoPostal;
	}
	public String getDescPoblacion() {
		return descPoblacion;
	}
	public void setDescPoblacion(String descPoblacion) {
		this.descPoblacion = descPoblacion;
	}
	public String getDescProvincia() {
		return descProvincia;
	}
	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}
	public String getDescPais() {
		return descPais;
	}
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}
	
    
    
    
    
	
}
