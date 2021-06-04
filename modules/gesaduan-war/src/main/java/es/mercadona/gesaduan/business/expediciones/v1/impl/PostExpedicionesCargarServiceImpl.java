package es.mercadona.gesaduan.business.expediciones.v1.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.core.io.Resource;
import es.mercadona.gesaduan.business.common.v1.FilesService;
import es.mercadona.gesaduan.business.expediciones.v1.PostExpedicionesCargarService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1.PostDeclaracionDeValorDAO;
import es.mercadona.gesaduan.dao.expediciones.postexpediciones.v1.PostExpedicionesDAO;
import es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1.ExpedicionDTO;
import es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1.ExpedicionResponsePKsDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorPK;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;
import es.mercadona.gesaduan.jpa.expediciones.v1.postexpediciones.ExpedicionesCabeceraJPA;


public class PostExpedicionesCargarServiceImpl implements PostExpedicionesCargarService {

	@Inject
	private FilesService filesService;

	@Inject
	private PostExpedicionesDAO postExpedicionesDAO;

	@Inject
	private PostDeclaracionDeValorDAO postDeclaracionDeValorDAO;

	@Inject
	private org.slf4j.Logger logger;

	@Inject
	private SecurityService securityService;

	@Transactional
	@Override
	public DeclaracionesDeValorPK cargarExpedicion(Resource expedicion, String usuario, String locale) throws Exception {

		DeclaracionesDeValorPK dv = new DeclaracionesDeValorPK();
		try {
			ExpedicionDTO expDTO = leerExcelExpedicion(expedicion);

			/* Comprobamos que la expedición está validada y hay lineas */
			if(expDTO.isEsCorrecta() && !expDTO.getLineas().isEmpty()) {


				/* PASO 1: Se crea la expedicion */
				ExpedicionesCabeceraJPA expJPA = new ExpedicionesCabeceraJPA();
				expJPA.setCodAplicacion(expDTO.getCodAplicación());
				expJPA.setCodVExpedicion(expDTO.getCodVExpedicion());
				expJPA.setCodVPedido(expDTO.getCodPedido());
				expJPA.setFechaAlbaran(expDTO.getFechaAlbaran());
				expJPA.setCondicionesEntrega(expDTO.getCondicionesEntrega());
				expJPA.setMcaDVGenerada("N");
				expJPA.setCreationDate(new Date());
				expJPA.setCreationUser(usuario);
				expJPA.setEditDate(new Date());
				expJPA.setEditUser(usuario);

				ExpedicionResponsePKsDTO pkExpedicion = this.postExpedicionesDAO.postCabecera(expJPA);


				/* PASO 2: Se crea la DV con la pk de la expedicion */
				if(pkExpedicion.getCrearDV()) {

					DeclaracionesDeValorPostJPA dvJPA = new DeclaracionesDeValorPostJPA();

					dvJPA.setAnyo(Calendar.getInstance().get(Calendar.YEAR));
					dvJPA.setExpedicion(pkExpedicion.getPkExp().getCodVExpedicion());
					dvJPA.setVersion(1);
					dvJPA.setCondicionesEntrega(expDTO.getCondicionesEntrega());
					dvJPA.setFechaAlbaran(pkExpedicion.getPkExp().getFechaAlbaran());
					dvJPA.setUsuarioCreacion(usuario);
					dvJPA.setUsuarioEdit(usuario);
					dvJPA.setFechaCreacion(new Date());
					dvJPA.setFechaCreacionRegistro(new Date());
					dvJPA.setFechaModificacionRegistro(new Date());
					dvJPA.setApp("GESADUAN");
					dvJPA.setMcaCargaAuto("E");
					dvJPA.setMcaDescarga("N");
					dvJPA.setMcaDvCorrecta("N");
					dvJPA.setMcaEnvio("N");
					dvJPA.setMcaFactura("D");
					dvJPA.setMcaUltimaVigente("S");

					DeclaracionesDeValorPostPK dvPK = this.postDeclaracionDeValorDAO.postCabecera(dvJPA);
					dv.setAnyo(dvPK.getAnyo());
					dv.setCodDeclaracionValor(dvPK.getCodDeclaracionValor());
					dv.setVersion(dvPK.getVersion());

				}
				/* PASO 3: Se genera el fichero EDI y se deja en ftp de gesaduan online */

				Date curDate = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
			    String DateToStr = format.format(curDate);

				String nameFile = "desadv-".
						concat(expDTO.getCodVExpedicion()).
						concat("-").
						concat(DateToStr).
						concat(".lot");

				this.filesService.createFile(nameFile, true, expDTO.getLineas());

				 /* PASO 4: Se genera una alerta en GESADUAN */
				/* Se elimina la alerta
				Integer codigoAlerta = 1;
				String codigoElemento = pkExpedicion.getPkExp().getCodVExpedicion();
				crearAlertasService.crearAlerta(codigoAlerta, codigoElemento, usuario);
				*/


			}
		} catch (Exception e) {
			throw e;
		}

		return dv;
	}


