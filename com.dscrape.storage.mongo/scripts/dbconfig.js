use dscrape;
db.createUser({user: "root",pwd: "root",roles: [ { role: "userAdmin", db: "dscrape" } ]});