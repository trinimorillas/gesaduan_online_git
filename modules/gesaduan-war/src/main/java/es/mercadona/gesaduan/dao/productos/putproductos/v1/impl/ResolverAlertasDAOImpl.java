package es.mercadona.gesaduan.dao.productos.putproductos.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.ResolverAlertasDAO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.InputMetadatosDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.InputPutProductosDTO;
import es.mercadona.gesaduan.jpa.productos.v1.ProductosJPA;

public class ResolverAlertasDAOImpl extends DaoBaseImpl<Long, ProductosJPA> implements ResolverAlertasDAO {
	
	@Inject
	private org.slf4j.Logger logger;
	
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
	public void resolverAlertas(InputPutProductosDTO datos) {
		String sql = "";
		
		try {		
			sql = "UPDATE S_NOTIFICACION_ALERTA NA " + 
				  "SET MCA_RESUELTA = 'S', " + 
				  "FEC_D_MODIFICACION = SYSDATE, " + 
				  "COD_V_USUARIO_MODIFICACION = ?codigoUsuario " + 
				  "WHERE COD_V_ELEMENTO = ?codigo AND COD_N_ALERTA IN (2,5)";	
			
			final Query query = getEntityManager().createNativeQuery(sql);	
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());	
			query.setParameter("codigo", datos.getDatos().getCodigo());
			
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PostDeclaracionDeValorDAOImpl(GESADUAN)","alertasSolucionadas",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}	
	}

}
