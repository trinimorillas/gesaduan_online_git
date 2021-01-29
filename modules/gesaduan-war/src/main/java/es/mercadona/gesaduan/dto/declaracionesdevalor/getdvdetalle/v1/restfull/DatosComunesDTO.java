	package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosComunesDTO extends AbstractDTO{
	private static final long serialVersionUID = 1L;

	private boolean esFactura;
	private boolean esUltimaVigente;
	private boolean esCorrecta;
	private boolean estaNotificada;
	private boolean estaDescargada;
	private String fechaDescargaDV;
	private boolean esCargaManual;
	private boolean tienePdf;
	private boolean tieneCsv;

	public boolean isTienePdf() {
		return tienePdf;
	}

	public void setTienePdf(boolean tienePdf) {
		this.tienePdf = tienePdf;
	}

	public boolean isTieneCsv() {
		return tieneCsv;
	}

	public void setTieneCsv(boolean tieneCsv) {
		this.tieneCsv = tieneCsv;
	}

	public boolean isEsFactura() {
		return esFactura;
	}

	public void setEsFactura(boolean esFactura) {
		this.esFactura = esFactura;
	}

	public boolean isEsUltimaVigente() {
		return esUltimaVigente;
	}

	public void setEsUltimaVigente(boolean esUltimaVigente) {
		this.esUltimaVigente = esUltimaVigente;
	}

	public boolean isEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	public boolean isEstaNotificada() {
		return estaNotificada;
	}

	public void setEstaNotificada(boolean estaNotificada) {
		this.estaNotificada = estaNotificada;
	}

	public boolean isEstaDescargada() {
		return estaDescargada;
	}

	public void setEstaDescargada(boolean estaDescargada) {
		this.estaDescargada = estaDescargada;
	}

	
	
	public boolean isEsCargaManual() {
		return esCargaManual;
	}

	public void setEsCargaManual(boolean esCargaManual) {
		this.esCargaManual = esCargaManual;
	}

	public String getFechaDescargaDV() {
		return fechaDescargaDV;
	}

	public void setFechaDescargaDV(String fechaDescargaDV) {
		this.fechaDescargaDV = fechaDescargaDV;
	}

}
