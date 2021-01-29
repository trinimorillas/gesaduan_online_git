package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class CabeceraDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosDeclaracionDTO datosDeclaracion;
	private DatosPedidoDTO datosPedido;
	private DatosExportadorDTO datosExportador;
	private DatosDestinatarioDTO datosDestinatario;
	public DatosDeclaracionDTO getDatosDeclaracion() {
		return datosDeclaracion;
	}
	public void setDatosDeclaracion(DatosDeclaracionDTO datosDeclaracion) {
		this.datosDeclaracion = datosDeclaracion;
	}
	public DatosPedidoDTO getDatosPedido() {
		return datosPedido;
	}
	public void setDatosPedido(DatosPedidoDTO datosPedido) {
		this.datosPedido = datosPedido;
	}
	public DatosExportadorDTO getDatosExportador() {
		return datosExportador;
	}
	public void setDatosExportador(DatosExportadorDTO datosExportador) {
		this.datosExportador = datosExportador;
	}
	public DatosDestinatarioDTO getDatosDestinatario() {
		return datosDestinatario;
	}
	public void setDatosDestinatario(DatosDestinatarioDTO datosDestinatario) {
		this.datosDestinatario = datosDestinatario;
	}
	
	

}
