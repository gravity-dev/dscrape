package com.dscrape.engine.web.crawler.base;

import java.util.List;

public interface ICrawlerRegistry {

	public IContentCrawler getCrawler(String name);
	
	public void register(String name, IContentCrawler crawler);

	public void deRegister(String name);

	public List<IContentCrawler> listCrawlers();

}
