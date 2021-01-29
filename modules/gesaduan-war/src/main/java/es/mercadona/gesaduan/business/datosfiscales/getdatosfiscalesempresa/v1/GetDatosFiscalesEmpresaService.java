package es.mercadona.gesaduan.business.datosfiscales.getdatosfiscalesempresa.v1;

import es.mercadona.gesaduan.dto.datosfiscales.getdatosfiscalesporempresa.v1.restfull.OutputGetDatosFiscalesPorEmpresaDTO;

public interface GetDatosFiscalesEmpresaService {

	public OutputGetDatosFiscalesPorEmpresaDTO getDatosFiscalesPorEmpresa(String codEmpresa);
	
}
