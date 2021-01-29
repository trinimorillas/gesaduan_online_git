package es.mercadona.gesaduan.business.cargas.putcarga.v1.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.cargas.putcarga.v1.PutCargaService;
import es.mercadona.gesaduan.dao.cargas.putcarga.v1.PutCargaDAO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.CargaDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull.CargaOutputDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.InputDatosPutCargaDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull.DatosPutCargaDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull.OutputPutCargaDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasPkJPA;

public class PutCargaServiceImpl implements PutCargaService {

	@Inject
	private PutCargaDAO putCargaDao;
	
	@Override
	public OutputPutCargaDTO putCarga(InputDatosPutCargaDTO input) {
		
		OutputPutCargaDTO result = new OutputPutCargaDTO();
		DatosPutCargaDTO datos = new DatosPutCargaDTO();
		List<CargaOutputDTO> listaCargas = new ArrayList<>();
		List<CargaDTO> cargas = input.getDatos().getCarga();
		
		for (CargaDTO carga: cargas) {
			CargasPkJPA pkCarga = new CargasPkJPA();
			CargasJPA datosCarga = new CargasJPA();
			if (carga.getCodigoCarga() == null || carga.getCodigoCarga().toString().equals("")) {
				datosCarga = anyadirDatosCarga(carga, pkCarga);
				datosCarga.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario());
				CargaOutputDTO cargaOutput = new CargaOutputDTO();
				cargaOutput.setCodigoCarga(putCargaDao.crearCarga(datosCarga));
				cargaOutput.setResultadoOK("S");
				listaCargas.add(cargaOutput);
			} else {
				/* if (putCargaDao.validarEstadoCarga(carga) == 0) {*/
					pkCarga.setCodigoCarga(carga.getCodigoCarga());
					datosCarga = anyadirDatosCarga(carga, pkCarga);
					datosCarga.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario());
					CargaOutputDTO cargaOutput = new CargaOutputDTO();
					cargaOutput.setCodigoCarga(putCargaDao.modificarCarga(datosCarga));
					cargaOutput.setResultadoOK("S");
					listaCargas.add(cargaOutput);
				/* }
				else {
					CargaOutputDTO cargaOutput = new CargaOutputDTO();
					cargaOutput.setCodigoCarga(carga.getCodigoCarga());
					cargaOutput.setResultadoOK("N");
					cargaOutput.setMensajeResultado("No se pueden modificar cargas con estado distinto de Pendiente Asignar o con divisiones asignadas.");
					listaCargas.add(cargaOutput);
				} */
			}
		}
		
		if (listaCargas.size() == 0) listaCargas = null;
		datos.setCarga(listaCargas);
		result.setDatos(datos);
		
		
		return result;
	}
	
	private CargasJPA anyadirDatosCarga(CargaDTO cargaOrigen, CargasPkJPA pkCarga) {
		
		CargasJPA datosCarga = new CargasJPA();
		pkCarga.setCodigoCentroOrigen(cargaOrigen.getCodigoAlmacenOrigen());
		datosCarga.setId(pkCarga);
		datosCarga.setCodigoTipoCarga(cargaOrigen.getCodigoTipoCarga());
		datosCarga.setCodigoTipoSuministro(cargaOrigen.getCodigoSuministro());
		datosCarga.setCategoria(cargaOrigen.getCodigoCategoria());
		datosCarga.setCodigoProveedor(cargaOrigen.getCodigoProveedor());
		datosCarga.setCodigoCentroDestino(cargaOrigen.getCodigoCentroDestino());
		Date fechaServicio = null;
		if (cargaOrigen.getFechaServicio() != null) {	
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				fechaServicio = dateFormat.parse(cargaOrigen.getFechaServicio());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		datosCarga.setFechaServicio(fechaServicio);
		Date fechaEntrega = null;
		if (cargaOrigen.getFechaEntrega() != null) {	
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				fechaEntrega = dateFormat.parse(cargaOrigen.getFechaEntrega());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		datosCarga.setFechaEntrega(fechaEntrega);
		datosCarga.setNumeroHuecos(cargaOrigen.getNumeroTotalHuecos());
		datosCarga.setNumeroPesoTotal(cargaOrigen.getNumeroTotalPeso());
		datosCarga.setMcaLPC(cargaOrigen.getMarcaLpC());
		datosCarga.setNumeroHuecosRestantes(cargaOrigen.getNumeroHuecosRestantes());
		datosCarga.setNumeroPesoRestante(cargaOrigen.getNumeroPesoRestante());
		
		return datosCarga;
		
	}

}
