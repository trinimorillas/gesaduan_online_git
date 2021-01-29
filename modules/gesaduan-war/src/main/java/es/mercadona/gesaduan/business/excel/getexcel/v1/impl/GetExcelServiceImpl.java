package es.mercadona.gesaduan.business.excel.getexcel.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.excel.getexcel.v1.GetExcelService;
import es.mercadona.gesaduan.dao.excel.getexcel.v1.GetExcelDAO;
import es.mercadona.gesaduan.dto.excel.getexcel.v1.OutputExcelDTO;

public class GetExcelServiceImpl implements GetExcelService {

	@Inject
	private GetExcelDAO getExcelDao;
	
	public OutputExcelDTO getExcel() {
		
		OutputExcelDTO result = getExcelDao.getExcel();

		return result;
		
	}

}
