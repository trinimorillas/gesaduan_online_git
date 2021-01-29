package es.mercadona.gesaduan.dao.planembarques.deleteplanembarque.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputDatosDeletePlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputPlanEmbarqueDeleteDTO;

public interface DeletePlanEmbarqueDAO {
	public void borrarPlanEmbarque(InputPlanEmbarqueDeleteDTO input);
	public void actualizarDatosCarga(Long codigoEmbarque);
	public void eliminarRelacionCargaEquipo(Long codigoEmbarque);
	public void reordenarDivisiones();	
	public void eliminarEquipo(Long codigoEmbarque);
	public List<CargaDTO> getCargasPlanEmbarque(Long codigoEmbarque);
	public void eliminarCargasAbortadas(List<CargaDTO> listaCarga);	
	public void eliminarPlanEmbarque(Long codigoEmbarque);
	public void eliminarRelacionContenedorEquipo(InputDatosDeletePlanEmbarqueDTO datos);
}
