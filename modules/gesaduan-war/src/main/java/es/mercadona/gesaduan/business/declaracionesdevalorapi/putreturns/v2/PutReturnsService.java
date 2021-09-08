package es.mercadona.gesaduan.business.declaracionesdevalorapi.putreturns.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.InputPutReturnsDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.restfull.OutputPutReturnsDTO;

public interface PutReturnsService {	
	public OutputPutReturnsDTO putReturns(InputPutReturnsDTO input);
}