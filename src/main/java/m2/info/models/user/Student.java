package m2.info.models.user;

import m2.info.models.Evaluation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "student")
public class Student extends User {

    @OneToMany(mappedBy="author")
    private Set<Evaluation> evaluations = new HashSet<>();

    public Student(){}

    public Student(String id, String username, String password, String lastname, String firstname) {
        super(id, username, password, lastname, firstname);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList((Authorities.STUDENT.name()));
    }

    public Set<Evaluation> getEvaluations() { return evaluations; }
    public void setEvaluations(Set<Evaluation> evaluations) { this.evaluations = evaluations; }
}

