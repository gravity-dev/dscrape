package com.dscrape.engine.web.crawler.base;

import java.util.Arrays;
import java.util.List;

public class CrawlerContext {

	private String sourceAddress = null;

	private String searchTags = null;

	private List<String> searchPaths = null;

	public String getSourceAddress() {
		return sourceAddress;
	}

	public String getSearchTags() {
		return searchTags;
	}

	public List<String> getSearchPaths() {
		return searchPaths;
	}

	public CrawlerContext(String tags, List<String> paths, String source) {
		this.searchTags = tags;
		this.searchPaths = paths;
		this.sourceAddress = source;
	}

	@Override
	public String toString() {
		return "CrawlerContext [sourceAddress=" + sourceAddress + ", searchTags=" + searchTags + ", searchPaths="
				+ Arrays.toString(searchPaths.toArray()) + "]";
	}

}
