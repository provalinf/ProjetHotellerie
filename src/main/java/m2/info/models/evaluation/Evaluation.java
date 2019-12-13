package m2.info.models.evaluation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evaluation {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private EvalMap marks;
    private String comment;

}
