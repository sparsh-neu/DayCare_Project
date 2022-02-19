package ui_DayCare.model.db;
import org.hibernate.annotations.GenericGenerator;
import ui_DayCare.model.Employee;
import ui_DayCare.model.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "db_teacher")
public class DBTeacher implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;
    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    @Column(name = "emergency_contact_name")
    public String emergencyContactName;
    @Column(name = "emergency_contact_number")
    public String emergencyContactNumber;
    @Column(name = "address")
    public String address;
    @Column(name = "date_of_birth")
    public Long dateOfBirth;
    @Column(name = "dateOfJoining")
    public Long dateOfJoining;
    @Column(name = "wage")
    public Double wage;
    @Column(name = "assignedGroup")
    public String assignedGroup;

    public DBTeacher() {}

    public DBTeacher(String firstName, String lastName,
                     String emergencyContactName, String emergencyContactNumber,
                     Long dateOfBirth, Long dateOfJoining, String assignedGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.wage = 0.0;
        this.assignedGroup = assignedGroup;
    }

    public DBTeacher(Teacher teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
        this.emergencyContactName = teacher.getGetEmergencyContactName();
        this.emergencyContactNumber = teacher.getEmergencyContactNumber();
        this.dateOfBirth = teacher.getDateOfBirth();
        this.dateOfJoining = teacher.getDateOfJoining();
        this.assignedGroup = teacher.getAssignedGroup();
    }

    public Employee getTeacher() {
        Teacher teacher = new Teacher(this);

        // TODO: Query and Populate
        teacher.setImmunizationIds(new ArrayList<>());
        teacher.setReviewIds(new ArrayList<>());
        teacher.setWage(0.0);
        teacher.setAssignedGroup(getAssignedGroup());
        return teacher;
    }


    // TODO: Groups Relation Define
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
    public String getEmergencyContactName() {
        return emergencyContactName;
    }
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }
    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }
    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address;}
    public Long getDateOfJoining() {
        return dateOfJoining;
    }
    public void setDateOfJoining(Long dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    public Double getWage() {
        return wage;
    }
    public void setWage(Double wage) {
        this.wage = wage;
    }
    public Long getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAssignedGroup() { return assignedGroup;}
    public void setAssignedGroup(String assignedGroup) { this.assignedGroup = assignedGroup;}


    @Override
    public String toString() {
        return "DBTeacher{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emergencyContactName='" + emergencyContactName + '\'' +
                ", emergencyContactNumber='" + emergencyContactNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfJoining=" + dateOfJoining +
                ", wage=" + wage +
                '}';
    }
}
