package vn.hdi.spring.constructorinjection.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component
public class EmailService implements MessageService {

    public EmailService() {
        System.out.println("Constructor of " + getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        System.out.println("Message after object "+ getClass().getSimpleName() + " CREATE");
    }


    @PreDestroy
    public void destroy() {
        System.out.println("Message after object "+ getClass().getSimpleName() + " DESTROY");
    }

    @Override
    public String sendMessage() {
        return "Email sending.... to huyhoang-doit";
    }
}
