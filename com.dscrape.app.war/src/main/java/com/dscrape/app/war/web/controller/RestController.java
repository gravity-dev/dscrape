package com.dscrape.app.war.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dscrape.app.model.SearchContentModel;
import com.dscrape.app.utils.log.EngineLoggerFactory;
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
		/*try {
			CrawlerService.crawlAndSaveContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			EngineLoggerFactory.error(e.getLocalizedMessage());
		}*/
		
		
		return "Nothing to do in this request";
	}
	
	@RequestMapping("/generatedata")
	public String saveData() {
		try {
			CrawlerService.crawlAndSaveContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			EngineLoggerFactory.error(e.getLocalizedMessage());
		}
		
		return "Data Inserted Successfully";
		
	}
	
   @RequestMapping(value="/searchcontent", method=RequestMethod.GET)
   public String queryMethod(@RequestParam String input) {
	
	 StringBuilder result = new StringBuilder();
	 try {
		List<SearchContentModel> resultList= (List<SearchContentModel>) CrawlerService.searchContent(input);
		for(SearchContentModel searchContentModel:resultList) {
			result.append(searchContentModel.toString());
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		EngineLoggerFactory.error(e.getLocalizedMessage());
	}
	 
	 return result.toString();
   }
	
	

}