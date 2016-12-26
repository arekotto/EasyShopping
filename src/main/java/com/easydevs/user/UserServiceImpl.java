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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by arekotto on 08/12/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private SecureRandom random = new SecureRandom();

    private final int TOKEN_LENGTH = 130;
    private final long TOKEN_VALIDATION_PERIOD = 1000 * 60 * 60 * 24;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public User getUserById(Integer userId) {
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
            return new TempUser(9);
        } else {
            return new StandardUser(9);
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

    @Override
    public boolean isTokenValid(Integer userId, String token) {
        User user = getUserById(userId);
        if (user != null) {
            Long tokenTimeStamp = user.getTokenValidationTimeStamp();
            if (tokenTimeStamp != null) {
                if(System.currentTimeMillis() - TOKEN_VALIDATION_PERIOD < tokenTimeStamp) {
                    return user.getToken().equals(token);
                }
            }
        }

        return false;
    }

    @Override
    public String generateToken() {
        return new BigInteger(TOKEN_LENGTH, random).toString(32);
    }

    @Override
    public boolean isPasswordFormatCorrect(String password) {
        return true;
    }

}
