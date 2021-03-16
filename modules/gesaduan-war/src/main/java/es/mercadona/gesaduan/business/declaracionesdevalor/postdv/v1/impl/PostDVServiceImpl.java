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
import es.mercadona.gesaduan.business.common.v1.FilesService;
import es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1.PostDVService;
import es.mercadona.gesaduan.business.proveedores.getproveedoresdetalle.v1.GetProveedoresDetalleService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1.PostDeclaracionDeValorDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.CabeceraDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.DVInsertPKDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.DatosDestinatarioDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.InputPostDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.LineaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.OutputPostDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.ProveedorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.ProvinciaDeCargaDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.LineaDeclaracionJPA;

public class PostDVServiceImpl implements PostDVService {

	@Inject
	private PostDeclaracionDeValorDAO postDVCabeceraDAO;

	@Inject
	private GetProveedoresDetalleService getProveedoresDetalleService;

	@Inject
	private FilesService filesService;

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
			CabeceraDTO cabecera = input.getDatos().getDeclaracionDeValor().getCabecera();

			if (cabecera.getDatosDeclaracion().getAnyo() != null) {
				declaracionJPA.setAnyo(cabecera.getDatosDeclaracion().getAnyo());
			} else {
				declaracionJPA.setAnyo(Calendar.getInstance().get(Calendar.YEAR));
			}

			if (cabecera.getDatosDeclaracion().getCodigo() != null) {
				declaracionJPA.setCodDeclaracionValor(cabecera.getDatosDeclaracion().getCodigo());
			}

			if (cabecera.getDatosDeclaracion().getVersion() != null) {
				declaracionJPA.setVersion(cabecera.getDatosDeclaracion().getVersion());
			} else {
				declaracionJPA.setVersion(1);
			}

			declaracionJPA.setApp("GESADUAN");

			/* DATOS PEDIDO */
			String codigoPedido = cabecera.getDatosPedido().getCodigo();

			/* PROVEEDOR */
			ProveedorDTO proveedor = cabecera.getDatosPedido().getProveedor();

			if (proveedor != null) {

				String codigoProv = getProveedoresDetalleService.getCodigoProveedorSap(proveedor.getCodigo());

				if (codigoProv != null) {
					declaracionJPA.setProveedor(codigoProv);
				}
			}

			/* PROVINCIA DE CARGA */
			ProvinciaDeCargaDTO provinciaDeCarga = cabecera.getDatosPedido().getProvinciaDeCarga();
			Integer codigoProvincia = Integer.parseInt(provinciaDeCarga.getCodigo());

			String codigoExpedicion = cabecera.getDatosPedido().getCodigoExpedicion();
			String condicionesEntrega = cabecera.getDatosPedido().getCondicionesEntrega();

			String fechaAlbaran = (cabecera.getDatosPedido().getFechaAlbaran() != null
					? cabecera.getDatosPedido().getFechaAlbaran().substring(0, 10)
					: null);
			String fechaEnvio = (cabecera.getDatosPedido().getFechaEnvio() != null
					? cabecera.getDatosPedido().getFechaEnvio().substring(0, 10)
					: null);

			if (codigoPedido != null) {
				declaracionJPA.setPedido(codigoPedido);
			}
			if (codigoProvincia != null) {
				declaracionJPA.setProvinciaCarga(codigoProvincia);
			}
			if (codigoExpedicion != null) {
				declaracionJPA.setExpedicion(codigoExpedicion);
			}

