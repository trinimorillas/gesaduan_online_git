package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putfacturaconfirmadescarga.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.InputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull.OutputPutFacturaConfirmaDescargaDTO;

public interface PutFacturaConfirmaDescargaDAO {		
		public OutputPutFacturaConfirmaDescargaDTO actualizarEstadoDescarga(InputPutFacturaConfirmaDescargaDTO input);
}