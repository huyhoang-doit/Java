package vn.hdi.spring.constructorinjection.service;

import org.springframework.stereotype.Component;

@Component
public class ZaloService implements MessageService{
    @Override
    public String sendMessage() {
        return "Send Zalo message ....";
    }
}
