### 스프링 시큐리티 기본 API 및 Filter 이해
#### 5) Logout 처리, LogoutFilter

* 스프링은 기본적으로 post 방식으로 로그아웃 한다. get으로 하면 오류발생.하지만 get방식으로도 로그아웃 처리할수 있다.

1. LogoutFilter 에서 인증 객체를 꺼내온다.CompositeLogoutHandler(5개의 핸들러를 가지고 있다) 로그아웃 처리
2. 세션을 무효화 한다던지 쿠키를 제거 한다던지
```java
   SecurityContext context = SecurityContextHolder.getContext();
   context.setAuthentication((Authentication)null);
```
3. this.logoutSuccessHandler 우리가 만든 핸들러를 호출해준다.
```java
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

//.addLogoutHandler 핸들러는 this.handler 의 로그아웃핸들러 에서 처리
//.logoutSuccessHandler this.logoutSuccessHandler 핸들러가 처리

```
* 핸들러에서 우리가 처리해야될 로직을 구현하면 된다.

![image](https://user-images.githubusercontent.com/40969203/112825501-c8b28880-90c6-11eb-84fd-d0d14588d170.png)
