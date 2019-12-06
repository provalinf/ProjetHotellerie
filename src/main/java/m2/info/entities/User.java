package m2.info.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id private String userId;
    private String lastName;
    private String firstName;

    public User(){}

    public User(String id, String lastName, String firstName) {
        this.userId = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() { return userId; }

    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getLastName() { return lastName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getFirstName() { return firstName; }
}
