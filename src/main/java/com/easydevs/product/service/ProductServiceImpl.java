package com.easydevs.product.service;

import com.easydevs.product.model.Product;
import com.easydevs.product.model.StandardProduct;
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
 * Created by Arek on 02.01.2017.
 */

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final String PRODUCT_ID_SEQUENCE_COLLECTION_NAME = "productIdSequence";

    @Override
    public Product getProductById(long productId) {

        Query query = new Query(Criteria.where("id").is(productId));
        List<StandardProduct> usersList = mongoTemplate.find(query, StandardProduct.class);
        if (usersList.size() == 1) {
            return usersList.get(0);
        } else {
            return null;
        }    }

    @Override
    public Product createNewProduct() {

//        if (userType == UserType.TEMP) {
//            return new TempUser(getNewIdAndInc());
//        } else {
            return new StandardProduct(getNewIdAndInc());
//        }

    }

    private Long getNewIdAndInc() {
        List<DbIdSequence> userIdSequenceList = mongoTemplate.find(new Query(), DbIdSequence.class, PRODUCT_ID_SEQUENCE_COLLECTION_NAME);

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
    public void updateProduct(Product product) {
        Query query = new Query(Criteria.where("id").is(product.getId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(product, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, StandardProduct.class);
    }
}
