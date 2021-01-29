package es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.proveedores.AbstractDTO;

public class OutputGetProveedoresDTO extends AbstractDTO{
	
	private static final long serialVersionUID = 1L;

	private Map<?, ?> metadatos;
	
	private List<ProveedorDTO> datos;
	
	

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}


	public List<ProveedorDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<ProveedorDTO> datos) {
		this.datos = datos;
	}
	
	
}
