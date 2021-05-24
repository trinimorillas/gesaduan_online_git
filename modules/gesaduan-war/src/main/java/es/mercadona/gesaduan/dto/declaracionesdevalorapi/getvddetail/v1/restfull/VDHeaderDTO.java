package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class VDHeaderDTO extends AbstractDTO {
	
	private static final long serialVersionUID = 1L;
	
	private VDDataDTO valueDeclarationData;
	
	private List<InternalOrderListDTO> internalOrderList;
	private SourceDTO source;
	private TargetDTO target;
	/**
	 * @return the valueDeclarationData
	 */
	public VDDataDTO getValueDeclarationData() {
		return valueDeclarationData;
	}
	/**
	 * @param valueDeclarationData the valueDeclarationData to set
	 */
	public void setValueDeclarationData(VDDataDTO valueDeclarationData) {
		this.valueDeclarationData = valueDeclarationData;
	}
	/**
	 * @return the internalOrderList
	 */
	public List<InternalOrderListDTO> getInternalOrderList() {
		return internalOrderList;
	}
	/**
	 * @param internalOrderList the internalOrderList to set
	 */
	public void setInternalOrderList(List<InternalOrderListDTO> internalOrderList) {
		this.internalOrderList = internalOrderList;
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
