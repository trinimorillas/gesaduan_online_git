package es.mercadona.gesaduan.dao.equipotransporte.deleterelacionequipocarga.v1;

import es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1.InputDatosEliminarRelacionEquipoCargaDTO;

public interface DeleteRelacionEquipoCargaDAO {
	public void actualizarCargas(InputDatosEliminarRelacionEquipoCargaDTO input);
	public void eliminarRelacionContenedorEquipo(InputDatosEliminarRelacionEquipoCargaDTO input);
	public void deleteRelacionEquipoCarga(InputDatosEliminarRelacionEquipoCargaDTO input);
	public void deleteCargasAbortadas(InputDatosEliminarRelacionEquipoCargaDTO input);	
	public void actualizarOcupacion(Long codigoEquipo);	
}
