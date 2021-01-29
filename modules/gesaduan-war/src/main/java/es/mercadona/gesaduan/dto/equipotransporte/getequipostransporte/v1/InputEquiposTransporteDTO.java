package es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.EquipoInputDTO;

public class InputEquiposTransporteDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoEmbarque;
	private String matricula;
	private Integer codigoCentroOrigen;
	private Integer codigoPuertoDesembarque;
	private String fechaCarga;
	private String fechaEmbarque;
	private Integer codigoEstado;
	private String usuarioCreacion;	
	private String mcaOcultaLlenos;	
	private String mcaIncluyeCargas;		
	private Integer paginaInicio;
	private Integer paginaTamanyo;
	private String orden;
	private List<EstadoDTO> estados;
	private List<EquipoInputDTO> equipos;	
	
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Integer getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}
	
	public void setCodigoCentroOrigen(Integer codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}
	
	public Integer getCodigoPuertoDesembarque() {
		return codigoPuertoDesembarque;
	}
	
	public void setCodigoPuertoDesembarque(Integer codigoPuertoDesembarque) {
		this.codigoPuertoDesembarque = codigoPuertoDesembarque;
	}
	
	public String getFechaCarga() {
		return fechaCarga;
	}
	
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	
	public String getFechaEmbarque() {
		return fechaEmbarque;
	}
	
	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	
	public Integer getCodigoEstado() {
		return codigoEstado;
	}
	
	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
	public Integer getPaginaInicio() {
		return paginaInicio;
	}
	
	public void setPaginaInicio(Integer paginaInicio) {
		this.paginaInicio = paginaInicio;
	}
	
	public Integer getPaginaTamanyo() {
		return paginaTamanyo;
	}
	
	public void setPaginaTamanyo(Integer paginaTamanyo) {
		this.paginaTamanyo = paginaTamanyo;
	}
	
	public String getOrden() {
		return orden;
	}
	
	public void setOrden(String orden) {
		this.orden = orden;
	}

	public List<EstadoDTO> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoDTO> estados) {
		this.estados = estados;
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

	public List<EquipoInputDTO> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoInputDTO> equipos) {
		this.equipos = equipos;
	}

	
	
}