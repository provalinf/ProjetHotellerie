package m2.info.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class User {

    @Id private long id;
    private String firstName;
    private String lastName;

}
