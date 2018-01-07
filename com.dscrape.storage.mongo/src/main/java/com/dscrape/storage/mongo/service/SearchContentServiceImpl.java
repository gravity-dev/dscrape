package com.dscrape.storage.mongo.service;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dscrape.app.model.ModelConstants;
import com.dscrape.app.model.ModelUtils;
import com.dscrape.app.model.SearchContentModel;
import com.dscrape.app.utils.IDGenUtil;
import com.dscrape.engine.Engine;
import com.dscrape.engine.managers.ISearchContentStorageService;
import com.dscrape.storage.mongo.MongoDbStorageManagerImpl;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

/**
 * Search content service implementation.
 * 
 * @author arvind
 *
 */
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
		searchObject.setUrlIndex(IDGenUtil.getStringUUID(url));
		searchObject.setContent(content);
		searchObject.setCreationDate(new Date());

		getCollection().insertOne(searchObject);
	}

	@Override
	public List<SearchContentModel> searchContentByTags(List<String> tags) throws Exception {
		String searchTags = ModelUtils.getTagsAsString(tags);
		return searchContentByTags(searchTags);
	}

	@Override
	public List<SearchContentModel> searchContentByTags(String tags) throws Exception {
		FindIterable<SearchContentModel> iter = getDb()
				.getCollection(ModelConstants.MODEL_SEARCH_CONTENT, SearchContentModel.class).find(Filters.text(tags))
				.projection(Projections.metaTextScore("score")).sort(Sorts.metaTextScore("score"));
		List<SearchContentModel> resultList = new ArrayList<SearchContentModel>();
		for (SearchContentModel search : iter) {
			resultList.add(search);
		}
		return resultList;
	}

	@Override
	public List<SearchContentModel> searchContentByUrl(String uri) throws Exception {
		FindIterable<SearchContentModel> iter = getCollection()
				.find(eq("SearchContent.urlIndex", IDGenUtil.getStringUUID(uri)));
		List<SearchContentModel> resultList = new ArrayList<SearchContentModel>();
		for (SearchContentModel search : iter) {
			resultList.add(search);
		}
		return resultList;
	}

	@Override
	public void deleteSearchContentByUrl(String url) throws Exception {
		getCollection().deleteMany(eq("SearchContent.urlIndex", IDGenUtil.getStringUUID(url)));
	}

	@Override
	public void deleteSearchContentById(String docId) throws Exception {
		getCollection().deleteOne(eq("SearchContent._id", docId));
	}

}
