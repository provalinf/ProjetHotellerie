package m2.info.services;

import m2.info.entities.Student;
import m2.info.entities.Teacher;
import m2.info.entities.User;

public interface IUserManagment {

	Iterable<Student> getAllStudents();
	Iterable<Teacher> getAllTeachers();
	Iterable<User> getAllUsers();
	User addStudent(String userId, String lastName, String firstName);
	User addTeacher(String userId, String lastName, String firstName);

}
