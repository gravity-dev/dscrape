package com.dscrape.storage.mongo.service;

import com.dscrape.engine.managers.ISearchContentStorageService;
import com.dscrape.engine.managers.IStorageService;

public class StorageServiceFacade implements IStorageService {

	public StorageServiceFacade() {
		super();
	}

	private StorageServiceFacade storageServiceFacade = null;
	private ISearchContentStorageService searchContentService = null;

	@Override
	public ISearchContentStorageService getContentStorageService() {
		return searchContentService;
	}

	@Override
	public IStorageService createInstance() {
		if (storageServiceFacade == null) {
			storageServiceFacade = new StorageServiceFacade();
			searchContentService = new SearchContentServiceImpl();
		}
		return storageServiceFacade;
	}

}
