package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.InputValueDeclarationSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.OutputValueDeclarationSumaryDTO;


public interface GetVDSumaryService {

	public OutputValueDeclarationSumaryDTO getValueDeclarationList(InputValueDeclarationSumaryDTO data);


}
