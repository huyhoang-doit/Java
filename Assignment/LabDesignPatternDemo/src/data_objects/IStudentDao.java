/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import business_objects.Student;
import java.util.List;

/**
 *
 * @author lvhho
 */
public interface IStudentDao {
    public List<Student> getAllStudents();
    public Student getStudent(int id);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
}
