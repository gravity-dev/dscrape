package com.dscrape.engine.web.crawler.impl;

public enum CrawlerTypeEnum {

	SIMPLE_WEB("simpleweb"), QUORA("quora");

	private final String name;

	CrawlerTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
}
