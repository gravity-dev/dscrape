package com.dscrape.storage.mongo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Database Yaml configuration reader. Do not initialize this class more than
 * once.
 * 
 * @author arvind
 *
 */
public final class MongoDbStorageConfig {

	private static final String CONFIG_PROPERTY = "com.dscrape.mongo.config";
	private DatabaseConfig dbConfig;
	private List<DbCollection> dbCollection;
	private Map<String, Object> config;

	/**
	 * C'tor accepts configuration file location. The system property
	 * com.dscrape.mongo.config can override this file location.
	 * 
	 * @param cfgFile
	 */
	public MongoDbStorageConfig(String cfgFile) {
		String path = System.getProperty(CONFIG_PROPERTY);
		if (path == null || path.isEmpty()) {
			path = System.getProperty("catalina.home") + File.separatorChar + "conf" + File.separatorChar + cfgFile;
		}

		Yaml yaml = new Yaml();
		File file = new File(path);

		if (!file.isFile()) {
			throw new RuntimeException("No Configration file found for the dscrape mondo engine");
		}

		try (InputStream stream = new FileInputStream(file)) {
			config = yaml.load(stream);
			init();
		} catch (Exception ex) {
			throw new RuntimeException("Mongo db Configuration loading ERROR: " + ex.getMessage(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	private void init() {
		dbConfig = new DatabaseConfig((Map<String, Object>) config.get("database"));
		List<Map<String, Object>> dbCollList = (List<Map<String, Object>>) config.get("collections");
		dbCollection = new ArrayList<>(dbCollList.size());

		for (Map<String, Object> collConfig : dbCollList) {
			dbCollection.add(new DbCollection(collConfig));
		}
	}

	public String getConfigProperty(String key) {
		return (String) config.get(key);
	}

	/**
	 * Get database configuration.
	 * 
	 * @return
	 */
	public DatabaseConfig getDatabaseConfig() {
		return dbConfig;
	}

	/**
	 * Get list of collections
	 * 
	 * @return
	 */
	public List<DbCollection> getCollections() {
		return dbCollection;
	}
}

/**
 * Database configuration bean class.
 */
class DatabaseConfig {

	private String dbname;
	private int port;
	private String host;
	private String username;
	private String password;

	public String getDbName() {
		return dbname;
	}

	public int getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public DatabaseConfig(Map<String, Object> dbConfig) {
		this.dbname = (String) dbConfig.get("dbname");
		this.port = (Integer) dbConfig.get("port");
		this.host = (String) dbConfig.get("host");
		this.username = (String) dbConfig.get("username");
		this.password = (String) dbConfig.get("password");

	}
}

/**
 * Database collection configuration
 * 
 * @author arvind
 *
 */
class DbCollection {

	private String name;

	public String getName() {
		return name;
	}

	public DbCollection(Map<String, Object> config) {
		this.name = (String) config.get("name");
	}
}