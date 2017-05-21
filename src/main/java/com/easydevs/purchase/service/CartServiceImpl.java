package com.easydevs.purchase.service;

import com.easydevs.product.model.StandardProduct;
import com.easydevs.purchase.model.Cart;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arekotto on 18/01/2017.
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public Cart createNewCart(long userId, boolean isTemp) {
        return new Cart(userId, new ArrayList<>(), isTemp);
    }

    @Override
    public Cart getCartForUser(long userId, boolean isTemp) {

        Query query = new Query(Criteria.where("userId").is(userId).andOperator(
                Criteria.where("isTemp").is(true)
        ));
        List<Cart> cartList = mongoTemplate.find(query, Cart.class);
        if (cartList.size() == 1) {
            return cartList.get(0);
        }
        return null;
    }

    @Override
    public void updateCartForUser(long userId, Cart cart, boolean isTemp) {
        Query query = new Query(Criteria.where("userId").is(userId).andOperator(
                Criteria.where("isTemp").is(true)
        ));
        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(cart, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, Cart.class);
    }
}
