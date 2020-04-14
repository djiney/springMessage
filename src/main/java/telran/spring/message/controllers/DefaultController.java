package telran.spring.message.controllers;

import org.springframework.web.bind.annotation.*;
import telran.spring.message.dto.MessageRequest;
import telran.spring.message.dto.MessageResponse;
import telran.spring.message.services.messages.MessageService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class DefaultController
{
	private final Map<String, MessageService> services;

	public DefaultController(List<MessageService> list)
	{
		services = list.stream().collect(Collectors.toMap(MessageService::getType,  x -> x));
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
