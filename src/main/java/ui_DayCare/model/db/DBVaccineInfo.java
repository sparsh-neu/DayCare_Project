package ui_DayCare.model.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db_vaccine_info")
public class DBVaccineInfo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;
    @Column(name = "student_id")
    public String studentID;
    @Column(name = "vaccine_id")
    public String vaccineID;

    public DBVaccineInfo() {
    }

    public DBVaccineInfo(String id, String studentID, String vaccineID) {
        this.id = id;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }
}
