### 스프링 시큐리티 기본 API 및 Filter 이해
#### 3) Form Login 인증 필터 : UsernamePasswordAuthenticationFilter

* UsernamePasswordAuthenticationFilter : 사용자가 로그인 하게 되면 인증처리를 담당하고 인증처리에 관련된 요청을 처리하는 필터
* SecurityContext : 인증된객체(User+Authorities) 를 SecurityContext 에 저장. 나중에는 세션에도 저장 전역적으로 사용자가
  SecurityContext 안에서 인증객체를 참조 할수 있다.
  
* FilterChainProxy : 모든 요청을 필터들에게 전달하면서 인증인가 처리 한다.


1. AbstractAuthenticationProcessingFilter 에서 "/login_proc" 해당 url 로 접근했는지 파악 /login_proc 로 
   접근했으면 AbstractAuthenticationProcessingFilter 의 자식인 UsernamePasswordAuthenticationFilter의 메소드 attemptAuthentication 로 이동
2. attemptAuthentication()에서 인증객체를 생성후 AuthenticationManager.ProviderManager.authenticate()메소드로 위임 한다.

3. 인증이 성공하면 DaoAuthenticationProvider 에서 인증객체를 다시 만든후 리턴해준다.

4. AbstractAuthenticationProcessingFilter 다시 새로만든 인증객체를 리턴받은후
   SecurityContextHolder.getContext().setAuthentication(인증객체) 를 저장한다.

![image](https://user-images.githubusercontent.com/40969203/112754809-c8f44a80-9018-11eb-8d2d-c7843c1f69a3.png)
