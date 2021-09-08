package es.mercadona.gesaduan.dao.alertas.getalertas.v1.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.alertas.getalertas.v1.GetAlertasDAO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.InputAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull.DatosAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull.DeclaracionDeValorAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull.OutputAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull.ReceptoresAlertasDTO;
import es.mercadona.gesaduan.jpa.alertas.v1.AlertasJPA;

@SuppressWarnings({ "unchecked" })
public class GetAlertasDAOImpl extends BaseDAO<AlertasJPA> implements GetAlertasDAO {

	@Inject
	private org.slf4j.Logger logger;

	@Inject
	private SecurityService securityService;

	@Override
	public void setEntityClass() {
		this.entityClass = AlertasJPA.class;

	}

	@Override
	public OutputAlertasDTO getAlertas(InputAlertasDTO input, BoPage paginacion) {
		OutputAlertasDTO result = new OutputAlertasDTO();
		List<DatosAlertasDTO> resultList = new ArrayList<>();

		final StringBuilder sqlService = new StringBuilder();
		final StringBuilder sqlCount = new StringBuilder();

		try {
			Integer paginaInicio = paginacion.getPage().intValue();
			Integer paginaTamanyo = paginacion.getLimit().intValue();

			String orden = input.getOrden();

			Date fechaDesde = (input.getFechaDesde() != null) ? input.getFechaDesde() : null;
			Date fechaHasta = (input.getFechaHasta() != null) ? input.getFechaHasta() : null;
			String numeroProveedor = (input.getNumeroProveedor() != null) ? input.getNumeroProveedor() : null;
			String nombreProveedor = (input.getNombreProveedor() != null) ? input.getNombreProveedor() : null;
			String tipoAlerta = (input.getTipoAlerta() != null) ? input.getTipoAlerta() : null;
			String estado = (input.getEstado() != null) ? input.getEstado() : null;
			Integer codigoDV =  (input.getCodigoDV() != null) ? input.getCodigoDV() : null;
			Integer anyoDV =  (input.getAnyoDV() != null) ? input.getAnyoDV() : null;
			Integer versionDV =  (input.getVersionDV() != null) ? input.getVersionDV() : null;

			String where = "";
			String order = "";
			String select = "SELECT * FROM (";
			String count = "SELECT COUNT(*) FROM (";

			String subQuery = "";
			String subQueryUnion1 = "SELECT DISTINCT " +
					"NOTAL.COD_V_ELEMENTO, " +
					"AL.MCA_ACTIVA, " +
					"NOTAL.FEC_D_CREACION, " +
					"TIPO.TXT_NOMBRE, " +
					"AL.TXT_PROPUESTA_RESOLUCION, " +
					"AL.MCA_ENLACE_PANTALLA, " +
					"NOTAL.MCA_RESUELTA, " +
					"NOTAL.MCA_SMS_ENVIADO, " +
					"NOTAL.MCA_CORREO_ENVIADO, " +
					"NULL AS COD_N_DECLARACION_VALOR, " +
					"NULL AS NUM_ANYO, " +
					"NULL AS COD_N_VERSION, " +
					"NOTAL.COD_N_ALERTA AS ALERTA, " +
					"AL.TXT_DESCRIPCION, " +
					"'N' AS ES_EXPEDICION, " +
					"NULL AS COD_V_EXPEDICION, " +
					"NULL AS FEC_D_ALBARAN, " +
					"NULL AS COD_N_PROVEEDOR_LEGACY, " +
					"NULL AS COD_N_PROVEEDOR, " +
					"NULL AS TXT_RAZON_SOCIAL, " +
					"AL.COD_V_TIPO_ELEMENTO " +
					"FROM S_NOTIFICACION_ALERTA NOTAL " +
					"INNER JOIN D_ALERTA AL ON AL.COD_N_ALERTA = NOTAL.COD_N_ALERTA " +
					"INNER JOIN D_TIPO_ELEMENTO TIPO ON TIPO.COD_V_TIPO_ELEMENTO = AL.COD_V_TIPO_ELEMENTO ";

			String subQueryUnion = "UNION ALL ";
			String subQueryUnion2 = "SELECT DISTINCT " +
					"EXPAL.COD_V_ELEMENTO, " +
					"AL.MCA_ACTIVA, " +
					"EXPAL.FEC_D_CREACION, " +
					"TIPO.TXT_NOMBRE, " +
					"AL.TXT_PROPUESTA_RESOLUCION, " +
					"AL.MCA_ENLACE_PANTALLA, " +
					"EXPAL.MCA_RESUELTA, " +
					"EXPAL.MCA_SMS_ENVIADO, " +
					"EXPAL.MCA_CORREO_ENVIADO, " +
					"DV.COD_N_DECLARACION_VALOR AS COD_N_DECLARACION_VALOR, " +
					"DV.NUM_ANYO AS NUM_ANYO, " +
					"DV.COD_N_VERSION AS COD_N_VERSION, " +
					"EXPAL.COD_N_ALERTA AS ALERTA, " +
					"AL.TXT_DESCRIPCION, " +
					"'S' AS ES_EXPEDICION, " +
					"EXPAL.COD_V_EXPEDICION, " +
					"EXPAL.FEC_D_ALBARAN, " +
					"PR.COD_N_LEGACY_PROVEEDOR AS COD_N_PROVEEDOR_LEGACY, " +
					"PR.COD_N_PROVEEDOR AS COD_N_PROVEEDOR, " +
					"PR.TXT_RAZON_SOCIAL AS TXT_RAZON_SOCIAL, " +
					"AL.COD_V_TIPO_ELEMENTO " +
					"FROM S_NOTIF_ALERTA_EXPED_DV EXPAL " +
					"INNER JOIN D_ALERTA AL ON AL.COD_N_ALERTA = EXPAL.COD_N_ALERTA " +
					"INNER JOIN D_TIPO_ELEMENTO TIPO ON TIPO.COD_V_TIPO_ELEMENTO = AL.COD_V_TIPO_ELEMENTO " +
					"INNER JOIN O_DECLARACION_VALOR_CAB DV ON DV.COD_N_DECLARACION_VALOR = EXPAL.COD_N_DECLARACION_VALOR "	+
					"AND DV.COD_N_VERSION = EXPAL.COD_N_VERSION "	+
					"AND DV.NUM_ANYO = EXPAL.NUM_ANYO " +
					"AND DV.MCA_ULTIMA_VIGENTE = 'S' " ;

			// Los proveedores solo tienen sentido para las alertas de DV, las DV, anyo y version tambien
			if (numeroProveedor != null || nombreProveedor != null || codigoDV != null) {
				if (numeroProveedor != null || nombreProveedor != null) {
					subQueryUnion2 += "INNER JOIN D_PROVEEDOR_R PR ON PR.COD_N_PROVEEDOR = DV.COD_N_PROVEEDOR ";
				} else {
					subQueryUnion2 += "LEFT JOIN D_PROVEEDOR_R PR ON PR.COD_N_PROVEEDOR = DV.COD_N_PROVEEDOR ";
				}

				subQuery += subQueryUnion2;

				if (numeroProveedor != null) {
					subQuery += "AND (PR.COD_N_PROVEEDOR = ?numeroProveedor OR PR.COD_N_LEGACY_PROVEEDOR = ?numeroProveedor) ";
		        }

		        if(nombreProveedor != null) {
		        	subQuery += "AND UPPER(PR.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?nombreProveedor) ||'%') ";
		        }

				subQuery += ") ";
				where += "WHERE ALERTA != 1 ";

		        if(codigoDV != null) {
		        	where += "AND COD_N_DECLARACION_VALOR = ?codigoDV ";
		        }

		        if(versionDV != null) {
		        	where += "AND COD_N_VERSION = ?versionDV ";
		        }
			} else {

				subQueryUnion2 += "LEFT JOIN D_PROVEEDOR_R PR ON PR.COD_N_PROVEEDOR = DV.COD_N_PROVEEDOR ";

				subQuery += subQueryUnion1;
				subQuery += subQueryUnion;
				subQuery += subQueryUnion2;

				subQuery += ") ";
				where += "WHERE ALERTA != 1 ";
			}

	        if(anyoDV != null) {
	        	where += "AND (NUM_ANYO = ?anyoDV OR NUM_ANYO IS NULL) ";
	        }

			if(fechaDesde != null) {
				where += "AND TRUNC(FEC_D_CREACION) >= TO_DATE(?fechaDesde, 'DD/MM/YYYY') ";
			}

			if(fechaHasta != null) {
				where += "AND TRUNC(FEC_D_CREACION) <= TO_DATE(?fechaHasta, 'DD/MM/YYYY') ";
			}

			if(tipoAlerta != null && !tipoAlerta.equals("TODO")) {
				where += "AND UPPER(TXT_NOMBRE) LIKE UPPER(?tipoAlerta) ";
			}

			if(estado != null && !estado.equals("TODAS")) {
				where += "AND UPPER(MCA_RESUELTA) LIKE UPPER(?estado) ";
			}

			where += "AND COD_V_TIPO_ELEMENTO NOT IN ('DOSIER', 'DV', 'DEVOL') ";

			if (orden.equals("-fecha"))
				order += "ORDER BY FEC_D_CREACION DESC";
			else if (orden.equals("+fecha"))
				order += "ORDER BY FEC_D_CREACION ASC";
			else if (orden.equals("-estaResuelta"))
				order += "ORDER BY MCA_RESUELTA DESC";
			else if (orden.equals("+estaResuelta"))
				order += "ORDER BY MCA_RESUELTA ASC";
			else if (orden.equals("-elemento"))
				order += "ORDER BY TXT_NOMBRE DESC";
			else if (orden.equals("+elemento"))
				order += "ORDER BY TXT_NOMBRE ASC";
			else if (orden.equals("-codigoElemento"))
				order += "ORDER BY COD_V_ELEMENTO DESC";
			else if (orden.equals("+codigoElemento"))
				order += "ORDER BY COD_V_ELEMENTO ASC";
			else if (orden.equals("-descripcion"))
				order += "ORDER BY TXT_DESCRIPCION DESC";
			else if (orden.equals("+descripcion"))
				order += "ORDER BY TXT_DESCRIPCION ASC";
			else if (orden.equals("-declaracionDeValor"))
				order += "ORDER BY COD_N_DECLARACION_VALOR DESC";
			else if (orden.equals("+declaracionDeValor"))
				order += "ORDER BY COD_N_DECLARACION_VALOR ASC";
			else if (orden.equals("-codigoProveedorLegacy"))
				order += "ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(COD_N_PROVEEDOR_LEGACY), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(COD_N_PROVEEDOR_LEGACY) END DESC, COD_N_PROVEEDOR_LEGACY DESC";
			else if (orden.equals("+codigoProveedorLegacy"))
				order += "ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(COD_N_PROVEEDOR_LEGACY), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(COD_N_PROVEEDOR_LEGACY) END, COD_N_PROVEEDOR_LEGACY";
			else if (orden.equals("-nombreProveedor"))
				order += "ORDER BY TXT_RAZON_SOCIAL DESC";
			else if (orden.equals("+nombreProveedor"))
				order += "ORDER BY TXT_RAZON_SOCIAL ASC";
			else if (orden.equals("-envioEmail"))
				order += "ORDER BY MCA_CORREO_ENVIADO DESC";
			else if (orden.equals("+envioEmail"))
				order += "ORDER BY MCA_CORREO_ENVIADO ASC";
			else if (orden.equals("-envioSMS"))
				order += "ORDER BY MCA_SMS_ENVIADO DESC";
			else if (orden.equals("+envioSMS"))
				order += "ORDER BY MCA_SMS_ENVIADO ASC";
			else if (orden.equals("-resolucion"))
				order += "ORDER BY TXT_PROPUESTA_RESOLUCION DESC";
			else if (orden.equals("+resolucion"))
				order += "ORDER BY TXT_PROPUESTA_RESOLUCION ASC";


			sqlCount.append(count).append(subQuery).append(where);
			sqlService.append(select).append(subQuery).append(where).append(order);

			final Query query = getEntityManager().createNativeQuery(sqlService.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());


			String dateMask = "dd/MM/YYYY";
			SimpleDateFormat sdf = new SimpleDateFormat(dateMask);

			if(fechaDesde != null) {
				String fDesde = sdf.format(fechaDesde);
				query.setParameter("fechaDesde", fDesde);
				queryCount.setParameter("fechaDesde", fDesde);
			}

			if(fechaHasta != null) {
				String fHasta = sdf.format(fechaHasta);
				query.setParameter("fechaHasta", fHasta);
				queryCount.setParameter("fechaHasta", fHasta);
			}

			if(numeroProveedor != null) {
				query.setParameter("numeroProveedor", numeroProveedor);
				queryCount.setParameter("numeroProveedor", numeroProveedor);
			}

			if(nombreProveedor != null) {
				query.setParameter("nombreProveedor", nombreProveedor);
				queryCount.setParameter("nombreProveedor", nombreProveedor);
			}

			if(tipoAlerta != null && !tipoAlerta.equals("TODO")) {
				if(tipoAlerta.equals("PRODU")) {
					query.setParameter("tipoAlerta", new String("Producto"));
					queryCount.setParameter("tipoAlerta", new String("Producto"));
				}else if(tipoAlerta.equals("TARIC")) {
					query.setParameter("tipoAlerta", new String("Código Taric"));
					queryCount.setParameter("tipoAlerta", new String("Código Taric"));
				}else if(tipoAlerta.equals("EAN")) {
					query.setParameter("tipoAlerta", new String("EAN Producto"));
					queryCount.setParameter("tipoAlerta", new String("EAN Producto"));
				}else if(tipoAlerta.equals("EXPED")) {
					query.setParameter("tipoAlerta", new String("Expedición"));
					queryCount.setParameter("tipoAlerta", new String("Expedición"));
				}else if(tipoAlerta.equals("PED")) {
					query.setParameter("tipoAlerta", new String("Pedido"));
					queryCount.setParameter("tipoAlerta", new String("Pedido"));
				}else if(tipoAlerta.equals("PROV")) {
					query.setParameter("tipoAlerta", new String("Proveedor"));
					queryCount.setParameter("tipoAlerta", new String("Proveedor"));
				}else if(tipoAlerta.equals("PO_CARGA")) {
					query.setParameter("tipoAlerta", new String("Punto Operacional de Carga"));
					queryCount.setParameter("tipoAlerta", new String("Punto Operacional de Carga"));
				}else if(tipoAlerta.equals("ALM")) {
					query.setParameter("tipoAlerta", new String("Almacén"));
					queryCount.setParameter("tipoAlerta", new String("Almacén"));
				}
			}

			if(estado != null && !estado.equals("TODAS")) {
				if(estado.equals("ACTIVAS")) {
					query.setParameter("estado", new String("N"));
					queryCount.setParameter("estado",  new String("N"));
				}else if(estado.equals("RESUELTAS")) {
					query.setParameter("estado", new String("S"));
					queryCount.setParameter("estado",  new String("S"));
				}
			}

			if(codigoDV != null) {
				query.setParameter("codigoDV", codigoDV);
				queryCount.setParameter("codigoDV", codigoDV);
			}

			if(anyoDV != null) {
				query.setParameter("anyoDV", anyoDV);
				queryCount.setParameter("anyoDV", anyoDV);
			}

			if(versionDV != null) {
				query.setParameter("versionDV", versionDV);
				queryCount.setParameter("versionDV", versionDV);
			}

			paginaInicio = (paginaInicio * paginaTamanyo) - paginaTamanyo;

			query.setFirstResult(paginaInicio);
			query.setMaxResults(paginaTamanyo);

			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {

					DatosAlertasDTO datos = new DatosAlertasDTO();

					String patternOutputDateTime = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
					SimpleDateFormat formatDateTime = new SimpleDateFormat(patternOutputDateTime);
					Date fechaOutput = null;

					String codigoElemento = String.valueOf(tmp[0]);
					String codigoAlerta = String.valueOf(tmp[12]);

					datos.setCodigo(codigoAlerta);
					datos.setEstaActiva(tmp[1].equals("S") ? true : false);

					if(tmp[2] != null) {
						fechaOutput = (Date) tmp[2];
						String dateToString = formatDateTime.format(fechaOutput);
						datos.setFechaAlerta(dateToString);
					}else {
						datos.setFechaAlerta(String.valueOf(tmp[2]));
					}

					datos.setElemento(String.valueOf(tmp[3]));
					datos.setDescripcionAlerta(String.valueOf(tmp[13]));

					datos.setResolucion(String.valueOf(tmp[4]));
					datos.setEstaResuelta(tmp[6].equals("S") ? true : false);
					datos.setTieneEnlace(tmp[5].equals("S") ? true : false);
					datos.setEnvioSMS(tmp[7].equals("S") ? true : false);
					datos.setEnvioEmail(tmp[8].equals("S") ? true : false);
					String codigoExpedicion = String.valueOf(tmp[15]);
					datos.setExpedicion(codigoExpedicion);
					datos.setFechaAlbaran(String.valueOf(tmp[16]));
					datos.setCodigoProveedorLegacy(String.valueOf(tmp[17]));
					datos.setCodigoProveedor(String.valueOf(tmp[18]));
					datos.setNombreProveedor(String.valueOf(tmp[19]));
					datos.setCodigoElemento(codigoElemento);

					DeclaracionDeValorAlertasDTO declaraciones = new DeclaracionDeValorAlertasDTO();

					declaraciones.setCodigo(tmp[9] != null ? Integer.parseInt(String.valueOf(tmp[9])) : null);
					declaraciones.setAnyo(tmp[10] != null ? Integer.parseInt(String.valueOf(tmp[10])) : null);
					declaraciones.setVersion(tmp[11] != null ? Integer.parseInt(String.valueOf(tmp[11])) : null);

					List<ReceptoresAlertasDTO> receptores = new ArrayList<>();

					String esExpedicion = String.valueOf(tmp[14]);
					Long codigoAlertaParse = (codigoAlerta != null) ? Long.parseLong(codigoAlerta) : 0;
					if(esExpedicion.equals("S")) {
						fechaOutput = (Date) tmp[16];
						String dateToString = formatDateTime.format(fechaOutput);
						datos.setFechaAlbaran(dateToString);

						String fechaAlbaran = sdf.format(fechaOutput);
						receptores = getReceptoresExped(codigoAlertaParse, codigoElemento, codigoExpedicion, fechaAlbaran, declaraciones);
					}else if(esExpedicion.equals("N")) {
						receptores = getReceptoresNoExped(codigoAlertaParse, codigoElemento);
					}

					datos.setDeclaracionDeValor(declaraciones);
					datos.setReceptores(receptores);
					resultList.add(datos);
				}
			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());


			result.setDatos(resultList);
			result.setMetadatos(mapaMetaData);

		} catch (NumberFormatException nfe) {
			this.logger.error("({}-{}) ERROR - {} {}","GetAlertasDAOImpl(GESADUAN)","getAlertas",nfe.getClass().getSimpleName(),nfe.getMessage());
			establecerSalidaError(nfe, "getAlertas", "error.NumberFormatException");
			throw new ApplicationException(nfe.getMessage());
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetAlertasDAOImpl(GESADUAN)","getAlertas",e.getClass().getSimpleName(),e.getMessage());
			establecerSalidaError(e, "getAlertas", "error.Exception");
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}


