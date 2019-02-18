//package com.example.demo.config;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.validation.OverridesAttribute;
//
//public class AppConfig extends WebSecurityConfigurerAdapter {
//
//    public static final String REALM_NAME = "memorynotfound.com";
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .realmName(REALM_NAME)
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("manager").password("password").roles("MANAGER");
//    }
//}
