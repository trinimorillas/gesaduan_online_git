package es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.proveedores.AbstractDTO;

public class PutProveedoresDatos extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean marcaCompraSobreMuelle;
	private Boolean marcaAgenteAduana;
	
	private List<PutProveedor> proveedores;

	public Boolean getMarcaCompraSobreMuelle() {
		return marcaCompraSobreMuelle;
	}

	public void setMarcaCompraSobreMuelle(Boolean marcaCompraSobreMuelle) {
		this.marcaCompraSobreMuelle = marcaCompraSobreMuelle;
	}

	public Boolean getMarcaAgenteAduana() {
		return marcaAgenteAduana;
	}

	public void setMarcaAgenteAduana(Boolean marcaAgenteAduana) {
		this.marcaAgenteAduana = marcaAgenteAduana;
	}

	public List<PutProveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<PutProveedor> proveedores) {
		this.proveedores = proveedores;
	}


	
	

}
