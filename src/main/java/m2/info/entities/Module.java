package m2.info.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Module {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id private long moduleId;
    private String verboseName;
    private String label;
    private String description;

    public Module() {}

    public Module(String verboseName, String label, String description) {
        this.verboseName = verboseName;
        this.label = label;
        this.description = description;
    }

    public long getModuleId() { return moduleId; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setLabel(String label) { this.label = label; }
    public String getLabel() { return label; }

    public void setVerboseName(String verboseName) { this.verboseName = verboseName; }
    public String getVerboseName() { return verboseName; }
}
