package vn.hdi.spring.caculater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyUtil {

    @Bean
    public Caculator getCaculator() {
        return new Caculator();
    }
}
