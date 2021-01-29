package es.mercadona.gesaduan.dao.equipotransporte.putrelacionequipocarga.v1;

import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.CargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.InputDatosCrearRelacionEquipoCargaDTO;

public interface PutRelacionEquipoCargaDAO {
	public Integer cargasNoProcesadas(InputDatosCrearRelacionEquipoCargaDTO input);
	public Integer comprobarRelaciones(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga);
	public void actualizarRelacion(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga);
	public void actualizarDivisionesCarga(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga);
	public void actualizarDivisionesRelacion(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga);
	public void putRelacionEquipoCarga(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga);	
	public void actualizarCarga(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga);
	public void actualizarOcupacion(Long codigoEquipo);
}
