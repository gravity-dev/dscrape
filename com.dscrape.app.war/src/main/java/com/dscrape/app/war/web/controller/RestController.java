package com.dscrape.app.war.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.dscrape.engine.web.crawler.base.IContentCrawler;
import com.dscrape.engine.web.crawler.services.CrawlerService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping("/getcontent")
	public String index() {
		/*
		 * String content=null;
		 * 
		 * YamlConfigLoader configLoader = new YamlConfigLoader(); CrawlerYamlConfig
		 * config = configLoader.parseCrawlerYaml(); Set<Entry<String,CrawlerDetails>>
		 * entrySet = config.getCrawlers().entrySet(); for(Entry<String,CrawlerDetails>
		 * entry: entrySet) { String name = entry.getValue().getName(); content +=
		 * CrawlerRegistryService.getInstance().getCrawler(name).getContent(); }
		 * 
		 * return content;
		 */
		String Content ="";
		List<IContentCrawler> crawlers = CrawlerService.getCrawlers();
		for(IContentCrawler crawler:crawlers) {
			Content += crawler.getContent();
		}
		
		return Content;
	}
	
	

}