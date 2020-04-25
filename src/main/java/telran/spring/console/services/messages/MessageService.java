package telran.spring.console.services.messages;

import telran.spring.console.dto.TypesResponse;
import telran.spring.message.dto.implementations.MessageResponse;

public interface MessageService
{
	void handshake();
	TypesResponse getTypes();
	MessageResponse sendMessage(String message);
}
