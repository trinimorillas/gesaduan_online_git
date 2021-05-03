package es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1.PostDVService;
import es.mercadona.gesaduan.business.proveedores.getproveedoresdetalle.v1.GetProveedoresDetalleService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1.PostDeclaracionDeValorDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.CabeceraDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.DVInsertPKDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.InputPostDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.LineaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.OutputPostDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.LineaDeclaracionJPA;

public class PostDVServiceImpl implements PostDVService {

	@Inject
	private PostDeclaracionDeValorDAO postDVCabeceraDAO;

	@Inject
	private GetProveedoresDetalleService getProveedoresDetalleService;

	@Inject
	private org.slf4j.Logger logger;

	@Inject
	private SecurityService securityService;
	
	private static final String LOG_FILE = "PostDVServiceImpl(GESADUAN)"; 
	private static final String LOG_METHOD_OUTPUTPOSTDECLARACIONES = "OutputPostDeclaracionesDeValorDTO"; 	
	private static final String DATE_FORMAT = "yyyy-MM-dd";	

	@Transactional
	@Override
	public OutputPostDeclaracionesDeValorDTO createDeclaracionesDeValor(InputPostDeclaracionesDeValorDTO input) {

		DeclaracionesDeValorPostJPA declaracionJPA = new DeclaracionesDeValorPostJPA();
		OutputPostDeclaracionesDeValorDTO result = new OutputPostDeclaracionesDeValorDTO();
		DVInsertPKDTO pkResult = new DVInsertPKDTO();
		List<LineaDeclaracionJPA> listaLineas = new ArrayList<LineaDeclaracionJPA>();

		try {
			/* PARAMETROS CABECERA */
			/* DATOS DECLARACION */
			CabeceraDTO cabecera = input.getDatos().getCabecera();
			
			/* DATOS COMUNES*/
			getDatosComunes(declaracionJPA, cabecera);
			
			/* DATOS FACTURA */
			getDatosFactura(declaracionJPA, cabecera);
			
			/* DATOS PEDIDO */
			String codigoPedido = cabecera.getPedidoList().getCodigoPedido();
			
			/* PROVEEDOR/ORIGEN */
			getOrigen(declaracionJPA, cabecera);
			
			/* DATOS DESTINATARIO */
			String codigoAlmacen = cabecera.getDestino().getCodigoDestino();
			declaracionJPA.setCodAlmacen(codigoAlmacen);

			if (codigoPedido != null) {
				declaracionJPA.setPedido(codigoPedido);
			}
			
			Date fechaInicio = new Date();

			declaracionJPA.setFechaCreacion(fechaInicio);
			declaracionJPA.setFechaCreacionRegistro(fechaInicio);
			declaracionJPA.setMcaCargaAuto("N");
			
			declaracionJPA.setApp("GESADUAN");

			

			

			
			
			

			

			

			try {
				if (input.getDatos().getDatosComunes().isEsCorrecta()) {
					declaracionJPA.setMcaDvCorrecta("S");
				} else {
					declaracionJPA.setMcaDvCorrecta("N");
				}
			} catch (Exception e1) {
				declaracionJPA.setMcaDvCorrecta("S");
			}

			declaracionJPA.setMcaEnvio("N");
			declaracionJPA.setMcaDescarga("N");
			declaracionJPA.setMcaFactura("F");

			declaracionJPA.setMcaUltimaVigente("S");
			declaracionJPA.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario());

			/* LINEAS */
			/* RECUPERAMOS LAS LISTAS A INSERTAR */

			List<LineaDTO> listado = input.getDatos().getLineas();

			/* ITERAMOS LAS LINEAS E INSERTAMOS EN EL OBJETO PARA PERSISTIR */

			if (listado != null && !listado.isEmpty()) {
				for (LineaDTO tmp : listado) {
					LineaDeclaracionJPA linea = new LineaDeclaracionJPA();

					Integer codigoMerca = tmp.getCodigoPublico();
					Long codigoTaric = tmp.getCodigoTaric();
					String codigoRea = tmp.getCodigoRea();
					String nombreAlternativo = tmp.getNombreAlternativo();
					String descFormatoVentaAlternativo = tmp.getDescFormatoVentaAlternativo();
					String nombreTipoBulto = tmp.getNombreTipoBulto();
					Integer numeroBultos = tmp.getNumDeBultos();
					Double pesoNetoLinea = tmp.getPesoNetoLinea();
					Double pesoBrutoLinea = tmp.getPesoBrutoLinea();
					Double volumenUnidad = tmp.getVolumenUnidad();
					Double cantidadFormato = tmp.getCantidadFormato();
					Double precioUnidad = tmp.getPrecioUnidad();
					Double importeTotal = tmp.getImporteTotal();
					Double gradoAlcohol = tmp.getGradoAlcohol();
					Double gradoPlato = tmp.getGradoPlato();
					String marca = tmp.getMarca();
					String marcaError = tmp.getMarcaError();

					String paisOrigen = null;
					if (tmp.getPaisOrigen() != null) {
						paisOrigen = tmp.getPaisOrigen();
					}

					linea.setCantidadFormato(cantidadFormato);

					if (codigoMerca != null) {
						linea.setCodMerca(codigoMerca);
					}
					
					if (codigoTaric != null) {
						linea.setCodigoTaric(codigoTaric);
					}
					
					if (codigoRea != null) {
						linea.setCodigoRea(codigoRea);
					}
					
					if (nombreAlternativo != null) {
						linea.setNombreAlternativo(nombreAlternativo);
					}
					
					if (descFormatoVentaAlternativo != null) {
						linea.setDescFormatoVentaAlternativo(descFormatoVentaAlternativo);
					}
					
					if (nombreTipoBulto != null) {
						linea.setNombreTipoBulto(nombreTipoBulto);
					}
					
					if (pesoNetoLinea != null) {
						linea.setPesoNetoLinea(pesoNetoLinea);
					}
					
					if (pesoBrutoLinea != null) {
						linea.setPesoBrutoLinea(pesoBrutoLinea);
					}
					
					if (volumenUnidad != null) {
						linea.setVolumenUnidad(volumenUnidad);
					}
					
					if (cantidadFormato != null) {
						linea.setCantidadFormato(cantidadFormato);
					}
					
					if (marca != null) {
						linea.setMarca(marca);
					}
					
					if (marcaError != null) {
						linea.setMarcaError(marcaError);
					}

					linea.setFechaCreacion(new Date());
					linea.setFechaModificacion(new Date());

					if (precioUnidad != null) {
						linea.setPrecioUnidad(precioUnidad);
					}
					
					if (importeTotal != null) {
						linea.setImporteTotal(importeTotal);
					}
					
					if (gradoAlcohol != null) {
						linea.setGradoAlcohol(gradoAlcohol);
					}
					
					if (gradoPlato != null) {
						linea.setGradoPlato(gradoPlato);
					}
					
					if (numeroBultos != null) {
						linea.setNumeroDeBultos(numeroBultos);
					}
					
					if (paisOrigen != null) {
						linea.setPaisOrigen(paisOrigen);
					}

					boolean esListoParaComer = tmp.isEsListoParaComer();
					if (esListoParaComer) {
						linea.setEsListoParaComer("S");
					} else {
						linea.setEsListoParaComer("N");
					}

					linea.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
					linea.setUsuarioEdit(input.getMetadatos().getCodigoUsuario().toUpperCase());
					linea.setCodAplicacion("GESADUAN");

					listaLineas.add(linea);
				}
			}

			declaracionJPA.setLineasProductos(listaLineas);

			/* FIN LINEAS */

			DeclaracionesDeValorPostPK dvpk = postDVCabeceraDAO.postCabecera(declaracionJPA);
			pkResult.setNumeroDecalaracion(dvpk.getCodDeclaracionValor());
			pkResult.setAnyo(dvpk.getAnyo());
			pkResult.setVersion(dvpk.getVersion());

			result.setNumeroDeclaracion(pkResult);
			
			// Crear una alerta cuando la factura que se modifica está asociada a un dosier ya descargado
			postDVCabeceraDAO.generarAlerta(input.getMetadatos().getCodigoUsuario(), cabecera.getDatosFactura().getNumFactura(), cabecera.getDatosFactura().getAnyoFactura());
			
			// Si la factura estaba asociada a un dosier con errores y todos ellos se han resuleto, se marca el dosier como OK y se genera su correspondiente notificación
			postDVCabeceraDAO.marcarDosierOK(input.getMetadatos().getCodigoUsuario(), cabecera.getDatosFactura().getNumFactura(), cabecera.getDatosFactura().getAnyoFactura());
		} catch (NumberFormatException nfe) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, LOG_METHOD_OUTPUTPOSTDECLARACIONES, nfe.getClass().getSimpleName(), nfe.getMessage());
			establecerSalidaError(nfe, "createFactura");
			throw new ApplicationException(nfe.getMessage());
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, LOG_METHOD_OUTPUTPOSTDECLARACIONES, e.getClass().getSimpleName(), e.getMessage());
			establecerSalidaError(e, "createFactura");
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}

	/**
	 * @param declaracionJPA
	 * @param cabecera
	 * @return
	 */
	private void getDatosFactura(DeclaracionesDeValorPostJPA declaracionJPA, CabeceraDTO cabecera) {
		if (cabecera.getDatosFactura().getAnyoFactura() != null) {
			declaracionJPA.setAnyo(Integer.parseInt(cabecera.getDatosFactura().getAnyoFactura()));
		} else {
			declaracionJPA.setAnyo(Calendar.getInstance().get(Calendar.YEAR));
		}

		if (cabecera.getDatosFactura().getNumFactura() != null) {
			declaracionJPA.setCodDeclaracionValor(Integer.parseInt(cabecera.getDatosFactura().getNumFactura()));
		}

		if (cabecera.getDatosFactura().getVersion() != null) {
			declaracionJPA.setVersion(Integer.parseInt(cabecera.getDatosFactura().getVersion()));
		} else {
			declaracionJPA.setVersion(1);
		}
		
		String codigoExpedicion = cabecera.getDatosFactura().getCodigoExpedicion();
		String condicionesEntrega = cabecera.getDatosFactura().getCondicionesEntrega();

		String fechaAlbaran = cabecera.getDatosFactura().getFechaAlbaran() != null ? cabecera.getDatosFactura().getFechaAlbaran().substring(0, 10) : null;
		String fechaEnvio = (cabecera.getDatosFactura().getFechaEnvio() != null
				? cabecera.getDatosFactura().getFechaEnvio().substring(0, 10)
				: null);
		
		if (codigoExpedicion != null) {
			declaracionJPA.setExpedicion(codigoExpedicion);
		}
		
		if (condicionesEntrega != null) {
			declaracionJPA.setCondicionesEntrega(condicionesEntrega);
		}

		if (fechaAlbaran != null) {
			Date fecha;
			try {
				fecha = new SimpleDateFormat(DATE_FORMAT).parse(fechaAlbaran);
				declaracionJPA.setFechaAlbaran(fecha);
			} catch (ParseException e) {
				this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, LOG_METHOD_OUTPUTPOSTDECLARACIONES, e.getClass().getSimpleName(), e.getMessage());	  
			}
		}

		if (fechaEnvio != null) {
			Date fecha;
			try {
				fecha = new SimpleDateFormat(DATE_FORMAT).parse(fechaEnvio);
				declaracionJPA.setFechaEnvio(fecha);
			} catch (ParseException e) {
				this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, LOG_METHOD_OUTPUTPOSTDECLARACIONES, e.getClass().getSimpleName(), e.getMessage());					
			}
		}
	}

	/**
	 * @param declaracionJPA
	 * @param cabecera
	 */
	private void getOrigen(DeclaracionesDeValorPostJPA declaracionJPA, CabeceraDTO cabecera) {
		String codigoOrigen = cabecera.getOrigen().getCodigoOrigen();
		String tipoOrigen = cabecera.getOrigen().getTipoOrigen();

		if (codigoOrigen != null && tipoOrigen != null && tipoOrigen.equals("PROVEEDOR")) {
			String codigoProv = getProveedoresDetalleService.getCodigoProveedorSap(codigoOrigen);

			if (codigoProv != null) {
				declaracionJPA.setProveedor(codigoProv);
			}
		}
		
		String provinciaOrigen = cabecera.getOrigen().getProvinciaOrigen();
		if (provinciaOrigen != null) {
			declaracionJPA.setProvinciaCarga(Integer.parseInt(provinciaOrigen));
		}
	}
	
	private void getDatosComunes(DeclaracionesDeValorPostJPA declaracionJPA, CabeceraDTO cabecera) {
		Boolean esUltimaVigente = cabecera.getDatosComunes().isEsUltimaVigente();
		Boolean esCorrecta = cabecera.getDatosComunes().isEsCorrecta();
		Boolean estaNotificada = cabecera.getDatosComunes().isEstaNotificada();
		Boolean estaDescargada = cabecera.getDatosComunes().isEstaDescargada();
		Boolean esCargaManual = cabecera.getDatosComunes().isEsCargaManual();
		String fechaDescarga = cabecera.getDatosComunes().getFechaDescarga();
		Boolean estaDescargadaExportador = cabecera.getDatosComunes().isEstaDescargadaExportador();
		String fechaDescargaExportador = cabecera.getDatosComunes().getFechaDescargaExportador();
		Boolean estaDescargadaImportador = cabecera.getDatosComunes().isEstaDescargadaImportador();
		String fechaDescargaImportador = cabecera.getDatosComunes().getFechaDescargaImportador();
		Boolean tienePdf = cabecera.getDatosComunes().isTienePdf();
		Boolean tieneCsv = cabecera.getDatosComunes().isTieneCsv();
	}

	private void establecerSalidaError(Exception exception, String metodo) {
		String login = this.securityService.getPrincipal().getLogin();		
		this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "establecerSalidaError", metodo, login, exception.getClass().getSimpleName(), exception.getMessage());
	}

}