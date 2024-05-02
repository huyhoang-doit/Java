package vn.hdi.spring.autobuildproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/")
    public String index() {
        return "Xin chao huyhoang-doit - Le Van Huy Hoang!";
    }
}
