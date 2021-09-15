package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restful;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DataDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private VDCommonDataDTO commonData;
	private VDHeaderDTO header;
	private List<VDHistoricalDTO> historical;
	private List<VDLineDTO> lineList;
	private List<VDItemDTO> itemList;

	/**
	 * @return the commonData
	 */
	public VDCommonDataDTO getCommonData() {
		return commonData;
	}

	/**
	 * @param commonData the commonData to set
	 */
	public void setCommonData(VDCommonDataDTO commonData) {
		this.commonData = commonData;
	}

	/**
	 * @return the header
	 */
	public VDHeaderDTO getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(VDHeaderDTO header) {
		this.header = header;
	}

	/**
	 * @return the historical
	 */
	public List<VDHistoricalDTO> getHistorical() {
		return historical;
	}

	/**
	 * @param historical the historical to set
	 */
	public void setHistorical(List<VDHistoricalDTO> historical) {
		this.historical = historical;
	}

	/**
	 * @return the lineList
	 */
	public List<VDLineDTO> getLineList() {
		return lineList;
	}

	/**
	 * @param lineList the lineList to set
	 */
	public void setLineList(List<VDLineDTO> lineList) {
		this.lineList = lineList;
	}

	/**
	 * @return the itemList
	 */
	public List<VDItemDTO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<VDItemDTO> itemList) {
		this.itemList = itemList;
	}

}
