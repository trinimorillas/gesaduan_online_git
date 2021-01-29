package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DeclaracionesDeValorDTO extends AbstractDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DatosComunesDTO	datosComunes;
	
	CabeceraDTO cabecera;
	
	List<DatosHistorico> historico;
	
	List<ProductoDTO> lineas;
	
	public List<DatosHistorico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<DatosHistorico> historico) {
		this.historico = historico;
	}
	

	public DatosComunesDTO getDatosComunes() {
		return datosComunes;
	}

	public void setDatosComunes(DatosComunesDTO datosComunes) {
		this.datosComunes = datosComunes;
	}

	public CabeceraDTO getCabecera() {
		return cabecera;
	}

	public void setCabecera(CabeceraDTO cabecera) {
		this.cabecera = cabecera;
	}

	public List<ProductoDTO> getLineas() {
		return lineas;
	}

	public void setLineas(List<ProductoDTO> lineas) {
		this.lineas = lineas;
	}

}
