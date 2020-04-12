package telran.spring.message.services.messages;

public class MessageService implements MessageServiceInterface
{
	public static final String EMAIL = "email";
	public static final String SMS = "sms";
	public static final String SKYPE = "skype";

	@Override
	public boolean sendMessage(String message)
	{
		return true;
	}
}
