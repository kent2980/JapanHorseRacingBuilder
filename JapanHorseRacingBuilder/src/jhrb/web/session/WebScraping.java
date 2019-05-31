package jhrb.web.session;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraping {

	public WebScraping(String url) throws IOException {
		this.url = url;
		document = Jsoup.connect(url).get();
	}
	Document document;
	String url;
	public Document getDocument() {
		return document;
	}

	public static void main(String[] args) throws IOException {
		WebScraping ws = new WebScraping("https://db.netkeiba.com/horse/2016104880/");
		Document dc = ws.getDocument();
		Elements els = dc.select("#HorseMainPhoto");

		for(Element el : els) {
			System.out.println(el.attr("src"));
		}

	}
}