	private ExpedicionDTO leerExcelExpedicion(Resource expedicion) throws IOException, ParseException, GesaduanException {

		ExpedicionDTO expDTO = new ExpedicionDTO();

		try {
			XSSFWorkbook wb;

			wb = new XSSFWorkbook(expedicion.getInputStream());
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFSheet sheetLineas = wb.getSheetAt(1);

			/* Valor validacion */
			String validacion = sheet.getRow(3).getCell(15).getStringCellValue();
			
			String version = sheet.getRow(1).getCell(15).getStringCellValue();
			String versionExcelBD = postExpedicionesDAO.comprobarVersion();
			
			if (!version.equals(versionExcelBD))
				throw new GesaduanException(EnumGesaduanException.VERSION_DISTINTA_EXCEL);
			
			/* Numero Albaran */
			Cell celdaAlbaran = sheet.getRow(5).getCell(4);
			celdaAlbaran.setCellType(Cell.CELL_TYPE_STRING);
			String numAlbaran = celdaAlbaran.getStringCellValue();
			
			// Obtener número expedición y pedido de la pestaña de resultados
			Cell celdaExpedicion = sheetLineas.getRow(0).getCell(0);
			celdaExpedicion.setCellType(Cell.CELL_TYPE_STRING);
			String cabeceraExpedicion = celdaExpedicion.getStringCellValue();
			String[] celdaExpedicionResultados = cabeceraExpedicion.split("\\|");
			String numExpedicion = celdaExpedicionResultados[1];
			String numPedidoResultados = celdaExpedicionResultados[7];
			
			if (!numExpedicion.equals(numAlbaran))
				throw new GesaduanException(EnumGesaduanException.NUM_ALBARAN_DISTINTO);

			/* Numero Pedido */
			Cell celdaNumPedido = sheet.getRow(6).getCell(4);
			celdaNumPedido.setCellType(Cell.CELL_TYPE_STRING);
			String numPedido = celdaNumPedido.getStringCellValue();
			
			if (!numPedidoResultados.equals(numPedido))
				throw new GesaduanException(EnumGesaduanException.NUM_PEDIDO_DISTINTO);

			/* Fecha Albaran */
			Date fecAlbaran = new SimpleDateFormat("dd/MM/yyyy").parse(sheet.getRow(7).getCell(4).getStringCellValue());
			/* Condiciones Entrega*/
			String condicionesEntrega = sheet.getRow(6).getCell(6).getStringCellValue().substring(0, 3);

			/* Lineas Expedicion */
			List<String> lineas = new ArrayList();
			for (int i = 0; i < 100; i++) {
				try {
					Cell cell = sheetLineas.getRow(i).getCell(0);
					lineas.add(cell.getStringCellValue());
				} catch (NullPointerException NPE) {
					break;
				}
			}

			expDTO.setLineas(lineas);
			expDTO.setCodAplicación("GESADUAN");
			if (validacion.equals("OK"))
				expDTO.setEsCorrecta(true);
			else
				expDTO.setEsCorrecta(false);
			expDTO.setCodVExpedicion(numAlbaran);
			expDTO.setCodPedido(numPedido);
			expDTO.setFechaAlbaran(fecAlbaran);
			expDTO.setCondicionesEntrega(condicionesEntrega);

		} catch (IOException ioe) {
			throw new ApplicationException(ioe.getMessage());
		} catch (ParseException pe) {
			throw new ApplicationException(pe.getMessage());
		} catch (Exception e) {
			throw e;
		}

		return expDTO;
	}



	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();

		    this.logger.error("Error ejecutando la clase: PostExpedicionesCargarServiceImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }

}
