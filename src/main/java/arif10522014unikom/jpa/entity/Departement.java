package arif10522014unikom.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "departements")
public class Departement {

    @EmbeddedId
    private DepartementId id;

    private String name;

    public DepartementId getId() {
        return id;
    }

    public void setId(DepartementId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
