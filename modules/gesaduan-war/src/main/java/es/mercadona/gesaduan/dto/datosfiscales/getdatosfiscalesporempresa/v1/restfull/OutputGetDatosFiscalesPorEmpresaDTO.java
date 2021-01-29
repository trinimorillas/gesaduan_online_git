package es.mercadona.gesaduan.dto.datosfiscales.getdatosfiscalesporempresa.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.datosfiscales.AbstractDTO;


public class OutputGetDatosFiscalesPorEmpresaDTO extends AbstractDTO{

	
private static final long serialVersionUID = 1L;
	
	
	private DatosFiscalesDTO datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	
	public DatosFiscalesDTO getDatos() {
		return datos;
	}
	public void setDatos(DatosFiscalesDTO datos) {
		this.datos = datos;
	}
		
	
}
