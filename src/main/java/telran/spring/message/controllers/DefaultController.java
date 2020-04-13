package telran.spring.message.controllers;

import org.springframework.web.bind.annotation.*;
import telran.spring.message.dto.MessageRequest;
import telran.spring.message.dto.MessageResponse;
import telran.spring.message.services.messages.MessageService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class DefaultController
{
	private final Map<String, MessageService> services = new LinkedHashMap<>();

	public DefaultController(List<MessageService> list)
	{
		for (MessageService service : list) {
			services.put(service.getType(), service);
		}
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
