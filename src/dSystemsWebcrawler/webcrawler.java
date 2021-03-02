package dSystemsWebcrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class webcrawler {
	
	public static void print(String ret) {
		System.out.println(ret);
	}
	
	public static void main(String[] args) {
		System.out.println("Running....");
		String url = args[0];
		System.out.println("Starting page: " + url);
		System.out.println("Domain prefix: " + args[1]);
		System.out.println("Link amount: " + args[2]);
		Document document;
		try {
			document = Jsoup.connect(url).timeout(10000).get();
			String title = document.title();
			System.out.println("Title: " + title);
			
			Element table = document.select("table.wikitable").first();
			print(table.text());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}
}
