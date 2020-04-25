package telran.spring.console.dto;

public class TypesResponse
{
	String status;
	String[] types;

	public TypesResponse(String status, String[] types)
	{
		this.status = status;
		this.types = types;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String[] getTypes()
	{
		return types;
	}

	public void setTypes(String[] types)
	{
		this.types = types;
	}

	public boolean isOK()
	{
		return status.equals("OK");
	}

}
