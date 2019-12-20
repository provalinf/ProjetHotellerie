package m2.info.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @Column(name = "description")
    private String description;

    public Module() {}

    public Module(String verboseName, String label, String description) {
        this.verboseName = verboseName;
        this.label = label;
        this.description = description;
    }

    public long getId() { return id; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setLabel(String label) { this.label = label; }
    public String getLabel() { return label; }

    public void setVerboseName(String verboseName) { this.verboseName = verboseName; }
    public String getVerboseName() { return verboseName; }
}
