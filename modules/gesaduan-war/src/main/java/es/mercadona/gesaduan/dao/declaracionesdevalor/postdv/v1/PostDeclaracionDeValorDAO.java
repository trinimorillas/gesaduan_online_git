package es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1;


import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;

public interface PostDeclaracionDeValorDAO {	
	public DeclaracionesDeValorPostPK postCabecera(DeclaracionesDeValorPostJPA input);
	public void generarAlerta(String codigoUsuario, String numFactura, String anyoFactura);
	public void marcarDosierOK(String codigoUsuario, String numFactura, String anyoFactura);
}