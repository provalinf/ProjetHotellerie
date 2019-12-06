package m2.info.entities;

import javax.persistence.Entity;

@Entity
public class Teacher extends User {

    public Teacher() {}
    public Teacher(String id, String lastName, String firstName) {
        super(id, lastName, firstName);
    }
}
