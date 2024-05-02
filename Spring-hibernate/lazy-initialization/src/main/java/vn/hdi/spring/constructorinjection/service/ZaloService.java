package vn.hdi.spring.constructorinjection.service;

import org.springframework.stereotype.Component;

@Component
public class ZaloService implements MessageService{
    public ZaloService(){
        System.out.println("Constructor of :" + getClass().getSimpleName());
    }
    @Override
    public String sendMessage() {
        return "Send Zalo message ....";
    }
}
