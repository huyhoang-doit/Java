package vn.hdi.spring.springmvcsecurityjpa.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import vn.hdi.spring.springmvcsecurityjpa.dao.RoleRepository;
import vn.hdi.spring.springmvcsecurityjpa.entity.Role;
import vn.hdi.spring.springmvcsecurityjpa.entity.User;
import vn.hdi.spring.springmvcsecurityjpa.service.UserService;
import vn.hdi.spring.springmvcsecurityjpa.web.RegisterUser;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/register")
public class RegisterController {

    UserService userService;
    RoleRepository roleRepository;

    @Autowired
    public RegisterController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model model) {
        RegisterUser registerUser = new RegisterUser();
        model.addAttribute("registerUser", registerUser);
        return "register/form";
    }

    @InitBinder
    public void initBinder(WebDataBinder data) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute RegisterUser registerUser,
                          BindingResult result,
                          Model model,
                          HttpSession session)
    {
        String username = registerUser.getUserName();
        if(result.hasErrors()) {
            return "register/form";
        }

        User user = userService.findByUserName(username);

        if(user != null) {
            model.addAttribute("registerUser", new RegisterUser());
            model.addAttribute("message", "Account already exists");
            return "register/form";
        }else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user = new User();
            user.setUserName(registerUser.getUserName());
            user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
            user.setFirstName(registerUser.getFirstName());
            user.setLastName(registerUser.getLastName());
            user.setEmail(registerUser.getEmail());

            Role role01 = roleRepository.findByName("ROLE_USER");
            Collection<Role> roles = new ArrayList<>();
            roles.add(role01);

            user.setRoles(roles);

            userService.save(user);
            session.setAttribute("myuser", user);
            return "register/confirmation";
        }
    }
}
