package com.easydevs.product.service;

import com.easydevs.product.model.ProductImage;
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
 * Created by arekotto on 24/01/2017.
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void updateProductImage(ProductImage productImage) {
        log.info("ProductService - updateProduct", productImage);

        Query query = new Query(Criteria.where("productId").is(productImage.getProductId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(productImage, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, ProductImage.class);
    }

    @Override
    public ProductImage getProductImage(long productId) {

        Query query = new Query(Criteria.where("productId").is(productId));
        List<ProductImage> usersList = mongoTemplate.find(query, ProductImage.class);
        if (usersList.size() == 1) {
            return usersList.get(0);
        } else {
            return null;
        }
    }

}
