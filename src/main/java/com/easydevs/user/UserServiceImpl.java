package com.easydevs.user;

import com.easydevs.product.model.Product;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.TempUser;
import com.easydevs.user.model.User;
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
 * Created by arekotto on 08/12/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final String USER_ID_SEQUENCE_COLLECTION_NAME = "userIdSequence";

    /**
     *
     * @param userId the user id
     * @return
     */
    @Override
    public User getUserById(long userId) {
        log.info("UserService.getUser", userId);

        Query query = new Query(Criteria.where("id").is(userId));
        List<StandardUser> usersList = mongoTemplate.find(query, StandardUser.class);
        if (usersList.size() == 1) {
            return usersList.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<StandardUser> getAllUsers(){
        log.info("UserService.getAllUsers");
        Query query = new Query();
        List<StandardUser> usersList = mongoTemplate.find(query, StandardUser.class);

        return usersList;
    }

    /**
     *
     * @param userId the user id
     */
    @Override
    public void removeUser(long userId) {
        log.info("ProductService - removeProduct", userId);

        Query query = new Query(Criteria.where("id").is(userId));
        mongoTemplate.findAndRemove(query, StandardUser.class);
    }

    @Override
    public void setUserAdmin(long userId, boolean isAdmin) {
        log.info("User service - setUserAdmin", userId, isAdmin);

        Query query = new Query(Criteria.where("id").is(userId));
        StandardUser existingUser = mongoTemplate.findOne(query, StandardUser.class);
        existingUser.setIsAdmin(isAdmin);
        this.updateUser(existingUser);
    }

    /**
     *
     * @param email the email
     * @return
     */
    @Override
    public User getUserByEmail (String email) {
        log.info("UserService.getUserByLogin", email);

        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<StandardUser> usersList = mongoTemplate.find(query, StandardUser.class);
        if (!usersList.isEmpty()) {
            return usersList.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @param userType the user type
     * @return
     */
    @Override
    public User createNewUser(UserType userType) {



        if (userType == UserType.TEMP) {
            return new TempUser(getNewIdAndInc());
        } else {
            return new StandardUser(getNewIdAndInc());
        }

    }

    /**
     *
     * @return
     */
    @Override
    public long getNewIdForTempUser() {
        return getNewIdAndInc();
    }

    private Long getNewIdAndInc() {
        List<DbIdSequence> userIdSequenceList = mongoTemplate.find(new Query(), DbIdSequence.class, USER_ID_SEQUENCE_COLLECTION_NAME);

        DbIdSequence userIdSequence;
        if (!userIdSequenceList.isEmpty()) {
            userIdSequence = userIdSequenceList.get(0);
        } else {
            userIdSequence = new DbIdSequence();
        }

        Long currentId = userIdSequence.getCurrent();
        userIdSequence.setCurrent(++currentId);

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(userIdSequence, dbDoc);
        Update update = Update.fromDBObject(dbDoc);
        mongoTemplate.upsert(new Query(), update, DbIdSequence.class, USER_ID_SEQUENCE_COLLECTION_NAME);

        return currentId;
    }

    /**
     *
     * @param user the user
     */
    @Override
    public void updateUser(User user) {
        log.info("UserService.updateUser", user);

        Query query = new Query(Criteria.where("id").is(user.getId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(user, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, StandardUser.class);
    }



}
