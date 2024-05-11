package vn.hdi.spring.springmvcsecurityjpa.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.hdi.spring.springmvcsecurityjpa.entity.User;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);
    public void save(User user);
}
