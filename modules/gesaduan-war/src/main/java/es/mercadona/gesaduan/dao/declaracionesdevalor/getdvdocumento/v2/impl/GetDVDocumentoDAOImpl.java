package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v2.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v2.GetDVDocumentoDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocLinDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataPK;

@Stateless
public class GetDVDocumentoDAOImpl extends DaoBaseImpl<DocumentoDVDataPK, DocumentoDVDataJPA> implements GetDVDocumentoDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DocumentoDVDataJPA.class;
		
	}

	@Override
	public OutputDeclaracionesDeValorDocCabDTO getDatosDocumento(InputDeclaracionesDeValorDocumentoDTO input) {
		
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO;
		
		// Obtiene los datos de cabecera del documento	
		outDVDocumentoDTO = getDatosCabecera(input);
		
		return outDVDocumentoDTO;
	}
	
	/* carga datos de cabecera de la DV */
	private OutputDeclaracionesDeValorDocCabDTO getDatosCabecera(InputDeclaracionesDeValorDocumentoDTO input) {
		
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO;
		List<OutputDeclaracionesDeValorDocLinDTO> lineas;
		
		// Query
		
		// Obtiene los datos de cabecera del documento	
		outDVDocumentoDTO = new OutputDeclaracionesDeValorDocCabDTO();
		
		// Obtiene los datos de lineas del documento 
		lineas = getDatosLineas(input);
		outDVDocumentoDTO.setLineas(lineas);
		
		return outDVDocumentoDTO;
	}
	
	/* carga datos de lineas de la DV */
	private List<OutputDeclaracionesDeValorDocLinDTO> getDatosLineas(InputDeclaracionesDeValorDocumentoDTO input) {
		
		List<OutputDeclaracionesDeValorDocLinDTO> lineas;
		
		// Obtiene los datos de lineas del documento según el tipo de informe por los separadores decimales		
		// Query		
		
		// Obtiene los datos de cabecera del documento	
		lineas = new ArrayList<>();		
		
		return lineas;
	}	
	
}
