package ui_DayCare.model;

import ui_DayCare.model.db.DBReview;

public class Review {

    private String id;
    private Integer rating;
    private Long reviewDate;
    private String personId;

    public Review(String id, Integer rating, Long reviewDate, String personId) {
        this.id = id;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.personId = personId;
    }

    public Review(Integer rating, String personId, Long reviewDate) {
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.personId = personId;
    }

    public Review(DBReview review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.reviewDate = review.getReviewDate();
        this.personId = review.getPersonId();
    }

    public DBReview getDBReview() {
        return new DBReview(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Long reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
