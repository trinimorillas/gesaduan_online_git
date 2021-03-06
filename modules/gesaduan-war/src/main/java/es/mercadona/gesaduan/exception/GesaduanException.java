package es.mercadona.gesaduan.exception;

import es.mercadona.fwk.core.exceptions.ApplicationException;

public class GesaduanException extends ApplicationException {
	
	private static final long serialVersionUID = 1L;
	EnumGesaduanException enumGesaduan;
	String descripcionAlt = "";

	public GesaduanException(String errorMessage) {
		super(errorMessage);
	}
	
	public GesaduanException(EnumGesaduanException enumGesaduanException) {
		super(enumGesaduanException.getDescripcion());
		this.enumGesaduan = enumGesaduanException;
	}

	public GesaduanException(EnumGesaduanException enumGesaduanException,String descripcion) {
		super(descripcion);		
		this.descripcionAlt = descripcion;
		this.enumGesaduan = enumGesaduanException;
	}	

	public EnumGesaduanException getEnumGesaduan() {
		return enumGesaduan;
	}
	
	public String getDescripcionAlt() {
		return this.descripcionAlt;
	}
	  
}