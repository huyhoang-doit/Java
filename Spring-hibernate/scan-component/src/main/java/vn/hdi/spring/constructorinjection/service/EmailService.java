package vn.hdi.spring.constructorinjection.service;

import org.springframework.stereotype.Component;

@Component
public class EmailService implements MessageInterface {
    @Override
    public String sendMessage() {
        return "Email sending.... to huyhoang-doit";
    }
}
