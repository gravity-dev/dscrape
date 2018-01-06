package com.dscrape.engine.web.crawler.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dscrape.engine.web.crawler.base.IContentCrawler;
import com.dscrape.engine.web.crawler.base.ICrawlerRegistry;
import com.dscrape.engine.web.crawler.config.CrawlerDetails;

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
	public List<IContentCrawler> listCrawlers() {
		List<IContentCrawler> list = new ArrayList<IContentCrawler>();
		for (Entry<String, IContentCrawler> entry : registryMap.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
		
	}

	@Override
	public IContentCrawler getCrawler(String name) {
		return registryMap.get(name);
	}
}
