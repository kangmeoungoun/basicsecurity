package io.security.basicsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final  UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http
               .authorizeRequests()
               .anyRequest().authenticated()
       ;
       http
               .formLogin()
               ;
       http
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .sessionFixation().changeSessionId();
               //.maximumSessions(1)
               //.maxSessionsPreventsLogin(false)


    }
}
