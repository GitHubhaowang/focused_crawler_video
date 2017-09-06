import java.util.Iterator;
import java.util.Set;

import com.hh.config.ReadXML;
import com.hh.entity.URLEntity;
import com.hh.sprider.WebSprider;

public class Main {
	public static void main(String[] args) {
		ReadXML.init();
		
		WebSprider ws = new WebSprider();
		ws.crawling();
		
		Set<URLEntity> set = ws.getUrlC().getVisiteds();
		Iterator<URLEntity> iter = set.iterator();
		while (iter.hasNext()) {
			System.out.println("1");
			System.out.println(iter.next());
		}
	}
}
