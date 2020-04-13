package telran.spring.message.services.messages.implementations;

import org.springframework.stereotype.Service;
import telran.spring.message.services.messages.MessageService;

@Service
public class SkypeService implements MessageService
{
	@Override
	public String getType()
	{
		return "skype";
	}

	@Override
	public boolean sendMessage(String message)
	{
		return true;
	}
}