package ui_DayCare.model.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db_vaccine_rules")
public class DBVaccineRules implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;
    @Column(name = "month")
    public Long month;
    @Column(name = "vaccine_id")
    public String vaccineID;

    public DBVaccineRules() {
    }

    public DBVaccineRules(String id, Long month, String vaccineID) {
        this.id = id;
        this.month = month;
        this.vaccineID = vaccineID;
    }

    public DBVaccineRules(Long month, String vaccineID) {
        this.month = month;
        this.vaccineID = vaccineID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    @Override
    public String toString() {
        return "DBVaccineRules{" +
                "id='" + id + '\'' +
                ", month=" + month +
                ", vaccineID='" + vaccineID + '\'' +
                '}';
    }
}
