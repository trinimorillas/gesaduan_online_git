package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putreturns.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.ItemListDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.ValueDeclarationJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2.DeclarationLineJPA;

public interface PutReturnsDAO {	
	public void putReturns(ValueDeclarationJPA input);
	public Long getDeclarationNumber();
	public Integer getProvincia(String codigoAlternativo);
	public DeclarationLineJPA getValoresProducto(ValueDeclarationJPA factura, DeclarationLineJPA lineaProducto, ItemListDTO item);
	public Long getTaric(String codigoProducto, ValueDeclarationJPA factura);
	public void generarAlerta(String numeroAlerta, String elemento, ValueDeclarationJPA factura);
	public void actualizarCabecera(ValueDeclarationJPA factura, String valor);
	public Boolean existeAlerta(ValueDeclarationJPA factura);
}