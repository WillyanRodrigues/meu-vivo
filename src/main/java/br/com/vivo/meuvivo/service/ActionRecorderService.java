package br.com.vivo.meuvivo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vivo.meuvivo.converter.FaturaDTOConverter;
import br.com.vivo.meuvivo.converter.RegistroDTOConverter;
import br.com.vivo.meuvivo.exception.HTTPActionRecorderException;
import br.com.vivo.meuvivo.model.actionrecorder.Action;
import br.com.vivo.meuvivo.model.actionrecorder.Line;

@Service
public class ActionRecorderService {

	@Value("${actionrecorder.endpoint}")
	private String endpoint;

	@Autowired
	RegistroDTOConverter registroDTOConverter;

	@Autowired
	FaturaDTOConverter faturaDTOConverter;

	private static final ObjectMapper mapper = new ObjectMapper();

	private static final HttpClient HTTP_CLIENT = new HttpClient(new MultiThreadedHttpConnectionManager());

	private String getBuscarRegistrosPorAssinaturaEndpoint(Long cpf, Long assinatura) {
		return this.endpoint + "/user/" + cpf + "/line/" + assinatura + "/actions";
	}

	private String getBuscarAssinaturaEndpoint(Long cpf, Long assinatura) {
		return this.endpoint + "/user/" + cpf + "/line/" + assinatura;
	}

	private String getBuscarRegistrosPorDataEndpoint(Long cpf, Long assinatura, LocalDateTime inicio,
			LocalDateTime fim) {
		return this.endpoint + "/user/" + cpf + "/line/" + assinatura + "/action?startDate=" + inicio + "&endDate="
				+ fim;
	}

	public List<Action> buscarRegistrosPorAssinatura(Long cpf, Long assinatura) throws IOException {
		GetMethod getMethod = new GetMethod(getBuscarRegistrosPorAssinaturaEndpoint(cpf, assinatura));
		HTTP_CLIENT.executeMethod(getMethod);
		if (getMethod.getStatusCode() != 200) {
			throw new HTTPActionRecorderException(getMethod.getStatusCode(), getMethod.getResponseBodyAsString());
		}
		return mapper.readValue(getMethod.getResponseBodyAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Action.class));
	}

	public Line buscarAssinatura(Long cpf, Long assinatura) throws IOException {
		GetMethod getMethod = new GetMethod(getBuscarAssinaturaEndpoint(cpf, assinatura));
		HTTP_CLIENT.executeMethod(getMethod);
		if (getMethod.getStatusCode() != 200) {
			throw new HTTPActionRecorderException(getMethod.getStatusCode(), getMethod.getResponseBodyAsString());
		}
		return mapper.readValue(getMethod.getResponseBodyAsString(), Line.class);
	}

	public List<Action> buscarRegistrosPorData(Long cpf, Long assinatura, LocalDateTime inicio, LocalDateTime fim)
			throws IOException {
		GetMethod getMethod = new GetMethod(getBuscarRegistrosPorDataEndpoint(cpf, assinatura, inicio, fim));
		HTTP_CLIENT.executeMethod(getMethod);
		if (getMethod.getStatusCode() != 200) {
			throw new HTTPActionRecorderException(getMethod.getStatusCode(), getMethod.getResponseBodyAsString());
		}
		return mapper.readValue(getMethod.getResponseBodyAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Action.class));

	}

}
