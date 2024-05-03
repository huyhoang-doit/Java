package vn.hdi.spring.constructorinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    private MessageInterface email;

    @Autowired
    public NotificationController(EmailService email) {
        this.email = email;
    }

    @GetMapping("/send-email")
    public String sendEmail(){
        return this.email.sendMessage();
    }
}
