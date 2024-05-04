package vn.hdi.spring.springmvc.service;


import vn.hdi.spring.springmvc.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();
    public Student getStudentById(int id);
    public Student addStudent(Student student);
    public void updateStudent(Student student);
    public void deleteStudent(int id);
}
