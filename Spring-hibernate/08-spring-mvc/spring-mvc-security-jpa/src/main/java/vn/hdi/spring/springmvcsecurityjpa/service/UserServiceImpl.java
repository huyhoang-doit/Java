package vn.hdi.spring.springmvcsecurityjpa.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import vn.hdi.spring.springmvcsecurityjpa.entity.Role;
import vn.hdi.spring.springmvcsecurityjpa.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.hdi.spring.springmvcsecurityjpa.dao.RoleRepository;
import vn.hdi.spring.springmvcsecurityjpa.dao.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
//    @PostConstruct
//    public void insertUsers() {
//        User user01 = new User();
//        user01.setUserName("Hoang");
//        user01.setPassword("$2a$12$wOVmZDH/lYO43KLTSIXxFu65os/tVCm8ufOqCiJ.H/2fX3nglpX.2");
//        user01.setEnabled(true);
//
//        Role role01 = new Role();
//        role01.setName("ROLE_ADMIN");
//
//
//        Collection<Role> roles = new ArrayList<>();
//        roles.add(role01);
//
//        user01.setRoles(roles);
//        userRepository.save(user01);
//    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user==null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), rolesToAuthoriries(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthoriries(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
