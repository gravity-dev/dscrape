package com.dscrape.engine.web.crawler.services;

import java.util.List;

import com.dscrape.engine.web.crawler.base.IContentCrawler;

public class CrawlerService {
	
	public static List<IContentCrawler> getCrawlers() {
		return CrawlerRegistryService.getInstance().listCrawlers();
	}

	public static List<IContentCrawler> searchContent(String searchText) {
		return CrawlerRegistryService.getInstance().listCrawlers();
	}
	
}
