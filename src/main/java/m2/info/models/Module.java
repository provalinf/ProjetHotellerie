package m2.info.models;

import m2.info.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "module")
public class Module {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_module")
    @Id private long id;

    @NotNull
    @Column(name = "verbose_name", nullable = false, unique = true)
    private String verboseName;

    @NotNull
    @Column(name = "label", nullable = false)
    private String label;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy="modules")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy="module")
    private Set<Evaluation> evaluations = new HashSet<>();

    public Module() {}

    public Module(String verboseName, String label, String description) {
        this.verboseName = verboseName;
        this.label = label;
        this.description = description;
    }

    public long getId() { return id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getVerboseName() { return verboseName; }
    public void setVerboseName(String verboseName) { this.verboseName = verboseName; }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }

    public Set<Evaluation> getEvaluations() { return evaluations; }

    public void setEvaluations(Set<Evaluation> evaluations) { this.evaluations = evaluations; }

}
