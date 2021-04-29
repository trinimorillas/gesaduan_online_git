package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosHistorico extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private String evento;
	private String fechaEvento;
	private Integer version;
	private boolean esVigente;
	private Boolean esCorrecta;

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isEsVigente() {
		return esVigente;
	}

	public void setEsVigente(boolean esVigente) {
		this.esVigente = esVigente;
	}

	/**
	 * @return the esCorrecta
	 */
	public Boolean getEsCorrecta() {
		return esCorrecta;
	}

	/**
	 * @param esCorrecta the esCorrecta to set
	 */
	public void setEsCorrecta(Boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

}