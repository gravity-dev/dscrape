# Dscrape
Data Scraping Engine

# Setup
1. Download and install mongo database from https://www.mongodb.com/download-center
1. Configure database for allowing roles using: dscrape/com.dscrape.storage.mongo/scripts/dbconfig.json
1. Build the project root using: mvn clean install
1. Consume dscrape-app.war springboot based webapp tomcat
1. Place com.dscrape.storage.mongo/src/main/resources/dbconfig.yml file in tomcat/conf folder, edit it as per config used in step 2 for db config.
1. Edit : com.dscrape.engine/src/main/resources/application.yml and crawler-config.yml for engine and crawler configuration.

# Components:
* Engine : com.dscrape.engine - pluggable engine with storage, model and crawler services interfaces
* Storage: com.dscrape.storage.mongo implements - mongodb based storgae interface
* Rest app: com.dscrape.app.war - Springboot based rest endpoints implementations.

# Upcoming
* Reactive streams based evented source listeners.
* Auto crawlers with self learning models.
