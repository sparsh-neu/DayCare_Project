package ui_DayCare.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "db_immunization_rules")
public class DBImmunizationRules implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    String id;
    @Column(name = "name")
    String name;
    @Column(name = "doses")
    Integer doses;
    @Column(name = "start_month")
    Long startMonth;
    @Column(name = "end_month")
    Long endMonth;

    public DBImmunizationRules() {
    }

    public DBImmunizationRules(String id, String name, Integer doses, Long startMonth, Long endMonth) {
        this.id = id;
        this.name = name;
        this.doses = doses;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDoses() {
        return doses;
    }

    public void setDoses(Integer doses) {
        this.doses = doses;
    }

    public Long getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Long startMonth) {
        this.startMonth = startMonth;
    }

    public Long getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Long endMonth) {
        this.endMonth = endMonth;
    }

    @Override
    public String toString() {
        return "ImmunizationRules{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", doses=" + doses +
                ", startMonth=" + startMonth +
                ", endMonth=" + endMonth +
                '}';
    }
}
