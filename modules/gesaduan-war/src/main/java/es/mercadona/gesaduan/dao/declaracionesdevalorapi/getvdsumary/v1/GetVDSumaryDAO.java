package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.OutputVDSumaryDTO;

public interface GetVDSumaryDAO {

	public OutputVDSumaryDTO getValueDeclarationList(InputVDSumaryDTO data);
	
}
