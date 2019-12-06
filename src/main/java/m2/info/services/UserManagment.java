package m2.info.services;

import m2.info.entities.Student;
import m2.info.entities.Teacher;
import m2.info.entities.User;
import m2.info.repositories.StudentRepository;
import m2.info.repositories.TeacherRepository;
import m2.info.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagment implements IUserManagment {

	@Autowired private StudentRepository studentRepository;
	@Autowired private TeacherRepository teacherRepository;
	@Autowired private UserRepository userRepository;

	@Override
	public Iterable<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Iterable<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User addStudent(String userId, String lastName, String firstName) {
		return userRepository.save(new Student(userId, lastName, firstName));
	}

	@Override
	public User addTeacher(String userId, String lastName, String firstName) {
		return userRepository.save(new Teacher(userId, lastName, firstName));
	}

}
