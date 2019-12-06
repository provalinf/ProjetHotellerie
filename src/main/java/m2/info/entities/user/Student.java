package m2.info.entities.user;

import javax.persistence.Entity;

@Entity
public class Student extends User {

    public Student(){}
    public Student(String id, String lastName, String firstName) {
        super(id, lastName, firstName);
    }
}
