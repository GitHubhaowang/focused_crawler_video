package com.hh.spider.parser.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hh.collection.URLEntity;
import com.hh.spider.parser.AbstractHtmlParser;
import com.hh.util.HttpClientUtil;

/**
 * 电影天堂
 * @author hh
 *
 */
public class HtmlParserOfDYTT extends AbstractHtmlParser {

	@Override
	public String getBody(Map<String, Object> map, String charaset, String url) {
		return HttpClientUtil.getInstance().getSend(map, charaset, url);
    }

	@Override
	public String analysisUrl(String page, String name) {
		Pattern pattern = Pattern.compile("<b><a href=\'(.+?)\'>(.+?)《<font color='red'>"+name+"</font>(.+?)");
		Matcher matcher = pattern.matcher(page);
		if (matcher.find()) {
			return "http://www.ygdy8.com" + matcher.group(1);
		}
		return "";
	}

	@Override
	public boolean isDownPage(String page) {
		Pattern pattern = Pattern.compile("<title>(.+?)迅雷下载_阳光电影(_电影天堂)?</title>");
		Matcher matcher = pattern.matcher(page);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	@Override
	public URLEntity getDownUrl(URLEntity ue) {
		System.out.println(ue.getName());
		Map<String, Object> map = null;
		map = getStartMap(ue.getName());
		
		return getDownUrl(map, ue, "http://s.dydytt.net/plus/search.php", 1);
	}
	
	private URLEntity getDownUrl(Map<String, Object> map, URLEntity ue, String url,int level) {
		
		String page = getBody(map, "gbk", url);
		if ("".equals(page)) {
			System.out.println("没有找到");
			return ue;
		}
		
		System.out.println("=======================================================================");
		if (level > 5) {	// 层级太深
			return ue;
		}
		// 判断是否为下载页面
		if (isDownPage(page)) {
			ue.setDownPageURL(url);
			ue.setDownURL(getDownUrlFromPage(page));
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
		Pattern pattern = Pattern.compile("<td style=\"WORD-WRAP: break-word\" bgcolor=\"#fdfddf\"><a href=\"(.+?)\">(.+?)</a></td>");
		Matcher matcher = pattern.matcher(page);
		if (matcher.find() &&matcher.groupCount()>=2 && matcher.group(1).equals(matcher.group(2))) {
			return matcher.group(1);
		}
		return "";
	}
	
	@Override
	public Map<String, Object> getStartMap(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwtype", "0");
		map.put("keyword", name);
		return map;
	}
}
