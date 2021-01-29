package es.mercadona.gesaduan.business.common.v1;

import java.util.List;

public interface FilesService {
	

	public boolean createFile(String name, boolean tieneContenido, List<String> contenido);
	

}
