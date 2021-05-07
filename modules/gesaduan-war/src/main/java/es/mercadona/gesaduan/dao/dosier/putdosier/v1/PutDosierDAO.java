package es.mercadona.gesaduan.dao.dosier.putdosier.v1;

import es.mercadona.gesaduan.jpa.dosier.DosierContenedorJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierEquipoJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierPkJPA;

public interface PutDosierDAO {
	public void crearDosier(DosierJPA dosierJPA);
	public void crearRelacionDosierEquipo(DosierEquipoJPA dosierEquipoJPA);
	public void actualizaEstadoDocumentacionEquipo(DosierEquipoJPA dosierEquipoJPA);
	public void crearRelacionDosierContenedor(DosierContenedorJPA dosierContenedorJPA);		
	public void crearRelacionDosierContenedorDeEquipo(DosierContenedorJPA dosierContenedorJPA);
	public DosierPkJPA getNewDosierPk();
	public void updateNumDosier();
	public Integer getNumCategorias(DosierEquipoJPA dosierEquipo);
	public void crearFacturas(DosierEquipoJPA dosierEquipo);
	public void actualizarContenedores(DosierEquipoJPA dosierEquipo);
	public void insertarLineasFacturas(DosierEquipoJPA dosierEquipo);
	public void updateFacturas(DosierEquipoJPA dosierEquipo);
	public void validarFacturas(DosierPkJPA dosierPkJPA, String codigoUsuario);
	public void validarTaricProducto(DosierPkJPA dosier, String codigoUsuario);
	public void validarVolumenAlcohol(DosierPkJPA dosier, String codigoUsuario);
	public void validarPaisREA(DosierPkJPA dosier, String codigoUsuario);
	public void validarPrecioProducto(DosierPkJPA dosier, String codigoUsuario);
	public void alertaErrorFactura(DosierPkJPA dosier);
	public void alertaFacturaOK(DosierPkJPA dosier, String codigoUsuario);
	public void facturaOK(DosierPkJPA dosier);
	public void errorDosier(DosierPkJPA dosier);
	public void dosierOK(DosierPkJPA dosier, String codigoUsuario);
	public void updateContenedoresFicticios(DosierEquipoJPA dosierEquipoJPA, String codigoUsuario);
	public void relFacturaPedidoCargaTT(DosierEquipoJPA dosierEquipoJPA, String codigoUsuario);
	public void relFacturaPedidoCargaDG(DosierEquipoJPA dosierEquipoJPA, String codigoUsuario);
}