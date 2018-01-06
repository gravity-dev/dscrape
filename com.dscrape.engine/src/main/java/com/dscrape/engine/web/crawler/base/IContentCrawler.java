package com.dscrape.engine.web.crawler.base;

public interface IContentCrawler {

	public String getName();
	
	public String getId();

	public void init();
	
	public String getContent();
}
