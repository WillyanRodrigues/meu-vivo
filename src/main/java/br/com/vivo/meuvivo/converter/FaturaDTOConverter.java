package br.com.vivo.meuvivo.converter;

import org.springframework.stereotype.Component;

import br.com.vivo.meuvivo.dto.FaturaDTO;
import br.com.vivo.meuvivo.dto.TipoPlano;
import br.com.vivo.meuvivo.model.actionrecorder.Line;

@Component
public class FaturaDTOConverter {

	
	public FaturaDTO converter(Line line) {
		FaturaDTO faturaDTO = new FaturaDTO();

		faturaDTO.setValor(line.getPlanDTO().getPrice());
		faturaDTO.setPlano(TipoPlano.valueOf(line.getPlanDTO().getType().name()));

		return faturaDTO;
	}

}
