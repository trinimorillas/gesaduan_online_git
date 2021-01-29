package es.mercadona.gesaduan.dao.productos.putproductos.v1.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.ActualizarProductosDAO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.restfull.OutputPutProductosDTO;
import es.mercadona.gesaduan.jpa.productos.v1.ProductosJPA;

public class ActualizarProductosDAOImpl extends DaoBaseImpl<Long, ProductosJPA> implements ActualizarProductosDAO {

	@PersistenceContext
	private EntityManager entityM;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = ProductosJPA.class;

	}

	@Transactional
	@Override
	public OutputPutProductosDTO actualizarProductos(Long codigoProducto, String denominacionAlt,
			String denominacionFormato, String codigoUsuario) {
		
		try {
			OutputPutProductosDTO response = new OutputPutProductosDTO();

			ProductosJPA producto = entityM.find(ProductosJPA.class, codigoProducto);
			if (denominacionAlt != null) {
				producto.setDenominaAlternativa(denominacionAlt);
			}
			if (denominacionFormato != null) {
				producto.setFormatoAlternativo(denominacionFormato);
			}

			Date fecha = new Date();
			producto.setFechaModificacion(fecha);
			producto.setUsuarioModificacion(codigoUsuario.toUpperCase());
			return response;
		}catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}

	}

}
