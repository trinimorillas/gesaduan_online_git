package es.mercadona.gesaduan.dao.equipotransporte.getcontenedores.v1;

import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.InputDatosGetContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull.OutputGetContenedoresDTO;

public interface GetContenedoresDAO {
	
	public OutputGetContenedoresDTO listarContenedores(InputDatosGetContenedorDTO datos);
	public Double totalContenedoresFacturados(Long codigoEquipo);
	public Double totalContenedoresNoFacturados(Long codigoEquipo);
	public Double totalHuecos(Long codigoEquipo);
	
}
