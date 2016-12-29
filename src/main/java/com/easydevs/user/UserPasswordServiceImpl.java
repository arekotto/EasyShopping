package com.easydevs.user;

import com.easydevs.user.model.UserPasswordTO;
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
 * Created by arekotto on 27/12/2016.
 */
@Service
public class UserPasswordServiceImpl implements UserPasswordService {

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getPasswordHash(long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<UserPasswordTO> usersList = mongoTemplate.find(query, UserPasswordTO.class) ;
        if (!usersList.isEmpty()) {
            return usersList.get(0).getPasswordHash();
        } else {
            return null;
        }
    }

    @Override
    public void insertOrUpdatePassword(long userId, String passwordHash) {
        Query query = new Query(Criteria.where("userId").is(userId));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(new UserPasswordTO(userId, passwordHash), dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, UserPasswordTO.class);
    }

    @Override
    public void deletePassword(long userId) {
        // TODO
    }
}
