package es.mercadona.gesaduan.dao.proveedores.putpuertoagencia.v1;

import es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1.PuertoDTO;
import es.mercadona.gesaduan.jpa.proveedores.putpuertoagencia.v1.PuertoAgenciaJPA;

public interface PutPuertoAgenciaDAO {
	public Integer consultarAgenciaPreferente(PuertoDTO puerto);
	public void insertarPuertoAgencia(PuertoAgenciaJPA crearPuertoAgencia);
	public void modificarAgenciaPreferente(Long codigoPuerto, String codigoAgencia);
	public void eliminarPuertoAgencia(PuertoAgenciaJPA eliminarPuertoAgencia);
}
