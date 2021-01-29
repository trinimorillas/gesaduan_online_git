package es.mercadona.gesaduan.dao.cargas.deletecargas.v1;

import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.EliminarCargaDTO;
import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.InputDatosEliminarCargaDTO;

public interface DeleteCargaDAO {
	public Integer validarEstadoCarga(EliminarCargaDTO carga);
	public void eliminarPedidosCarga(EliminarCargaDTO carga);	
	public void eliminarCarga(EliminarCargaDTO carga);
	public void cambiarEstado(InputDatosEliminarCargaDTO datos, EliminarCargaDTO carga);
}
