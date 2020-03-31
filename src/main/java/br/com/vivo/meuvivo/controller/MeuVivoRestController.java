package br.com.vivo.meuvivo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.vivo.meuvivo.dto.FaturaDTO;
import br.com.vivo.meuvivo.dto.RegistroDTO;
import br.com.vivo.meuvivo.dto.SaldoDTO;
import br.com.vivo.meuvivo.middleware.Middleware;

@RestController
@RequestMapping("cliente/{cpf}/assinatura/{numero}")
public class MeuVivoRestController {
	
	@Autowired
	Middleware middleware;

	@GetMapping("/sms")
	public SaldoDTO saldoAtualSMS(@PathVariable(name = "cpf" , required=true) Long cpf,
			@PathVariable(name="numero") Long numero) throws IOException {
		return middleware.buscarSaldoAtualSMS(cpf, numero);
	}
	
	@GetMapping("/internet")
	public SaldoDTO saldoAtualInternet(@PathVariable(name = "cpf" , required=true) Long cpf,
			@PathVariable(name="numero") Long numero) throws IOException {
		return middleware.buscarSaldoAtualInternet(cpf, numero);
	}
	
	@GetMapping("/linha")
	public SaldoDTO saldoAtualLigacoes(@PathVariable(name = "cpf" , required=true) Long cpf,
			@PathVariable(name="numero") Long numero) throws IOException {
		return middleware.buscarSaldoAtualLigacoes(cpf, numero);
	}
	
	@GetMapping("/fatura")
	public FaturaDTO fatura(@PathVariable(name = "cpf" , required=true) Long cpf,
										@PathVariable(name="numero") Long numero) throws IOException {
		return middleware.gerarFaturaMensal(cpf, numero);
	}
	
	
	@GetMapping("/extrato")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public List<RegistroDTO> extratoDetalhado(@PathVariable(name = "cpf" , required=true) Long cpf,
										@PathVariable(name="numero") Long numero,
										@RequestParam(name="dataInicio")LocalDate dataInicio,
										@RequestParam(name="dataFim")LocalDate dataFim) throws IOException {
		return middleware.gerarExtratoDetalhadoPorPeriodo(cpf, numero, dataInicio, dataFim);
	}
	
}
