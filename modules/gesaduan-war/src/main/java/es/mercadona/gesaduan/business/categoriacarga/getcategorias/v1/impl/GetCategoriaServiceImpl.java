package es.mercadona.gesaduan.business.categoriacarga.getcategorias.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.categoriacarga.getcategorias.v1.GetCategoriaService;
import es.mercadona.gesaduan.dao.categoriacarga.getcategorias.v1.GetCategoriaDAO;
import es.mercadona.gesaduan.dto.categoriacarga.getcategorias.v1.OutputCategoriaDTO;

public class GetCategoriaServiceImpl implements GetCategoriaService {

	@Inject
	private GetCategoriaDAO getCategoriaDao;
	
	public OutputCategoriaDTO listarCategoria() {
		
		OutputCategoriaDTO result = getCategoriaDao.listarCategoria();

		return result;
		
	}

}