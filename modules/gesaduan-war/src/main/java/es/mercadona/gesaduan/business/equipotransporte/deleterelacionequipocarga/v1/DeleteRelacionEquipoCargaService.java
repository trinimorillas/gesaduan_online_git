package es.mercadona.gesaduan.business.equipotransporte.deleterelacionequipocarga.v1;

import es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1.InputDatosEliminarRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface DeleteRelacionEquipoCargaService {	
	public void deleteRelacionEquipoCarga(InputDatosEliminarRelacionEquipoCargaDTO input) throws GesaduanException;	
}