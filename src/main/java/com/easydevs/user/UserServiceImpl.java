package com.easydevs.user;

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
    public User getUser(Integer userId) {
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
    public void createUser(User newUser) {
        log.info("UserService.createUser", newUser);

        User exisitingUser = this.getUserByLogin(newUser.getLogin());

        if (exisitingUser != null) {
            throw new InternalError("User currently existing in database");
        } else {
            mongoTemplate.insert(newUser);
        }

    }

    @Override
    public void changeUserName(Integer userId, String newName) {
        log.info("UserService.changeUserName", userId, newName);

        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("id").is(userId));

        mongoTemplate.updateFirst(query, update.set("name", newName), StandardUser.class);

    }

    @Override
    public void changeUserLogin(Integer userId, String newLogin) {
        log.info("UserService.changeUserLogin", userId, newLogin);

        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("id").is(userId));

        User user = this.getUserByLogin(newLogin);

        if (user != null) {
            throw new InternalError("User currently existing in database");
        }

        mongoTemplate.updateFirst(query, update.set("login", newLogin), StandardUser.class);
    }

    @Override
    public void updateTokenTimeStamp(Integer userId, Long timeStamp) {
        log.info("UserService.updateTokenTimeStamp", userId);

        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("id").is(userId));

        mongoTemplate.updateFirst(query, update.set("tokenValidationTimeStamp", timeStamp), StandardUser.class);
    }

    @Override
    public void updateUser(User user) {
        log.info("UserService.updateUser", user);

        Query query = new Query();
        DBObject userDocument = new BasicDBObject();
        Update update  = Update.fromDBObject(userDocument, "id");

        query.addCriteria(Criteria.where("id").is(user.getId()));

        mongoTemplate.upsert(query, update, StandardUser.class);
    }

}
