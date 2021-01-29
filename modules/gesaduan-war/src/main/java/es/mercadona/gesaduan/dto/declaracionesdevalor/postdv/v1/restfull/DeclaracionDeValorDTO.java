package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DeclaracionDeValorDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosComunesDTO datosComunes;
	private CabeceraDTO cabecera;
	private List<DatosHistorico> historico;
	private List<LineaDTO> lineas;

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

	public List<DatosHistorico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<DatosHistorico> historico) {
		this.historico = historico;
	}

	public List<LineaDTO> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaDTO> lineas) {
		this.lineas = lineas;
	}
	
	

}
