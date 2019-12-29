package m2.info.services.user;

import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserManagement extends UserDetailsService {

    Iterable<User> getAllUsers();
    boolean addStudent(Student student);
    boolean updateStudent(String id, Student student);
    boolean deleteStudent(String id);
    Student getStudent(String id);
    Iterable<Student> getAllStudents();
    boolean addTeacher(Teacher teacher);
    boolean updateTeacher(String id, Teacher teacher);
    boolean deleteTeacher(String id);
    Teacher getTeacher(String id);
    Iterable<Teacher> getAllTeachers();

}
