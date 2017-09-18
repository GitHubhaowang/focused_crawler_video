package com.hh.util;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class AbstractHttpClientUtil {
	protected CloseableHttpClient httpClient;
	
	protected void init() {
		httpClient = HttpClients.createDefault();
	}
	
	/**
	 * 关闭CloseableHttpClient
	 * @param httpClient
	 */
	protected void httpClientClose(CloseableHttpClient httpClient) {
		if (httpClient != null) {
    		try {
    			httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
	
	/**
	 * 关闭CloseableHttpResponse
	 * @param httpResponse
	 */
	protected synchronized void httpResponseClose(CloseableHttpResponse httpResponse) {
		if (httpResponse != null) {
    		try {
				httpResponse.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
}
