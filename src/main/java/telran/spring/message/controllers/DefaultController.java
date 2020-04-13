package telran.spring.message.controllers;

import org.springframework.web.bind.annotation.*;
import telran.spring.message.components.MessageRequest;
import telran.spring.message.components.MessageResponse;
import telran.spring.message.services.messages.MessageServiceInterface;

import java.util.Map;

@RestController
@RequestMapping("/rest")
public class DefaultController
{
	private final Map<String, MessageServiceInterface> services;

	public DefaultController(Map<String, MessageServiceInterface> services) {
		this.services = services;
	}

	@ResponseBody @PostMapping("/send")
	public MessageResponse sendMessage(@RequestBody MessageRequest request)
	{
		boolean result = false;

		if (services.containsKey(request.getService())) {
			result = services.get(request.getService()).sendMessage(request.getMessage());
		}

		return new MessageResponse(result ? "OK" : "ERROR", request.getService(), request.getMessage());
	}
}
