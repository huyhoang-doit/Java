package vn.hdi.spring.constructorinjection.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hdi.spring.constructorinjection.service.MessageService;

@RestController
public class NotificationController {
    private MessageService service;
    private MessageService service2;

    @Autowired
    public NotificationController(
            @Qualifier("emailService") MessageService service,
            @Qualifier("emailService") MessageService service2)
    {
        this.service = service;
        this.service2 = service2;
    }

    @GetMapping("/send-email")
    public String sendEmail(){
        return this.service.sendMessage();
    }

    @GetMapping("/check")
    public String check() {
        return "Check: " + (service == service2);
    }

    @GetMapping("/check2")
    public String check2() {
        if(service == service2) {
            return "SINGLETON";
        }else {
            return "PROTOTYPE";
        }
    }
}
