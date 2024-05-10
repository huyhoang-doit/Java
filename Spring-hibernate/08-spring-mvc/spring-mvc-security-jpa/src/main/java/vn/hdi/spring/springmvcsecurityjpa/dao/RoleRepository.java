package vn.hdi.spring.springmvcsecurityjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hdi.spring.springmvcsecurityjpa.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName (String name);
}
