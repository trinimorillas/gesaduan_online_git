package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1;

import java.util.List;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.DatosHistorico;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;

public interface GetValueDeclarationDetailDAO {	
	public OutputValueDeclarationDetailDTO getValueDeclarationDetail(InputValueDeclarationDetailDTO input, BoPage paginacion);
	public List<DatosHistorico> obtenerHistoricoDV(String declarationValue, Integer declarationYear);
}