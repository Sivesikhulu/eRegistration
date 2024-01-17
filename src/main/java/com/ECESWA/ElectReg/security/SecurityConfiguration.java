package com.ECESWA.ElectReg.security;

import com.ECESWA.ElectReg.service.UsersService;
import com.ECESWA.ElectReg.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    @Autowired
    UserDetailsService userDetailsService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
         auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                        configurer
                                .requestMatchers("/2/**").hasRole("EPC")
                                .requestMatchers("/3/**").hasRole("JC")
                                .requestMatchers("/13/**").hasRole("EGCSE")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(form->
                        form.loginPage("/login")
                                .loginProcessingUrl("/authenticateUser")
                                .defaultSuccessUrl("/centreDetails", true)
                                .permitAll()
                )
                .logout(logout->logout.permitAll());
        return http.build();
    }

}