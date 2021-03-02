package dSystemsWebcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class parserTest {
	public static void print(String ret) {
		System.out.println(ret);
	}
	public static void main(String[] args) {
		System.out.println("running");
		String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(html);
		Element link = doc.select("a").first();
		
		String text = doc.body().text();
		print(text);
		String linkHref = link.attr("href");
		print(linkHref);
		String linkText = link.text();
		print(linkText);
		
		String linkOuterH = link.outerHtml();
		print(linkOuterH);
		String linkInnerH = link.html();
		print(linkInnerH);
	}
}
