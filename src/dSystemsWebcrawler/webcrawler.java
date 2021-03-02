package dSystemsWebcrawler;

import java.io.File;
import java.io.FileWriter;
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
			
			Element caption = table.select("caption").first();
			
			Elements tableHeads = table.select("th");
			
			Elements tableRows = table.select("tr");
			
			try {
				File retFile = new File("filename.txt");
				
				if(retFile.createNewFile()) {
					System.out.println("File created: " + retFile.getName());
				}else {
					System.out.println("File already exists");
				}
				
				FileWriter writer = new FileWriter("filename.txt");
				
				for(int i = 1; i < tableRows.size(); i++) {
					Element row = tableRows.get(i);
					Elements cols = row.select("td");
					writer.write(cols.get(0).text() + " ");
					writer.write(cols.get(1).text() + "\n");
				}
				writer.close();
			}catch(Exception e) {
				e.printStackTrace();
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
		while(doNums >= 1) {
			parseTable(url);
			count++;
			doNums--;
			print(count + " tables parsed");
		}
		
		System.out.println("Done");
	}
}
