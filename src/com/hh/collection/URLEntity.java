package com.hh.collection;

/**
 * 资源实体类
 * @author hh
 * 2017-9-4 15:02:46
 */
public class URLEntity {
	private String name;	// 资源名称
	private String downURL;		// 下载地址
	private String downPageURL;		// 下载页面地址
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDownURL() {
		return downURL;
	}
	public void setDownURL(String downURL) {
		this.downURL = downURL;
	}
	public String getDownPageURL() {
		return downPageURL;
	}
	public void setDownPageURL(String downPageURL) {
		this.downPageURL = downPageURL;
	}
	@Override
	public String toString() {
		return "URLEntity [name=" + name + ", downURL=" + downURL + ", downPageURL=" + downPageURL + "]";
	}
}
