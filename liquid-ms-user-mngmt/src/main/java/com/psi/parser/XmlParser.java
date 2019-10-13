package com.psi.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("xmlParser")
public class XmlParser implements IParser {

	@Override
	public String serialize(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deSerialize(String jsonString, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
