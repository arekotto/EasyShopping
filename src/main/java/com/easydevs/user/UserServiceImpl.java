package com.easydevs.user;

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

    @Override
    public User getUser(Integer userId) {
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
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        List<StandardUser> usersList = mongoTemplate.find(query, StandardUser.class);
        if (usersList.size() == 1) {
            return usersList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void createUser(User newUser) {
        User user = this.getUserByLogin(newUser.getLogin());

        if (user != null) {
            throw new InternalError("User currently existing in database");
        }

        mongoTemplate.insert(user);
    }

    @Override
    public void changeUserName(Integer userId, String newName) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("id").is(userId));

        mongoTemplate.updateFirst(query, update.set("name", newName), StandardUser.class);

    }

    @Override
    public void changeUserLogin(Integer userId, String newLogin) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("id").is(userId));

        User user = this.getUserByLogin(newLogin);

        if (user != null) {
            throw new InternalError("User currently existing in database");
        }

        mongoTemplate.updateFirst(query, update.set("login", newLogin), StandardUser.class);
    }

//    @Override
//    public void changeUserPassword(Integer userId, String newPassword) {
//
//    }
}
