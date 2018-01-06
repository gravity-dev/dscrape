package com.dscrape.storage.mongo.service;

import java.util.Date;
import java.util.List;

import com.dscrape.engine.Engine;
import com.dscrape.engine.managers.ISearchContentStorageService;
import com.dscrape.storage.mongo.MongoDbStorageManagerImpl;
import com.dscrape.storage.mongo.model.ModelConstants;
import com.dscrape.storage.mongo.model.ModelUtils;
import com.dscrape.storage.mongo.model.SearchContentModel;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

public class SearchContentServiceImpl implements ISearchContentStorageService {

	private MongoDatabase getDb() throws Exception {
		MongoDbStorageManagerImpl storageManager = (MongoDbStorageManagerImpl) Engine.getInstance().getStorageManager();
		return storageManager.getMongoDb();
	}

	private MongoCollection<SearchContentModel> getCollection() throws Exception {
		MongoCollection<SearchContentModel> collection = getDb().getCollection(ModelConstants.MODEL_SEARCH_CONTENT,
				SearchContentModel.class);
		return collection;
	}

	/**
	 * 
	 * Insert search content data
	 * 
	 * @throws Exception
	 */
	@Override
	public void saveSearchContent(List<String> tags, String url, String content) throws Exception {
		SearchContentModel searchObject = new SearchContentModel();
		searchObject.setTags(ModelUtils.getTagsAsString(tags));
		searchObject.setUrl(url);
		searchObject.setContent(content);
		searchObject.setCreationDate(new Date());

		getCollection().insertOne(searchObject);
	}

	@Override
	public Object searchContent(List<String> tags) {

		return null;
	}

	@Override
	public Object searchContentByUri(String uri) throws Exception {
		FindIterable<SearchContentModel> iter = getCollection().find(eq("SearchContent.uri", uri));
		return iter;
	}

	@Override
	public void deleteSearchContentByUri() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSearchContentById() {
		// TODO Auto-generated method stub

	}

}
