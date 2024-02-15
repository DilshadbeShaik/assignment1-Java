package Java_Selenium;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class foreach {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		for (String link : findLinks("https://www.flipkart.com/")) {
        System.out.println(link);
        System.out.println(" ");
    	}
        
	
	}
	 private static List<String> findLinks(String url) throws IOException {

	        List<String> links = new LinkedList<>();

	        Document doc = Jsoup.connect(url).get();

	        Elements elements = doc.select("a[href]");
	        for (Element element : elements) {
	            links.add(element.attr("href"));
	        }

	        return links;

	    }

}







