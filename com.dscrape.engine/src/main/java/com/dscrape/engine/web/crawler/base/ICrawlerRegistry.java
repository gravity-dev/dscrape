package com.dscrape.engine.web.crawler.base;

public interface ICrawlerRegistry {

	public IContentCrawler getCrawler(String name);
	
	public void register(String name, IContentCrawler crawler);

	public void deRegister(String name);

	public void listCrawlers();

}
