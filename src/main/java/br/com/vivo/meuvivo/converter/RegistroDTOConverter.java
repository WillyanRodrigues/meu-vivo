package br.com.vivo.meuvivo.converter;

import org.springframework.stereotype.Component;

import br.com.vivo.meuvivo.dto.RegistroDTO;
import br.com.vivo.meuvivo.dto.TipoServico;
import br.com.vivo.meuvivo.model.actionrecorder.Action;

@Component
public class RegistroDTOConverter {

	public RegistroDTO converter(Action action) {
		RegistroDTO registroDTO = new RegistroDTO();
		registroDTO.setTipoServico(TipoServico.valueOf(action.getServiceType()));
		registroDTO.setData(action.getDate());
		registroDTO.setDadosUsados(Double.valueOf(action.getUsedData()));
		return registroDTO;
	}
}
