package com.hh.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	/**
	 * get方式发送请求
	 * @param map 参数
	 * @param charaset 字符集
	 * @param url 地址
	 * @return
	 */
	public static synchronized String getSend(Map<String, Object> map, String charaset, String url) {
		String page = "";
		//创建默认的httpClient实例
		CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        
        //封装请求参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			params.add(new BasicNameValuePair(key, (String) map.get(key)));
		}

        String str = "";  
      
        try {
        	str = EntityUtils.toString(new UrlEncodedFormEntity(params, charaset));
        	if (!"".equals(str)) {
        		url += "?"+str;
        	}
            System.out.println("--GET请求的URL-- " + url);
            //用get方法发送http请求
            HttpGet get = new HttpGet(url);
            //发送get请求
            System.out.println("发送请求");
            httpResponse = httpClient.execute(get);
            
			//response实体
			HttpEntity entity = httpResponse.getEntity();
			if (null != entity){
				page = EntityUtils.toString(entity, charaset);
			}
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
        	if (httpResponse != null) {
        		try {
    				httpResponse.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
        	}
        }
		return page;
	}
	
	/**
	 * get方式发送请求
	 * @param charaset 字符集
	 * @param url 地址
	 * @return
	 */
	public static synchronized String getSend(String charaset, String url) {
		String page = "";
		//创建默认的httpClient实例
		CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        
        try {
            System.out.println("--GET请求的URL-- " + url);
            //用get方法发送http请求
            HttpGet get = new HttpGet(url);
            //发送get请求
            httpResponse = httpClient.execute(get);
            
			//response实体
			HttpEntity entity = httpResponse.getEntity();
			if (null != entity){
				page = EntityUtils.toString(entity, charaset);
			}
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
        	if (httpResponse != null) {
        		try {
    				httpResponse.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
        	}
        }
		return page;
	}
}
