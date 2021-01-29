package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.SuministroDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.TipoCargaDTO;

public class InputPlanEmbarqueDetalleDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private Long codigoEmbarque;
	private String usuarioCreacion;
	private String mcaOcultaLlenos;
	private String mcaIncluyeCargas;	
	private String mcaIncluyePedidos;	
	private String orden;
	private List<SuministroDTO> suministro;
	private List<TipoCargaDTO> tipoCarga;	
	
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getMcaOcultaLlenos() {
		return mcaOcultaLlenos;
	}

	public void setMcaOcultaLlenos(String mcaOcultaLlenos) {
		this.mcaOcultaLlenos = mcaOcultaLlenos;
	}
	
	public String getMcaIncluyeCargas() {
		return mcaIncluyeCargas;
	}

	public void setMcaIncluyeCargas(String mcaIncluyeCargas) {
		this.mcaIncluyeCargas = mcaIncluyeCargas;
	}

	public String getMcaIncluyePedidos() {
		return mcaIncluyePedidos;
	}

	public void setMcaIncluyePedidos(String mcaIncluyePedidos) {
		this.mcaIncluyePedidos = mcaIncluyePedidos;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public List<SuministroDTO> getSuministro() {
		return suministro;
	}

	public void setSuministro(List<SuministroDTO> suministro) {
		this.suministro = suministro;
	}

	public List<TipoCargaDTO> getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(List<TipoCargaDTO> tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	

}
