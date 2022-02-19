package ui_DayCare.model;
import ui_DayCare.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String address;
    private Long dateOfBirth;
    private List<String> immunizationIds;

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emergencyContactName='" + emergencyContactName + '\'' +
                ", emergencyContactNumber='" + emergencyContactNumber + '\'' +
                ", Address='" + address + '\''+
                ", dateOfBirth=" + dateOfBirth +
                ", immunizationIds=" + immunizationIds +
                '}';
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }



    public Person(String firstName, String lastName,
                  String emergencyContactName, String emergencyContactNumber, String address,
                  Long dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.immunizationIds = new ArrayList<>();
    }

    public Person() {
        this.immunizationIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getGetEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public String getAddress() { return address;}

    public void setAddress(String address) { this.address = address; }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = Helper.convertStringDateToLong(dateOfBirth);
    }

    public List<String> getImmunizationIds() {
        return immunizationIds;
    }

    public void setImmunizationIds(List<String> immunizationIds) {
        this.immunizationIds = immunizationIds;
    }
}
