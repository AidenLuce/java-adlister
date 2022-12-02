package com.codeup.springblog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.DispatcherType;

@Configuration
public class SecurityConfiguration {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws
            Exception {
        http.authorizeRequests()
                .antMatchers("/posts/allPosts", "/posts/{id}/edit").authenticated()
                .antMatchers("/").permitAll()
            .and().formLogin().loginPage("/login").defaultSuccessUrl("/posts/allPosts")
            .and().logout().logoutSuccessUrl("/")
            .and().httpBasic();
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
