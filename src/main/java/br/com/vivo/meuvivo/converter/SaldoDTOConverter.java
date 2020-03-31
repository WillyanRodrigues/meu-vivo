package br.com.vivo.meuvivo.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.vivo.meuvivo.dto.SaldoDTO;
import br.com.vivo.meuvivo.dto.TipoServico;
import br.com.vivo.meuvivo.model.actionrecorder.Action;
import br.com.vivo.meuvivo.model.actionrecorder.Plan;

@Component
public class SaldoDTOConverter {

	public SaldoDTO converterSMS(List<Action> actions, Plan plan) {
		int saldo = plan.getConsumeLimit().getSms();
		int gasto = 0;
		for (Action action : actions) {
			if (action.getServiceType().equalsIgnoreCase("SMS")) {
				gasto += Integer.valueOf(action.getUsedData());
			}
		}
		SaldoDTO saldoDTO = new SaldoDTO();
		saldoDTO.setSaldo((double) (saldo - gasto));
		saldoDTO.setServico(TipoServico.SMS.name());
		saldoDTO.setUnidadeDeUso(TipoServico.SMS.getTipoConsumo());

		return saldoDTO;
	}

	public SaldoDTO converterInternet(List<Action> actions, Plan plan) {
		double saldo = plan.getConsumeLimit().getInternet();
		double gasto = 0;
		for (Action action : actions) {
			if (action.getServiceType().equalsIgnoreCase(TipoServico.DEVICE_APPLICATION.name())
					|| action.getServiceType().equalsIgnoreCase(TipoServico.INTERNET_WEBPAGE.name())) {
				gasto += Double.valueOf(action.getUsedData());
			}
		}
		SaldoDTO saldoDTO = new SaldoDTO();
		saldoDTO.setSaldo((double) (saldo - gasto));
		saldoDTO.setServico("INTERNET");
		saldoDTO.setUnidadeDeUso(TipoServico.DEVICE_APPLICATION.getTipoConsumo());

		return saldoDTO;
	}

	public SaldoDTO converterLigacao(List<Action> actions, Plan plan) {
		int saldo = plan.getConsumeLimit().getCall();
		int gasto = 0;
		for (Action action : actions) {
			if (action.getServiceType().equalsIgnoreCase("CALL")) {
				gasto += Integer.valueOf(action.getUsedData());
			}
		}
		SaldoDTO saldoDTO = new SaldoDTO();
		saldoDTO.setSaldo((double) (saldo - gasto));
		saldoDTO.setServico(TipoServico.CALL.name());
		saldoDTO.setUnidadeDeUso(TipoServico.CALL.getTipoConsumo());

		return saldoDTO;
	}

}
