package telran.spring.message.services.messages.implementations;

import org.springframework.stereotype.Service;
import telran.spring.message.services.messages.MessageService;

@Service
public class SmsService implements MessageService
{
	@Override
	public String getType()
	{
		return "sms";
	}

	@Override
	public boolean sendMessage(String message)
	{
		return true;
	}
}
