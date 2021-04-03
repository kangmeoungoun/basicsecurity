### 스프링 시큐리티 주요 아키텍처 이해
#### 6) 인증 흐름 이해 - Authentication FLow

##### 인증의 흐름
1. 로그인 요청을 한다.(폼인증 방식)
2. UsernamePasswordAuthenticationFilter 요청을 받아서 Authentication(id+pass) 담은 인증객체 생성
3. AuthenticationManager 에게 인증 처리를 맡긴다 생성한 인즉객체를 전달하면서(여기까지가 UsernamePasswordAuthenticationFilter 1차적으로 인증처리를 하는 단계)
4. AuthenticationManager 필터로 부터 받은 인증객체를 전달 받는다.
    - 인증의 전반적인 관리를 한다.
    - 실제 인증 역할은 하지 않는다.
    - AuthenticationProvider 에 위임함다.
    
5. AuthenticationManager 는 AuthenticationProvider 을 담는 List 가 존재 하는데 현재 인증에 사용될수 있는 AuthenticationProvider 를 찾아서  
    그 AuthenticationProvider 에 인증처리를 위임한다. 이때 인증객체를 전달해준다.
   
6. AuthenticationProvider 가 실질적으로 id 와 pw 검증 한다. 
7. AuthenticationProvider 는 loadUserByUsername(username) 을 호출하면서 User 객체를 요청한다. 여기서 username 은 아이디
8. UserDetailsService 클래스에다가 사용자 user 객체 요청 DB 검증
9. 검증이 실패하면 예외를 던저서 UsernamePasswordAuthenticationFilter 가 받아서 fail핸들러 처리
10. 검증이 성공하면 UserDetails 타입으로 반환 
11. AuthenticationProvider 가 받아서 Authentication(UserDetails + authorities) 를 담은 인증객체를 생성해서
    AuthenticationManager 에게 전달
    
12. AuthenticationManager 는 전달받은 Authentication 인증객체를 다시  UsernamePasswordAuthenticationFilter 에게 전달
13. UsernamePasswordAuthenticationFilter 는 전달받은 Authentication 을 SecurityContext 에 저장한다.

![image](https://user-images.githubusercontent.com/40969203/113472346-a4c3be00-949d-11eb-9657-5b391ceadffc.png)
