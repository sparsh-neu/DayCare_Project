package ui_DayCare.model;

import ui_DayCare.model.db.DBImmunization;

public class Immunization {
    private String id;
    private String immunizationRuleId;
    private String personId;
    private Long administrationDate;
    private Long dueDate;
    private Integer dosesSoFar;


    public Immunization() {}

    public Immunization(String immunizationRuleId, String personId, Long administrationDate, Long dueDate, Integer dosesSoFar) {
        this.immunizationRuleId = immunizationRuleId;
        this.personId = personId;
        this.administrationDate = administrationDate;
        this.dueDate = dueDate;
        this.dosesSoFar = dosesSoFar;
    }

    public Immunization(DBImmunization immunization) {
        this.id = immunization.id;
        this.immunizationRuleId = immunization.immunizationRuleId;
        this.personId = immunization.personId;
        this.administrationDate = immunization.administrationDate;
        this.dueDate = immunization.dueDate;
        this.dosesSoFar = immunization.dosesSoFar;
    }

    @Override
    public String toString() {
        return "Immunization{" +
                "id='" + id + '\'' +
                ", immunizationRuleId='" + immunizationRuleId + '\'' +
                ", personId='" + personId + '\'' +
                ", administrationDate=" + administrationDate +
                ", dueDate=" + dueDate +
                ", dosesSoFar=" + dosesSoFar +
                '}';
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
