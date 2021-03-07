package dSystemsWebcrawler;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class webcrawler {
	
	public static void print(Object ret) {
		System.out.println(ret);
	}
	
	public static boolean parseTable(String url) {
		Boolean tableMade = false;
		Document document;
		try {
			document = Jsoup.connect(url).timeout(10000).get();
			String title = document.title();
			String titleNew = title.substring(0, title.indexOf("-"));
			
			Element table = document.select("table.wikitable").first();
			
			
			
			Element caption = table.select("caption").first();
			String captionNew = caption.text().replaceAll("\\s", "");
			
			Elements tableHeads = table.select("th");
			
			if(tableHeads.get(0) != null) {
				System.out.println("Table does exist");
				
				Elements tableRows = table.select("tr");
				
				String fileName = titleNew + "_" + captionNew + ".txt";
				try {
					File retFile = new File(fileName);
					
					if(retFile.createNewFile()) {
						System.out.println("File created: " + retFile.getName());
					}else {
						System.out.println("File already exists");
					}
					
					FileWriter writer = new FileWriter(fileName);
					
					writer.write("\"URL\"," + url + "\"\n");
					
					writer.write("\"Table\", \"" + caption.text() + "\"\n");
					
					writer.write("\n");
					
					writer.write("\"Headings\",");
					
					for(int i = 0; i < tableHeads.size(); i++) {
						if(i+1 != tableHeads.size()) {
							writer.write("\""+ tableHeads.get(i).text() + "\",");
						}else {
							writer.write("\"" + tableHeads.get(i).text() + "\"");
						}
						
					}
					writer.write("\n");
					
					for(int i = 1; i < tableRows.size(); i++) {
						Element row = tableRows.get(i);
						Elements cols = row.select("td");
						writer.write("\"\",");
						writer.write("\"" + cols.get(0).text() + "\",");
						writer.write("\"" + cols.get(1).text() + "\"\n");
					}
					writer.close();
					tableMade = true;
					return tableMade;
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				print("No table");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tableMade;
	}
	
	public static String[] getLinks(String url, String domain) {
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements links = doc.select("a[href]");
			
			for(int i = 2; i < links.size(); i++) {
				print(links.get(i).attr("href").toString());
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("Running....");
		String url = args[0];
		String domainName = args[1];
		int bfsDepthMax = Integer.parseInt(args[2]);
		
		print("Starting point: " + url);
		print("Domain: " + domainName);
		
		print(parseTable(url));
		
		print("First page done, starting BFS from " + url);
		print("BFS Depth = " + bfsDepthMax);
		
		getLinks(url, domainName);
		
		print("Done");
	}
}
