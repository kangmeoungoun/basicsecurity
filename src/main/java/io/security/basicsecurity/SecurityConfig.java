package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.CompositeLogoutHandler;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //인가 정책
        http
                .authorizeRequests()
                .anyRequest().authenticated();
        //인증정책
        http
                .formLogin()
                //.loginPage("/loginPage")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("passwd")
                .loginProcessingUrl("/login_proc")
                .successHandler((request , response , authentication) -> {
                    System.out.println("authentication : "+authentication.getName());
                    response.sendRedirect("/");
                })
                .failureHandler((request , response , e) ->{
                    System.out.println("exception : "+e.getMessage());
                    response.sendRedirect("/login");
                })
                .permitAll()
        ;

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler((request , response , authentication) -> {
                    HttpSession session = request.getSession();
                    session.invalidate();

                })
                .logoutSuccessHandler((request , response , authentication) -> {
                    response.sendRedirect("/login");
                })
                .deleteCookies("remember-me")
        ;

    }
}
