package telran.spring.message.services.messages;

public interface MessageService
{
	String getType();
	boolean sendMessage(String message);
}
