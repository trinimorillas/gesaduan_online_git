package es.mercadona.gesaduan.dao.cargas.cambiarestado.v1;

import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.CambiarEstadoCargaDTO;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.InputDatosCambiarEstadoCargaDTO;

public interface CambiarEstadoCargaDAO {
	public Integer getEstadoActual(CambiarEstadoCargaDTO carga);
	public Integer comprobarPedidosAsociados(CambiarEstadoCargaDTO carga);
	public void cambiarEstado(InputDatosCambiarEstadoCargaDTO datos, CambiarEstadoCargaDTO carga);
	public void validarPedidosAsociados(InputDatosCambiarEstadoCargaDTO datos, CambiarEstadoCargaDTO carga);
}
