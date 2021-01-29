package es.mercadona.gesaduan.business.equipotransporte.movercargas.v1;

import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.InputDatosMoverCargasDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface PutMoverCargasService {
	public void moverCargas(InputDatosMoverCargasDTO input) throws GesaduanException;
}