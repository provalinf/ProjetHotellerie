package m2.info.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity (name = "evaluation")
public class Evaluation {

    @Column(name = "id_eval")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id private long id;

    @NotNull
    @Column(name = "consistency_mark", nullable = false)
    private short consistency;

    @NotNull
    @Column(name = "documentation_mark", nullable = false)
    private short documentation;

    @NotNull
    @Column(name = "lecture_mark", nullable = false)
    private short lecture;

    @NotNull
    @Column(name = "personal_interest_mark", nullable = false)
    private short personalInterest;

    @NotNull
    @Column(name = "practical_work_mark", nullable = false)
    private short practicalWork;

    @NotNull
    @Column(name = "tutorial_mark", nullable = false)
    private short tutorial;

    @NotNull
    @Column(name = "workload_mark", nullable = false)
    private short workload;

    @Column(name = "comment")
    private String comment;

}
