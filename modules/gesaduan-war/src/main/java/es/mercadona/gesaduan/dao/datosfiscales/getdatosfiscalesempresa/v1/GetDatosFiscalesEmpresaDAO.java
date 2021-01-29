package es.mercadona.gesaduan.dao.datosfiscales.getdatosfiscalesempresa.v1;

import es.mercadona.gesaduan.jpa.datosfiscales.v1.DatosFiscalesEmpresaJPA;

public interface GetDatosFiscalesEmpresaDAO {

	public DatosFiscalesEmpresaJPA getDatosFiscalesPorEmpresa(String codigoEmpresa);

	public String getDescPais(String codigoPais);
	
}
