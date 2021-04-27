package es.mercadona.gesaduan.dao.equipotransporte.cambiarestado.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.EquipoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.InputDatosCambiarEstadoDTO;

public interface PutCambiarEstadoDAO {
	public List<EquipoDTO> comprobarPedidosSinValidar(List<EquipoDTO> input);
	public List<EquipoDTO> comprobarEquiposDosierGenerado(List<EquipoDTO> input);
	public List<EquipoDTO> comprobarEstadoPlanEmbarqueConfirmado(List<EquipoDTO> input);
	public List<EquipoDTO> comprobarEstadoPlanEmbarqueFacturado(List<EquipoDTO> input);
	public void actualizarEstados(InputDatosCambiarEstadoDTO input);
	public void confirmarPlanEmbarque(InputDatosCambiarEstadoDTO input);
	public void asignarCarga(InputDatosCambiarEstadoDTO input);
	public void facturarPlanEmbarque(InputDatosCambiarEstadoDTO input);
	public void facturarCarga(InputDatosCambiarEstadoDTO input);
	public void cargarPlanEmbarque(InputDatosCambiarEstadoDTO input);
	public void cargarCarga(InputDatosCambiarEstadoDTO input);
}
