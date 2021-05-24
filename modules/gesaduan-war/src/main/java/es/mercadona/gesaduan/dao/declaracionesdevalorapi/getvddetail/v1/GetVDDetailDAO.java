package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddetail.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v1.InputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v1.restfull.OutputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v1.restfull.VDHistoricalDTO;

public interface GetVDDetailDAO {	
	public OutputVDDetailDTO getValueDeclarationDetail(InputVDDetailDTO input);
	public List<VDHistoricalDTO> getHistorico(String valueDeclarationNumber, String valueDeclarationYear);
}