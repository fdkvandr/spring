package com.corp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отсключим csrf, потому что его нужно конфигурировать на наших страничках отдельно, поэтому об этом дальше
                .authorizeHttpRequests().anyRequest().authenticated() // Только пользователи, которые прошли аутентификацию могут делать любые запросы по нашему приложению
                .and()
                .formLogin(login -> login // Тут мы настраиваем нашу страничку логина
                        .loginPage("/login") // Говорим, что она находится на url /login - это как раз наш @GetMapping /login контроллер, который перенаправит нас на нашу VIEW
                        .defaultSuccessUrl("/users") // Говорим, что делать в случае успешной аутентификации - иди на path /users ибо раньше он шел на просто /
                        .permitAll()); // И говорим, что все кто идет на страничку /login имеет доступ, даже если он не аутентифицирвован
                // Конечно не очень хорошо, что мы доступ к страничкам выдавали и сверху в authorizeHttpRequests и в formLogin но когда мы дойдем чуть дальше, до авторизации, то мы посмотрим как убрать permiteAll и оставить его только в одном месте
        return http.build();
    }
}
