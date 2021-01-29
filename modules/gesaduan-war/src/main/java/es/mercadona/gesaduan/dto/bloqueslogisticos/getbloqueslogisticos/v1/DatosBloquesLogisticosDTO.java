package es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosBloquesLogisticosDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoBloqueLogistico;
	private String nombreBloqueLogistico;
	private List<CentroDTO> centro;
	
	public Integer getCodigoBloqueLogistico() {
		return codigoBloqueLogistico;
	}
	
	public void setCodigoBloqueLogistico(Integer codigoBloqueLogistico) {
		this.codigoBloqueLogistico = codigoBloqueLogistico;
	}
	
	public String getNombreBloqueLogistico() {
		return nombreBloqueLogistico;
	}
	
	public void setNombreBloqueLogistico(String nombreBloqueLogistico) {
		this.nombreBloqueLogistico = nombreBloqueLogistico;
	}
	
	public List<CentroDTO> getCentro() {
		return centro;
	}
	
	public void setCentro(List<CentroDTO> centro) {
		this.centro = centro;
	}	

}