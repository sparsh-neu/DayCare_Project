package ui_DayCare.model.db;

import org.hibernate.annotations.GenericGenerator;
import ui_DayCare.model.Immunization;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "db_immunization")
public class DBImmunization implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;
    @Column(name = "immunization_rule_id")
    public String immunizationRuleId;
    @Column(name = "person_id")
    public String personId;
    @Column(name = "administration_date")
    public Long administrationDate;
    @Column(name = "due_date")
    public Long dueDate;
    @Column(name = "doses_so_far")
    public Integer dosesSoFar;

    public DBImmunization() {}

    public DBImmunization(String immunizationRuleId, String personId, Long administrationDate, Long dueDate, Integer dosesSoFar) {
        this.immunizationRuleId = immunizationRuleId;
        this.personId = personId;
        this.administrationDate = administrationDate;
        this.dueDate = dueDate;
        this.dosesSoFar = dosesSoFar;
    }

    public DBImmunization(Immunization immunization) {
        this.immunizationRuleId = immunization.getImmunizationRuleId();
        this.personId = immunization.getPersonId();
        this.administrationDate = immunization.getAdministrationDate();
        this.dueDate = immunization.getDueDate();
        this.dosesSoFar = immunization.getDosesSoFar();
    }

    public String getId() {
        return id;
    }

    public String getImmunizationRuleId() {
        return immunizationRuleId;
    }

    public void setImmunizationRuleId(String immunizationRuleId) {
        this.immunizationRuleId = immunizationRuleId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Long getAdministrationDate() {
        return administrationDate;
    }

    public void setAdministrationDate(Long administrationDate) {
        this.administrationDate = administrationDate;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getDosesSoFar() {
        return dosesSoFar;
    }

    public void setDosesSoFar(Integer dosesSoFar) {
        this.dosesSoFar = dosesSoFar;
    }


}