/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_objects.Student;
import java.util.List;

/**
 *
 * @author lvhho
 */
public interface Iservice {
    void deleteStudent(Student student);
    List<Student> getAllStudents();
    Student getStudent(int id);
    void updateStudent(Student student);
}
