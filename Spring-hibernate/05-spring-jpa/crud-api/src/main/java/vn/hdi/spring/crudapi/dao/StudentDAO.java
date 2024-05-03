package vn.hdi.spring.crudapi.dao;

import vn.hdi.spring.crudapi.entity.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> findAll();
    public Student getById(int id);
    //add
    public Student save(Student student);
    //update
    public void saveAndFlush(Student student);
    //delete
    public void deleteById(int id);
}