	private List<ReceptoresAlertasDTO> getReceptoresExped(Long codAlerta, String codigoElemento, String codigoExpedicion, String fechaAlbaran, DeclaracionDeValorAlertasDTO declaracion) {
		List<ReceptoresAlertasDTO> result = new ArrayList<>();

		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT USU.TXT_NOMBRE, ");
			sql.append("USU.TXT_DIRECCION_ELECTRONICA, ");
			sql.append("USU.TXT_TELEFONO, ");
			sql.append("USU.MCA_ENVIO_SMS, ");
			sql.append("USU.MCA_ENVIO_EMAIL ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NOTIF ");
			sql.append("INNER JOIN S_USUARIO_NOTIF_ALERTA_EXPED UNAE ON (");
			sql.append("UNAE.COD_N_ALERTA = NOTIF.COD_N_ALERTA ");
			sql.append("AND UNAE.COD_V_ELEMENTO = NOTIF.COD_V_ELEMENTO ");
			sql.append("AND UNAE.COD_V_EXPEDICION = NOTIF.COD_V_EXPEDICION ");
			sql.append("AND TRUNC(UNAE.FEC_D_ALBARAN) = TRUNC(NOTIF.FEC_D_ALBARAN) ");
			sql.append("AND UNAE.COD_N_DECLARACION_VALOR = NOTIF.COD_N_DECLARACION_VALOR ");
			sql.append("AND UNAE.NUM_ANYO = NOTIF.NUM_ANYO ");
			sql.append("AND UNAE.COD_N_VERSION = NOTIF.COD_N_VERSION) ");
			sql.append("INNER JOIN D_USUARIO USU ON UNAE.COD_V_USUARIO = USU.COD_V_USUARIO ");
			sql.append("WHERE NOTIF.COD_N_ALERTA = ?codigoAlerta ");
			sql.append("AND NOTIF.COD_V_ELEMENTO = ?codigoElemento ");
			sql.append("AND NOTIF.COD_V_EXPEDICION = ?codigoExpedicion ");
			sql.append("AND TRUNC(NOTIF.FEC_D_ALBARAN) = TO_DATE(?fechaAlbaran, 'DD/MM/YYYY') ");
			sql.append("AND NOTIF.COD_N_DECLARACION_VALOR = ?codigoDeclaracion ");
			sql.append("AND NOTIF.NUM_ANYO = ?anyoDeclaracion ");
			sql.append("AND NOTIF.COD_N_VERSION = ?version ");
			sql.append("UNION ");
			sql.append("SELECT CONT.TXT_NOMBRE, ");
			sql.append("CONT.TXT_DIRECCION_ELECTRONICA, ");
			sql.append("CONT.TXT_TELEFONO, ");
			sql.append("CONT.MCA_ENVIO_SMS, ");
			sql.append("CONT.MCA_ENVIO_EMAIL ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NOTIF ");
			sql.append("INNER JOIN S_CONTACTO_NOTIF_ALERTA_EXPED CNAE ON (");
			sql.append("CNAE.COD_N_ALERTA = NOTIF.COD_N_ALERTA ");
			sql.append("AND CNAE.COD_V_ELEMENTO = NOTIF.COD_V_ELEMENTO ");
			sql.append("AND CNAE.COD_V_EXPEDICION = NOTIF.COD_V_EXPEDICION ");
			sql.append("AND TRUNC(CNAE.FEC_D_ALBARAN) = TRUNC(NOTIF.FEC_D_ALBARAN) ");
			sql.append("AND CNAE.COD_N_DECLARACION_VALOR = NOTIF.COD_N_DECLARACION_VALOR ");
			sql.append("AND CNAE.NUM_ANYO = NOTIF.NUM_ANYO ");
			sql.append("AND CNAE.COD_N_VERSION = NOTIF.COD_N_VERSION) ");
			sql.append("INNER JOIN S_CONTACTO_PROVEEDOR_R CONT ON CNAE.COD_N_CONTACTO = CONT.COD_N_CONTACTO ");
			sql.append("WHERE NOTIF.COD_N_ALERTA = ?codigoAlerta ");
			sql.append("AND NOTIF.COD_V_ELEMENTO = ?codigoElemento ");
			sql.append("AND NOTIF.COD_V_EXPEDICION = ?codigoExpedicion ");
			sql.append("AND TRUNC(NOTIF.FEC_D_ALBARAN) = TO_DATE(?fechaAlbaran, 'DD/MM/YYYY') ");
			sql.append("AND NOTIF.COD_N_DECLARACION_VALOR = ?codigoDeclaracion ");
			sql.append("AND NOTIF.NUM_ANYO = ?anyoDeclaracion ");
			sql.append("AND NOTIF.COD_N_VERSION = ?version ");
			sql.append("UNION ");
			sql.append("SELECT USU.TXT_NOMBRE, ");
			sql.append("USU.TXT_DIRECCION_ELECTRONICA, ");
			sql.append("USU.TXT_TELEFONO, ");
			sql.append("USU.MCA_ENVIO_SMS, ");
			sql.append("USU.MCA_ENVIO_EMAIL ");
			sql.append("FROM S_NOTIFICACION_ALERTA NOTIF ");
			sql.append("INNER JOIN S_USUARIO_NOTIFICACION_ALERTA UNA ON (");
			sql.append("UNA.COD_N_ALERTA = NOTIF.COD_N_ALERTA ");
			sql.append("AND UNA.COD_V_ELEMENTO = NOTIF.COD_V_ELEMENTO)");
			sql.append("INNER JOIN D_USUARIO USU ON UNA.COD_V_USUARIO = USU.COD_V_USUARIO ");
			sql.append("WHERE NOTIF.COD_N_ALERTA = ?codigoAlerta ");
			sql.append("AND NOTIF.COD_V_ELEMENTO = ?codigoElemento ");
			sql.append("UNION ");
			sql.append("SELECT CONT.TXT_NOMBRE, ");
			sql.append("CONT.TXT_DIRECCION_ELECTRONICA, ");
			sql.append("CONT.TXT_TELEFONO, ");
			sql.append("CONT.MCA_ENVIO_SMS, ");
			sql.append("CONT.MCA_ENVIO_EMAIL ");
			sql.append("FROM S_NOTIFICACION_ALERTA NOTIF ");
			sql.append("INNER JOIN S_CONTACTO_NOTIFICACION_ALERTA CNA ON (");
			sql.append("CNA.COD_N_ALERTA = NOTIF.COD_N_ALERTA ");
			sql.append("AND CNA.COD_V_ELEMENTO = NOTIF.COD_V_ELEMENTO) ");
			sql.append("INNER JOIN S_CONTACTO_PROVEEDOR_R CONT ON CNA.COD_N_CONTACTO = CONT.COD_N_CONTACTO ");
			sql.append("WHERE NOTIF.COD_N_ALERTA = ?codigoAlerta ");
			sql.append("AND NOTIF.COD_V_ELEMENTO = ?codigoElemento");

			final Query query = getEntityManager().createNativeQuery(sql.toString());

			query.setParameter("codigoAlerta", codAlerta);
			query.setParameter("codigoElemento", codigoElemento);
			query.setParameter("codigoExpedicion", codigoExpedicion);
			query.setParameter("fechaAlbaran", fechaAlbaran);
			query.setParameter("codigoDeclaracion", declaracion.getCodigo());
			query.setParameter("anyoDeclaracion", declaracion.getAnyo());
			query.setParameter("version", declaracion.getVersion());

			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					ReceptoresAlertasDTO receptores = new ReceptoresAlertasDTO();

					receptores.setNombre(String.valueOf(tmp[0]));
					receptores.setApellidos(String.valueOf(tmp[0]));
					receptores.setEmail(String.valueOf(tmp[1]));
					receptores.setTelefono(String.valueOf(tmp[2]));
					receptores.setEnvioSms(tmp[3].equals("S") ? true : false);
					receptores.setEnvioEmail(tmp[4].equals("S") ? true : false);

					result.add(receptores);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetAlertasDAOImpl(GESADUAN)","getReceptoresExped",e.getClass().getSimpleName(),e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return result;

	}

