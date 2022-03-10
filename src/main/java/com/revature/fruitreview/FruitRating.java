package com.revature.fruitreview;

public class FruitRating {
    private String fruitName;
    private int rating;
    private String review;

    public FruitRating() {
    }

    public FruitRating(String fruitName, int rating, String review) {
        this.fruitName = fruitName;
        this.rating = rating;
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }
}