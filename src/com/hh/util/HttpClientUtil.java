package com.hh.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil extends AbstractHttpClientUtil{
	private static HttpClientUtil instance = null;
	
	private HttpClientUtil() {
		init();
	}
	
	public synchronized static HttpClientUtil getInstance() {
		if (instance == null) {
			instance = new HttpClientUtil();
		}
		return instance;
	}
	
	
	/**
	 * get方式发送请求
	 * 
	 * @param map 参数
	 * @param charset 字符集
	 * @param url 地址
	 * @return
	 */
	public synchronized String getSend(Map<String, Object> map, String charset, String url) {
		String page = "";
		
        CloseableHttpResponse httpResponse = null;
	    try {
	    	// 请求条件
	        if (map != null && !map.isEmpty()) {
	        	//封装请求参数
	    		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    		
	    		Set<String> set = map.keySet();
	    		Iterator<String> iter = set.iterator();
	    		while (iter.hasNext()) {
	    			String key = iter.next();
	    			params.add(new BasicNameValuePair(key, (String) map.get(key)));
	    		}
	    		String str = "";  
	    		str = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));
	
	        	if (!"".equals(str)) {
	        		url += str;
	        	}
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
				page = EntityUtils.toString(entity, charset);
			}
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
        	// 关闭连接,释放资源
        	httpResponseClose(httpResponse);
        }
		return page;
	}
	
	/**
	 * post方式发送请求
	 * @param map 请求条件
	 * @param charset 字符集
	 * @param url 地址
	 * @return
	 */
	public synchronized String getPost(Map<String, Object> map, String charset, String url) {
        String responseStr = null;
        // 响应对象
        CloseableHttpResponse response = null;
        try {
            // 创建 Post 连接对象
            HttpPost httpPost = new HttpPost(url);
            
            // 请求条件
            if (map != null || !map.isEmpty()) {
                // 创建参数队列
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                Set<String> set = map.keySet();
        		Iterator<String> iter = set.iterator();
        		while (iter.hasNext()) {
        			String key = iter.next();
        			formparams.add(new BasicNameValuePair(key, (String) map.get(key)));
        		}

                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, charset);
                httpPost.setEntity(uefEntity);
            }

            // 连接并得到响应对象
            response = httpClient.execute(httpPost);

            // 得到响应参数
            HttpEntity entity = response.getEntity();
            if (entity != null) {
//                System.out.println("--------------------------------------");
                responseStr = EntityUtils.toString(entity, charset);
//                System.out.println("响应内容:\n " + responseStr);
//                System.out.println("--------------------------------------");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
        	httpResponseClose(response);
        }
        return responseStr;
	}
}
