package telran.spring.message.services.messages.implementations;

import org.springframework.stereotype.Service;
import telran.spring.message.services.messages.MessageService;

@Service(MessageService.SMS)
public class SmsService extends MessageService {}
