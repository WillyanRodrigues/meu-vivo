package br.com.vivo.meuvivo.middleware;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vivo.meuvivo.converter.FaturaDTOConverter;
import br.com.vivo.meuvivo.converter.RegistroDTOConverter;
import br.com.vivo.meuvivo.converter.SaldoDTOConverter;
import br.com.vivo.meuvivo.dto.FaturaDTO;
import br.com.vivo.meuvivo.dto.RegistroDTO;
import br.com.vivo.meuvivo.dto.SaldoDTO;
import br.com.vivo.meuvivo.model.actionrecorder.Action;
import br.com.vivo.meuvivo.model.actionrecorder.Line;
import br.com.vivo.meuvivo.service.ActionRecorderService;

@Component
public class Middleware {
	
	@Autowired
	ActionRecorderService service;
	
	@Autowired
	FaturaDTOConverter faturaDtoConverter;
	
	@Autowired
	RegistroDTOConverter registroDTOConverter;
	
	@Autowired
	SaldoDTOConverter saldoDTOConverter;
	
	
	public FaturaDTO gerarFaturaMensal(Long cpf , Long assinatura) throws IOException {
		Line line = service.buscarAssinatura(cpf, assinatura);
		List<Action> actions =  service.buscarRegistrosPorAssinatura(cpf, assinatura);
		List<RegistroDTO> registros = new ArrayList<>();
		actions.forEach(action ->registros.add(registroDTOConverter.converter(action)));
		FaturaDTO faturaDTO = faturaDtoConverter.converter(line);
		faturaDTO.setRegistros(registros);
		
		return faturaDTO;
	}
	
	public List<RegistroDTO> gerarExtratoDetalhadoPorPeriodo(Long cpf, Long assinatura , LocalDate dataInicio 
														, LocalDate dataFim) throws IOException {
		List<Action> actions =  service.buscarRegistrosPorData(cpf, assinatura , dataInicio.atStartOfDay() , dataFim.atTime(23, 59));
		List<RegistroDTO> registros = new ArrayList<>();
		actions.forEach(action ->registros.add(registroDTOConverter.converter(action)));
		
		return registros;
	}
	
	public SaldoDTO buscarSaldoAtualSMS(Long cpf ,Long assinatura) throws IOException {
		Line line = service.buscarAssinatura(cpf, assinatura);
		List<Action> actions =  service.buscarRegistrosPorAssinatura(cpf, assinatura);
		return saldoDTOConverter.converterSMS(actions, line.getPlanDTO());
		
	}
	
	public SaldoDTO buscarSaldoAtualInternet(Long cpf ,Long assinatura) throws IOException {
		Line line = service.buscarAssinatura(cpf, assinatura);
		List<Action> actions =  service.buscarRegistrosPorAssinatura(cpf, assinatura);
		return saldoDTOConverter.converterInternet(actions, line.getPlanDTO());
		
	}
	
	public SaldoDTO buscarSaldoAtualLigacoes(Long cpf, Long assinatura) throws IOException {
		Line line = service.buscarAssinatura(cpf, assinatura);
		List<Action> actions =  service.buscarRegistrosPorAssinatura(cpf, assinatura);
		return saldoDTOConverter.converterLigacao(actions, line.getPlanDTO());
		

	}

}
