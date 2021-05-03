package es.mercadona.gesaduan.dao.dosier.cambiarestado.v1;

import es.mercadona.gesaduan.jpa.dosier.getdocumento.v1.DocumentoDataJPA;

public interface GuardarPDFDAO {
	public void guardarPDF(DocumentoDataJPA documento);
}