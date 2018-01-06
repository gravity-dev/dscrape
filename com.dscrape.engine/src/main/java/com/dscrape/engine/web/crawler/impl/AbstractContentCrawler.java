package com.dscrape.engine.web.crawler.impl;

import java.util.UUID;

import com.dscrape.engine.web.crawler.base.CrawlerContext;
import com.dscrape.engine.web.crawler.base.IContentCrawler;
import com.dscrape.engine.web.crawler.enums.CrawlerTypeEnum;

public abstract class AbstractContentCrawler implements IContentCrawler {

	protected final CrawlerContext ctx;

	protected final CrawlerTypeEnum type;

	protected String id = null;

	public AbstractContentCrawler(CrawlerContext ctx, CrawlerTypeEnum type) {
		this.ctx = ctx;
		this.type = type;
	}

	public void genCrawlerId() {
		this.id = UUID.randomUUID().toString();
	}

	public abstract void initialize(CrawlerContext ctx);

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void init() {
		this.genCrawlerId();
		initialize(this.ctx);
	}

	@Override
	public String getName() {
		return this.type.getName();
	}
	
}
