package es.mercadona.gesaduan.dao.dosier.cambiarestado.v1;

import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;

public interface CambiarEstadoDAO {
	
	public void actualizaContenedores(DosierJPA dosierJPA);
	public void actualizaEquipos(DosierJPA dosierJPA);	
	public void eliminaRelacionEquipo(DosierJPA dosierJPA);	
	public OutputCambiarEstadoDTO cambiarEstado(DosierJPA dosierJPA);
	
}
