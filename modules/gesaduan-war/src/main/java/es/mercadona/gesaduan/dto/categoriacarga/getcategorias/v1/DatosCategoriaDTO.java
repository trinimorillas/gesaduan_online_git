package es.mercadona.gesaduan.dto.categoriacarga.getcategorias.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosCategoriaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoCategoriaCarga;
	private String nombreCategoriaCarga;
	
	public Integer getCodigoCategoriaCarga() {
		return codigoCategoriaCarga;
	}
	
	public void setCodigoCategoriaCarga(Integer codigoCategoriaCarga) {
		this.codigoCategoriaCarga = codigoCategoriaCarga;
	}
	
	public String getNombreCategoriaCarga() {
		return nombreCategoriaCarga;
	}
	
	public void setNombreCategoriaCarga(String nombreCategoriaCarga) {
		this.nombreCategoriaCarga = nombreCategoriaCarga;
	}
}