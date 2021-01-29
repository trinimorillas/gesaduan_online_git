package es.mercadona.gesaduan.business.equipotransporte.putrelacionequipocarga.v1;

import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.InputDatosCrearRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface PutRelacionEquipoCargaService {
	public void putRelacionEquipoCarga(InputDatosCrearRelacionEquipoCargaDTO input) throws GesaduanException;
}