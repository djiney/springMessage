package telran.spring.message.dto;

public class BaseResponse
{
	String status;

	public BaseResponse(String status)
	{
		this.status = status;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}
