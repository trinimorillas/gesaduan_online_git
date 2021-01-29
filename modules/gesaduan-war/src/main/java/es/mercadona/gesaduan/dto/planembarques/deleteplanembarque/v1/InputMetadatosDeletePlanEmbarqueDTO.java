package es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputMetadatosDeletePlanEmbarqueDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoUsuario;
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

}
