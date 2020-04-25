package telran.spring.console.services.messages;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import telran.spring.console.dto.TypesResponse;
import telran.spring.message.dto.implementations.MessageResponse;

import java.util.List;

@Service
public class MessageServiceBean implements MessageService
{
	public static final String URL = "http://localhost:8080/rest/";
	private final HttpHeaders headers = new HttpHeaders();
	private final RestTemplate restTemplate;

	public MessageServiceBean(RestTemplateBuilder builder)
	{
		restTemplate = builder
			.basicAuthentication("user", System.getenv("UPWD"))
			.build();
	}

	public void handshake()
	{
		HttpEntity<String> response = restTemplate.exchange(
			URL + "handshake",
			HttpMethod.GET,
			new HttpEntity<String>(headers),
			String.class
		);

		List<String> cookieList = response.getHeaders().get(HttpHeaders.SET_COOKIE);

		if (cookieList != null) {
			for (String cookie : cookieList) {
				headers.add("Cookie", cookie);
			}
		}
	}

	public TypesResponse getTypes()
	{
		HttpEntity<TypesResponse> response = restTemplate.exchange(
			URL + "get-types",
			HttpMethod.GET,
			new HttpEntity<TypesResponse>(headers),
			TypesResponse.class
		);

		return response.getBody();
	}

	public MessageResponse sendMessage(String message)
	{
		HttpEntity<MessageResponse> response = restTemplate.exchange(
			URL + "send",
			HttpMethod.POST,
			new HttpEntity<MessageResponse>(headers),
			MessageResponse.class,
			"Hello, world"
		);

		return response.getBody();
	}
}
