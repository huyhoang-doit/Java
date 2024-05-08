package vn.hdi.spring.mvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/error403")
    public String error403() {
        return "error/403";
    }
}
