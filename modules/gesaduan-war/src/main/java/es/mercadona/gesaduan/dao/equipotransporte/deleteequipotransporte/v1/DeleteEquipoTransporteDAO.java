package es.mercadona.gesaduan.dao.equipotransporte.deleteequipotransporte.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1.InputEquipoTransporteDeleteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;

public interface DeleteEquipoTransporteDAO {
	public void borrarEquipoTransporte(InputEquipoTransporteDeleteDTO input);
	public void actualizarDatosCarga(Long codigoEquipo);
	public void eliminarRelacionContenedorEquipo(Long codigoEquipo, String codigoUsuario);
	public void eliminarRelacionCargaEquipo(Long codigoEquipo);
	public void actualizarNumeroEquipos(Long codigoEquipo);
	public void eliminarCargasAbortadas(List<CargaDTO> listaCarga);	
	public List<CargaDTO> getCargasEquipo(Long codigoEquipo);	
	public Integer getEstadoActual(Long codigoEquipo);
	public void borraRelacionEquipoDosier(Long codigoEquipo);
}
