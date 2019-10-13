package com.psi.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Qualifier("jsonParser")
public class JsonParser implements IParser {
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public String serialize(Object obj) {
		if(obj != null) {
			try {
				mapper.writerWithDefaultPrettyPrinter();
				return mapper.writeValueAsString(obj).replaceAll("\\\\", "");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}

	@Override
	public Object deSerialize(String jsonString, Object obj) {
		if(obj != null) {
			try {
				return gson.fromJson(jsonString, obj.getClass());
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}

}
