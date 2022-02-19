package ui_DayCare.model;

import ui_DayCare.model.db.DBTeacher;
import ui_DayCare.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
    private Double wage;
    private Long dateOfJoining;
    private List<String> reviewIds;

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "wage=" + wage +
                ", dateOfJoining=" + dateOfJoining +
                ", reviewIds=" + reviewIds +
                "} " + super.toString();
    }

    public Employee(String firstName,
                    String lastName,
                    String emergencyContactName,
                    String emergencyContactNumber,
                    String address,
                    Long dateOfBirth, Long dateOfJoining) {
        super(firstName, lastName, emergencyContactName, emergencyContactNumber, address, dateOfBirth);
        this.wage = 0.0;
        this.dateOfJoining = dateOfJoining;
        this.reviewIds = new ArrayList<>();
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Long getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Long dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = Helper.convertStringDateToLong(dateOfJoining);
    }

    public List<String> getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(List<String> reviewIds) {
        this.reviewIds = reviewIds;
    }
}
