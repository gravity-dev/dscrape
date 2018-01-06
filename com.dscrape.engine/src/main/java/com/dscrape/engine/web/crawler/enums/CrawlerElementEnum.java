package com.dscrape.engine.web.crawler.enums;

public enum CrawlerElementEnum {

	META("Meta"),ELEMENT("Element"), SUBELEMENTCLASS("SubElementClass"),SUBELEMENTCLASSTAG("SubElementClassTag");

	private final String name;

	CrawlerElementEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
}
