package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class VDLineDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private Integer productPublicId;
	private Long taricId;
	private Long actualTaricId;
	private String ssrId;
	private String gtin;
	private String productName;
	private String productAlternativeName;
	private String standardSalesFormat;
	private String brandName;
	private String alternativeSalesFormatDescription;
	private String packageTypeId;
	private String packageName;
	private Integer packageQuantity;
	private Double lineNetWeight;
	private String lineNetWeightUnit;
	private Double lineGrossWeight;
	private String lineGrossWeightUnit;
	private Double volume;
	private String volumeUnit;
	private Double formatQuantity;
	private Double unitPrice;
	private String unitPriceCurrency;
	private Double totalLineAmount;
	private String totalLineAmountCurrency;
	private Double alcoholPercentage;
	private String alcoholPercentageUnit;
	private Double plateGrade;
	private String plateGradeUnit;
	private String sourceCountryId;
	private Boolean isReadyToEat;
	private String hasError;
	private Long lineNumber;

	/**
	 * @return the productPublicId
	 */
	public Integer getProductPublicId() {
		return productPublicId;
	}

	/**
	 * @param productPublicId the productPublicId to set
	 */
	public void setProductPublicId(Integer productPublicId) {
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
	 * @return the ssrId
	 */
	public String getSsrId() {
		return ssrId;
	}

	/**
	 * @param ssrId the ssrId to set
	 */
	public void setSsrId(String ssrId) {
		this.ssrId = ssrId;
	}

	/**
	 * @return the gtin
	 */
	public String getGtin() {
		return gtin;
	}

	/**
	 * @param gtin the gtin to set
	 */
	public void setGtin(String gtin) {
		this.gtin = gtin;
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
	public Double getLineNetWeight() {
		return lineNetWeight;
	}

	/**
	 * @param lineNetWeight the lineNetWeight to set
	 */
	public void setLineNetWeight(Double lineNetWeight) {
		this.lineNetWeight = lineNetWeight;
	}

	/**
	 * @return the lineNetWeightUnit
	 */
	public String getLineNetWeightUnit() {
		return lineNetWeightUnit;
	}

	/**
	 * @param lineNetWeightUnit the lineNetWeightUnit to set
	 */
	public void setLineNetWeightUnit(String lineNetWeightUnit) {
		this.lineNetWeightUnit = lineNetWeightUnit;
	}

	/**
	 * @return the lineGrossWeight
	 */
	public Double getLineGrossWeight() {
		return lineGrossWeight;
	}

	/**
	 * @param lineGrossWeight the lineGrossWeight to set
	 */
	public void setLineGrossWeight(Double lineGrossWeight) {
		this.lineGrossWeight = lineGrossWeight;
	}

	/**
	 * @return the lineGrossWeightUnit
	 */
	public String getLineGrossWeightUnit() {
		return lineGrossWeightUnit;
	}

	/**
	 * @param lineGrossWeightUnit the lineGrossWeightUnit to set
	 */
	public void setLineGrossWeightUnit(String lineGrossWeightUnit) {
		this.lineGrossWeightUnit = lineGrossWeightUnit;
	}

	/**
	 * @return the volume
	 */
	public Double getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
	}

	/**
	 * @return the volumeUnit
	 */
	public String getVolumeUnit() {
		return volumeUnit;
	}

	/**
	 * @param volumeUnit the volumeUnit to set
	 */
	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	/**
	 * @return the formatQuantity
	 */
	public Double getFormatQuantity() {
		return formatQuantity;
	}

	/**
	 * @param formatQuantity the formatQuantity to set
	 */
	public void setFormatQuantity(Double formatQuantity) {
		this.formatQuantity = formatQuantity;
	}

	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the unitPriceCurrency
	 */
	public String getUnitPriceCurrency() {
		return unitPriceCurrency;
	}

	/**
	 * @param unitPriceCurrency the unitPriceCurrency to set
	 */
	public void setUnitPriceCurrency(String unitPriceCurrency) {
		this.unitPriceCurrency = unitPriceCurrency;
	}

	/**
	 * @return the totalLineAmount
	 */
	public Double getTotalLineAmount() {
		return totalLineAmount;
	}

	/**
	 * @param totalLineAmount the totalLineAmount to set
	 */
	public void setTotalLineAmount(Double totalLineAmount) {
		this.totalLineAmount = totalLineAmount;
	}

	/**
	 * @return the totalLineAmountCurrency
	 */
	public String getTotalLineAmountCurrency() {
		return totalLineAmountCurrency;
	}

	/**
	 * @param totalLineAmountCurrency the totalLineAmountCurrency to set
	 */
	public void setTotalLineAmountCurrency(String totalLineAmountCurrency) {
		this.totalLineAmountCurrency = totalLineAmountCurrency;
	}

	/**
	 * @return the alcoholPercentage
	 */
	public Double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	/**
	 * @param alcoholPercentage the alcoholPercentage to set
	 */
	public void setAlcoholPercentage(Double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	/**
	 * @return the alcoholPercentageUnit
	 */
	public String getAlcoholPercentageUnit() {
		return alcoholPercentageUnit;
	}

	/**
	 * @param alcoholPercentageUnit the alcoholPercentageUnit to set
	 */
	public void setAlcoholPercentageUnit(String alcoholPercentageUnit) {
		this.alcoholPercentageUnit = alcoholPercentageUnit;
	}

	/**
	 * @return the plateGrade
	 */
	public Double getPlateGrade() {
		return plateGrade;
	}

	/**
	 * @param plateGrade the plateGrade to set
	 */
	public void setPlateGrade(Double plateGrade) {
		this.plateGrade = plateGrade;
	}

	/**
	 * @return the plateGradeUnit
	 */
	public String getPlateGradeUnit() {
		return plateGradeUnit;
	}

	/**
	 * @param plateGradeUnit the plateGradeUnit to set
	 */
	public void setPlateGradeUnit(String plateGradeUnit) {
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
	 * @return the isReadyToEat
	 */
	public Boolean getIsReadyToEat() {
		return isReadyToEat;
	}

	/**
	 * @param isReadyToEat the isReadyToEat to set
	 */
	public void setIsReadyToEat(Boolean isReadyToEat) {
		this.isReadyToEat = isReadyToEat;
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

	/**
	 * @return the lineNumber
	 */
	public Long getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}

}