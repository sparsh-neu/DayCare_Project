package ui_DayCare.model.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db_vaccines")
public class DBVaccines implements Serializable {
    @Id
    @Column(name = "id")
    public String id;
    @Column(name = "name")
    public String name;

    public DBVaccines() {
    }

    public DBVaccines(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "DBVaccines{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
