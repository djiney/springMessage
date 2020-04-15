package telran.spring.message.dto.implementations;

import telran.spring.message.dto.BaseResponse;

public class TypesResponse extends BaseResponse
{
	Object[] types;

	public TypesResponse(Object[] types)
	{
		super("OK");
		this.types = types;
	}

	public Object[] getTypes()
	{
		return types;
	}

	public void setTypes(Object[] types)
	{
		this.types = types;
	}
}
