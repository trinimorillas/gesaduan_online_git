package es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1.PostDVService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1.PostDeclaracionDeValorDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.DVInsertPKDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.InputPutValueDeclarationDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.OutputPostDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.ValueDeclarationLineDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.LineaDeclaracionJPA;

public class PostDVServiceImpl implements PostDVService {
	
	@Inject
	private PostDeclaracionDeValorDAO postDVCabeceraDAO;

	@Inject
	private org.slf4j.Logger logger;
	
	private static final String LOG_FILE = "PostDVServiceImpl(GESADUAN)"; 

	@Transactional
	@Override
	public OutputPostDeclaracionesDeValorDTO createValueDeclaration(InputPutValueDeclarationDTO input) {
		OutputPostDeclaracionesDeValorDTO result = new OutputPostDeclaracionesDeValorDTO();
		DeclaracionesDeValorPostJPA declaracionJPA = new DeclaracionesDeValorPostJPA();
		DVInsertPKDTO pkResult = new DVInsertPKDTO();
		List<LineaDeclaracionJPA> listaLineas = new ArrayList<LineaDeclaracionJPA>();		

		try {
			// Cabecera
			if (input.getData().getValueDeclarationHeader().getValueDeclarationNumber() != null) {
				declaracionJPA.setCodDeclaracionValor(Integer.parseInt(input.getData().getValueDeclarationHeader().getValueDeclarationNumber()));
			}
			if (input.getData().getValueDeclarationHeader().getValueDeclarationYear() == null) {
				declaracionJPA.setAnyo(Calendar.getInstance().get(Calendar.YEAR));
			} else {
				declaracionJPA.setAnyo(Integer.parseInt(input.getData().getValueDeclarationHeader().getValueDeclarationYear()));
			}
			if (input.getData().getValueDeclarationHeader().getValueDeclarationVersion() == null) {
				declaracionJPA.setVersion(1);
			} else {
				declaracionJPA.setVersion(Integer.parseInt(input.getData().getValueDeclarationHeader().getValueDeclarationVersion()));
			}
			if (input.getData().getValueDeclarationHeader().getDispatchId() != null) {
				declaracionJPA.setExpedicion(input.getData().getValueDeclarationHeader().getDispatchId());
			}
			if (declaracionJPA.getExpedicion() != null) {
				if (!input.getData().getValueDeclarationHeader().getInternalOrderList().isEmpty()) {
					declaracionJPA.setPedido(input.getData().getValueDeclarationHeader().getInternalOrderList().get(0).getInternalOrderId());
				}
			}
			if (input.getData().getValueDeclarationHeader().getSource() != null) {
				if (input.getData().getValueDeclarationHeader().getSource().getId() != null) {
					declaracionJPA.setProveedor(input.getData().getValueDeclarationHeader().getSource().getId());
				}
				if (input.getData().getValueDeclarationHeader().getSource().getRegionId() != null) {
					declaracionJPA.setProvinciaCarga(Integer.parseInt(input.getData().getValueDeclarationHeader().getSource().getRegionId()));
				}
			}
			if (input.getData().getValueDeclarationHeader().getTarget() != null) { 
				if (input.getData().getValueDeclarationHeader().getTarget().getId() != null) {
					declaracionJPA.setCodAlmacen(input.getData().getValueDeclarationHeader().getTarget().getId());
				}
			}
			if (input.getData().getValueDeclarationHeader().getIncoterm() != null) {
				declaracionJPA.setCondicionesEntrega(input.getData().getValueDeclarationHeader().getIncoterm());
			}
			declaracionJPA.setMcaFactura("F");
			declaracionJPA.setMcaUltimaVigente(input.getData().getValueDeclarationCommonData().isLastCurrent() ? "S" : "N");
			declaracionJPA.setMcaCargaAuto(input.getData().getValueDeclarationCommonData().isManualLoading() ? "N" : "S");
			declaracionJPA.setMcaDvCorrecta(input.getData().getValueDeclarationCommonData().isValueDeclarationOk() ? "S" : "N");
			declaracionJPA.setMcaEnvio(input.getData().getValueDeclarationCommonData().isNotified() ? "S" : "N");
			if (input.getData().getValueDeclarationCommonData().getValueDeclarationDownloadDate() != null) {
				declaracionJPA.setMcaDescarga("S");
			} else {
				declaracionJPA.setMcaDescarga("N");
			}
			if (input.getData().getValueDeclarationHeader().getDeliveryNoteDate() != null) {
				declaracionJPA.setFechaAlbaran(toDate(input.getData().getValueDeclarationHeader().getDeliveryNoteDate()));
			}
			if (input.getData().getValueDeclarationHeader().getDispatchDate() != null) {
				declaracionJPA.setFechaEnvio(toDate(input.getData().getValueDeclarationHeader().getDispatchDate()));
			}
			if (input.getData().getValueDeclarationHeader().getValueDeclarationDate() != null) {
				declaracionJPA.setFechaCreacion(toDate(input.getData().getValueDeclarationHeader().getValueDeclarationDate()));
			} else {
				declaracionJPA.setFechaCreacion(new Date());
			}
			if (input.getData().getValueDeclarationCommonData().getValueDeclarationDownloadDate() != null) {
				declaracionJPA.setFechaDescarga(toDate(input.getData().getValueDeclarationCommonData().getValueDeclarationDownloadDate()));
			}
			if (input.getData().getValueDeclarationHeader().getDossierNumber() != null) {
				declaracionJPA.setNumDosier(Long.parseLong(input.getData().getValueDeclarationHeader().getDossierNumber()));
			}
			if (input.getData().getValueDeclarationHeader().getDossierYear() != null) {
				declaracionJPA.setAnyoDosier(Integer.parseInt(input.getData().getValueDeclarationHeader().getDossierYear()));
			}
			declaracionJPA.setFechaCreacionRegistro(new Date());
			declaracionJPA.setUsuarioCreacion(input.getMetadata().getUserCode());
			declaracionJPA.setApp("GESADUAN");
			
			// Líneas
			List<ValueDeclarationLineDTO> listado = input.getData().getValueDeclarationLine();

			if (listado != null && !listado.isEmpty()) {
				for (ValueDeclarationLineDTO tmp : listado) {
					LineaDeclaracionJPA linea = new LineaDeclaracionJPA();
					
					if (tmp.getProductPublicId() != null) {
						linea.setCodMerca(tmp.getProductPublicId());
					}
					if (tmp.getTaricId() != null) {
						linea.setCodigoTaric(tmp.getTaricId());
					}
					if (tmp.getRea() != null) {
						linea.setCodigoRea(tmp.getRea());
					}
					if (tmp.getProductAlternativeName() != null) {
						linea.setNombreAlternativo(tmp.getProductAlternativeName());
					}
					if (tmp.getAlternativeSalesFormatDescription() != null) {
						linea.setDescFormatoVentaAlternativo(tmp.getAlternativeSalesFormatDescription());
					}
					if (tmp.getBrandName() != null) {
						linea.setMarca(tmp.getBrandName());
					}
					if (tmp.getPackageName() != null) {
						linea.setNombreTipoBulto(tmp.getPackageName());
					}
					if (tmp.getPackageQuantity() != null) {
						linea.setNumeroDeBultos(tmp.getPackageQuantity());
					}
					if (tmp.getLineNetWeight() != null) {
						linea.setPesoNetoLinea(tmp.getLineNetWeight());
					}
					if (tmp.getLineGrossWeight() != null) {
						linea.setPesoBrutoLinea(tmp.getLineGrossWeight());
					}
					if (tmp.getVolume() != null) {
						linea.setVolumenUnidad(tmp.getVolume());
					}
					if (tmp.getFormatQuantity() != null) {
						linea.setCantidadFormato(tmp.getFormatQuantity());
					}
					if (tmp.getUnitPrice() != null) {
						linea.setPrecioUnidad(tmp.getUnitPrice());
					}
					if (tmp.getTotalLineAmount() != null) {
						linea.setImporteTotal(tmp.getTotalLineAmount());
					}
					if (tmp.getAlcoholPercentage() != null) {
						linea.setGradoAlcohol(tmp.getAlcoholPercentage());
					}
					if (tmp.getPlateGrade() != null) {
						linea.setGradoPlato(tmp.getPlateGrade());
					}
					if (tmp.getSourceCountryId() != null) {
						linea.setPaisOrigen(tmp.getSourceCountryId());
					}
					if (tmp.getIsLpc() != null) {
						linea.setEsListoParaComer(tmp.getIsLpc() ? "S" : "N");
					}
					if (tmp.getHasError() != null) {
						linea.setMarcaError(tmp.getHasError());
					}
					linea.setFechaCreacion(new Date());
					linea.setCodAplicacion("GESADUAN");
					linea.setUsuarioCreacion(input.getMetadata().getUserCode());

					listaLineas.add(linea);
				}
			}
			
			declaracionJPA.setLineasProductos(listaLineas);
			
			DeclaracionesDeValorPostPK dvpk = postDVCabeceraDAO.postCabecera(declaracionJPA);
			
			pkResult.setValueDeclarationNumber(dvpk.getCodDeclaracionValor());
			pkResult.setValueDeclarationYear(dvpk.getAnyo());
			pkResult.setValueDeclarationVersion(dvpk.getVersion());

			result.setDatos(pkResult);
			result.setMetadata(input.getMetadata());
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "createValueDeclaration", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;	
	}
	
	private Date toDate(String fecha) {
		Date fechaEnvio = null;
		if (fecha != null) {
			try {
				fechaEnvio = new SimpleDateFormat("yyyy/MM/dd").parse(fecha);
			} catch (Exception e) {
				this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "toDate", e.getClass().getSimpleName(), e.getMessage());					
			}
		}
		
		return fechaEnvio;
	}

}