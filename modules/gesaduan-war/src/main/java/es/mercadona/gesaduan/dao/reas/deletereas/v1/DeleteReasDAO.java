package es.mercadona.gesaduan.dao.reas.deletereas.v1;

import es.mercadona.gesaduan.dto.reas.deletereas.v1.InputDeleteReasDTO;
import es.mercadona.gesaduan.jpa.reas.deletereas.v1.ReasDeleteJPA;

public interface DeleteReasDAO {

	public ReasDeleteJPA deleteRea(InputDeleteReasDTO input);
	
	public void desasociarReaProductos(String codigoRea, String codigoUsuario);
	
}
