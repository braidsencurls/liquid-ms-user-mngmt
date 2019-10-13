package com.psi.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by mrprintedwall on 11/14/16.
 */
public interface RestfulClientInterface
{
	/**
	 * Retrieval via HTTP GET and process as POJO result object
	 *
	 * @param eid
	 * @param urlParameters
	 * @param typeOfT
	 * @return
	 * @throws Exception
	 */
	<T> T zgetSingle(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception;

	/**
	 * Retrieval via HTTP GET and process as POJO result list
	 *
	 * @param eid
	 * @param urlParameters
	 * @param typeOfT
	 * @return
	 * @throws Exception
	 */
	<T> List<T> zgetList(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception;

	/**
	 * Retrieval via HTTP POST and process as POJO result object
	 *
	 * @param eid
	 * @param urlParameters
	 * @param typeOfT
	 * @return
	 * @throws Exception
	 */
	<T> T zpostSingle(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception;

	/**
	 * Retrieval via HTTP POST and process as POJO result list
	 *
	 * @param eid
	 * @param urlParameters
	 * @param typeOfT
	 * @return
	 * @throws Exception
	 */
	<T> List<T> zpostList(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception;

	/**
	 * Insert via HTTP POST and process as POJO result
	 *
	 * @param eid
	 * @param urlParameters
	 * @param item
	 * @param typeOfT
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	<T> T zinsert(String eid, Map<String, Object> urlParameters, T item, Type typeOfT) throws Exception;

	/**
	 * Update via HTTP POST and process as POJO result
	 *
	 * @param eid
	 * @param urlParameters
	 * @param item
	 * @param typeOfT
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	<T> T zupdate(String eid, Map<String, Object> urlParameters, T item, Type typeOfT) throws Exception;

	/**
	 * This is similar to insert but more emphasis on creating information with binary content.
	 * byte[] are converted into base64 string before transmission. Result from REST service are processed
	 * as POJO
	 *
	 * @param eid
	 * @param urlParameters
	 * @param item
	 * @param typeOfT
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	<T> T zupload(String eid, Map<String, Object> urlParameters, T item, Type typeOfT) throws Exception;

	/**
	 * Delete via HTTP DELETE and process as POJO result object
	 *
	 * @param eid
	 * @param urlParameters
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	<T> T zdelete(String eid, Map<String, Object> urlParameters, Type typeOfT) throws Exception;

	/**
	 * Retrieval via HTTP GET and process as JSON result string
	 *
	 * @param eid
	 * @param urlParameters
	 * @return
	 * @throws Exception
	 */
	String zgetJSON(String eid, Map<String, Object> urlParameters) throws Exception;

	/**
	 * Retrieval via HTTP POST and process as JSON result string
	 *
	 * @param eid
	 * @param urlParameters
	 * @return
	 * @throws Exception
	 */
	String zpostJSON(String eid, Map<String, Object> urlParameters) throws Exception;

	/**
	 * Insert via HTTP POST and process as JSON result string
	 *
	 * @param eid
	 * @param urlParameters
	 * @return
	 * @throws Exception
	 */
	<T> String zinsertJSON(String eid, Map<String, Object> urlParameters, T item) throws Exception;

	/**
	 * Update via HTTP POST and process as JSON result string
	 *
	 * @param eid
	 * @param urlParameters
	 * @return
	 * @throws Exception
	 */
	<T> String zupdateJSON(String eid, Map<String, Object> urlParameters, T item) throws Exception;

	/**
	 * This is similar to insert but more emphasis on creating information with binary content.
	 * byte[] are converted into base64 string before transmission. Result from REST service are processed
	 * as JSON result string
	 *
	 * @param eid
	 * @param urlParameters
	 * @param item
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	<T> String zuploadJSON(String eid, Map<String, Object> urlParameters, T item) throws Exception;

	/**
	 * Delete via HTTP DELETE and process as JSON result string
	 *
	 * @param eid
	 * @param urlParameters
	 * @return
	 * @throws Exception
	 */
	String zdeleteJSON(String eid, Map<String, Object> urlParameters) throws Exception;
}
