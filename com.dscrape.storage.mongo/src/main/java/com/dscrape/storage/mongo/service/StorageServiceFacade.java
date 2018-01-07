package com.dscrape.storage.mongo.service;

import com.dscrape.engine.managers.ISearchContentStorageService;
import com.dscrape.engine.managers.IStorageService;

public class StorageServiceFacade implements IStorageService {

	private ISearchContentStorageService searchContentService = null;

	@Override
	public ISearchContentStorageService getContentStorageService() {
		return this.searchContentService;
	}

	@Override
	public IStorageService initialize() {
		searchContentService = new SearchContentServiceImpl();
		return this;
	}

}
