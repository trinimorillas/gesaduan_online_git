package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CategoriaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private Long codigoCategoria;
	private String nombreCategoria;
	
	/**
	 * @return the codigoCategoria
	 */
	public Long getCodigoCategoria() {
		return codigoCategoria;
	}
	/**
	 * @param codigoCategoria the codigoCategoria to set
	 */
	public void setCodigoCategoria(Long codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	/**
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	

	
}
