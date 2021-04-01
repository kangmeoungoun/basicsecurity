package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(0)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        String password = "{noop}1111";
        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
        auth.inMemoryAuthentication().withUser("sys").password(password).roles("SYS");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN","SYS","USER");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                .anyRequest().permitAll();

        http
                .formLogin();
    }
}

