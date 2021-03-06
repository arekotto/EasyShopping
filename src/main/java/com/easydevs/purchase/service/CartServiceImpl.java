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

    /**
     *
     * @param userId the user id
     * @param isTemp the is temp
     * @return
     */
    @Override
    public Cart createNewCart(long userId, boolean isTemp) {
        return new Cart(userId, new ArrayList<>(), isTemp);
    }

    /**
     *
     * @param userId the user id
     * @param isTemp the is temp
     * @return
     */
    @Override
    public Cart getCartForUser(long userId, boolean isTemp) {

        Query query = new Query(Criteria.where("userId").is(userId).andOperator(
                Criteria.where("isTemp").is(isTemp)
        ));
        List<Cart> cartList = mongoTemplate.find(query, Cart.class);
        if (cartList.size() == 1) {
            return cartList.get(0);
        }
        return null;
    }

    /**
     *
     * @param userId the user id
     * @param cart   the cart
     */
    @Override
    public void updateCartForUser(long userId, Cart cart) {
        Query query = new Query(Criteria.where("userId").is(userId));
        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(cart, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, Cart.class);
    }
}
