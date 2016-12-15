package com.easydevs.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public void createUser(User user) {
        mongoTemplate.insert(user);
    }

}
