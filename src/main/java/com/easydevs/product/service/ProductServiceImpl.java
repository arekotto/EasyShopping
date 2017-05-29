package com.easydevs.product.service;

import com.easydevs.product.model.Category;
import com.easydevs.product.model.Product;
import com.easydevs.product.model.Review;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.support.DbIdSequence;
import com.easydevs.user.EmailService;
import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Arek on 02.01.2017.
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final String PRODUCT_ID_SEQUENCE_COLLECTION_NAME = "productIdSequence";

    /**
     *
     * @param productId the product id
     * @return
     */
    @Override
    public Product getProductById(long productId) {
        log.info("ProductService - getProductById", productId);

        Query query = new Query(Criteria.where("id").is(productId));
        List<StandardProduct> usersList = mongoTemplate.find(query, StandardProduct.class);
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
    public Product createNewProduct() {

//        if (userType == UserType.TEMP) {
//            return new TempUser(getNewIdAndInc());
//        } else {
            return new StandardProduct(getNewIdAndInc());
//        }

    }

    /**
     *
     * @return
     */
    private Long getNewIdAndInc() {
        log.info("ProductService - getNewIdAndInc");

        List<DbIdSequence> productIdSequenceList = mongoTemplate.find(new Query(), DbIdSequence.class, PRODUCT_ID_SEQUENCE_COLLECTION_NAME);

        DbIdSequence userIdSequence;
        if (!productIdSequenceList.isEmpty()) {
            userIdSequence = productIdSequenceList.get(0);
        } else {
            userIdSequence = new DbIdSequence();
        }

        Long currentId = userIdSequence.getCurrent();
        userIdSequence.setCurrent(++currentId);

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(userIdSequence, dbDoc);
        Update update = Update.fromDBObject(dbDoc);
        mongoTemplate.upsert(new Query(), update, DbIdSequence.class, PRODUCT_ID_SEQUENCE_COLLECTION_NAME);

        return currentId;
    }

    /**
     *
     * @param product the product
     */
    @Override
    public void updateProduct(Product product) {
        log.info("ProductService - updateProduct", product.toString());

        Query query = new Query(Criteria.where("id").is(product.getId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(product, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, StandardProduct.class);
    }

    /**
     *
     * @param productId the product id
     * @param review    the review
     */
    @Override
    public void rateProduct(long productId, Review review) {
        log.info("ProductService - rateProduct", productId, review.toString());

        StandardUser user = (StandardUser) this.userService.getUserById(review.getUserId());
        StandardProduct updatedProduct = (StandardProduct) this.getProductById(productId);

        review.setUserName(user.getName());
        List<Review> reviews;

        if (updatedProduct.isHasReviews()) {
            reviews = updatedProduct.getReviews();
        } else {
            reviews = new ArrayList<Review>();
        }

        reviews.add(review);
        updatedProduct.setReviews(reviews);

        StandardUser sendMailToUser = (StandardUser) userService.getUserById(updatedProduct.getAddedByUserId());
        String addressSendMailTo = sendMailToUser.getEmail();
        String subject = "Your product- " + updatedProduct.getName() + "was rated";
        String message = review.toString();

        emailService.sendEmail(addressSendMailTo, subject, message);

        this.updateProduct(updatedProduct);
    }

    /**
     *
     * @param product the product
     */
    @Override
    public void removeProduct(Product product) {
        log.info("ProductService - removeProduct", product.getId());

        Query query = new Query(Criteria.where("id").is(product.getId()));
        mongoTemplate.findAndRemove(query, StandardProduct.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<StandardProduct> getAll() {
        return mongoTemplate.findAll(StandardProduct.class);
    }

    /**
     *
     * @param userId the user id
     * @return
     */
    @Override
    public List<StandardProduct> getProductsByUserId(long userId) {
        log.info("ProductService - getProductsByUserId", userId);

        Query query = new Query(Criteria.where("addedByUserId").is(userId));
        return mongoTemplate.find(query, StandardProduct.class);
    }

    /**
     *
     * @param categoryId the category id
     * @return
     */
    @Override
    public List<StandardProduct> getProductsByCategory(long categoryId) {
        log.info("ProductService - getProductsByCategory", categoryId);

        Query query = new Query(Criteria.where("categoryId").is(categoryId));
        return mongoTemplate.find(query, StandardProduct.class);
    }

    /**
     *
     * @param searchQuery    the search query
     * @param searchCategory the search category
     * @return
     */
    @Override
    public List<StandardProduct> search(String searchQuery, long searchCategory) {
        log.info("ProductService - searching products by query", searchQuery);

        Query query = new Query(
                new Criteria().orOperator(
                        Criteria.where("name").regex(searchQuery, "i"),
                        Criteria.where("manufacturer").regex(searchQuery, "i")
                ).andOperator(
                        Criteria.where("categoryId").is(searchCategory)

                ));


        return mongoTemplate.find(query, StandardProduct.class);
    }

    /**
     *
     * @param searchQuery the search query
     * @return
     */
    @Override
    public List<StandardProduct> search(String searchQuery) {
        Query query = new Query(
                new Criteria().orOperator(
                        Criteria.where("name").regex(searchQuery, "i"),
                        Criteria.where("manufacturer").regex(searchQuery, "i")
                ));


        return mongoTemplate.find(query, StandardProduct.class);    }

    /**
     *
     * @return
     */
    @Override
    public List<Category> getAllCategories() {
        log.info("ProductService - getAllCategories");

        return mongoTemplate.findAll(Category.class);
    }
}
