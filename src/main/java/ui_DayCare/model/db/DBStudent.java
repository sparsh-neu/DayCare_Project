package ui_DayCare.model.db;
import org.hibernate.annotations.GenericGenerator;
import ui_DayCare.model.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "db_student")
public class DBStudent implements Serializable {
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
    @Column(name = "registration_date")
    Long registrationDate;
    @Column(name = "gpa")
    Double gpa;

    public DBStudent() {}

    public DBStudent(String firstName, String lastName,
                     String emergencyContactName, String emergencyContactNumber,
                     String address, Long dateOfBirth, Long registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
    }

    public DBStudent(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.emergencyContactName = student.getGetEmergencyContactName();
        this.emergencyContactNumber = student.getEmergencyContactNumber();
        this.address = student.getAddress();
        this.dateOfBirth = student.getDateOfBirth();
        this.registrationDate = student.getRegistrationDate();
    }

    public Student getStudent() {
        Student student = new Student(firstName, lastName,
                emergencyContactName, emergencyContactNumber, address,
                dateOfBirth, registrationDate);

        // TODO: Query and Populate
        student.setImmunizationIds(new ArrayList<>());
        student.setGpa(0.0);
        return student;
    }

    // TODO: Groups Relation Define
    public Long getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Long registrationDate) {
        this.registrationDate = registrationDate;
    }
    public Double getGpa() {
        return gpa;
    }
    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}
