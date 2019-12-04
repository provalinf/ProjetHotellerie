package m2.info.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Module {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id private long id;
    private String label;
    private String verboseName;
    private String description;

}
