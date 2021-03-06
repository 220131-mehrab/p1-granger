package com.revature.fruitreview;

public class FruitRating {
    private int reviewId;
    private String fruitName;
    private int rating;
    private String review;

    public FruitRating() {
    }

    public FruitRating(int id, String fruitName, int rating, String review) {
        this.reviewId = id;
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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int id) {
        this.reviewId = id;
    }

    @Override
    public String toString() {
        return ("Review [reviewId=" + reviewId) + ", fruitName=" + fruitName + ", rating=" + rating + ", review=" + review + "]";
    }
}