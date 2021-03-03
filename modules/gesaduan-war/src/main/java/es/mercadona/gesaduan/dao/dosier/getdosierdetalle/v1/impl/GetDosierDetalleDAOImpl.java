package es.mercadona.gesaduan.dao.dosier.getdosierdetalle.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.dosier.getdosierdetalle.v1.GetDosierDetalleDAO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDosierDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.ContenedorDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.DatosDosierDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.EquipoDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.OutputDosierDetalleDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;

public class GetDosierDetalleDAOImpl extends BaseDAO<DosierJPA> implements GetDosierDetalleDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = DosierJPA.class;
	}

	
	/*
	 * Procedimiento de consulta de dosiseres
	 * */
	@Override
	public OutputDosierDetalleDTO consultarDosier(InputDatosDetalleDTO datos) {
		
		OutputDosierDetalleDTO result = new OutputDosierDetalleDTO();
		DatosDosierDetalleDTO dosier = null;
		InputDosierDetalleDTO input = datos.getDatos();
		
		try {		
		
			Long numDosier = input.getNumDosier();		
			Integer anyoDosier = input.getAnyoDosier();			
			final StringBuilder sql = new StringBuilder();		
	
			StringBuilder select = new StringBuilder();	
			select.append("SELECT ");		
			StringBuilder campos = new StringBuilder();	 
			campos.append("D.NUM_DOSIER, "); 
			campos.append("D.NUM_ANYO,  "); 
			campos.append("TO_CHAR(D.FEC_DT_CREACION,'DD/MM/YYYY'), "); 
			campos.append("D.COD_V_USUARIO_CREACION, "); 
			campos.append("D.COD_N_ESTADO, "); 
			campos.append("ED.TXT_NOMBRE_ESTADO, "); 
			campos.append("TO_CHAR(D.FEC_DT_DESCARGA,'DD/MM/YYYY') ");
			StringBuilder from = new StringBuilder();	 
			from.append("FROM D_DOSIER D "); 
			from.append("JOIN D_ESTADO_DOSIER ED ON (ED.COD_N_ESTADO = D.COD_N_ESTADO) "); 
			from.append("JOIN S_DOSIER_EQUIPO DE ON (DE.NUM_DOSIER = D.NUM_DOSIER AND DE.NUM_ANYO = D.NUM_ANYO) "); 
			StringBuilder where = new StringBuilder();	
			where.append("WHERE ");			
			where.append("D.NUM_DOSIER=?numDosier AND ");  
			where.append("D.NUM_ANYO =?anyoDosier "); 
								
			sql.append(select).append(campos).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("numDosier", numDosier);
			query.setParameter("anyoDosier", anyoDosier);			
					
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();			
	
			if (listado != null && !listado.isEmpty()) {
				
				List<EquipoDTO> listaEquipo = new ArrayList<>();				
				List<ContenedorDTO> listaContenedor = new ArrayList<>();				
				
				for (Object[] tmp : listado) {			
					dosier = new DatosDosierDetalleDTO();
					
					dosier.setNumDosier(Long.parseLong(String.valueOf(tmp[0])));
					dosier.setAnyoDosier(Integer.parseInt(String.valueOf(tmp[1])));
					dosier.setFechaCreacion(String.valueOf(tmp[2]));
					dosier.setUsuarioCreacion(String.valueOf(tmp[3]));
					dosier.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[4])));
					dosier.setNombreEstado(String.valueOf(tmp[5]));
					if (tmp[6] != null) dosier.setFechaDescarga(String.valueOf(tmp[6]));
		
					listaEquipo = consultarEquiposDoiser(datos);	
					
					listaContenedor = consultarContenedoresDosier(datos);					
										
					dosier.setEquipo(listaEquipo);
					dosier.setContenedor(listaContenedor);					
				}						
			}		
		
			Map<String, String> mapaMetaData = new HashMap<>();

			result.setMetadatos(mapaMetaData);
			result.setDatos(dosier);

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetDosierDetalleDAOImpl(GESADUAN)","consultarDosier",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}
	
	/*
	 * Procedimiento de consulta de equipos de dosiseres
	 * */	
	private List<EquipoDTO> consultarEquiposDoiser(InputDatosDetalleDTO datos) {		
		
		InputDosierDetalleDTO input = datos.getDatos();
		List<EquipoDTO> listaEquipo = null;		
		
		try {
		
			Long numDosier = input.getNumDosier();		
			Integer anyoDosier = input.getAnyoDosier();	

			final StringBuilder sql = new StringBuilder();
			
			StringBuilder select = new StringBuilder();	
			select.append("SELECT ");		
			StringBuilder campos = new StringBuilder();	 
			campos.append("DISTINCT DE.COD_N_EQUIPO, "); 
			campos.append("DE.TXT_MATRICULA ");			
			StringBuilder from = new StringBuilder();	 
			from.append("FROM D_DOSIER D ");
			from.append("JOIN S_DOSIER_EQUIPO DE ON (DE.NUM_DOSIER = D.NUM_DOSIER AND DE.NUM_ANYO = D.NUM_ANYO) ");	
			StringBuilder where = new StringBuilder();	 
			where.append("WHERE ");
			where.append("D.NUM_DOSIER=?numDosier AND "); 
			where.append("D.NUM_ANYO=?anyoDosier ");
			
			sql.append(select).append(campos).append(from).append(where);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("numDosier", numDosier);
			query.setParameter("anyoDosier", anyoDosier);	
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			listaEquipo = new ArrayList<>() ;					
	
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {			
					EquipoDTO equipo = null;
									
					equipo = new EquipoDTO();
					equipo.setCodigoEquipo(Long.parseLong(String.valueOf(tmp[0])));
					equipo.setMatricula(String.valueOf(tmp[1]));
					
					listaEquipo.add(equipo);				
				}			
			}
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetDosierDetalleDAOImpl(GESADUAN)","consultarEquiposDoiser",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
				
		return listaEquipo;
	}
	
	/*
	 * Procedimiento de consulta de contenedores de dosiseres
	 * */	
	private List<ContenedorDTO> consultarContenedoresDosier(InputDatosDetalleDTO datos) {		
		
		InputDosierDetalleDTO input = datos.getDatos();
		List<ContenedorDTO> listaContenedor = null;		
		
		try {
		
			Long numDosier = input.getNumDosier();		
			Integer anyoDosier = input.getAnyoDosier();	
			
			String orden = null;
			if (input.getOrden() != null)
				orden = input.getOrden();			

			final StringBuilder sql = new StringBuilder();
			
			StringBuilder select = new StringBuilder();	
			select.append("SELECT * FROM ( ");		
			StringBuilder campos = new StringBuilder();
			campos.append("SELECT ");			
			campos.append("DISTINCT CE.NUM_CONTENEDOR, "); 
			campos.append("DE.TXT_MATRICULA, ");
			campos.append("CE.COD_V_CARGA, ");
			campos.append("TS.COD_N_TIPO_SUMINISTRO, ");
			campos.append("TS.TXT_NOMBRE_TIPO_SUMINISTRO, ");
			campos.append("P.COD_N_PROVEEDOR, ");
			campos.append("P.TXT_RAZON_SOCIAL, ");
			campos.append("C.COD_V_ALMACEN_ORIGEN, ");
			campos.append("C.COD_V_CENTRO_DESTINO, ");
			campos.append("TO_CHAR(C.FEC_D_ENTREGA,'DD/MM/YYYY') FEC_D_ENTREGA, ");
			campos.append("CC.COD_N_CATEGORIA, ");
			campos.append("CC.TXT_NOMBRE_CATEGORIA, ");
			campos.append("C.MCA_CONTIENE_LPC ");			
			StringBuilder from = new StringBuilder();	 
			from.append("FROM D_DOSIER D "); 	
			from.append("JOIN S_DOSIER_EQUIPO DE ON (DE.NUM_DOSIER = D.NUM_DOSIER AND DE.NUM_ANYO = D.NUM_ANYO) "); 
			from.append("JOIN O_CONTENEDOR_EXPEDIDO CE ON (CE.NUM_DOSIER = D.NUM_DOSIER AND CE.NUM_ANYO = D.NUM_ANYO AND CE.COD_N_EQUIPO = DE.COD_N_EQUIPO) "); 
			from.append("JOIN D_CARGA C ON (C.COD_V_CARGA = CE.COD_V_CARGA AND C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN) "); 
			from.append("JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = C.COD_N_TIPO_SUMINISTRO) "); 
			from.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = C.COD_N_PROVEEDOR) "); 
			from.append("LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA= C .COD_N_CATEGORIA) ");			  
			StringBuilder where = new StringBuilder();	 
			where.append("WHERE "); 
			where.append("D.NUM_DOSIER=?numDosier AND "); 
			where.append("D.NUM_ANYO=?anyoDosier ");
			where.append(") ");			
			
			StringBuilder order = new StringBuilder();	 
			
			if (orden.equals("-numContenedor"))
				order.append("ORDER BY NUM_CONTENEDOR DESC");
			else if (orden.equals("+numContenedor"))
				order.append("ORDER BY NUM_CONTENEDOR ASC");
			else if (orden.equals("-matricula"))
				order.append("ORDER BY TXT_MATRICULA DESC");
			else if (orden.equals("+matricula"))
				order.append("ORDER BY TXT_MATRICULA ASC");
			else if (orden.equals("-codigoCarga"))
				order.append("ORDER BY COD_V_CARGA DESC");
			else if (orden.equals("+codigoCarga"))
				order.append("ORDER BY COD_V_CARGA ASC");
			else if (orden.equals("-nombreSuministro"))
				order.append("ORDER BY TXT_NOMBRE_TIPO_SUMINISTRO DESC");
			else if (orden.equals("+nombreSuministro"))
				order.append("ORDER BY TXT_NOMBRE_TIPO_SUMINISTRO ASC");
			else if (orden.equals("-nombreProveedor"))
				order.append("ORDER BY TXT_RAZON_SOCIAL DESC");
			else if (orden.equals("+nombreProveedor"))
				order.append("ORDER BY TXT_RAZON_SOCIAL ASC");
			else if (orden.equals("-codigoAlmacenOrigen"))
				order.append("ORDER BY COD_V_ALMACEN_ORIGEN DESC");
			else if (orden.equals("+codigoAlmacenOrigen"))
				order.append("ORDER BY COD_V_ALMACEN_ORIGEN ASC");
			else if (orden.equals("-codigoCentroDestino"))
				order.append("ORDER BY COD_V_CENTRO_DESTINO DESC");
			else if (orden.equals("+codigoCentroDestino"))
				order.append("ORDER BY COD_V_CENTRO_DESTINO ASC");
			else if (orden.equals("-fechaEntrega"))
				order.append("ORDER BY TO_DATE(FEC_D_ENTREGA,'DD/MM/YYYY') DESC");
			else if (orden.equals("+fechaEntrega"))
				order.append("ORDER BY TO_DATE(FEC_D_ENTREGA,'DD/MM/YYYY') ASC");			
			else if (orden.equals("-nombreCategoria"))
				order.append("ORDER BY TXT_NOMBRE_CATEGORIA DESC");
			else if (orden.equals("+nombreCategoria"))
				order.append("ORDER BY TXT_NOMBRE_CATEGORIA ASC");				
			else if (orden.equals("-marcaLpC"))
				order.append("ORDER BY MCA_CONTIENE_LPC DESC");
			else if (orden.equals("+marcaLpC"))
				order.append("ORDER BY MCA_CONTIENE_LPC ASC");				
			
			sql.append(select).append(campos).append(from).append(where).append(order);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("numDosier", numDosier);
			query.setParameter("anyoDosier", anyoDosier);	
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			listaContenedor = new ArrayList<>() ;					
	
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {			
					ContenedorDTO contenedor = null;
									
					contenedor = new ContenedorDTO();
					contenedor.setNumContenedor(Long.parseLong(String.valueOf(tmp[0])));
					contenedor.setMatricula(String.valueOf(tmp[1]));
					contenedor.setCodigoCarga(String.valueOf(tmp[2]));
					contenedor.setCodigoSuministro(Long.parseLong(String.valueOf(tmp[3])));
					contenedor.setNombreSuministro(String.valueOf(tmp[4]));
					if (tmp[5] != null) contenedor.setCodigoProveedor(String.valueOf(tmp[5]));
					if (tmp[6] != null) contenedor.setNombreProveedor(String.valueOf(tmp[6]));
					contenedor.setCodigoAlmacenOrigen(String.valueOf(tmp[7]));
					contenedor.setCodigoCentroDestino(String.valueOf(tmp[8]));
					contenedor.setFechaEntrega(String.valueOf(tmp[9]));
					if (tmp[10] != null) contenedor.setCodigoCategoria(Long.parseLong(String.valueOf(tmp[10])));					
					if (tmp[11] != null) contenedor.setNombreCategoria(String.valueOf(tmp[11]));
					if (tmp[12] != null) contenedor.setMarcaLpC(String.valueOf(tmp[12]));					
					
					listaContenedor.add(contenedor);				
				}			
			}
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetDosierDetalleDAOImpl(GESADUAN)","consultarContenedoresDosier",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
				
		return listaContenedor;
	}	
	
}
