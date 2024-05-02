package vn.hdi.spring.constructorinjection.service;

import org.springframework.stereotype.Component;

@Component
public class EmailService implements MessageService {

    public EmailService(){
        System.out.println("Constructor of :" + getClass().getSimpleName());
    }

    @Override
    public String sendMessage() {
        return "Email sending.... to huyhoang-doit";
    }
}
