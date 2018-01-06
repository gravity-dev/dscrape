package com.dscrape.engine.web.crawler.factory;

import com.dscrape.engine.web.crawler.base.CrawlerContext;
import com.dscrape.engine.web.crawler.base.IContentCrawler;
import com.dscrape.engine.web.crawler.impl.CrawlerTypeEnum;
import com.dscrape.engine.web.crawler.impl.QuoraContentCrawler;
import com.dscrape.engine.web.crawler.impl.SimpleWebContentCrawler;

public class CrawlerFactory {
	
	private CrawlerFactory(){
		
	}
	
	public static IContentCrawler getCrawlerInstance(CrawlerTypeEnum type,CrawlerContext ctx) {
		IContentCrawler instance=null;
		switch(type) {
			case SIMPLE_WEB:
				instance = new SimpleWebContentCrawler(ctx);
				break;
			case QUORA:
				instance = new QuoraContentCrawler(ctx);
				break;
				
		}		
		return instance;
	}

}
