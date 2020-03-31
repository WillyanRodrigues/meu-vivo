package br.com.vivo.meuvivo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;



public class RegistroDTO {
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime data;
	
	private Double dadosUsados;
	
	private String tipoConsumo;
	
	private TipoServico tipoServico;

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
		setTipoConsumo(this.tipoServico.getTipoConsumo());
	}

	public Double getDadosUsados() {
		return dadosUsados;
	}

	public void setDadosUsados(Double dadosUsados) {
		this.dadosUsados = dadosUsados;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
	
	
	

}
