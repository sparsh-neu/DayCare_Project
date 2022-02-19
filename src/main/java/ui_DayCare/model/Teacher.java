package ui_DayCare.model;

import ui_DayCare.model.db.DBTeacher;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import java.util.ArrayList;

public class Teacher extends Employee {

    private String assignedGroup;

    public Teacher() {
        super();
        assignedGroup = "";
    }

    public Teacher(String firstName, String lastName, String emergencyContactName, String emergencyContactNumber, String address, Long dateOfBirth, Long dateOfJoining, String assignedGroup) {
        super(firstName, lastName, emergencyContactName, emergencyContactNumber, address, dateOfBirth, dateOfJoining);
        this.assignedGroup = assignedGroup;
    }

    public Teacher(DBTeacher teacher) {
        super(teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmergencyContactName(),
                teacher.getEmergencyContactNumber(),
                teacher.getAddress(),
                teacher.getDateOfBirth(), teacher.getDateOfJoining());
        this.setId(teacher.getId());
        this.setWage(teacher.getWage());
        this.setReviewIds(new ArrayList<>());
        this.setAssignedGroup(teacher.getAssignedGroup());
    }

    public Teacher(String csvString) throws Exception {
        super();
        /*
        order of params - firstName, lastName, emergencyContactName, emergencyContactNumber, gpa, dob, RegistrationDate ("yyyy-mm-dd")
         */
        String[] params = csvString.split(",");
        this.setFirstName(params[0].trim());
        this.setLastName(params[1].trim());
        this.setEmergencyContactName(params[2].trim());
        this.setEmergencyContactNumber(params[3].trim());
        this.setAddress(params[4].trim());
        this.setWage(Double.valueOf(params[5].trim()));
        this.setDateOfBirth(Helper.convertStringDateToLong(params[6].trim()));
        this.setDateOfJoining(Helper.convertStringDateToLong(params[7].trim()));
        this.setAssignedGroup(params[8].trim());
    }

    public DBTeacher getDBTeacher() {
        DBTeacher teacher = new DBTeacher(this);
        return teacher;
    }

    public String[] toStringArray() {
        String[] result = {getFirstName(), getLastName(), getGetEmergencyContactName(), getEmergencyContactNumber(),
                Helper.convertLongToFormattedString(getDateOfBirth()), Helper.convertLongToFormattedString(getDateOfJoining()),
                getAssignedGroup()};
        return result;
    }

    public Validation checkValidity() {
        String message = "";
        if (getFirstName().isEmpty()) message = "First name can not be empty";
        else if (getLastName().isEmpty()) message = "Last name can not be empty";
        else if (getGetEmergencyContactName().isEmpty()) message = "Emergency Contact Name name can not be empty";
        else if (getEmergencyContactNumber().isEmpty()) message = "Phone number can not be empty";
        else if (getDateOfBirth().equals(Long.valueOf(0))) message = "Date of birth can not be empty";
        else if (getDateOfJoining().equals(Long.valueOf(0))) message = "Date of joining can not be empty";

        if (message.isEmpty()) return new Validation("", true);
        else return new Validation(message, false);
    }

    public String getAssignedGroup() {
        return assignedGroup;
    }

    public void setAssignedGroup(String assignedGroup) {
        this.assignedGroup = assignedGroup;
    }


    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
