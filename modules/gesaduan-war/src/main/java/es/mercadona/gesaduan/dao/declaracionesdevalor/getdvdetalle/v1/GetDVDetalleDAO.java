package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdetalle.v1;

import java.util.List;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.InputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.OutputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosHistorico;

public interface GetDVDetalleDAO {

	
	public OutputDeclaracionesDeValorDetalleDTO obtenerDeclaracionesPorCodigo(InputDeclaracionesDeValorDetalleDTO input, BoPage paginacion);
	
	public List<DatosHistorico> obtenerHistoricoDV(Integer codDv, Integer anyo);
	
}
