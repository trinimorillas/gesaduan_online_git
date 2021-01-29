package es.mercadona.gesaduan.business.planembarques.putplanembarque.v1;

import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull.OutputPlanEmbarquePutDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface PutPlanEmbarqueService {
	public OutputPlanEmbarquePutDTO crearPlanEmbarque(InputDatosPutDTO input);
	public OutputPlanEmbarquePutDTO modificarPlanEmbarque(InputDatosPutDTO input) throws GesaduanException;
}
