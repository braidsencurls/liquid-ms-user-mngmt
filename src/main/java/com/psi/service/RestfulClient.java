package com.psi.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;

/**
 * Created by mrprintedwall on 11/15/16.
 */
public class RestfulClient implements RestfulClientInterface
{
	private static Logger logger = LoggerFactory.getLogger(RestfulClient.class);
	private String baseUrl;
	private boolean isKeyRequired;
	private String apiKey;

	public RestfulClient(String baseUrl, boolean isKeyRequired, String apiKey)
	{
		this.baseUrl = baseUrl;
		this.isKeyRequired = isKeyRequired;
		this.apiKey = apiKey;
	}

	@Override
	public <T> T zgetSingle(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.get(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processSinglePojoResult(httpResponse, typeOfT);
	}

	@Override
	public <T> List<T> zgetList(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.get(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processListPojoResult(httpResponse, typeOfT);
	}

	@Override
	public <T> T zpostSingle(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processSinglePojoResult(httpResponse, typeOfT);
	}

	@Override
	public <T> List<T> zpostList(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processListPojoResult(httpResponse, typeOfT);
	}

	@Override
	public <T> T zinsert(String eid, Map<String, Object> urlParameters, T item, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.body(objectMapper.writeValueAsString(item))
				.asStringAsync();
		return processSinglePojoResult(httpResponse, typeOfT);
	}

	@Override
	public <T> T zupdate(String eid, Map<String, Object> urlParameters, T item, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.body(objectMapper.writeValueAsString(item))
				.asStringAsync();
		return processSinglePojoResult(httpResponse, typeOfT);
	}

	@Override
	public <T> T zupload(String eid, Map<String, Object> urlParameters, T item, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.body(objectMapper.writeValueAsString(item))
				.asStringAsync();
		return processSinglePojoResult(httpResponse, typeOfT);
	}

	@Override
	public <T> T zdelete(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.get(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processSinglePojoResult(httpResponse, typeOfT);
	}

	@Override
	public String zgetJSON(String eid, Map<String, Object> urlParameters) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.get(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processJsonResult(httpResponse);
	}

	@Override
	public String zpostJSON(String eid, Map<String, Object> urlParameters) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processJsonResult(httpResponse);
	}

	@Override
	public <T> String zinsertJSON(String eid, Map<String, Object> urlParameters, T item) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.body(objectMapper.writeValueAsString(item))
				.asStringAsync();
		return processJsonResult(httpResponse);
	}

	@Override
	public <T> String zupdateJSON(String eid, Map<String, Object> urlParameters, T item) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.body(objectMapper.writeValueAsString(item))
				.asStringAsync();
		return processJsonResult(httpResponse);
	}

	@Override
	public <T> String zuploadJSON(String eid, Map<String, Object> urlParameters, T item) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.body(objectMapper.writeValueAsString(item))
				.asStringAsync();
		return processJsonResult(httpResponse);
	}

	@Override
	public String zdeleteJSON(String eid, Map<String, Object> urlParameters) throws Exception
	{
		Map<String, String> headers = new HashMap<>();
		if(isKeyRequired)
		{
			headers.put("Authorization", this.apiKey);
		}
		headers.put("accept", "application/json;charset=UTF-8");

		Future<HttpResponse<String>> httpResponse = Unirest.post(getBaseUrl() + eid)
				.headers(headers)
				.queryString("eid", eid)
				.queryString(urlParameters)
				.asStringAsync();
		return processJsonResult(httpResponse);
	}

	private <T> T processSinglePojoResult(Future<HttpResponse<String>> httpResponse, Type typeOfT) throws Exception
	{
		Gson gson = getGsonWithSerializerDeserializer();
		String jsonString = httpResponse.get().getBody().toString();
		JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
		if (!jsonString.equalsIgnoreCase("null") && !jsonString.contains("\"data\":null") && jsonElement != null)
		{
			JsonObject resultObject = jsonElement.getAsJsonObject();
			JsonElement dataMember = resultObject.getAsJsonObject("data");
			if (dataMember != null)
			{
				if (!dataMember.isJsonNull())
				{
					return gson.fromJson(dataMember, typeOfT);
				}
				else
				{
					return gson.fromJson(resultObject, typeOfT);
				}
			}
			else
			{
				return gson.fromJson(resultObject, typeOfT);
			}
		}
		else
		{
			return null;
		}
	}

	private <T> List<T> processListPojoResult(Future<HttpResponse<String>> httpResponse, Type typeOfT) throws Exception
	{
		Gson gson = getGsonWithSerializerDeserializer();
		JsonElement jsonElement = gson.fromJson(httpResponse.get().getBody(), JsonElement.class);
		JsonObject resultObject = jsonElement.getAsJsonObject();
		JsonArray dataMember = resultObject.getAsJsonArray("data");
		if (!dataMember.isJsonNull())
		{
			if (dataMember.isJsonArray())
			{
				return gson.fromJson(dataMember, typeOfT);
			}
		}
		return new ArrayList<>();
	}

	private String processJsonResult(Future<HttpResponse<String>> httpResponse) throws Exception
	{
		Gson gson = getGsonWithSerializerDeserializer();
		JsonElement jsonElement = gson.fromJson(httpResponse.get().getBody(), JsonElement.class);
		if (jsonElement.isJsonArray())
		{
			JsonArray mainJsonObject = jsonElement.getAsJsonArray();
			return mainJsonObject.toString();
		}
		else
		{
			JsonObject mainJsonObject = jsonElement.getAsJsonObject();
			return mainJsonObject.toString();
		}
	}

	private Gson getGsonWithSerializerDeserializer()
	{
		GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping();
		gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>()
		{
			public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
			{
				String dateString = json.getAsString();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSZ");
				try
				{
					if (StringUtils.isBlank(dateString))
					{
						return null;
					}
					else
					{
						return df.parse(dateString);
					}
				}
				catch (ParseException parseException)
				{
					System.out.println(parseException.getMessage());
					return null;
				}
			}
		}).registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter());
		return gsonBuilder.create();
	}

	//CODE FROM or BASED: https://gist.github.com/orip/3635246
	private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
		public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			return Base64.getDecoder().decode(json.getAsString());
		}

		public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
		}
	}

	public String getBaseUrl()
	{
		if(baseUrl != null)
		{
			if(!baseUrl.endsWith("/"))
				return baseUrl + "/";
		}
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	public boolean isKeyRequired()
	{
		return isKeyRequired;
	}

	public void setKeyRequired(boolean keyRequired)
	{
		isKeyRequired = keyRequired;
	}

	public String getApiKey()
	{
		return apiKey;
	}

	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}
}
