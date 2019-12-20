package m2.info.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import java.util.Collection;

@Entity (name = "teacher")
public class Teacher extends User {

    public Teacher(){}

    public Teacher(String id, String username, String password, String lastname, String firstname) {
        super(id, username, password, lastname, firstname);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList((Authorities.TEACHER.name()));
    }
}

