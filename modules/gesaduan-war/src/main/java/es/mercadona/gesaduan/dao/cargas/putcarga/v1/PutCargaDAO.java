package es.mercadona.gesaduan.dao.cargas.putcarga.v1;

import es.mercadona.gesaduan.dto.cargas.putcargas.v1.CargaDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;

public interface PutCargaDAO {
	public String crearCarga(CargasJPA datosCarga);
	public Integer validarEstadoCarga(CargaDTO carga);
	public String modificarCarga(CargasJPA datosCarga);
}
