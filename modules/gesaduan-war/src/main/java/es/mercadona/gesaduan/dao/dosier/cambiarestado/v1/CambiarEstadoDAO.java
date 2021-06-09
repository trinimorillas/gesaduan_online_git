package es.mercadona.gesaduan.dao.dosier.cambiarestado.v1;

import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;

public interface CambiarEstadoDAO {
	public void actualizaContenedores(DosierJPA dosierJPA);
	public void actualizaEquipos(DosierJPA dosierJPA);
	public void eliminaRelacionEquipo(DosierJPA dosierJPA);
	public OutputCambiarEstadoDTO cambiarEstado(DosierJPA dosierJPA);
	public Boolean tieneErrorDosier(DosierJPA dosierJPA);
	public void generarPDF(DosierJPA dosierJPA);
	public void eliminarAlertas(DosierJPA dosierJPA);
	public void eliminarAlertasFacturasDosier(DosierJPA dosierJPA);
	public void eliminarAlertasAsociacionContactos(DosierJPA dosierJPA);
	public void eliminarAlertasFacturas(DosierJPA dosierJPA);
	public void eliminarContactoAlertas(DosierJPA dosierJPA);
	public void eliminarAlertasUsuarios(DosierJPA dosierJPA);
	public void eliminarAlertasGenerales(DosierJPA dosierJPA);
	public void eliminarLineasFacturas(DosierJPA dosierJPA);
	public void eliminarRelacionPedidos(DosierJPA dosierJPA);
	public void eliminarContenedores(DosierJPA dosierJPA);
	public void eliminarCabeceraFacturas(DosierJPA dosierJPA);
	public void insertarAlerta(DosierJPA dosierJPA, String codigoUsuario);
}