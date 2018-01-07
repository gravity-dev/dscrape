package com.dscrape.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.dscrape.app.utils.file.YamlReader;
import com.dscrape.app.utils.log.EngineLoggerFactory;
import com.dscrape.engine.managers.IStorageManager;
import com.dscrape.engine.managers.IStorageService;
import com.dscrape.engine.web.crawler.base.CrawlerContext;
import com.dscrape.engine.web.crawler.base.IContentCrawler;
import com.dscrape.engine.web.crawler.config.CrawlerDetails;
import com.dscrape.engine.web.crawler.config.CrawlerYamlConfig;
import com.dscrape.engine.web.crawler.enums.CrawlerTypeEnum;
import com.dscrape.engine.web.crawler.factory.CrawlerFactory;
import com.dscrape.engine.web.crawler.services.CrawlerRegistryService;

/**
 * Main application engine class
 * 
 * @author arvind
 *
 */
public class Engine {

	/**
	 * Private constructor single instance
	 */
	private Engine() {
	}

	private static final String APPLICATION_CONFIG_FILE = "application.yml";
	private static final String APP_CRAWLER_CONFIG_FILE = "crawler-config.yml";

	private static Engine engine = null;
	private IStorageManager storageManager;
	private IStorageService storageService;

	/**
	 * Get engine instance
	 * 
	 * @return
	 */
	public static Engine getInstance() throws Exception {
		if (engine == null) {
			try {
				engine = new Engine();
				engine.init();
				EngineLoggerFactory.info("********************* ENGINE STARTED *********************");
			} catch (Exception ex) {
				EngineLoggerFactory.severe("ENGINE FAILED TO START");
				throw new Exception("--------------------- ENGINE FAILED TO START ---------------------", ex);
			}
		}
		return engine;
	}

	/**
	 * Start engine
	 * 
	 * @throws Exception
	 */
	private void init() throws Exception {
		EngineLoggerFactory.info("Starting engine configuration");
		initConfugration();

		EngineLoggerFactory.info("Starting engine storage");
		startUpStorage();
	}

	private void startUpStorage() throws Exception {
		ApplicationConfig appConfig = new ApplicationConfig(
				YamlReader.parseYamlResource(Engine.class, APPLICATION_CONFIG_FILE));
		storageService = instantiate(appConfig.getStorageConfig().getProviderClass(), IStorageService.class)
				.initialize();
		EngineLoggerFactory.info("Loaded Storage Service : " + appConfig.getStorageConfig().getProviderClass());

		storageManager = instantiate(appConfig.getStorageConfig().getStorageManagerClass(), IStorageManager.class);
		storageManager.initialize(appConfig.getStorageConfig().getDbConfigFilePath());
		EngineLoggerFactory.info("Loaded Storage Manager : " + appConfig.getStorageConfig().getProviderClass());
	}

	/**
	 * Get engine storage manager
	 * 
	 * @return
	 */
	public IStorageManager getStorageManager() {
		return storageManager;
	}

	/**
	 * Get Storage service manager
	 * 
	 * @return
	 */
	public IStorageService getStorageService() {
		return storageService;
	}

	/**
	 * Initialize configuration
	 */
	private void initConfugration() {
		CrawlerYamlConfig config = YamlReader.parseYamlResourceAsClass(CrawlerYamlConfig.class,
				APP_CRAWLER_CONFIG_FILE);
		Set<Entry<String, CrawlerDetails>> entrySet = config.getCrawlers().entrySet();
		for (Entry<String, CrawlerDetails> entry : entrySet) {
			String name = entry.getValue().getName();

			String tags = entry.getValue().getContext().get("searchTags");

			String source = entry.getValue().getContext().get("sourceAddress");

			List<String> paths = new ArrayList<String>();
			String[] array = entry.getValue().getContext().get("searchPaths").split(",");
			for (String str : array)
				paths.add(str);

			CrawlerContext context = new CrawlerContext(tags, paths, source);
			IContentCrawler crawlerInstance = null;
			if (CrawlerTypeEnum.SIMPLE_WEB.getName().equalsIgnoreCase(entry.getValue().getType())) {
				crawlerInstance = CrawlerFactory.getCrawlerInstance(CrawlerTypeEnum.SIMPLE_WEB, context);
			} else if (CrawlerTypeEnum.QUORA.getName().equalsIgnoreCase(entry.getValue().getType())) {
				crawlerInstance = CrawlerFactory.getCrawlerInstance(CrawlerTypeEnum.SIMPLE_WEB, context);
			}

			CrawlerRegistryService.getInstance().register(name, crawlerInstance);
		}
	}

	/**
	 * Initialize classes dynamically
	 * 
	 * @param className
	 * @param type
	 * @return
	 */
	public <T> T instantiate(final String className, final Class<T> type) {
		try {
			return type.cast(Class.forName(className).newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

}
