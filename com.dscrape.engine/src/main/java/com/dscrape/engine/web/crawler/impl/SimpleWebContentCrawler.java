package com.dscrape.engine.web.crawler.impl;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dscrape.engine.web.crawler.base.CrawlerContext;
import com.dscrape.engine.web.crawler.enums.CrawlerElementEnum;
import com.dscrape.engine.web.crawler.enums.CrawlerTypeEnum;

public class SimpleWebContentCrawler extends AbstractContentCrawler {

	public SimpleWebContentCrawler(CrawlerContext ctx) {
		super(ctx, CrawlerTypeEnum.SIMPLE_WEB);
	}

	public void initialize(CrawlerContext ctx) {
		System.out.println("initialized " + SimpleWebContentCrawler.class + " , ID=" + this.id);
	}

	@Override
	public String getContent() {
		//return "Visit nearest professionals for your hair designs: Mark Antony, Indiranagarm contant:+9198748987983";
		
        Document doc=null;
		try {	  
		    Connection connection =Jsoup.connect(ctx.getSourceAddress());
		    connection.validateTLSCertificates(false);
			doc = connection.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //String title = doc.title();
        Element element = null;
        Elements subElementClass = null;
        //Elements attributes=null;
        Elements meta=null;
        String metaStr=null;
        Element attribute=null;
        String returnString="";
        //String body = doc.body().text();
                	
    	for(String path:ctx.getSearchPaths()) {
    		String[] paths = path.split(":");
    		if(paths[0].equalsIgnoreCase(CrawlerElementEnum.META.getName())){
    			String[] metaValues = paths[1].split("=");
    			String[] attrKeyValue = metaValues[0].split("-");
    			meta = doc.getElementsByAttributeValue(attrKeyValue[0], attrKeyValue[1]);
    			metaStr =meta.attr(metaValues[1]); 
    		}
    		
    		if(paths[0].equalsIgnoreCase(CrawlerElementEnum.ELEMENT.getName())) {
    				element = doc.getElementById(paths[1]);
    		}else if(paths[0].equalsIgnoreCase(CrawlerElementEnum.SUBELEMENTCLASS.getName())) {
    			if(element!=null)
    				subElementClass = element.getElementsByClass(paths[1]);
    			else 
    				subElementClass = doc.getElementsByClass(paths[1]);
    			
    		}else if(paths[0].equalsIgnoreCase(CrawlerElementEnum.SUBELEMENTCLASSTAG.getName())) {
    			if(subElementClass!=null) {
    				String[] tagAndIndex= paths[1].split("=");
    				attribute = subElementClass.get(0).getElementsByTag(tagAndIndex[0]).get(Integer.valueOf(tagAndIndex[1]));
    			}
    		}
    	}
        
        //Element element = doc.select("div.field-itmes").first();            	
        
    	if(meta!=null)
    		returnString +=metaStr;
    	else if(attribute!=null)
        	returnString +=attribute;
        else if(subElementClass!=null)
        	returnString +=subElementClass;
        else if(element!=null)
        	returnString +=element;
        
        return returnString;
	}
	
}
