package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restfull.OutputVDSumaryDTO;

public interface GetVDSumaryDAO {

	public OutputVDSumaryDTO getValueDeclarationList(InputVDSumaryDTO data);
	
}
