package ui_DayCare.model.db;
import org.hibernate.annotations.GenericGenerator;
import ui_DayCare.model.Review;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db_review")
public class DBReview implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;
    @Column(name = "rating")
    Integer rating;
    @Column(name = "review_date")
    Long reviewDate;
    @Column(name = "person_id")
    String personId;

    public DBReview() {}

    public DBReview(String id, Integer rating, Long reviewDate, String personId) {
        this.id = id;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.personId = personId;
    }

    public DBReview(Review review) {
        this.rating = review.getRating();
        this.reviewDate = review.getReviewDate();
        this.personId = review.getPersonId();
    }

    public Review getReview() {
        return new Review(this);
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
