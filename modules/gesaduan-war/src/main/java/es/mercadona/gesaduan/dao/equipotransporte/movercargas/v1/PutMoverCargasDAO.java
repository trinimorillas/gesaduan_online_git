package es.mercadona.gesaduan.dao.equipotransporte.movercargas.v1;

import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.CargasDTO;
import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.InputDatosMoverCargasDTO;

public interface PutMoverCargasDAO {
	public Integer comprobarEquipoCargado(Long codigoEquipo);
	public Integer comprobarRelacion(InputDatosMoverCargasDTO datos, CargasDTO carga);
	public void actualizarRelacion(InputDatosMoverCargasDTO datos, CargasDTO carga);
	public void actualizarDivisiones(InputDatosMoverCargasDTO datos, CargasDTO carga);
	public void borrarRelacionOrigen(Long codigoEquipoDestino, CargasDTO carga);
	public void cambiarRelacion(InputDatosMoverCargasDTO datos, CargasDTO carga);
	public void actualizarOcupacionEquipoOrigen(InputDatosMoverCargasDTO datos, CargasDTO carga);
	public void actualizarOcupacionEquipoDestino(InputDatosMoverCargasDTO datos, CargasDTO carga);
}