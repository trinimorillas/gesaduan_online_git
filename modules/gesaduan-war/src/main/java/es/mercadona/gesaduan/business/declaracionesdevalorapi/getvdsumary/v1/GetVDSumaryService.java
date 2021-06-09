package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.OutputVDSumaryDTO;


public interface GetVDSumaryService {

	public OutputVDSumaryDTO getValueDeclarationList(InputVDSumaryDTO data);


}
