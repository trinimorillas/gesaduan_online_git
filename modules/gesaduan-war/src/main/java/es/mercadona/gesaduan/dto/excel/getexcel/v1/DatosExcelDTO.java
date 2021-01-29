package es.mercadona.gesaduan.dto.excel.getexcel.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosExcelDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private String versionExcel;

	public String getVersionExcel() {
		return versionExcel;
	}

	public void setVersionExcel(String versionExcel) {
		this.versionExcel = versionExcel;
	}

}