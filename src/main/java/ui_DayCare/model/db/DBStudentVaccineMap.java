package ui_DayCare.model.db;

import java.io.Serializable;

public class DBStudentVaccineMap implements Serializable {
    String firstName, LastName, vaccineId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public DBStudentVaccineMap(String firstName, String lastName, String vaccineId) {
        this.firstName = firstName;
        LastName = lastName;
        this.vaccineId = vaccineId;
    }
}
