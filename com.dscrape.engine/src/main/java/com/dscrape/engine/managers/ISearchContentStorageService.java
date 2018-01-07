package com.dscrape.engine.managers;

import java.util.List;

/**
 * Search Content storage manager interface.
 * 
 * @author arvind
 *
 */
public interface ISearchContentStorageService {

	/**
	 * Save search content
	 * 
	 * @param tags
	 * @param url
	 * @param snapShot
	 * @throws Exception
	 */
	public void saveSearchContent(List<String> tags, String url, String snapShot) throws Exception;

	/**
	 * Search content by tags list
	 * 
	 * @param tags
	 * @return
	 * @throws Exception
	 */
	public List<?> searchContentByTags(List<String> tags) throws Exception;

	/**
	 * Search content by tags string
	 * 
	 * @param tags
	 * @return
	 * @throws Exception
	 */
	public List<?> searchContentByTags(String tags) throws Exception;

	
	/**
	 * Search content by url string
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public List<?> searchContentByUrl(String uri) throws Exception;

	/**
	 * Delete content by url
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void deleteSearchContentByUrl(String url) throws Exception;

	/**
	 * Delete by document id : _id
	 * 
	 * @param docId
	 * @throws Exception
	 */
	public void deleteSearchContentById(String docId) throws Exception;
}
