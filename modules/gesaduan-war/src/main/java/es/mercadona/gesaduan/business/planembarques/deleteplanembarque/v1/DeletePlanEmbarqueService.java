package es.mercadona.gesaduan.business.planembarques.deleteplanembarque.v1;

import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputDatosDeletePlanEmbarqueDTO;
import es.mercadona.gesaduan.exception.GesaduanException;


public interface DeletePlanEmbarqueService {	
	public void deletePlanEmbarque(InputDatosDeletePlanEmbarqueDTO input) throws GesaduanException;
	public void actualizarDatosCarga(Long codigoEmbarque);
	public void eliminarRelacionCargaEquipo(Long codigoEmbarque);
	public void reordenarDivisiones();	
}
