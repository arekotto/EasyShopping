package com.easydevs.user;

import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.TempUser;
import com.easydevs.user.model.User;
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
 * Created by arekotto on 08/12/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public User getUserById(long userId) {
        log.info("UserService.getUser", userId);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        List<StandardUser> usersList = mongoTemplate.find(query, StandardUser.class);
        if (usersList.size() == 1) {
            return usersList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByLogin (String login) {
        log.info("UserService.getUserByLogin", login);

        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        List<StandardUser> usersList = mongoTemplate.find(query, StandardUser.class);
        if (!usersList.isEmpty()) {
            return usersList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User createNewUser(UserType userType) {
        if (userType == UserType.TEMP) {
            return new TempUser(9L);
        } else {
            return new StandardUser(9L);
        }

    }



    @Override
    public void updateUser(User user) {
        log.info("UserService.updateUser", user);

//        Query query = new Query();
//        DBObject userDocument = new BasicDBObject();
//        Update update  = Update.fromDBObject(userDocument, "id");
//
//        query.addCriteria(Criteria.where("id").is(user.getId()));

        Query query = new Query(Criteria.where("id").is(user.getId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(user, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, StandardUser.class);
    }



}
