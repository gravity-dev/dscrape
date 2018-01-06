package com.dscrape.engine.managers;

import java.util.List;

/**
 * Search Content storage manager interface.
 * 
 * @author arvind
 *
 */
public interface ISearchContentStorageService {

	public void saveSearchContent(List<String> tags, String url, String snapShot) throws Exception;

	public Object searchContent(List<String> tags)  throws Exception;

	public Object searchContentByUri(String uri) throws Exception;

	public void deleteSearchContentByUri() throws Exception;

	public void deleteSearchContentById() throws Exception;
}
