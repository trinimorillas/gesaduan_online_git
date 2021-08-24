package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class HeaderDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ReturnDTO returnIds;
	private String returnDate;
	private SourceDTO source;
	private TargetDTO target;

	/**
	 * @return the returnIds
	 */
	public ReturnDTO getReturnIds() {
		return returnIds;
	}

	/**
	 * @param returnIds the returnIds to set
	 */
	public void setReturnIds(ReturnDTO returnIds) {
		this.returnIds = returnIds;
	}

	/**
	 * @return the returnDate
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return the source
	 */
	public SourceDTO getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(SourceDTO source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public TargetDTO getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(TargetDTO target) {
		this.target = target;
	}

}
