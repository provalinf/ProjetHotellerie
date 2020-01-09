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
    public boolean addStudent(Student student) {
        if (!studentRepository.exists(student.getId())) {
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(String id, Student student) {
        if (deleteStudent(id)) {
            addStudent(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(String id) {
        if (studentRepository.exists(id)) {
            studentRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Student getStudent(String id) { return studentRepository.findOne(id); }

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        if (!teacherRepository.exists(teacher.getId())) {
            teacherRepository.save(teacher);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeacher(String id, Teacher teacher) {
        if (deleteTeacher(id)) {
            addTeacher(teacher);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTeacher(String id) {
        if (teacherRepository.exists(id)) {
            teacherRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Teacher getTeacher(String id) { return teacherRepository.findOne(id); }

    @Override
    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

}