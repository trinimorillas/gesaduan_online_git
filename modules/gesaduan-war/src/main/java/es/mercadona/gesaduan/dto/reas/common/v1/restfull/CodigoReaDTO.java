package es.mercadona.gesaduan.dto.reas.common.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class CodigoReaDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
	
	

}
