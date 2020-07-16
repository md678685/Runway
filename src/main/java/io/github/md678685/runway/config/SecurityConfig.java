package io.github.md678685.runway.config;

import io.github.md678685.runway.service.RunwayUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RunwayUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // todo: actual security
        http
                .authorizeRequests()
                        .antMatchers("/", "/register", "/register/verify-email").permitAll()
                        .anyRequest().authenticated()
                        .and()
                .formLogin()
                        .permitAll()
                        .and()
                .logout()
                        .permitAll();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
