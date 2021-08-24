package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ReturnDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long returnNumber;
	private Integer returnYear;

	/**
	 * @return the returnNumber
	 */
	public Long getReturnNumber() {
		return returnNumber;
	}

	/**
	 * @param returnNumber the returnNumber to set
	 */
	public void setReturnNumber(Long returnNumber) {
		this.returnNumber = returnNumber;
	}

	/**
	 * @return the returnYear
	 */
	public Integer getReturnYear() {
		return returnYear;
	}

	/**
	 * @param returnYear the returnYear to set
	 */
	public void setReturnYear(Integer returnYear) {
		this.returnYear = returnYear;
	}

}
