package es.mercadona.gesaduan.dao.excel.getexcel.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.estadoplanembarque.getestadoplanembarque.v1.GetEstadoPlanEmbarqueDAO;
import es.mercadona.gesaduan.dao.excel.getexcel.v1.GetExcelDAO;
import es.mercadona.gesaduan.dto.estadoplanembarque.getestadoplanembarque.v1.DatosEstadoPlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.estadoplanembarque.getestadoplanembarque.v1.OutputEstadoPlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.excel.getexcel.v1.DatosExcelDTO;
import es.mercadona.gesaduan.dto.excel.getexcel.v1.OutputExcelDTO;
import es.mercadona.gesaduan.jpa.estadoplanembarque.v1.EstadoPlanEmbarqueJPA;
import es.mercadona.gesaduan.jpa.excel.v1.ExcelJPA;

public class GetExcelDAOImpl extends BaseDAO<ExcelJPA> implements GetExcelDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = ExcelJPA.class;		
	}
	
	@Override
	public OutputExcelDTO getExcel() {
		
		OutputExcelDTO result = new OutputExcelDTO();
		DatosExcelDTO datos = null;
		
		try {		
			final StringBuilder sql = new StringBuilder();			
			String select = "SELECT V.TXT_VALOR FROM C_VARIABLE V WHERE V.COD_V_VARIABLE = 'VERSION_EXCEL'"; 			
			sql.append(select);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<String> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {	
				for (String tmp : listado) {
					datos = new DatosExcelDTO();
					datos.setVersionExcel(String.valueOf(tmp));
				}		
			}

			result.setDatos(datos);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetVersionExcelDAOImpl(GESADUAN)","getVersionExcel",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
