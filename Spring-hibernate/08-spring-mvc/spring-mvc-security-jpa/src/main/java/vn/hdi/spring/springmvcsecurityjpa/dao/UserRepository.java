package vn.hdi.spring.springmvcsecurityjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hdi.spring.springmvcsecurityjpa.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
}
