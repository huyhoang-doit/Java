package vn.hdi.spring.crudapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hdi.spring.crudapi.dao.StudentDAO;
import vn.hdi.spring.crudapi.entity.Student;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    private StudentDAO studentDAO;


    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional
    public Student getStudentById(int id) {
        return studentDAO.getById(id);
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        return studentDAO.save(student);
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        studentDAO.saveAndFlush(student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        studentDAO.deleteById(id);
    }
}
