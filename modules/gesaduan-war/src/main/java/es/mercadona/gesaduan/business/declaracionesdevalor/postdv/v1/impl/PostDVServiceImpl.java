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
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.InputPutVDDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.OutputPutVDDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.VDLineDTO;
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
	public OutputPutVDDTO createValueDeclaration(InputPutVDDTO input) {
		OutputPutVDDTO result = new OutputPutVDDTO();
		DeclaracionesDeValorPostJPA declaracionJPA = new DeclaracionesDeValorPostJPA();
		DVInsertPKDTO pkResult = new DVInsertPKDTO();
		List<LineaDeclaracionJPA> listaLineas = new ArrayList<LineaDeclaracionJPA>();

		try {
			// Cabecera
			if (input.getData().getHeader().getValueDeclarationData().getValueDeclarationIds().getValueDeclarationNumber() != null) {
				declaracionJPA.setCodDeclaracionValor(Integer.parseInt(input.getData().getHeader().getValueDeclarationData().getValueDeclarationIds().getValueDeclarationNumber()));
			}
			if (input.getData().getHeader().getValueDeclarationData().getValueDeclarationIds().getValueDeclarationYear() == null) {
				declaracionJPA.setAnyo(Calendar.getInstance().get(Calendar.YEAR));
			} else {
				declaracionJPA.setAnyo(Integer.parseInt(input.getData().getHeader().getValueDeclarationData().getValueDeclarationIds().getValueDeclarationYear()));
			}
			if (input.getData().getHeader().getValueDeclarationData().getValueDeclarationIds().getValueDeclarationVersion() == null) {
				declaracionJPA.setVersion(1);
			} else {
				declaracionJPA.setVersion(Integer.parseInt(input.getData().getHeader().getValueDeclarationData().getValueDeclarationIds().getValueDeclarationVersion()));
			}
			if (input.getData().getHeader().getValueDeclarationData().getDispatchCode() != null) {
				declaracionJPA.setExpedicion(input.getData().getHeader().getValueDeclarationData().getDispatchCode());
			}

			if (input.getData().getHeader().getInternalOrderList() != null) {
				if (!input.getData().getHeader().getInternalOrderList().isEmpty()) {
					declaracionJPA.setPedido(input.getData().getHeader().getInternalOrderList().get(0).getInternalOrderId());
				}
			}
			if (input.getData().getHeader().getSource() != null) {
				if (input.getData().getHeader().getSource().getPublicId() != null) {
					//declaracionJPA.setProveedor(input.getData().getHeader().getSource().getPublicId());
					declaracionJPA.setProveedor(postDVCabeceraDAO.getProveedor(input.getData().getHeader().getSource().getPublicId()));
				}
				if (input.getData().getHeader().getSource().getRegionId() != null) {
					declaracionJPA.setProvinciaCarga(Integer.parseInt(input.getData().getHeader().getSource().getRegionId()));
				}
			}
			if (input.getData().getHeader().getTarget() != null) { 
				if (input.getData().getHeader().getTarget().getId() != null) {
					declaracionJPA.setCodAlmacen(input.getData().getHeader().getTarget().getId());
				}
			}
			if (input.getData().getHeader().getValueDeclarationData().getIncotermId() != null) {
				declaracionJPA.setCondicionesEntrega(input.getData().getHeader().getValueDeclarationData().getIncotermId());
			}
			declaracionJPA.setMcaFactura("F");
			declaracionJPA.setMcaUltimaVigente(input.getData().getCommonData().isIsLastCurrent() ? "S" : "N");
			declaracionJPA.setMcaCargaAuto(input.getData().getCommonData().isIsAutomaticLoading() ? "S" : "N");
			declaracionJPA.setMcaDvCorrecta(input.getData().getCommonData().isIsValueDeclarationOk() ? "S" : "N");
			declaracionJPA.setMcaEnvio(input.getData().getCommonData().isIsNotified() ? "S" : "N");
			if (input.getData().getCommonData().getValueDeclarationDownloadDate() != null) {
				declaracionJPA.setMcaDescarga("S");
			} else {
				declaracionJPA.setMcaDescarga("N");
			}
			if (input.getData().getHeader().getValueDeclarationData().getDeliveryNoteDate() != null) {
				declaracionJPA.setFechaAlbaran(toDate(input.getData().getHeader().getValueDeclarationData().getDeliveryNoteDate()));
			}
			if (input.getData().getHeader().getValueDeclarationData().getDispatchDate() != null) {
				declaracionJPA.setFechaEnvio(toDate(input.getData().getHeader().getValueDeclarationData().getDispatchDate()));
			}
			if (input.getData().getHeader().getValueDeclarationData().getValueDeclarationGenerationDate() != null) {
				declaracionJPA.setFechaCreacion(toDate(input.getData().getHeader().getValueDeclarationData().getValueDeclarationGenerationDate()));
			} else {
				declaracionJPA.setFechaCreacion(new Date());
			}
			if (input.getData().getCommonData().getValueDeclarationDownloadDate() != null) {
				declaracionJPA.setFechaDescarga(toDate(input.getData().getCommonData().getValueDeclarationDownloadDate()));
			}
			if (input.getData().getHeader().getValueDeclarationData().getDossierIds() != null) {
				if (input.getData().getHeader().getValueDeclarationData().getDossierIds().getDossierNumber() != null) {
					declaracionJPA.setNumDosier(Long.parseLong(input.getData().getHeader().getValueDeclarationData().getDossierIds().getDossierNumber()));
				}
				if (input.getData().getHeader().getValueDeclarationData().getDossierIds().getDossierYear() != null) {
					declaracionJPA.setAnyoDosier(Integer.parseInt(input.getData().getHeader().getValueDeclarationData().getDossierIds().getDossierYear()));
				}
			}
			declaracionJPA.setFechaCreacionRegistro(new Date());
			declaracionJPA.setUsuarioCreacion(input.getMetadata().getUserId());
			declaracionJPA.setApp("GESADUAN");
			
			// Líneas
			List<VDLineDTO> listado = input.getData().getLineList();

			if (listado != null && !listado.isEmpty()) {
				for (VDLineDTO tmp : listado) {
					LineaDeclaracionJPA linea = new LineaDeclaracionJPA();
					
					if (tmp.getProductPublicId() != null) {
						linea.setCodMerca(tmp.getProductPublicId());
					}
					if (tmp.getTaricId() != null) {
						linea.setCodigoTaric(tmp.getTaricId());
					}
					if (tmp.getSsrId() != null) {
						linea.setCodigoRea(tmp.getSsrId());
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
					if (tmp.getIsReadyToEat() != null) {
						linea.setEsListoParaComer(tmp.getIsReadyToEat() ? "S" : "N");
					}
					if (tmp.getHasError() != null) {
						linea.setMarcaError(tmp.getHasError());
					}
					linea.setFechaCreacion(new Date());
					linea.setCodAplicacion("GESADUAN");
					linea.setUsuarioCreacion(input.getMetadata().getUserId());

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