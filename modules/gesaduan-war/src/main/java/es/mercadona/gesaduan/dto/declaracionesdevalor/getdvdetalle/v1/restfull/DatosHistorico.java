package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;
import java.util.Date;

public class DatosHistorico extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String evento;
	
	private String fechaEvento;
	
	private Integer version;
	
	private boolean esVigente;
	
	private boolean esCorrecta;

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

	public boolean isEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}
	
	
	
	

}
