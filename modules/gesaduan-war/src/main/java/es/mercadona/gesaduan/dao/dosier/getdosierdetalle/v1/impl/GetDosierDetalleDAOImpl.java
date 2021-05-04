package es.mercadona.gesaduan.dao.dosier.getdosierdetalle.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.dosier.getdosierdetalle.v1.GetDosierDetalleDAO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDosierDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.ContenedorDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.DatosDosierDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.DeclaracionValorDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.EquipoDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.OrigenDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.OutputDosierDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.PedidoDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;

public class GetDosierDetalleDAOImpl extends BaseDAO<DosierJPA> implements GetDosierDetalleDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = DosierJPA.class;
	}
	
	private static final String NOMBRE_CLASE = "GetDosierDetalleDAOImpl(GESADUAN)";

	
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
			campos.append("TO_CHAR(D.FEC_DT_CREACION,'DD/MM/YYYY - HH24:MI'), "); 
			campos.append("D.COD_V_USUARIO_CREACION, "); 
			campos.append("D.COD_N_ESTADO, "); 
			campos.append("ED.TXT_NOMBRE_ESTADO, ");
			campos.append("D.MCA_ERROR, ");
			campos.append("D.FEC_DT_DESCARGA_EXPORTADOR, ");
			campos.append("D.FEC_DT_DESCARGA_IMPORTADOR ");
			StringBuilder from = new StringBuilder();	 
			from.append("FROM D_DOSIER D "); 
			from.append("JOIN D_ESTADO_DOSIER ED ON (ED.COD_N_ESTADO = D.COD_N_ESTADO) "); 
			from.append("JOIN S_DOSIER_EQUIPO DE ON (DE.NUM_DOSIER = D.NUM_DOSIER AND DE.NUM_ANYO = D.NUM_ANYO) "); 
			StringBuilder where = new StringBuilder();	
			where.append("WHERE ");			
			where.append("D.NUM_DOSIER = ?numDosier AND ");  
			where.append("D.NUM_ANYO = ?anyoDosier "); 
								
			sql.append(select).append(campos).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("numDosier", numDosier);
			query.setParameter("anyoDosier", anyoDosier);			
					
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();			
	
			if (listado != null && !listado.isEmpty()) {
				
				List<EquipoDTO> listaEquipo = new ArrayList<>();				
				List<ContenedorDTO> listaContenedor = new ArrayList<>();
				List<DeclaracionValorDTO> listaDV = new ArrayList<>();
				
				for (Object[] tmp : listado) {			
					dosier = new DatosDosierDetalleDTO();
					
					dosier.setNumDosier(Long.parseLong(String.valueOf(tmp[0])));
					dosier.setAnyoDosier(Integer.parseInt(String.valueOf(tmp[1])));
					dosier.setFechaCreacion(String.valueOf(tmp[2]));
					dosier.setUsuarioCreacion(String.valueOf(tmp[3]));
					dosier.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[4])));
					dosier.setNombreEstado(String.valueOf(tmp[5]));
					if (tmp[6] != null) dosier.setMcaError(String.valueOf(tmp[6]));
					if (tmp[7] != null) dosier.setFechaDescargaExportador(String.valueOf(tmp[7]));
					if (tmp[8] != null) dosier.setFechaDescargaImportador(String.valueOf(tmp[8]));
		
					listaEquipo = consultarEquiposDoiser(datos);
					listaDV = consultarFacturasDosier(datos);
					listaContenedor = consultarContenedoresDosier(datos);					
										
					dosier.setEquipo(listaEquipo);
					dosier.setDeclaracionValor(listaDV);
					dosier.setContenedor(listaContenedor);
				}						
			}		
		
			Map<String, String> mapaMetaData = new HashMap<>();

			result.setMetadatos(mapaMetaData);
			result.setDatos(dosier);

		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "consultarDosier", e.getClass().getSimpleName(), e.getMessage());	
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "consultarEquiposDoiser", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
				
		return listaEquipo;
	}
	
	/*
	 * Procedimiento de consulta de facturas de dosiseres
	 * */	
	private List<DeclaracionValorDTO> consultarFacturasDosier(InputDatosDetalleDTO datos) {		
		List<DeclaracionValorDTO> listaDV = null;		
		
		try {		
			Long numDosier = datos.getDatos().getNumDosier();		
			Integer anyoDosier = datos.getDatos().getAnyoDosier();
			String orden = null;
			
			if (datos.getDatos().getOrden() != null)
				orden = datos.getDatos().getOrden();

			final StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT DISTINCT F.COD_N_DECLARACION_VALOR, F.NUM_ANYO, F.MCA_DV_CORRECTA, ");
			sql.append("DECODE(F.COD_V_EXPEDICION, NULL, TO_CHAR(F.FEC_DT_EXPEDICION, 'DD/MM/YYYY'), TO_CHAR(F.FEC_D_ALBARAN, 'DD/MM/YYYY')) AS FEC_DT_EXPEDICION, ");
			sql.append("DECODE(F.COD_N_PROVEEDOR, NULL, NULL, F.COD_N_PROVEEDOR) AS CODIGO_ORIGEN, ");
			sql.append("DECODE(P.TXT_RAZON_SOCIAL, NULL, BL.TXT_NOMBRE, P.TXT_RAZON_SOCIAL) AS NOMBRE_ORIGEN, ");
			sql.append("F.COD_N_PROVINCIA_CARGA, ");
			sql.append("DECODE(F.COD_V_EXPEDICION, NULL, 'BLOQUE', 'PROVEEDOR') AS TIPO_ORIGEN ");
			sql.append("FROM O_DECLARACION_VALOR_CAB F ");
			sql.append("INNER JOIN O_CONTENEDOR_EXPEDIDO CE ON (CE.COD_N_DECLARACION_VALOR = F.COD_N_DECLARACION_VALOR AND CE.NUM_ANYO_DV = F.NUM_ANYO ");
			sql.append("AND CE.COD_N_VERSION_DV = F.COD_N_VERSION) ");
			sql.append("LEFT JOIN D_EQUIPO_TRANSPORTE ET ON (ET.COD_N_EQUIPO = CE.COD_N_EQUIPO) ");
			sql.append("LEFT JOIN D_PLAN_EMBARQUE PE ON (PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE) ");
			sql.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = F.COD_N_PROVEEDOR) ");
			sql.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON (BL.COD_N_BLOQUE_LOGISTICO = F.COD_N_BLOQUE_LOGISTICO) ");
			sql.append("WHERE F.NUM_DOSIER = ?numDosier ");
			sql.append("AND F.NUM_ANYO_DOSIER = ?anyoDosier ");
			
			if (orden.equals("-codigoDV"))
				sql.append("ORDER BY F.COD_N_DECLARACION_VALOR DESC");
			else if (orden.equals("+codigoDV"))
				sql.append("ORDER BY F.COD_N_DECLARACION_VALOR ASC");
			else if (orden.equals("-anyoDV"))
				sql.append("ORDER BY F.NUM_ANYO DESC");
			else if (orden.equals("+anyoDV"))
				sql.append("ORDER BY F.NUM_ANYO ASC");			
			else if (orden.equals("-fechaExpedicion"))
				sql.append("ORDER BY FEC_DT_EXPEDICION DESC");
			else if (orden.equals("+fechaExpedicion"))
				sql.append("ORDER BY FEC_DT_EXPEDICION ASC");			
			else if (orden.equals("-codigoOrigen"))
				sql.append("ORDER BY CODIGO_ORIGEN DESC");
			else if (orden.equals("+codigoOrigen"))
				sql.append("ORDER BY CODIGO_ORIGEN ASC");			
			else if (orden.equals("-nombreOrigen"))
				sql.append("ORDER BY NOMBRE_ORIGEN DESC");
			else if (orden.equals("+nombreOrigen"))
				sql.append("ORDER BY NOMBRE_ORIGEN ASC");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("numDosier", numDosier);
			query.setParameter("anyoDosier", anyoDosier);	
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			listaDV = new ArrayList<>() ;					
	
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {			
					DeclaracionValorDTO dv = new DeclaracionValorDTO();
					OrigenDTO origen = new OrigenDTO();
					
					if (tmp[0] != null) dv.setCodigoDV(Long.parseLong(String.valueOf(tmp[0])));
					if (tmp[1] != null) dv.setAnyoDV(Integer.parseInt(String.valueOf(tmp[1])));
					if (tmp[2] != null) dv.setEsDVCorrecta(String.valueOf(tmp[2]));
					if (tmp[3] != null) dv.setFechaExpedicion(String.valueOf(tmp[3]));					
					if (tmp[4] != null) origen.setCodigoOrigen(String.valueOf(tmp[4]));
					if (tmp[5] != null) origen.setNombreOrigen(String.valueOf(tmp[5]));
					if (tmp[6] != null) origen.setProvinciaOrigen(String.valueOf(tmp[6]));
					if (tmp[7] != null) origen.setTipoOrigen(String.valueOf(tmp[7]));
					dv.setOrigen(origen);
					listaDV.add(dv);
					
					final StringBuilder sqlPedido = new StringBuilder();
					
					String selectPedido = "SELECT DVP.COD_V_PEDIDO ";
					String fromPedido   = "FROM S_DECLARACION_VALOR_PEDIDO DVP ";
					String wherePedido  = "WHERE DVP.COD_N_DECLARACION_VALOR = ?codigoFactura AND DVP.NUM_ANYO_DV = ?anyoFactura";

					sqlPedido.append(selectPedido).append(fromPedido).append(wherePedido);

					final Query queryPedido = getEntityManager().createNativeQuery(sqlPedido.toString());
					queryPedido.setParameter("codigoFactura", dv.getCodigoDV());
					queryPedido.setParameter("anyoFactura", dv.getAnyoDV());

					@SuppressWarnings("unchecked")
					List<String> listadoPedido = queryPedido.getResultList();

					if (listadoPedido != null && !listadoPedido.isEmpty()) {
						List<PedidoDTO> listaPedidos = new ArrayList<>();
						for (Object tmpPedido : listadoPedido) {
							PedidoDTO pedido = new PedidoDTO();
							if (tmpPedido != null) pedido.setCodigoPedido(String.valueOf(tmpPedido));
							listaPedidos.add(pedido);
						}
						dv.setPedido(listaPedidos);
					}
				}			
			}
		
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "consultarFacturasDosier", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
				
		return listaDV;
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
			StringBuilder from = new StringBuilder();
			StringBuilder where = new StringBuilder();
				
			select.append("SELECT DISTINCT CE.NUM_CONTENEDOR, ");
			select.append("DE.TXT_MATRICULA, ");
			select.append("CE.COD_V_CARGA, ");
			select.append("C.COD_N_TIPO_CARGA, ");
			select.append("TS.COD_N_TIPO_SUMINISTRO, ");
			select.append("TS.TXT_NOMBRE_TIPO_SUMINISTRO, ");
			select.append("P.COD_N_PROVEEDOR, ");
			select.append("P.TXT_RAZON_SOCIAL, ");
			select.append("C.COD_V_ALMACEN_ORIGEN, ");
			select.append("C.COD_V_CENTRO_DESTINO, ");
			select.append("TO_CHAR(C.FEC_D_ENTREGA,'DD/MM/YYYY') FEC_D_ENTREGA, ");
			select.append("CC.COD_N_CATEGORIA, ");
			select.append("CC.TXT_NOMBRE_CATEGORIA, ");
			select.append("C.MCA_CONTIENE_LPC, ");
			select.append("CE.COD_N_DECLARACION_VALOR, ");
			select.append("CE.NUM_ANYO_DV ");			
			from.append("FROM D_DOSIER D "); 	
			from.append("JOIN D_ESTADO_DOSIER ED ON (ED.COD_N_ESTADO = D.COD_N_ESTADO) ");
			from.append("JOIN S_DOSIER_EQUIPO DE ON (DE.NUM_DOSIER = D.NUM_DOSIER AND DE.NUM_ANYO = D.NUM_ANYO) ");
			from.append("JOIN O_CONTENEDOR_EXPEDIDO CE ON (CE.NUM_DOSIER = D.NUM_DOSIER AND CE.NUM_ANYO = D.NUM_ANYO AND CE.COD_N_EQUIPO = DE.COD_N_EQUIPO) ");
			from.append("JOIN D_CARGA C ON (C.COD_V_CARGA = CE.COD_V_CARGA AND C.COD_V_ALMACEN_ORIGEN = CE.COD_V_ALMACEN) ");
			from.append("JOIN D_TIPO_SUMINISTRO TS ON (TS.COD_N_TIPO_SUMINISTRO = C.COD_N_TIPO_SUMINISTRO) ");
			from.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = C.COD_N_PROVEEDOR) ");
			from.append("LEFT JOIN D_CATEGORIA_CARGA CC ON (CC.COD_N_CATEGORIA = C.COD_N_CATEGORIA) ");				 
			where.append("WHERE "); 
			where.append("D.NUM_DOSIER = ?numDosier AND D.NUM_ANYO = ?anyoDosier ");
			
			StringBuilder order = new StringBuilder();	 
			
			if (orden.equals("-numContenedor"))
				order.append("ORDER BY CE.NUM_CONTENEDOR DESC");
			else if (orden.equals("+numContenedor"))
				order.append("ORDER BY CE.NUM_CONTENEDOR ASC");
			else if (orden.equals("-matricula"))
				order.append("ORDER BY DE.TXT_MATRICULA DESC");
			else if (orden.equals("+matricula"))
				order.append("ORDER BY DE.TXT_MATRICULA ASC");
			else if (orden.equals("-codigoCarga"))
				order.append("ORDER BY CE.COD_V_CARGA DESC");
			else if (orden.equals("+codigoCarga"))
				order.append("ORDER BY CE.COD_V_CARGA ASC");
			else if (orden.equals("-nombreSuministro"))
				order.append("ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO DESC");
			else if (orden.equals("+nombreSuministro"))
				order.append("ORDER BY TS.TXT_NOMBRE_TIPO_SUMINISTRO ASC");
			else if (orden.equals("-nombreProveedor"))
				order.append("ORDER BY P.TXT_RAZON_SOCIAL DESC");
			else if (orden.equals("+nombreProveedor"))
				order.append("ORDER BY P.TXT_RAZON_SOCIAL ASC");
			else if (orden.equals("-codigoAlmacenOrigen"))
				order.append("ORDER BY C.COD_V_ALMACEN_ORIGEN DESC");
			else if (orden.equals("+codigoAlmacenOrigen"))
				order.append("ORDER BY C.COD_V_ALMACEN_ORIGEN ASC");
			else if (orden.equals("-codigoCentroDestino"))
				order.append("ORDER BY C.COD_V_CENTRO_DESTINO DESC");
			else if (orden.equals("+codigoCentroDestino"))
				order.append("ORDER BY C.COD_V_CENTRO_DESTINO ASC");
			else if (orden.equals("-fechaEntrega"))
				order.append("ORDER BY TO_DATE(FEC_D_ENTREGA,'DD/MM/YYYY') DESC");
			else if (orden.equals("+fechaEntrega"))
				order.append("ORDER BY TO_DATE(FEC_D_ENTREGA,'DD/MM/YYYY') ASC");			
			else if (orden.equals("-nombreCategoria"))
				order.append("ORDER BY CC.TXT_NOMBRE_CATEGORIA DESC");
			else if (orden.equals("+nombreCategoria"))
				order.append("ORDER BY CC.TXT_NOMBRE_CATEGORIA ASC");				
			else if (orden.equals("-marcaLpC"))
				order.append("ORDER BY C.MCA_CONTIENE_LPC DESC");
			else if (orden.equals("+marcaLpC"))
				order.append("ORDER BY C.MCA_CONTIENE_LPC ASC");			
			else if (orden.equals("-facturaContenedor"))
				order.append("ORDER BY CE.COD_N_DECLARACION_VALOR DESC");
			else if (orden.equals("+facturaContenedor"))
				order.append("ORDER BY CE.COD_N_DECLARACION_VALOR ASC");
			
			
			sql.append(select).append(from).append(where).append(order);	
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
					contenedor.setCodigoTipoCarga(Integer.parseInt(String.valueOf(tmp[3])));
					contenedor.setCodigoSuministro(Long.parseLong(String.valueOf(tmp[4])));
					contenedor.setNombreSuministro(String.valueOf(tmp[5]));
					if (tmp[6] != null) contenedor.setCodigoProveedor(String.valueOf(tmp[6]));
					if (tmp[7] != null) contenedor.setNombreProveedor(String.valueOf(tmp[7]));
					contenedor.setCodigoAlmacenOrigen(String.valueOf(tmp[8]));
					contenedor.setCodigoCentroDestino(String.valueOf(tmp[9]));
					contenedor.setFechaEntrega(String.valueOf(tmp[10]));
					if (tmp[11] != null) contenedor.setCodigoCategoria(Long.parseLong(String.valueOf(tmp[11])));					
					if (tmp[12] != null) contenedor.setNombreCategoria(String.valueOf(tmp[12]));
					if (tmp[13] != null) contenedor.setMarcaLpC(String.valueOf(tmp[13]));
					if (tmp[14] != null) contenedor.setCodigoDV(Long.parseLong(String.valueOf(tmp[14])));
					if (tmp[15] != null) contenedor.setAnyoDV(Integer.parseInt(String.valueOf(tmp[15])));
					
					listaContenedor.add(contenedor);				
				}			
			}
		
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "consultarContenedoresDosier", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
				
		return listaContenedor;
	}	
	
}
