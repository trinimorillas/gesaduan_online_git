package es.mercadona.gesaduan.business.declaracionesdevalorapi.putreturns.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1.InputPutReturnsDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1.restfull.OutputPutReturnsDTO;

public interface PutReturnsService {	
	public OutputPutReturnsDTO putReturns(InputPutReturnsDTO input);
}