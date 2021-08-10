package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.OutputVDSumaryDTO;

public interface GetVDSumaryService {

	public OutputVDSumaryDTO getValueDeclarationList(InputVDSumaryDTO data);


}