			if (fechaAlbaran != null) {
				Date fecha;
				try {
					fecha = new SimpleDateFormat(DATE_FORMAT).parse(fechaAlbaran);
					declaracionJPA.setFechaAlbaran(fecha);
				} catch (ParseException e) {
					this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,LOG_METHOD_OUTPUTPOSTDECLARACIONES,e.getClass().getSimpleName(),e.getMessage());	  
				}

			}
			if (condicionesEntrega != null) {
				declaracionJPA.setCondicionesEntrega(condicionesEntrega);
			}

			if (fechaEnvio != null) {
				Date fecha;
				try {
					fecha = new SimpleDateFormat(DATE_FORMAT).parse(fechaEnvio);
					declaracionJPA.setFechaEnvio(fecha);
				} catch (ParseException e) {
					this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,LOG_METHOD_OUTPUTPOSTDECLARACIONES,e.getClass().getSimpleName(),e.getMessage());					
				}

			}

			/* DATOS DESTINATARIO */

			DatosDestinatarioDTO destinatarios = cabecera.getDatosDestinatario();
			if (destinatarios != null) {
				String codigoAlmacen = destinatarios.getCodigoAlmacen();

				if (codigoAlmacen != null) {
					declaracionJPA.setCodAlmacen(codigoAlmacen);
				}
			}

			Date fechaInicio = new Date();

			declaracionJPA.setFechaCreacion(fechaInicio);
			declaracionJPA.setFechaCreacionRegistro(fechaInicio);

			declaracionJPA.setMcaCargaAuto("N");

			try {
				if (input.getDatos().getDeclaracionDeValor().getDatosComunes().isEsCorrecta()) {
					declaracionJPA.setMcaDvCorrecta("S");
				} else {
					declaracionJPA.setMcaDvCorrecta("N");
				}
			} catch (Exception e1) {
				declaracionJPA.setMcaDvCorrecta("S");
			}

			declaracionJPA.setMcaEnvio("N");
			declaracionJPA.setMcaDescarga("N");

			try {
				if (input.getDatos().getDeclaracionDeValor().getDatosComunes().isEsFactura()) {
					declaracionJPA.setMcaFactura("F");
				} else {
					declaracionJPA.setMcaFactura("D");
				}
			} catch (Exception e1) {
				this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,LOG_METHOD_OUTPUTPOSTDECLARACIONES,e1.getClass().getSimpleName(),e1.getMessage());				
			}

			declaracionJPA.setMcaUltimaVigente("S");

			declaracionJPA.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario());

			/* LINEAS */

			/* RECUPERAMOS LAS LISTAS A INSERTAR */

			List<LineaDTO> listado = input.getDatos().getDeclaracionDeValor().getLineas();

			/* ITERAMOS LAS LINEAS E INSERTAMOS EN EL OBJETO PARA PERSISTIR */

			if (listado != null && !listado.isEmpty()) {
				for (LineaDTO tmp : listado) {
					LineaDeclaracionJPA linea = new LineaDeclaracionJPA();

					Integer codigoMerca = tmp.getProducto().getCodigoPublico();
					Long codigoTaric = tmp.getProducto().getCodigoTaric();
					String codigoRea = tmp.getProducto().getCodigoRea();
					String nombreAlternativo = tmp.getProducto().getNombreAlternativo();
					String descFormatoVentaAlternativo = tmp.getProducto().getDescFormatoVentaAlternativo();
					String nombreTipoBulto = tmp.getProducto().getNombreTipoBulto();
					Integer numeroBultos = tmp.getProducto().getNumeroDeBultos();
					Double pesoNetoLinea = tmp.getProducto().getPesoNetoLinea();
					Double pesoBrutoLinea = tmp.getProducto().getPesoBrutoLinea();
					Double volumenUnidad = tmp.getProducto().getVolumenUnidad();
					Double cantidadFormato = tmp.getProducto().getCantidadFormato();
					Double precioUnidad = tmp.getProducto().getPrecioUnidad();
					Double importeTotal = tmp.getProducto().getImporteTotal();
					Double gradoAlcohol = tmp.getProducto().getGradoAlcohol();
					Double gradoPlato = tmp.getProducto().getGradoPlato();
					String marca = tmp.getProducto().getMarca();

					String paisOrigen = null;
					if (tmp.getProducto().getPaisOrigen() != null) {
						paisOrigen = tmp.getProducto().getPaisOrigen();
					}

					linea.setCantidadFormato(cantidadFormato);

					Date fechaAlb;
					try {
						fechaAlb = new SimpleDateFormat(DATE_FORMAT).parse(fechaAlbaran);
						linea.setFechaAlbaran(fechaAlb);
					} catch (ParseException pe) {
						this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,LOG_METHOD_OUTPUTPOSTDECLARACIONES,pe.getClass().getSimpleName(),pe.getMessage());						
					} catch (NullPointerException npe) {
						this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,LOG_METHOD_OUTPUTPOSTDECLARACIONES,npe.getClass().getSimpleName(),npe.getMessage());						
					}

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

					boolean esListoParaComer = tmp.getProducto().isEsListoParaComer();
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

			/*
			 * COMPROBAMOS SI ES LA MISMA DV, SI ES QUE SI ES UNA MODIFICACIÓN POR LO QUE
			 * GENERAMOS EL TXT EN EL FTP PARA CREAR EL PDF, SI NO NO HACEMOS NADA
			 */
			if (dvpk.getCodDeclaracionValor()
					.equals(input.getDatos().getDeclaracionDeValor().getCabecera().getDatosDeclaracion().getCodigo())
					&& dvpk.getAnyo().equals(
							input.getDatos().getDeclaracionDeValor().getCabecera().getDatosDeclaracion().getAnyo())) {

				Date curDate = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
				String DateToStr = format.format(curDate);

				String nameFile = "generaPdfOnline-".concat(DateToStr).concat(".txt");

				List<String> lineas = new ArrayList<String> ();

				lineas.add(dvpk.getCodDeclaracionValor().toString());
				lineas.add(dvpk.getAnyo().toString());
				lineas.add(dvpk.getVersion().toString());

				filesService.createFile(nameFile, true, lineas);
			}
			/*
			 * ES UNA NUEVA DV POR LO QUE TMB CREAMOS UN FICHERO SI ES LA VERSION NÚMERO 1
			 */
			else if (dvpk.getCodDeclaracionValor() != null && dvpk.getAnyo() != null && dvpk.getVersion().equals(1)) {
				Date curDate = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
				String DateToStr = format.format(curDate);

				String nameFile = "generaPdfOnline-".concat(DateToStr).concat(".txt");

				List<String> lineas = new ArrayList<String> ();

				lineas.add(dvpk.getCodDeclaracionValor().toString());
				lineas.add(dvpk.getAnyo().toString());
				lineas.add(dvpk.getVersion().toString());

				filesService.createFile(nameFile, true, lineas);

			}

			pkResult.setNumeroDecalaracion(dvpk.getCodDeclaracionValor());
			pkResult.setAnyo(dvpk.getAnyo());
			pkResult.setVersion(dvpk.getVersion());

			result.setNumeroDeclaracion(pkResult);

		} catch (NumberFormatException nfe) {
			this.logger.error("({}-{}) ERROR - {} {}", LOG_FILE,LOG_METHOD_OUTPUTPOSTDECLARACIONES,nfe.getClass().getSimpleName(), nfe.getMessage());
			establecerSalidaError(nfe, "createDeclaracionesDeValor");
			throw new ApplicationException(nfe.getMessage());
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", LOG_FILE, LOG_METHOD_OUTPUTPOSTDECLARACIONES,e.getClass().getSimpleName(), e.getMessage());
			establecerSalidaError(e, "createDeclaracionesDeValor");
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}

	private void establecerSalidaError(Exception exception, String metodo) {

		String login = this.securityService.getPrincipal().getLogin();
		
		this.logger.error("({}-{}) ERROR - {}{} {} {}",LOG_FILE,"establecerSalidaError",metodo,login,exception.getClass().getSimpleName(),exception.getMessage());
	}

}
