package com.hrs.financial.httpclient;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class HttpClientUtil {
	
	private static final String encoding = "UTF-8";
	
	/**
	 * 
	 * @param <T>
	 * @param url
	 * @param requestObject java.util.Map or  bean
	 * @return
	 */
	public static  <T> T doPost(String url,Object requestObject,Class<T> resultClass){
		return post(url,requestObject,resultClass,null);
	}


	public static  <T> T doPost(String url,Object requestObject,Class<T> resultClass,List<NameValuePair> others){
		return post(url,requestObject,resultClass,others);
	}

	public static  <T> T doPost(String url,Object requestObject,TypeReference<T> typeReference,List<NameValuePair> others){
		return post(url, requestObject, typeReference,others);
	}

	public static  <T> T doPost(String url,Object requestObject,TypeReference<T> typeReference){
		return post(url, requestObject, typeReference,null);
	}




	private static  <T> T post(String url,Object requestObject,Object resultType,List<NameValuePair> others){
		{
			T result = null;
			HttpClient httpClient = null;
			HttpResponse response = null;
			try {
				httpClient = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);

				if(requestObject != null){
					HttpEntity formEntity = createUrlEncodedFormEntity(requestObject,others);
					if(formEntity != null ){
						post.setEntity(formEntity);
					}
				}
				response = httpClient.execute(post);

				String JSONStr = getJSONString(response);

				// transfer json to bean
				if(resultType instanceof Class<?>){
					result =  consumeJSONStr(JSONStr,(Class<T>)resultType);
				}else if (resultType instanceof TypeReference<?>){
					result = consumeJSONStr(JSONStr,(TypeReference<T>)resultType);
				}else{
					throw new IllegalArgumentException("resultType参数:暂不支持类型为"+
										(resultType==null? "null":result.getClass().getName())+"的JSON解析!");
				}

			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}


	}


	private static String getJSONString(HttpResponse response) throws IOException{
		HttpEntity responseEntity = response.getEntity();
		return EntityUtils.toString(responseEntity,encoding);
	}

	private static <T>T consumeJSONStr(String JSONStr,TypeReference<T> typeReference) throws IOException {
		T result = null ;
		ObjectMapper mapper = new ObjectMapper();
		result = mapper.readValue(JSONStr,typeReference);
		return result;
	}

	private static <T>T consumeJSONStr(String JSONStr,Class<T> resultClass) throws IOException {
		T result = null ;
		ObjectMapper mapper = new ObjectMapper();
		result = mapper.readValue(JSONStr,resultClass);
		return result;
	}
	
	private static HttpEntity createUrlEncodedFormEntity(Object requestObject,List<NameValuePair> others) throws IOException,IllegalAccessException{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(requestObject instanceof Map){
			Map<?,?> requestMap = (Map<?,?>)requestObject;
			for(Map.Entry<?, ?> entry: requestMap.entrySet()){
				Object key = entry.getKey();
				Object val = entry.getValue();
				if(key != null && val != null){
					params.add(new BasicNameValuePair(key.toString(),val.toString()));
				}
			}			
		}else{
			Class<?> requestClass = requestObject.getClass();
			Field fields[] = requestClass.getDeclaredFields();
			for(Field field : fields){
				String name = field.getName();
				if(Modifier.isPrivate(field.getModifiers()) || Modifier.isProtected(field.getModifiers())){
					field.setAccessible(true);
				}
				Object val = field.get(requestObject);
				if(val != null ){
					params.add(new BasicNameValuePair(name,String.valueOf(val)));
				}
			}
		}
		if(others != null && others.size() >0 ){
			params.addAll(others);
		}

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,encoding);

		return formEntity;
	}
	
	
	
	
	

}
