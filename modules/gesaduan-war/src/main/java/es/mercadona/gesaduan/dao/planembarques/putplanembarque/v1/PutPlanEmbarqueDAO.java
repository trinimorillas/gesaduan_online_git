package es.mercadona.gesaduan.dao.planembarques.putplanembarque.v1;

import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull.OutputPlanEmbarquePutDTO;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public interface PutPlanEmbarqueDAO {
	
	public OutputPlanEmbarquePutDTO crearPlanEmbarque(PlanEmbarquesJPA input);
	public OutputPlanEmbarquePutDTO modificarPlanEmbarque(PlanEmbarquesJPA input);
	
}
