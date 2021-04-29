package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosGeneralDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private DatosComunesDTO datosComunes;
	private CabeceraDTO cabecera;
	private DatosHistorico historico;
	private List<LineaDTO> lineas;

	/**
	 * @return the datosComunes
	 */
	public DatosComunesDTO getDatosComunes() {
		return datosComunes;
	}

	/**
	 * @param datosComunes the datosComunes to set
	 */
	public void setDatosComunes(DatosComunesDTO datosComunes) {
		this.datosComunes = datosComunes;
	}

	/**
	 * @return the cabecera
	 */
	public CabeceraDTO getCabecera() {
		return cabecera;
	}

	/**
	 * @param cabecera the cabecera to set
	 */
	public void setCabecera(CabeceraDTO cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * @return the historico
	 */
	public DatosHistorico getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(DatosHistorico historico) {
		this.historico = historico;
	}

	/**
	 * @return the lineas
	 */
	public List<LineaDTO> getLineas() {
		return lineas;
	}

	/**
	 * @param lineas the lineas to set
	 */
	public void setLineas(List<LineaDTO> lineas) {
		this.lineas = lineas;
	}

}