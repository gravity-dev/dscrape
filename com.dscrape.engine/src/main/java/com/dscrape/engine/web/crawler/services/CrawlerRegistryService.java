package com.dscrape.engine.web.crawler.services;

import java.util.HashMap;
import java.util.Map;

import com.dscrape.engine.web.crawler.base.IContentCrawler;
import com.dscrape.engine.web.crawler.base.ICrawlerRegistry;

public class CrawlerRegistryService implements ICrawlerRegistry {

	private static Map<String, IContentCrawler> registryMap = new HashMap<String, IContentCrawler>();

	private static CrawlerRegistryService registry = null;

	private CrawlerRegistryService() {

	}

	public static CrawlerRegistryService getInstance() {
		if (registry == null) {
			registry = new CrawlerRegistryService();
		}
		return registry;
	}

	@Override
	public void register(String name, IContentCrawler crawler) {
		registryMap.put(name, crawler);
	}

	@Override
	public void deRegister(String name) {
		registryMap.remove(name);
	}

	@Override
	public void listCrawlers() {
	}

	@Override
	public IContentCrawler getCrawler(String name) {
		return registryMap.get(name);
	}
}
