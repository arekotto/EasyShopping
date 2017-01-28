package com.easydevs.product.model;

/**
 * Created by ibm on 2017-01-27.
 */
public class Review {

    long userId;
    String userName;
    int rating;
    String reviewText;

    public Review() {

    }


    public Review(int rating, String reviewText, long userId, String userName) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return this.rating + " stars:\n" + this.reviewText;
    }
}
