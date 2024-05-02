package vn.hdi.spring.crudapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.hdi.spring.crudapi.entity.Student;

@RepositoryRestResource(path="students")
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Other methods can be added here
    // Srping HATEOAS
    // Spring data REST - hỗ trợ paging, sort,...
}
