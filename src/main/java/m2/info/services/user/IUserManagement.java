package m2.info.services.user;

import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserManagement extends UserDetailsService {

    void saveUser(User User);
    boolean deleteUser(String id);
    User getUser(String id);
    Iterable<User> getAllUsers();

    Student getStudent(String id);

    Teacher getTeacher(String id);

}
