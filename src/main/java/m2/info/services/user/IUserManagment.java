package m2.info.services.user;

import m2.info.entities.user.Student;
import m2.info.entities.user.Teacher;
import m2.info.entities.user.User;

public interface IUserManagment {

	Iterable<Student> getAllStudents();
	Iterable<Teacher> getAllTeachers();
	Iterable<User> getAllUsers();
	User addStudent(String userId, String lastName, String firstName);
	User addTeacher(String userId, String lastName, String firstName);

}
