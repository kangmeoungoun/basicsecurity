package io.security.basicsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        String password = "{noop}1111";
        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
        auth.inMemoryAuthentication().withUser("sys").password(password).roles("SYS");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN","SYS","USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http
               .authorizeRequests()
               .antMatchers("/login").permitAll()
               .antMatchers("/user").hasRole("USER")
               .antMatchers("/admin/pay").hasRole("ADMIN")
               .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
               .anyRequest().authenticated();
       http
               .formLogin()
               .successHandler((request , response , authentication) -> {
                   RequestCache requestCache = new HttpSessionRequestCache();
                   SavedRequest savedRequest = requestCache.getRequest(request , response);
                   response.sendRedirect(savedRequest.getRedirectUrl());
               });

       http
               .exceptionHandling()
//               .authenticationEntryPoint((request , response , e) -> {
//                   response.sendRedirect("/login");
//
//               })
               .accessDeniedHandler((request , response , e) -> {
                    response.sendRedirect("/denied");
               });

    }
}
