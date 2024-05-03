package vn.hdi.spring.constructorinjection.service;

import org.springframework.stereotype.Component;

@Component
public class SmsService implements MessageService{
    public SmsService(){
        System.out.println("Constructor of :" + getClass().getSimpleName());
    }
    @Override
    public String sendMessage() {
        return "Send SMS message ...";
    }
}
