package com.sic777.db.mongo;


import com.sic777.db.mongo.config.MongoConfig;
import com.sic777.utils.container.ContainerGetter;
import com.sic777.utils.container.tuple.TwoTuple;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zhengzhenxie on 2017/9/12.
 */
public abstract class Mongo {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Mongo.class);
    private MongoDatabase mongoDatabase;
    private static MongoClient mongoClient;

    protected Mongo(String dbName) {
        mongoDatabase = mongoClient.getDatabase(dbName);
        logger.info("init mongo database : " + dbName);
    }

    /**
     * 初始化mongo db
     *
     * @throws Exception
     */
    public static void init() {
        logger.info("init mongo ...");
        MongoConfig.init();//初始化配置文件
        MongoClientOptions.Builder potionBuilder = MongoClientOptions.builder();
        potionBuilder.connectionsPerHost(MongoConfig.getConnectionsPerHost());
        potionBuilder.threadsAllowedToBlockForConnectionMultiplier(MongoConfig.getThreadsAllowedToBlockForConnectionMultiplier());
        potionBuilder.connectTimeout(MongoConfig.getConnectTimeout());
        potionBuilder.maxWaitTime(MongoConfig.getMaxWaitTime());
        potionBuilder.socketKeepAlive(MongoConfig.isSocketKeepAlive());
        potionBuilder.socketTimeout(MongoConfig.getSocketTimeout());

        List<ServerAddress> seeds = ContainerGetter.arrayList();
        List<MongoCredential> credentials = Arrays.asList(MongoCredential.createScramSha1Credential(MongoConfig.getUsername(), "admin", MongoConfig.getPassword().toCharArray()));
        List<TwoTuple<String, Integer>> hosts = MongoConfig.hosts;
        for (TwoTuple<String, Integer> TwoTuple : hosts) {
            seeds.add(new ServerAddress(TwoTuple.first, TwoTuple.second));
        }
        try {
            mongoClient = new MongoClient(seeds, credentials, potionBuilder.build());
        } catch (Exception e) {
            logger.error("", e);
            System.exit(-1);
        }
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    /*                             新增操作                             */
    /*                             新增操作                             */
    /*                             新增操作                             */

    /**
     * 新增数据
     *
     * @param collectionName
     * @param doc
     */
    public void insert(String collectionName, Document doc) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(doc);
    }

    /**
     * 新增数据,只有object id
     *
     * @param collectionName
     * @param _id
     */
    public void insert(String collectionName, Object _id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        Document doc = new Document();
        doc.append("_id", _id);

        collection.insertOne(doc);
    }

    /**
     * 批量插入
     *
     * @param collectionName
     * @param docs
     */
    public void insertMany(String collectionName, List<Document> docs) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertMany(docs);
    }


    /**
     * 递增
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void incByNum(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$inc", update), new UpdateOptions().upsert(true));
    }

    /**
     * 找到就递增，没找到就放弃
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void findAndInc(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndUpdate(filter, new Document("$inc", update));
    }


    /*                             删除操作                             */
    /*                             删除操作                             */
    /*                             删除操作                             */

    /**
     * 根据id进行删除
     *
     * @param collectionName
     * @param id
     */
    public void deleteByID(String collectionName, Object id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndDelete(Filters.eq("_id", id));
    }

    /**
     * 根据id列表进行删除
     *
     * @param collectionName
     * @param ids
     */
    public void deleteByIDs(String collectionName, List<Object> ids) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.deleteMany(Filters.in("_id", ids));
    }

    /*                             修改操作                             */
    /*                             修改操作                             */
    /*                             修改操作                             */

    /**
     * 更新数据
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void update(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$set", update), new UpdateOptions().upsert(true));
    }

    /**
     * 找到并且更新
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void findOneAndUpdate(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndUpdate(filter, new Document("$set", update));
    }

    /**
     * 向数组push一个文档
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void push(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$push", update));
    }

    /**
     * 向数组pull一个文档
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void pull(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$pull", update));
    }

    /**
     * 更新多个
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void updateMulti(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateMany(filter, new Document("$set", update), new UpdateOptions().upsert(true));
    }

    /*                             查找操作                             */
    /*                             查找操作                             */
    /*                             查找操作                             */

    /**
     * 根据id查找
     *
     * @param collectionName
     * @param id
     * @return
     */
    public Document findById(String collectionName, Object id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        Document document = collection.find(Filters.eq("_id", id)).first();
        if (null == document) {
            document = new Document();
        }
        return document;
    }

    /**
     * 根据条件查询一个
     *
     * @param collectionName
     * @param filter
     * @return
     */
    public Document queryOne(String collectionName, Bson filter) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        Document document = collection.find(filter).first();
        if (null == document) {
            document = new Document();
        }
        return document;
    }

    /**
     * 根据条件查询
     *
     * @param collectionName
     * @param filter
     * @return
     */
    public MongoCursor<Document> query(String collectionName, Bson filter) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection.find(filter).iterator();
    }


    /*                             系统操作                             */
    /*                             系统操作                             */
    /*                             系统操作                             */

    /**
     * 查看是否存在集合名称
     *
     * @param collectionName
     * @return
     */
    public boolean exist(String collectionName) {
        boolean exist = false;
        MongoCursor<String> cursor = mongoDatabase.listCollectionNames().iterator();
        while (cursor.hasNext()) {
            if (collectionName.equals(cursor.next())) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    /**
     * 创建索引
     *
     * @param collectionName
     * @param index
     */
    public void createIndex(String collectionName, Bson index) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.createIndex(index);
    }

    /**
     * 查询某个索引是否存在
     *
     * @param collectionName
     * @param indexName
     * @return
     */
    public boolean exsitIndex(String collectionName, String indexName) {
        boolean exist = false;
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.listIndexes().iterator();
        while (cursor.hasNext()) {
            Document index = cursor.next();
            String name = index.getString("name");
            if (name.equals(indexName)) {
                exist = true;
                break;
            }
        }

        return exist;
    }

    /*                             其他操作                             */
    /*                             其他操作                             */
    /*                             其他操作                             */

    /**
     * 更新或插入 (找到就更新，没找到就插入新数据)
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void upsert(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndUpdate(filter, new Document("$set", update), new FindOneAndUpdateOptions().upsert(true));
    }
}
