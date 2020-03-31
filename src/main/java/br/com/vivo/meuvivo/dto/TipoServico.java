package br.com.vivo.meuvivo.dto;

public enum TipoServico {
	
	INTERNET_WEBPAGE("Gigabytes"),
	DEVICE_APPLICATION("Gigabytes"),
	CALL("Minutos"),
	SMS("Quantidade");
	
	String tipoConsumo;
	
	public String getTipoConsumo() {
		return this.tipoConsumo;
	}
	
	TipoServico(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
	
	

}
