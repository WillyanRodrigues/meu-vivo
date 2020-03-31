package br.com.vivo.meuvivo.model.actionrecorder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {

	private Long id;
	
	private int countryCode;
	
	private int areaCode;
	
	private Long number;

	private Plan planDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Plan getPlanDTO() {
		return planDTO;
	}

	public void setPlanDTO(Plan planDTO) {
		this.planDTO = planDTO;
	}


	
	
}
