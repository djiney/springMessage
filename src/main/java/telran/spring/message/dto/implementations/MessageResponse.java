package telran.spring.message.dto.implementations;

import telran.spring.message.dto.BaseResponse;

public class MessageResponse extends BaseResponse
{
	String service;
	String message;

	public MessageResponse(String status, String service, String message)
	{
		super(status);
		this.service = service;
		this.message = message;
	}

	public String getService()
	{
		return service;
	}

	public void setService(String service)
	{
		this.service = service;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
