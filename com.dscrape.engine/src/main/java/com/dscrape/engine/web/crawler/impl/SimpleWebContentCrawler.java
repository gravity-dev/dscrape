package com.dscrape.engine.web.crawler.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dscrape.engine.web.crawler.base.CrawlerContext;

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
			doc = Jsoup.connect(ctx.getSourceAddress()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String title = doc.title();
        String body = doc.body().text();
        
        return title + body;
	}
	
}
