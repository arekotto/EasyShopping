package com.easydevs.product.model;

/**
 * Created by ibm on 2017-01-27.
 */
public class Review {

    /**
     * The User id.
     */
    long userId;
    /**
     * The User name.
     */
    String userName;
    /**
     * The Rating.
     */
    int rating;
    /**
     * The Review text.
     */
    String reviewText;

    /**
     * Instantiates a new Review.
     */
    public Review() {

    }


    /**
     * Instantiates a new Review.
     *
     * @param rating     the rating
     * @param reviewText the review text
     * @param userId     the user id
     * @param userName   the user name
     */
    public Review(int rating, String reviewText, long userId, String userName) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.userId = userId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets review text.
     *
     * @return the review text
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Sets review text.
     *
     * @param reviewText the review text
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return this.rating + " stars:\n" + this.reviewText;
    }
}
