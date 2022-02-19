package ui_DayCare.model;

import ui_DayCare.model.db.DBStudent;
import ui_DayCare.utils.Helper;

import java.util.ArrayList;

public class Student extends Person {

    Long registrationDate;
    Double gpa;

    public Student() {
        super();
    }

    public Student(String firstName,
                   String lastName,
                   String emergencyContactName,
                   String emergencyContactNumber,
                   String address,
                   Long dateOfBirth,
                   Long registrationDate) {
        super(firstName, lastName, emergencyContactName, emergencyContactNumber, address, dateOfBirth);
        this.registrationDate = registrationDate;
    }

    /*DB Objects Constructor & Getter*/
    public Student(DBStudent student) {
        super(student.firstName, student.lastName, student.emergencyContactName, student.emergencyContactNumber, student.address, student.dateOfBirth);
        this.setId(student.id);
        this.registrationDate = student.getRegistrationDate();
        this.gpa = student.getGpa();
        this.setImmunizationIds(new ArrayList<>());
    }

    public String[] toStringArray() {
        String[] result = {getFirstName(), getLastName(), getGetEmergencyContactName(), getEmergencyContactNumber(), getAddress(),
                Helper.convertLongToFormattedString(getDateOfBirth()),
                Helper.convertLongToFormattedString(getRegistrationDate())};
        return result;
    }

    public String[] toStringArrayWithGroup(String groupInfo, String teacherName) {
        String[] result = {
                getFirstName(),
                getLastName(),
                groupInfo,
                teacherName
        };
        return result;
    }
    
    public Student(String csvString) throws Exception {
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
        this.setGpa(Double.valueOf(params[5].trim()));
        this.setDateOfBirth(Helper.convertStringDateToLong(params[6].trim()));
        this.setRegistrationDate(Helper.convertStringDateToLong(params[7].trim()));
    }

    public DBStudent getDBStudent() {
        return new DBStudent(this);
    }

    // TODO: Groups Relation Define
    public Long getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Long registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = Helper.convertStringDateToLong(registrationDate);
    }
    public Double getGpa() {
        return gpa;
    }
    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public GroupInfo getGroupInfo() {
        return GroupInfo.getGroupInfo(getDateOfBirth());
    }

    public Validation checkValidity() {
        String message = "";
        if (getFirstName().isEmpty()) message = "First name can not be empty";
        else if (getLastName().isEmpty()) message = "Last name can not be empty";
        else if (getGetEmergencyContactName().isEmpty()) message = "Guardian name can not be empty";
        else if (getEmergencyContactNumber().isEmpty()) message = "Phone number can not be empty";
        else if (getDateOfBirth().equals(Long.valueOf(0))) message = "Date of birth can not be empty";
        else if (getRegistrationDate().equals(Long.valueOf(0))) message = "Date of registration can not be empty";

        if (message.isEmpty()) return new Validation("", true);
        else return new Validation(message, false);
    }


    @Override
    public String toString() {
        return "Student{" +
                "registrationDate=" + registrationDate +
                ", gpa=" + gpa +
                "} " + super.toString();
    }
}
