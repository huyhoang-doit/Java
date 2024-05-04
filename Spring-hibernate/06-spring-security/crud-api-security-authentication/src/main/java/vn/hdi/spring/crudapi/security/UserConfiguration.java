package vn.hdi.spring.crudapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfiguration {
    @Bean
     public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
         UserDetails hh = User
                 .withUsername("HuyHoang")
                 .password("{noop}12345")
                 .roles("teacher")
                 .build();

         UserDetails user = User
                 .withUsername("user")
                 .password("{noop}12345")
                 .roles("manager")
                 .build();

         UserDetails admin = User
                 .withUsername("Admin")
                 .password("{noop}12345")
                 .roles("admin")
                 .build();
         return new InMemoryUserDetailsManager(hh,user,admin);
     }
}
