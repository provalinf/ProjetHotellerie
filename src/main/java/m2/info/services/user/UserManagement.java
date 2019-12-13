package m2.info.services.user;

import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.models.user.User;
import m2.info.repositories.StudentRepository;
import m2.info.repositories.TeacherRepository;
import m2.info.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserManagement implements IUserManagement {

    @Autowired private StudentRepository studentRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) return user;
        else throw new UsernameNotFoundException("No user present with username : " + username);
    }

    @Override
    public void addStudent(String id, String username, String password, String firstname, String lastname) {
        studentRepository.save(new Student(id, username, password, firstname, lastname));
    }

    @Override
    public void addTeacher(String id, String username, String password, String firstname, String lastname) {
        teacherRepository.save(new Teacher(id, username, password, firstname, lastname));
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}