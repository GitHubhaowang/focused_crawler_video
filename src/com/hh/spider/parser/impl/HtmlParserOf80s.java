package com.hh.spider.parser.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hh.collection.URLEntity;
import com.hh.spider.parser.HtmlParser;
import com.hh.util.HttpClientUtil;

/**
 * 80s网站的页面解析
 * @author hh
 *
 */
public class HtmlParserOf80s implements HtmlParser{

	@Override
	public String getBody(Map<String, Object> map, String charset, String url) {
		if (map == null || map.isEmpty()) {
			return HttpClientUtil.getInstance().getSend(map, charset, url);
		}
		return HttpClientUtil.getInstance().getPost(map, charset, url);
	}

	@Override
	public String analysisUrl(String page, String name) {
		Pattern pattern = Pattern.compile("<li>([\\s\\S]*)<a href=\"/movie/([0-9]*)\" target=\"_blank\">([\\s\\S]*?)<i class=\"fa fa-film\"></i>([\\s\\S]*?)[电影]([\\s\\S]*?)" + name + "([\\s\\S]*?)(.+?)([\\s\\S]*?)</a>");
		Matcher matcher = pattern.matcher(page);
		if (matcher.find()) {
			return "http://www.80s.tw/movie/" + matcher.group(2);
		}
		return "";
	}

	@Override
	public boolean isDownPage(String page) {
		Pattern pattern = Pattern.compile("<title>(\\s)*?(.+?)高清mp4迅雷下载-80s手机电影</title>");
		Matcher matcher = pattern.matcher(page);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	@Override
	public URLEntity getDownUrl(URLEntity ue) {

		Map<String, Object> map = null;
		map = getStartMap(ue.getName());
		
		return getDownUrl(map, ue, "http://www.80s.tw/search", 1);
	}
	
	private URLEntity getDownUrl(Map<String, Object> map, URLEntity ue, String url,int level) {
		
		String page = getBody(map, "utf-8", url);
		if ("".equals(page)) {
			System.out.println("没有找到");
			return ue;
		}
		
		if (level > 5) {	// 层级太深
			return ue;
		}
		// 判断是否为下载页面
		if (isDownPage(page)) {
			ue.setDownPageURL(url);
			ue.setDownURL(getDownUrlFromPage(page, ue.getName()));
			return ue;
		}
		// 解析当前页面
		String newUrl = analysisUrl(page, ue.getName());
		if ("".equals(newUrl)) {
			return ue;
		}
		return getDownUrl(null, ue, newUrl, level+1);
	}

	@Override
	public String getDownUrlFromPage(String page) {
		Pattern pattern = Pattern.compile("<a rel=\"nofollow\" href=\"(.+?)\" >");
		Matcher matcher = pattern.matcher(page);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}
	
	private String getDownUrlFromPage(String page, String name) {
		Pattern pattern = Pattern.compile("<a rel=\"nofollow\" href=\"(.+?)\" thunderrestitle=\""+ name);
		Matcher matcher = pattern.matcher(page);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}

	@Override
	public Map<String, Object> getStartMap(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", name);
		return map;
	}
	
	
	public static void main(String[] args) {
		HtmlParser hp = new HtmlParserOf80s();
		
		URLEntity ue = new URLEntity();
		ue.setName("使徒行者");
		
		System.out.println(hp.getDownUrl(ue));
	}
}
