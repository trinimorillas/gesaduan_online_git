package es.mercadona.gesaduan.dto.tiposuministro.gettiposuministro.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosTipoSuministroDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoTipoSuministro;
	private String nombreTipoSuministro;
	
	public Integer getCodigoTipoSuministro() {
		return codigoTipoSuministro;
	}
	
	public void setCodigoTipoSuministro(Integer codigoTipoSuministro) {
		this.codigoTipoSuministro = codigoTipoSuministro;
	}
	
	public String getNombreTipoSuministro() {
		return nombreTipoSuministro;
	}
	
	public void setNombreTipoSuministro(String nombreTipoSuministro) {
		this.nombreTipoSuministro = nombreTipoSuministro;
	}

}