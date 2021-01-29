package es.mercadona.gesaduan.dao.equipotransporte.putcontenedor.v1;

import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.ContenedorDTO;

public interface PutContenedorDAO {	
	public void actualizarContenedor(ContenedorDTO contenedor, Long codigoEquipo, String codigoUsuario);
}