	private List<ReceptoresAlertasDTO> getReceptoresNoExped(Long codAlerta, String codigoElemento){

		List<ReceptoresAlertasDTO> result = new ArrayList<>();

		try {

			final StringBuilder sql = new StringBuilder();

			sql.append(" SELECT USU.TXT_NOMBRE, ");
			sql.append(" USU.TXT_DIRECCION_ELECTRONICA, ");
			sql.append(" USU.TXT_TELEFONO, ");
			sql.append(" USU.MCA_ENVIO_SMS, ");
			sql.append(" USU.MCA_ENVIO_EMAIL ");
			sql.append(" FROM S_USUARIO_NOTIFICACION_ALERTA UNA ");
			sql.append(" INNER JOIN D_USUARIO USU ON UNA.COD_V_USUARIO = USU.COD_V_USUARIO ");
			sql.append(" WHERE UNA.COD_N_ALERTA = ?codAlerta ");
			sql.append(" AND UNA.COD_V_ELEMENTO = ?codigoElemento ");

			final Query query = getEntityManager().createNativeQuery(sql.toString());

			query.setParameter("codAlerta", codAlerta);
			query.setParameter("codigoElemento", codigoElemento);

			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					ReceptoresAlertasDTO receptores = new ReceptoresAlertasDTO();

					receptores.setNombre(String.valueOf(tmp[0]));
					receptores.setApellidos(String.valueOf(tmp[0]));
					receptores.setEmail(String.valueOf(tmp[1]));
					receptores.setTelefono(String.valueOf(tmp[2]));
					receptores.setEnvioSms(tmp[3].equals("S") ? true : false);
					receptores.setEnvioEmail(tmp[4].equals("S") ? true : false);

					result.add(receptores);
				}
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetAlertasDAOImpl(GESADUAN)","getReceptoresNoExped",e.getClass().getSimpleName(),e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return result;

	}


	  private void establecerSalidaError(Exception exception, String metodo, String codError) {
		    String login = this.securityService.getPrincipal().getLogin();

		    this.logger.error("Error ejecutando la clase: GetAlertasDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }

	  /*
	   * SE COMPRUEBA LA EXISTINCIA DE UNA NOTIFICACION EXISTENTE PARA UN ELEMENTO
	   *
	   *
	   */
	@Override
	public boolean checkNotificacionExiste(String codigoElemento) {

		if(codigoElemento != null) {

			String sql = "";

			sql += " SELECT CASE WHEN COUNT(*) > 0 THEN 'TRUE' ELSE 'FALSE' END NOTIFICACION "
					+ "FROM S_NOTIFICACION_ALERTA "
					+ "WHERE COD_V_ELEMENTO = ?codigoElemento ";

			String result = (String) getEntityManager().createNativeQuery(sql).setParameter("codigoElemento", codigoElemento).getSingleResult();

			if(result.equals("TRUE")) {
				return true;
			}else {
				return false;
			}

		}else {
			return false;
		}


	}

}