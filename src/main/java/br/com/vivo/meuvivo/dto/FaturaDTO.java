package br.com.vivo.meuvivo.dto;

import java.math.BigDecimal;
import java.util.List;

public class FaturaDTO {

	private BigDecimal valor;
	
	private TipoPlano plano;
	
	private List<RegistroDTO> registros;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPlano getPlano() {
		return plano;
	}

	public void setPlano(TipoPlano plano) {
		this.plano = plano;
	}

	public List<RegistroDTO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroDTO> registros) {
		this.registros = registros;
	}
	
	
}
