package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class ValueDeclarationLineDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	private Long productPublicId;
	private Long taricId;
	private Long actualTaricId;
	private String rea;
	private String gtin13;
	private String productName;
	private String productAlternativeName;
	private String standardSalesFormat;
	private String brandName;
	private String alternativeSalesFormatDescription;
	private String packageTypeId;
	private String packageName;
	private Integer packageQuantity;
	private Integer lineNetWeight;
	private Integer lineNetWeightUnit;
	private Integer lineGrossWeight;
	private Integer lineGrossWeightUnit;
	private Integer volume;
	private Integer volumeUnit;
	private Integer formatQuantity;
	private Integer unitPrice;
	private Integer unitPriceCurrency;
	private Integer totalLineAmount;
	private Integer totalLineAmountCurrency;
	private Integer alcoholPercentage;
	private Integer alcoholPercentageUnit;
	private Integer plateGrade;
	private Integer plateGradeUnit;
	private String sourceCountryId;
	private Boolean isLpc;
	private String hasError;

	/**
	 * @return the productPublicId
	 */
	public Long getProductPublicId() {
		return productPublicId;
	}

	/**
	 * @param productPublicId the productPublicId to set
	 */
	public void setProductPublicId(Long productPublicId) {
		this.productPublicId = productPublicId;
	}

	/**
	 * @return the taricId
	 */
	public Long getTaricId() {
		return taricId;
	}

	/**
	 * @param taricId the taricId to set
	 */
	public void setTaricId(Long taricId) {
		this.taricId = taricId;
	}

	/**
	 * @return the actualTaricId
	 */
	public Long getActualTaricId() {
		return actualTaricId;
	}

	/**
	 * @param actualTaricId the actualTaricId to set
	 */
	public void setActualTaricId(Long actualTaricId) {
		this.actualTaricId = actualTaricId;
	}

	/**
	 * @return the rea
	 */
	public String getRea() {
		return rea;
	}

	/**
	 * @param rea the rea to set
	 */
	public void setRea(String rea) {
		this.rea = rea;
	}

	/**
	 * @return the gtin13
	 */
	public String getGtin13() {
		return gtin13;
	}

	/**
	 * @param gtin13 the gtin13 to set
	 */
	public void setGtin13(String gtin13) {
		this.gtin13 = gtin13;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productAlternativeName
	 */
	public String getProductAlternativeName() {
		return productAlternativeName;
	}

	/**
	 * @param productAlternativeName the productAlternativeName to set
	 */
	public void setProductAlternativeName(String productAlternativeName) {
		this.productAlternativeName = productAlternativeName;
	}

	/**
	 * @return the standardSalesFormat
	 */
	public String getStandardSalesFormat() {
		return standardSalesFormat;
	}

	/**
	 * @param standardSalesFormat the standardSalesFormat to set
	 */
	public void setStandardSalesFormat(String standardSalesFormat) {
		this.standardSalesFormat = standardSalesFormat;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the alternativeSalesFormatDescription
	 */
	public String getAlternativeSalesFormatDescription() {
		return alternativeSalesFormatDescription;
	}

	/**
	 * @param alternativeSalesFormatDescription the
	 *                                          alternativeSalesFormatDescription to
	 *                                          set
	 */
	public void setAlternativeSalesFormatDescription(String alternativeSalesFormatDescription) {
		this.alternativeSalesFormatDescription = alternativeSalesFormatDescription;
	}

	/**
	 * @return the packageTypeId
	 */
	public String getPackageTypeId() {
		return packageTypeId;
	}

	/**
	 * @param packageTypeId the packageTypeId to set
	 */
	public void setPackageTypeId(String packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the packageQuantity
	 */
	public Integer getPackageQuantity() {
		return packageQuantity;
	}

	/**
	 * @param packageQuantity the packageQuantity to set
	 */
	public void setPackageQuantity(Integer packageQuantity) {
		this.packageQuantity = packageQuantity;
	}

	/**
	 * @return the lineNetWeight
	 */
	public Integer getLineNetWeight() {
		return lineNetWeight;
	}

	/**
	 * @param lineNetWeight the lineNetWeight to set
	 */
	public void setLineNetWeight(Integer lineNetWeight) {
		this.lineNetWeight = lineNetWeight;
	}

	/**
	 * @return the lineNetWeightUnit
	 */
	public Integer getLineNetWeightUnit() {
		return lineNetWeightUnit;
	}

	/**
	 * @param lineNetWeightUnit the lineNetWeightUnit to set
	 */
	public void setLineNetWeightUnit(Integer lineNetWeightUnit) {
		this.lineNetWeightUnit = lineNetWeightUnit;
	}

	/**
	 * @return the lineGrossWeight
	 */
	public Integer getLineGrossWeight() {
		return lineGrossWeight;
	}

	/**
	 * @param lineGrossWeight the lineGrossWeight to set
	 */
	public void setLineGrossWeight(Integer lineGrossWeight) {
		this.lineGrossWeight = lineGrossWeight;
	}

	/**
	 * @return the lineGrossWeightUnit
	 */
	public Integer getLineGrossWeightUnit() {
		return lineGrossWeightUnit;
	}

	/**
	 * @param lineGrossWeightUnit the lineGrossWeightUnit to set
	 */
	public void setLineGrossWeightUnit(Integer lineGrossWeightUnit) {
		this.lineGrossWeightUnit = lineGrossWeightUnit;
	}

	/**
	 * @return the volume
	 */
	public Integer getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	/**
	 * @return the volumeUnit
	 */
	public Integer getVolumeUnit() {
		return volumeUnit;
	}

	/**
	 * @param volumeUnit the volumeUnit to set
	 */
	public void setVolumeUnit(Integer volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	/**
	 * @return the formatQuantity
	 */
	public Integer getFormatQuantity() {
		return formatQuantity;
	}

	/**
	 * @param formatQuantity the formatQuantity to set
	 */
	public void setFormatQuantity(Integer formatQuantity) {
		this.formatQuantity = formatQuantity;
	}

	/**
	 * @return the unitPrice
	 */
	public Integer getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the unitPriceCurrency
	 */
	public Integer getUnitPriceCurrency() {
		return unitPriceCurrency;
	}

	/**
	 * @param unitPriceCurrency the unitPriceCurrency to set
	 */
	public void setUnitPriceCurrency(Integer unitPriceCurrency) {
		this.unitPriceCurrency = unitPriceCurrency;
	}

	/**
	 * @return the totalLineAmount
	 */
	public Integer getTotalLineAmount() {
		return totalLineAmount;
	}

	/**
	 * @param totalLineAmount the totalLineAmount to set
	 */
	public void setTotalLineAmount(Integer totalLineAmount) {
		this.totalLineAmount = totalLineAmount;
	}

	/**
	 * @return the totalLineAmountCurrency
	 */
	public Integer getTotalLineAmountCurrency() {
		return totalLineAmountCurrency;
	}

	/**
	 * @param totalLineAmountCurrency the totalLineAmountCurrency to set
	 */
	public void setTotalLineAmountCurrency(Integer totalLineAmountCurrency) {
		this.totalLineAmountCurrency = totalLineAmountCurrency;
	}

	/**
	 * @return the alcoholPercentage
	 */
	public Integer getAlcoholPercentage() {
		return alcoholPercentage;
	}

	/**
	 * @param alcoholPercentage the alcoholPercentage to set
	 */
	public void setAlcoholPercentage(Integer alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	/**
	 * @return the alcoholPercentageUnit
	 */
	public Integer getAlcoholPercentageUnit() {
		return alcoholPercentageUnit;
	}

	/**
	 * @param alcoholPercentageUnit the alcoholPercentageUnit to set
	 */
	public void setAlcoholPercentageUnit(Integer alcoholPercentageUnit) {
		this.alcoholPercentageUnit = alcoholPercentageUnit;
	}

	/**
	 * @return the plateGrade
	 */
	public Integer getPlateGrade() {
		return plateGrade;
	}

	/**
	 * @param plateGrade the plateGrade to set
	 */
	public void setPlateGrade(Integer plateGrade) {
		this.plateGrade = plateGrade;
	}

	/**
	 * @return the plateGradeUnit
	 */
	public Integer getPlateGradeUnit() {
		return plateGradeUnit;
	}

	/**
	 * @param plateGradeUnit the plateGradeUnit to set
	 */
	public void setPlateGradeUnit(Integer plateGradeUnit) {
		this.plateGradeUnit = plateGradeUnit;
	}

	/**
	 * @return the sourceCountryId
	 */
	public String getSourceCountryId() {
		return sourceCountryId;
	}

	/**
	 * @param sourceCountryId the sourceCountryId to set
	 */
	public void setSourceCountryId(String sourceCountryId) {
		this.sourceCountryId = sourceCountryId;
	}

	/**
	 * @return the isLpc
	 */
	public Boolean getIsLpc() {
		return isLpc;
	}

	/**
	 * @param isLpc the isLpc to set
	 */
	public void setIsLpc(Boolean isLpc) {
		this.isLpc = isLpc;
	}

	/**
	 * @return the hasError
	 */
	public String getHasError() {
		return hasError;
	}

	/**
	 * @param hasError the hasError to set
	 */
	public void setHasError(String hasError) {
		this.hasError = hasError;
	}

}
