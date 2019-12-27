package m2.info.services.user;

import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserManagement extends UserDetailsService {

    Iterable<User> getAllUsers();
    void addStudent(String id, String username, String password, String firstname, String lastname);
    Student getStudent(String id);
    Iterable<Student> getAllStudents();
    void addTeacher(String id, String username, String password, String firstname, String lastname);
    Teacher getTeacher(String id);
    Iterable<Teacher> getAllTeachers();

}
