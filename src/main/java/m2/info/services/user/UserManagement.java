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
    public void saveUser(User user) { userRepository.save(user); }

    @Override
    public boolean deleteUser(String id) {
        if (userRepository.exists(id)) {
            userRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String id) { return userRepository.findOne(id); }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Student getStudent(String id) { return studentRepository.findOne(id); }


    @Override
    public Teacher getTeacher(String id) { return teacherRepository.findOne(id); }

}