package es.mercadona.gesaduan.business.proveedores.putpuertoagencia.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.proveedores.putpuertoagencia.v1.PutPuertoAgenciaService;
import es.mercadona.gesaduan.dao.proveedores.putpuertoagencia.v1.PutPuertoAgenciaDAO;
import es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1.InputDatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1.PuertoDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.proveedores.putpuertoagencia.v1.PuertoAgenciaJPA;
import es.mercadona.gesaduan.jpa.proveedores.putpuertoagencia.v1.PuertoAgenciaPkJPA;

public class PutPuertoAgenciaServiceImpl implements PutPuertoAgenciaService {
	
	@Inject
	private PutPuertoAgenciaDAO putPuertoAgenciaDao;

	@Override
	public void putPuertoAgencia(InputDatosPuertoAgenciaDTO datos) throws GesaduanException {
		String operacion = datos.getDatos().getOperacion();
		
		List<PuertoDTO> puertos = datos.getDatos().getPuerto();
		for (PuertoDTO puerto : puertos) {
			if (operacion.equals("M") && puerto.getMcaPreferente() == null)
				throw new GesaduanException(EnumGesaduanException.PUERTO_MCA_PREFERENTE_NULO);
			if (operacion.equals("A")) {
				PuertoAgenciaPkJPA pkPuertoAgencia = new PuertoAgenciaPkJPA();
				PuertoAgenciaJPA puertoAgencia = new PuertoAgenciaJPA();
				pkPuertoAgencia.setCodigoAgencia(datos.getDatos().getCodigoAgencia());
				pkPuertoAgencia.setCodigoPuerto(puerto.getCodigoPuerto());		
				puertoAgencia.setId(pkPuertoAgencia);
				if (putPuertoAgenciaDao.consultarAgenciaPreferente(puerto) > 0) puertoAgencia.setMcaAgenciaPreferente("N");
				else puertoAgencia.setMcaAgenciaPreferente("S");
				puertoAgencia.setUsuarioCreacion(datos.getMetadatos().getCodigoUsuario());
				putPuertoAgenciaDao.insertarPuertoAgencia(puertoAgencia);
			} else if (operacion.equals("M") && puerto.getMcaPreferente().equals("S"))
				putPuertoAgenciaDao.modificarAgenciaPreferente(puerto.getCodigoPuerto(), datos.getDatos().getCodigoAgencia());
			else if (operacion.equals("B")) {
				PuertoAgenciaPkJPA pkPuertoAgencia = new PuertoAgenciaPkJPA();
				PuertoAgenciaJPA puertoAgencia = new PuertoAgenciaJPA();
				pkPuertoAgencia.setCodigoAgencia(datos.getDatos().getCodigoAgencia());
				pkPuertoAgencia.setCodigoPuerto(puerto.getCodigoPuerto());		
				puertoAgencia.setId(pkPuertoAgencia);
				putPuertoAgenciaDao.eliminarPuertoAgencia(puertoAgencia);
			}
		}
		
		
	}

}
