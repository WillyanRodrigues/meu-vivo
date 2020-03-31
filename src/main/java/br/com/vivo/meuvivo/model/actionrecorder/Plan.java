package br.com.vivo.meuvivo.model.actionrecorder;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {
		

	private PlanType type;
	
	private BigDecimal price;
	
	private ConsumeLimit consumeLimit;
	
	
	public PlanType getType() {
		return type;
	}

	public void setType(PlanType type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ConsumeLimit getConsumeLimit() {
		return consumeLimit;
	}

	public void setConsumeLimit(ConsumeLimit consumeLimit) {
		this.consumeLimit = consumeLimit;
	}

}
