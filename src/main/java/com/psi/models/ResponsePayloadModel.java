package com.psi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * THIS MODEL IS THE STANDARD RESPONSE FROM FORM API REQUESTS
 * T = FORM VIEW MODEL OR SET TO OBJECT IF NO VIEW MODEL
 * @param <T>
 */
public class ResponsePayloadModel<T>
{
	private T data;
	private String reference;
	private String result;
	private String action;
	private String payload;
	private Map<String, List<String>> messages;
	private Map<String, List<String>> errors;

	public ResponsePayloadModel()
	{
		this.messages = new HashMap<>();
		this.errors = new HashMap<>();
		this.errors.put("generalErrors", new ArrayList<>());
	}

	public ResponsePayloadModel(T data,
	                            String reference,
	                            String result,
	                            String action,
	                            String payload,
	                            Map<String, List<String>> messages,
	                            Map<String, List<String>> errors)
	{
		this.data = data;
		this.reference = reference;
		this.result = result;
		this.action = action;
		this.payload = payload;
		this.messages = messages;
		this.errors = errors;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public String getReference()
	{
		return reference;
	}

	public void setReference(String reference)
	{
		this.reference = reference;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public String getPayload()
	{
		return payload;
	}

	public void setPayload(String payload)
	{
		this.payload = payload;
	}

	public Map<String, List<String>> getMessages()
	{
		return messages;
	}

	public void setMessages(Map<String, List<String>> messages)
	{
		this.messages = messages;
	}

	public Map<String, List<String>> getErrors()
	{
		return errors;
	}

	public void setErrors(Map<String, List<String>> errors)
	{
		this.errors = errors;
	}


}
