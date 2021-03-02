package dSystemsWebcrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class webcrawler {
	
	public static void print(Object ret) {
		System.out.println(ret);
	}
	
	public static void parseTable(String url) {
		Document document;
		try {
			document = Jsoup.connect(url).timeout(10000).get();
			String title = document.title();
			
			Element table = document.select("table.wikitable").first();
			
			Elements tableHeads = table.select("th");
			
			for(int i = 0; i < tableHeads.size(); i++) {
				print(tableHeads.get(i).text());
			}
			
			Elements tableRows = table.select("tr");
			
			for(int i = 1; i < tableRows.size(); i++) {
				Element row = tableRows.get(i);
				Elements cols = row.select("td");
				print(cols.get(0).text());
				print(cols.get(1).text());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Running....");
		String url = args[0];
			
		int doNums = Integer.parseInt(args[2]);
		
		int count = 0;
		while(doNums >= 0) {
			parseTable(url);
			count++;
			doNums--;
			print(count + " tables parsed");
		}
		
		System.out.println("Done");
	}
}
