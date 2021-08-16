package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddetail.v2;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.InputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restful.OutputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restful.VDHistoricalDTO;



public interface GetVDDetailDAO {	
	public OutputVDDetailDTO getValueDeclarationDetail(InputVDDetailDTO input);
	public List<VDHistoricalDTO> getHistorico(String valueDeclarationNumber, String valueDeclarationYear);
}