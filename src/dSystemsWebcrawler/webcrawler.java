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
			
			Elements tableHeads = table.select("th");
			
			for(int i = 0; i < tableHeads.size(); i++) {
				print(tableHeads.get(i).text());
			}
			
			Elements tableRows = table.select("tr");
			
			for(int i = 0; i < tableRows.size(); i++) {
				Element row = tableRows.get(i);
				Elements cols = row.select("td");
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}
}
