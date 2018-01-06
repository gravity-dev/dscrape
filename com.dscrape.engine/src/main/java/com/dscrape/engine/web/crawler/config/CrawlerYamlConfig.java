package com.dscrape.engine.web.crawler.config;

import java.util.HashMap;
import java.util.Map;

public final class CrawlerYamlConfig {

	Map<String, CrawlerDetails> crawlers = new HashMap<String, CrawlerDetails>();

	public Map<String, CrawlerDetails> getCrawlers() {
		return crawlers;
	}

	public void setCrawlers(Map<String, CrawlerDetails> crawlers) {
		this.crawlers = crawlers;
	}
}
