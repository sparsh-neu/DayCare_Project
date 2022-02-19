package ui_DayCare.model.db;

import javax.persistence.Column;
import java.io.Serializable;

public class DBTeacherAvgReview implements Serializable {
    public String firstName;
    public String lastName;
    public Double averageRating;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public DBTeacherAvgReview(String firstName, String lastName, Double averageRating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageRating = averageRating;
    }

    public String[] getStringArray() {
        return new String[] {getFirstName(), getLastName(), getAverageRating().toString()};
    }

    @Override
    public String toString() {
        return "DBTeacherAvgReview{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }
}
