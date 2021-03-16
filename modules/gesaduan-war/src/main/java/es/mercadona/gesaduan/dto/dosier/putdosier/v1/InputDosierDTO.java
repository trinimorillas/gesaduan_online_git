package es.mercadona.gesaduan.dto.dosier.putdosier.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDosierDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Long codigoEmbarque;
	private String codigoAgenciaExportacion;
	private String codigoAgenciaImportacion;
	private List<InputDosierEquipoDTO> equipo;
	
	/**
	 * @return the codigoEmbarque
	 */
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	/**
	 * @param codigoEmbarque the codigoEmbarque to set
	 */
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}
	/**
	 * @return the codigoAgenciaExportacion
	 */
	public String getCodigoAgenciaExportacion() {
		return codigoAgenciaExportacion;
	}
	/**
	 * @param codigoAgenciaExportacion the codigoAgenciaExportacion to set
	 */
	public void setCodigoAgenciaExportacion(String codigoAgenciaExportacion) {
		this.codigoAgenciaExportacion = codigoAgenciaExportacion;
	}
	/**
	 * @return the codigoAgenciaImportacion
	 */
	public String getCodigoAgenciaImportacion() {
		return codigoAgenciaImportacion;
	}
	/**
	 * @param codigoAgenciaImportacion the codigoAgenciaImportacion to set
	 */
	public void setCodigoAgenciaImportacion(String codigoAgenciaImportacion) {
		this.codigoAgenciaImportacion = codigoAgenciaImportacion;
	}
	/**
	 * @return the equipo
	 */
	public List<InputDosierEquipoDTO> getEquipo() {
		return equipo;
	}
	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(List<InputDosierEquipoDTO> equipo) {
		this.equipo = equipo;
	}

	
	
}
