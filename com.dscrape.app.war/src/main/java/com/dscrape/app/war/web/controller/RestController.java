package com.dscrape.app.war.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;

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
		return "I am running :)";
	}

}