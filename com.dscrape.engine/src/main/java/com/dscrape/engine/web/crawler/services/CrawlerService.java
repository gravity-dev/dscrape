package com.dscrape.engine.web.crawler.services;

import java.util.ArrayList;
import java.util.List;

import com.dscrape.app.utils.dataprocessing.DataCleaning;
import com.dscrape.engine.Engine;
import com.dscrape.engine.web.crawler.base.IContentCrawler;
import com.dscrape.engine.web.crawler.impl.AbstractContentCrawler;

public class CrawlerService {
	/**
	 * 	 Crawls the content and saves to db
	 */
	
	public static void crawlAndSaveContent() throws Exception{
		
		List<String> tagsList= new ArrayList<String>();
		List<IContentCrawler> crawlerList = CrawlerRegistryService.getInstance().listCrawlers();
		for(IContentCrawler crawlerInterface:crawlerList) {
			AbstractContentCrawler abstractCrawler = (AbstractContentCrawler)crawlerInterface;
			tagsList = DataCleaning.splitStrings(abstractCrawler.getCtx().getSearchTags(), ","); 
			
			try {
				Engine.getInstance().getStorageService().getContentStorageService().saveSearchContent(tagsList, abstractCrawler.getCtx().getSourceAddress(), crawlerInterface.getContent());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}
		
		
	}

	public static List<?> searchContent(String searchText) throws Exception{
		
		return Engine.getInstance().getStorageService().getContentStorageService().searchContentByTags(searchText);
		
	}
	
}
