package es.mercadona.gesaduan.dao.planembarques.cambiarestado.v1;

import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public interface CambiarEstadoDAO {	
	public OutputCambiarEstadoDTO cambiarEstado(PlanEmbarquesJPA datos);
	public Integer getEstadoActual(Long codigoEmbarque);
	public Integer getEquipos(Long codigoEmbarque);
	public Integer getEquiposCargas(Long codigoEmbarque);
	public Integer getEquiposNoCargados(Long codigoEmbarque);
	public void crearContenedoresFicticios(Long codigoEmbarque, String codigoUsuario);
}
