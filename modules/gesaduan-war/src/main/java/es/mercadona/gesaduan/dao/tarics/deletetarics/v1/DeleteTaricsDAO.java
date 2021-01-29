package es.mercadona.gesaduan.dao.tarics.deletetarics.v1;

import es.mercadona.gesaduan.dto.tarics.deletetarics.v1.InputTaricDeleteDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

public interface DeleteTaricsDAO {

	public TaricsJPA deleteTarics(InputTaricDeleteDTO input);
}
