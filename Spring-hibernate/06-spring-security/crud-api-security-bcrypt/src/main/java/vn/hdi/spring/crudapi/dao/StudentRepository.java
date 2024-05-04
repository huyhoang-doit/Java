package vn.hdi.spring.crudapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hdi.spring.crudapi.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Other methods can be added here
}
