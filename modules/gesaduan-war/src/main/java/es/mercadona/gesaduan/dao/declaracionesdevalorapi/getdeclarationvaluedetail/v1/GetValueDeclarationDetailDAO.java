package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationHistoricalDTO;

public interface GetValueDeclarationDetailDAO {	
	public OutputValueDeclarationDetailDTO getValueDeclarationDetail(InputValueDeclarationDetailDTO input);
	public List<ValueDeclarationHistoricalDTO> getHistorico(Long valueDeclarationNumber, Integer valueDeclarationYear);
}