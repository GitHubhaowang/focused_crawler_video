package focused_crawler_video;

import java.util.HashMap;
import java.util.Map;

import com.hh.util.HttpClientUtil;
import com.hh.util.ThunderUtil;

public class Main {
	public static void main(String[] args) {
//		ThunderUtil.instance.XL_Init();
		/*ThunderUtil.DownTaskParam.ByValue dtp = new ThunderUtil.DownTaskParam.ByValue();
		dtp.szTaskUrl = new String("http://img3.redocn.com/tupian/20150312/haixinghezhenzhubeikeshiliangbeijing_3937174.jpg").toCharArray();
		dtp.szFilename = new String("111111111").toCharArray();
		dtp.szSavePath = new String("D://test").toCharArray();
		dtp.IsOnlyOriginal = 0;
		dtp.DisableAutoRename = 1;
		dtp.IsResume = 1;
		ThunderUtil.instance.XL_StartTask(ThunderUtil.instance.XL_CreateTask(dtp));*/
		
		/*ThunderUtil.instance.XL_CreateTaskByThunder(new WString("ftp://ygdy8:ygdy8@y153.dydytt.net:8273/[阳光电影www.ygdy8.com].拆弹专家.HD.720p.国语中字.mkv"), new WString("[阳光电影www.ygdy8.com].拆弹专家.HD.720p.国语中字.mkv"), new WString(""), new WString("utf-8"), new WString(""));
		ThunderUtil.instance.XL_UnInit();*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", "复仇者联盟");
		System.out.println(HttpClientUtil.getInstance().getPost(map, "utf-8", "http://www.80s.tw/search"));
	}
}
