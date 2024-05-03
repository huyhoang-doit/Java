package vn.hdi.spring.constructorinjection.service;

import org.springframework.stereotype.Component;

@Component
public class SmsService implements MessageService{
    @Override
    public String sendMessage() {
        return "Send SMS message ...";
    }
}
