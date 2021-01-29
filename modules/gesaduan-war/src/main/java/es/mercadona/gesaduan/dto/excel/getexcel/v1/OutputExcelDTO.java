package es.mercadona.gesaduan.dto.excel.getexcel.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputExcelDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private DatosExcelDTO datos;

	public DatosExcelDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosExcelDTO datos) {
		this.datos = datos;
	}

}