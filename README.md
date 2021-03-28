### 스프링 시큐리티 기본 API 및 Filter 이해
#### 2) 사용자 정의 보안 기능 구현

* WebSecurityConfigurerAdapter 이 클래스는 웹보안 기능 초기화 하고 설정을 하는 클래스
  이 클래스가 HttpSecurity 라는 클래스를 생성 HttpSecurity 이클래스는 세부적인 보안 기능을 설정할수 있는 api 제공
  WebSecurityConfigurerAdapter,HttpSecurity 이 두개의 클래스가 웹보안 기능을 활성화 하고 보안 기능을 작동하게끔
  하는 역할을 한다.
  
* @EnableWebSecurity 어노테이션 활성화 해야 웹보안 활성화 된다.
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
}
```
##### 시큐리티 기본 user 생성과정
* SecurityConfig 에서 별도의 설정을 하지 않는다면 application.properties 에서 읽어드려 계정을 생성하고 application.properties 에도 아무런 설정을 하지 않는다면  
  스프링 시큐리티가 SecurityProperties 클래스에 기본적으로 생성되는 user 정보를 가지고 계정을 제공합니다.
```properties
spring.security.user.name=user
spring.security.user.password=1111
```

![image](https://user-images.githubusercontent.com/40969203/112751906-53ce4880-900b-11eb-8ed6-50329d3d295e.png)



  