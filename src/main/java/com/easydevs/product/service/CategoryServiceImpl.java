package com.easydevs.product.service;

import com.easydevs.product.model.Category;
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
import java.util.regex.Pattern;

/**
 * Created by ibm on 2017-01-18.
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final String CATEGORY_ID_SEQUENCE_COLLECTION_NAME = "categoryIdSequence";

    @Override
    public Category getCategoryById(long categoryId) {
        log.info("CategoryService - getCategoryById", categoryId);

        Query query = new Query(Criteria.where("id").is(categoryId));
        List<Category> usersList = mongoTemplate.find(query, Category.class);
        if (usersList.size() == 1) {
            return usersList.get(0);
        } else {
            return null;
        }    }

    @Override
    public Category createNewCategory() {

//        if (userType == UserType.TEMP) {
//            return new TempUser(getNewIdAndInc());
//        } else {
        return new Category(getNewIdAndInc());
//        }

    }

    private Long getNewIdAndInc() {
        log.info("CategoryService - getNewIdAndInc");

        List<DbIdSequence> userIdSequenceList = mongoTemplate.find(new Query(), DbIdSequence.class, CATEGORY_ID_SEQUENCE_COLLECTION_NAME);

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
        mongoTemplate.upsert(new Query(), update, DbIdSequence.class);

        return currentId;
    }
    @Override
    public void updateCategory(Category category) {
        log.info("CategoryService - updateCategory", category.toString());

        Query query = new Query(Criteria.where("id").is(category.getId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(category, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, Category.class);
    }

    @Override
    public void removeCategory(Category category) {
        log.info("ProductService - removeProduct", category.getId());

        Query query = new Query(Criteria.where("id").is(category.getId()));
        mongoTemplate.findAndRemove(query, Category.class);
    }

    @Override
    public List<Category> getAll() {
        return mongoTemplate.findAll(Category.class);
    }

    @Override
    public List<Category> search(String searchQuery) {
        log.info("ProductService - searching products by query", searchQuery);

//        BasicDBObject regexQuery = new BasicDBObject();
//        regexQuery.put("name", new BasicDBObject("$regex", "^(?)" + Pattern.quote(searchQuery))
//                .append("$options", "i"));

        Query query = new Query((Criteria.where("name").regex("^(?)" + Pattern.quote(searchQuery), "i")));
        //DBCursor cursor = StandardProduct.class;//.find(regexQuery);
        return mongoTemplate.find(query, Category.class);
    }

}
