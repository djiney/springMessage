package telran.spring.message.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.spring.message.components.MessageResponse;
import telran.spring.message.services.messages.MessageService;
import telran.spring.message.services.messages.implementations.EmailService;
import telran.spring.message.services.messages.implementations.SkypeService;
import telran.spring.message.services.messages.implementations.SmsService;

@RestController
@RequestMapping("/rest")
public class DefaultController
{
	private final EmailService emailService;
	private final SmsService smsService;
	private final SkypeService skypeService;

	@Autowired
	public DefaultController()
	{
		emailService = new EmailService();
		smsService = new SmsService();
		skypeService = new SkypeService();
	}

	@RequestMapping(value = "/send/{service}/{message}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageResponse sendMessage(@PathVariable String service, @PathVariable String message)
	{
		boolean result = false;

		switch (service) {
			case MessageService.EMAIL:
				result = emailService.sendMessage(message);
			break;
			case MessageService.SMS:
				result = smsService.sendMessage(message);
			break;
			case MessageService.SKYPE:
				result = skypeService.sendMessage(message);
			break;
		}

		return new MessageResponse(result ? "OK" : "ERROR", service, message);
	}
}
