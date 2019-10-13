package com.psi.parser;

public interface IParser {
	
	public String serialize(Object obj);
	
	public Object deSerialize(String jsonString, Object obj);

}
