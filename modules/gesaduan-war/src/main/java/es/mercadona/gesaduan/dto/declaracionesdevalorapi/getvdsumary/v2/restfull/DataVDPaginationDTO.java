package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataVDPaginationDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long requestedPage;
	private Long requestedSize ;
	private Long retrievedResults;
	private Long totalResults;	
	private String nextPage;	
	private String previousPage;
	
	/**
	 * @return the requestedPage
	 */
	public Long getRequestedPage() {
		return requestedPage;
	}
	/**
	 * @param requestedPage the requestedPage to set
	 */
	public void setRequestedPage(Long requestedPage) {
		this.requestedPage = requestedPage;
	}
	/**
	 * @return the requestedSize
	 */
	public Long getRequestedSize() {
		return requestedSize;
	}
	/**
	 * @param requestedSize the requestedSize to set
	 */
	public void setRequestedSize(Long requestedSize) {
		this.requestedSize = requestedSize;
	}
	/**
	 * @return the retrievedResults
	 */
	public Long getRetrievedResults() {
		return retrievedResults;
	}
	/**
	 * @param retrievedResults the retrievedResults to set
	 */
	public void setRetrievedResults(Long retrievedResults) {
		this.retrievedResults = retrievedResults;
	}
	/**
	 * @return the totalResults
	 */
	public Long getTotalResults() {
		return totalResults;
	}
	/**
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
	/**
	 * @return the nextPage
	 */
	public String getNextPage() {
		return nextPage;
	}
	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	/**
	 * @return the previousPage
	 */
	public String getPreviousPage() {
		return previousPage;
	}
	/**
	 * @param previousPage the previousPage to set
	 */
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	
	
}
