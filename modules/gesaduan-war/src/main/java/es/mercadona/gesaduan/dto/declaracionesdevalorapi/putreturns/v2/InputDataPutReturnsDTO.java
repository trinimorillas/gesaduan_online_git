package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDataPutReturnsDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private HeaderDTO header;
	private List<ItemListDTO> itemList;

	/**
	 * @return the header
	 */
	public HeaderDTO getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	/**
	 * @return the itemList
	 */
	public List<ItemListDTO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<ItemListDTO> itemList) {
		this.itemList = itemList;
	}

}
