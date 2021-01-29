package es.mercadona.gesaduan.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.mercadona.fwk.data.DaoBaseImpl;

public abstract class BaseDAO<T> extends DaoBaseImpl<Long, T>{

	/** 
	 * EntityManager.
	 */
	@PersistenceContext(unitName="gesaduan-model")
	private EntityManager entityManager;
	
	/**
	 * Obtienen el EntityManager.
	 * @return el EntityManager
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	/**
	 * devuelve valor Double de un String o BigDecimal 
	 * devuelve null cuando recibe nuell
	 */
	protected Double getDoubleField(Object item) {
		Double result = null;
		if (item == null) {
			return result;
		}

		try {
			if (item instanceof String) {
				result = Double.valueOf(String.valueOf(item));
			} else if (item instanceof BigDecimal) {
				result = ((BigDecimal) item).doubleValue();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
	/**
	 * devuelve valor Long de un String o BigDecimal 
	 * devuelve null cuando recibe null
	 */
	protected Long getLongField(Object item) {
		Long result = null;
		if (item == null) {
			return result;
		}

		try {
			if (item instanceof String) {
				result = Long.valueOf(String.valueOf(item));
			} else if (item instanceof BigDecimal) {
				result = ((BigDecimal) item).longValue();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
	
	
	/**
	 * devuelve valor Fecha a partir de un string
	 * devuelve null cuando recibe null
	 */
	protected Date getFechaDate(String fecha) {
		Date result = null;
		if (fecha == null) {
			return result;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");

		try {
			result = df.parse(fecha);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * devuelve valor Fecha a partir de un string
	 * devuelve null cuando recibe null
	 */
	protected String getFechaString(Date fecha) {
		String result = null;
		if (fecha == null) {
			return result;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
		
		try {
			result = df.format(fecha);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
}