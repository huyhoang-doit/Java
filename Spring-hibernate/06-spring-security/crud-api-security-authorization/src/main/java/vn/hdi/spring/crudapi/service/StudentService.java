package vn.hdi.spring.crudapi.service;

import vn.hdi.spring.crudapi.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();
    public Student getStudentById(int id);
    public Student addStudent(Student student);
    public void updateStudent(Student student);
    public void deleteStudent(int id);
}
