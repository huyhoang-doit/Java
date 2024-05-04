package vn.hdi.spring.crudapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET,"/api/students").hasAnyRole("teacher","admin","manager")
                        .requestMatchers(HttpMethod.GET,"/api/students/**").hasAnyRole("teacher","admin","manager")
                        .requestMatchers(HttpMethod.POST,"/api/students").hasAnyRole("admin","manager")
                        .requestMatchers(HttpMethod.PUT,"/api/students").hasAnyRole("admin","manager")
                        .requestMatchers(HttpMethod.DELETE,"/api/students/**").hasRole("admin")
        );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf->csrf.disable());

        return http.build();
     }
}
