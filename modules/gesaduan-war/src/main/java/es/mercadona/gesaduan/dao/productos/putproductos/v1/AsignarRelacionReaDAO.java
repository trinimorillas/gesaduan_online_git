package es.mercadona.gesaduan.dao.productos.putproductos.v1;

public interface AsignarRelacionReaDAO {

	public void asignarReaProducto(String codigoRea, Long codigoProducto, String nuevoCodigoRea, String codigoUsuario);
	
	public void desasociarReaProducto(Long codigoProducto, String codigoUsuario);
	
}
