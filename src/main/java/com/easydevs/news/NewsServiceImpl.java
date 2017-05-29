package com.easydevs.news;

import com.easydevs.support.DbIdSequence;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by arekotto on 02/02/2017.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private final String NEWS_ID_SEQUENCE_COLLECTION_NAME = "newsIdSequence";

    /**
     *
     * @param news the news
     */
    @Override
    public void insertNews(News news) {
        news.setId(getNewIdAndInc());
        Query query = new Query(Criteria.where("id").is(news.getId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(news, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, News.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<News> findAll() {
        return mongoTemplate.findAll(News.class);
    }

    private Long getNewIdAndInc() {

        List<DbIdSequence> productIdSequenceList = mongoTemplate.find(new Query(), DbIdSequence.class, NEWS_ID_SEQUENCE_COLLECTION_NAME);

        DbIdSequence userIdSequence;
        if (!productIdSequenceList.isEmpty()) {
            userIdSequence = productIdSequenceList.get(0);
        } else {
            userIdSequence = new DbIdSequence();
        }

        Long currentId = userIdSequence.getCurrent();
        userIdSequence.setCurrent(++currentId);

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(userIdSequence, dbDoc);
        Update update = Update.fromDBObject(dbDoc);
        mongoTemplate.upsert(new Query(), update, DbIdSequence.class, NEWS_ID_SEQUENCE_COLLECTION_NAME);

        return currentId;
    }
}
