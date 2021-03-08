package es.mercadona.gesaduan.dao.dosier.putdosier.v1;

import es.mercadona.gesaduan.jpa.dosier.DosierContenedorJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierEquipoJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierPkJPA;

public interface PutDosierDAO {
	
	public void crearDosier(DosierJPA dosierJPA);
	public void crearRelacionDosierEquipo(DosierEquipoJPA dosierEquipoJPA);
	public void crearRelacionDosierContenedor(DosierContenedorJPA dosierContenedorJPA);		
	public DosierPkJPA getNewDosierPk();
	
}
