package es.mercadona.gesaduan.business.equipotransporte.putequipotransporte.v1.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.putequipotransporte.v1.PutEquipoTransporteService;
import es.mercadona.gesaduan.dao.equipotransporte.putequipotransporte.v1.PutEquipoTransporteDAO;
import es.mercadona.gesaduan.dao.planembarques.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.restfull.OutputEquipoTransportePutDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public class PutEquipoTransporteServiceImpl implements PutEquipoTransporteService {

	@Inject
	private PutEquipoTransporteDAO putEquipoTransporteDao;
	
	@Inject
	private CambiarEstadoDAO cambiarEstadoDao;
	
	@Override
	public OutputEquipoTransportePutDTO crearEquipoTransporte(InputDatosPutDTO input) throws GesaduanException {
		
		if (cambiarEstadoDao.getEstadoActual(input.getDatos().getCodigoEmbarque()) != 1)
			throw new GesaduanException(EnumGesaduanException.ESTADO_NO_EN_CURSO);
		
		Date fechaCarga = null;
		if (input.getDatos().getFechaCarga() != null) {	
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				fechaCarga = dateFormat.parse(input.getDatos().getFechaCarga());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		EquipoTransporteJPA datos = new EquipoTransporteJPA();
		datos.setCodigoEmbarque(input.getDatos().getCodigoEmbarque()); 
		datos.setMatricula(input.getDatos().getMatricula());
		datos.setCodigoProveedor(input.getDatos().getCodigoTransportista());
		datos.setTemperatura(input.getDatos().getCodigoTemperatura());
		datos.setCapacidad(input.getDatos().getCapacidad());
		datos.setOcupacion(input.getDatos().getOcupacion());
		datos.setCodigoEstado(input.getDatos().getCodigoEstado());
		datos.setFechaCarga(fechaCarga);
		datos.setCodigoEstadoDocumentacion(1);		
		datos.setObservaciones(input.getDatos().getObservaciones());
		datos.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		
		OutputEquipoTransportePutDTO result = putEquipoTransporteDao.crearEquipoTransporte(datos);

		return result;
	}
	
	@Override
	public OutputEquipoTransportePutDTO modificarEquipoTransporte(InputDatosPutDTO input) throws GesaduanException {
		
		if (cambiarEstadoDao.getEstadoActual(input.getDatos().getCodigoEmbarque()) != 1)
			throw new GesaduanException(EnumGesaduanException.ESTADO_NO_EN_CURSO);
		
		EquipoTransporteJPA datos = new EquipoTransporteJPA();		
		datos.setCodigoEquipo(input.getDatos().getCodigoEquipo());
		
		Date fechaCarga = null;
		if (input.getDatos().getFechaCarga() != null) {	
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				fechaCarga = dateFormat.parse(input.getDatos().getFechaCarga());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
				
		datos.setMatricula(null);
		datos.setCodigoProveedor(null);
		datos.setTemperatura(null);
		datos.setFechaCarga(null);
		datos.setCapacidad(null);		
		datos.setObservaciones(null);
		
		if (input.getDatos().getMatricula() != null) datos.setMatricula(input.getDatos().getMatricula());
		if (input.getDatos().getCodigoTransportista() != null) datos.setCodigoProveedor(input.getDatos().getCodigoTransportista());
		if (input.getDatos().getCodigoTemperatura() != null) datos.setTemperatura(input.getDatos().getCodigoTemperatura());
		if (input.getDatos().getFechaCarga() != null) datos.setFechaCarga(fechaCarga);
		if (input.getDatos().getCapacidad() != null) datos.setCapacidad(input.getDatos().getCapacidad());		
		if (input.getDatos().getObservaciones() != null) datos.setObservaciones(input.getDatos().getObservaciones());
		datos.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		
		OutputEquipoTransportePutDTO result = putEquipoTransporteDao.modificarEquipoTransporte(datos);

		return result;
	}
}
