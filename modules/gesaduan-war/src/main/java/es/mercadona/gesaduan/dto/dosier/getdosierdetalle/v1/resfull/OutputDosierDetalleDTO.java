package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputDosierDetalleDTO extends AbstractDTO  {

	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	private DatosDosierDetalleDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosDosierDetalleDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosDosierDetalleDTO datos) {
		this.datos = datos;
	}
	
}
