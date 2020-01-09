package m2.info.models;

import m2.info.models.user.Student;

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

    @ManyToOne
    @JoinColumn(name="author")
    private Student author;

    @ManyToOne
    @JoinColumn(name="module")
    private Module module;

    public long getId() { return id; }

    public void setConsistency(short consistency) { this.consistency = consistency; }
    public short getConsistency() { return consistency; }

    public void setDocumentation(short documentation) { this.documentation = documentation; }
    public short getDocumentation() { return documentation; }

    public void setLecture(short lecture) { this.lecture = lecture; }
    public short getLecture() { return lecture; }

    public void setPersonalInterest(short personalInterest) { this.personalInterest = personalInterest; }
    public short getPersonalInterest() { return personalInterest; }

    public void setPracticalWork(short practicalWork) { this.practicalWork = practicalWork; }
    public short getPracticalWork() { return practicalWork; }

    public void setTutorial(short tutorial) { this.tutorial = tutorial; }
    public short getTutorial() { return tutorial; }

    public void setWorkload(short workload) { this.workload = workload; }
    public short getWorkload() { return workload; }

    public void setComment(String comment) { this.comment = comment; }
    public String getComment() { return comment; }

    public void setAuthor(Student author) { this.author = author; }
    public Student getAuthor() { return author; }

    public void setModule(Module module) { this.module = module; }
    public Module getModule() { return module; }
}
