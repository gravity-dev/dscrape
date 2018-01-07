package com.dscrape.storage.mongo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.dscrape.engine.managers.IStorageManager;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Indexes;

/**
 * Mongo database storage implementation
 * 
 * @author arvind
 *
 */
public class MongoDbStorageManagerImpl implements IStorageManager {

	private final static String STORAGE_TYPE = "MONGO_NOSQL";
	private final static String STORAGE_ID = "mongodb-3.6";

	private MongoDbStorageConfig configuration;
	private MongoClient mongoClient;

	/**
	 * Get Configured Mongo database
	 * 
	 * @return
	 */
	public MongoDatabase getMongoDb() {
		return this.mongoClient.getDatabase(configuration.getDatabaseConfig().getDbName());
	}

	/**
	 * Get Mongo database Collection
	 * 
	 * @return
	 */
	public MongoCollection<?> getCollection(String collectionName) {
		MongoDatabase db = this.mongoClient.getDatabase(configuration.getDatabaseConfig().getDbName());
		return db.getCollection(collectionName);
	}

	@Override
	public String getStorageType() {
		return STORAGE_TYPE;
	}

	@Override
	public String getStorageId() {
		return STORAGE_ID;
	}

	@Override
	public void initialize(String configFile) throws Exception {
		configuration = new MongoDbStorageConfig(configFile);
		createMongoClient(configuration.getDatabaseConfig());
		createCollections(configuration.getCollections());
	}

	private CodecRegistry getPojoCodecRegistry() {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		return pojoCodecRegistry;
	}

	private void createMongoClient(DatabaseConfig dbConfig) {
		ServerAddress serverAddress = new ServerAddress(dbConfig.getHost(), dbConfig.getPort());

		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		MongoCredential rootUserCred = MongoCredential.createCredential(dbConfig.getUsername(), dbConfig.getDbName(),
				dbConfig.getPassword().toCharArray());
		credentials.add(rootUserCred);

		mongoClient = new MongoClient(serverAddress, credentials,
				MongoClientOptions.builder().codecRegistry(getPojoCodecRegistry()).build());
	}

	/**
	 * Create collection if they do not exists.
	 * 
	 * @param collections
	 */
	private void createCollections(List<DbCollection> collections) {
		for (DbCollection coll : collections) {
			MongoIterable<String> collection = this.getMongoDb().listCollectionNames();
			boolean found = false;
			for (String collectionName : collection) {
				found = collectionName.equals(coll.getName());
				if (found)
					break;
			}
			if (!found) {
				//create collection
				this.getMongoDb().createCollection(coll.getName());
				
				//if index defined create indices
				if (coll.getIndices() != null && coll.getIndices().isEmpty()) {
					MongoCollection<Document> doc = this.getMongoDb().getCollection(coll.getName());
					for (String index : coll.getIndices()) {
						doc.createIndex(Indexes.text(index));
					}
				}
			}
		}
	}
}