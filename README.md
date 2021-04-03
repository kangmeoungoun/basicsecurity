### 스프링 시큐리티 주요 아키텍처 이해
#### 11) 스프링 시큐리티 필터 및 아키텍처 정리

##### 전반적인 정리
1. 두개의 설정 클래스를 만들었고 초기화 될때 각각의 필터들이 생성되고 필터가 webSecurity 에 전달되고
    FilterChainProxy 생성하면서 생성자로 filters 전달하게 된다
2. FilterChainProxy 는 각각의 Filter 목록을 가지고 있다.
3. DelegatingFilterProxy 가 SpringSecurityFilterChain 이라는 이름의 빈 을 찾는데 그 빈이 FilterChainProxy 이다.
4. 그래서 DelegatingFilterProxy 가 FilterChainProxy 에게 요청을 위임하도록 그 준비를 초기화 과정속에서 진행하게 된다.

사용자 요청 인증시도
5. FilterChainProxy 가 filters 목록을 가지고 있어서 사용자 요청을 각각의 필터들에게 그요청을 처리하도록 호출한다 순서대로
6. SecurityContextPersistenceFilter(1번 필터) SecurityContext 객체를 생성하고 세션에 저장하고 세션에 저장된 SecurityContext
조회하고 참조하는 역할을 한다.SecurityContext 만들어서 SecurityContextHolder 에 저장
   
7. LogoutFilter(2번 필터) 로그아웃 요청을 하지 않으면 건너뛴다.
8. UsernamePasswordAuthenticationFilter(3번 필터) 아이디 패스워드 를 받아서 인증객체를 만든다.
AuthenticationManager 에게 인증처리를 맡긴다. 매니저가 AuthenticationProvider 에게 위임한다.  
provider 가  UserDetailService 클래스를 활용해서 아이디 패스워드 검증 인증 성공하면 인증객체를 만든다.
SecurityContextHolder 안의 SecurityContext 의 인증객체를 저장.
   
9. SessionManagementFilter(7번 필터) 인증에 성공하게 되면 후속처리로 이전에도 동일한 사용자가 존재했는지 확인
2가지 전략 으로 접근 동일한 계정으로 세션최대개수가 1개이면 현재사용자의 인증시도를 차단할것인가 이전사용자 세션을 만료할것인지를 결정
   
10. 인증성공후 일반적으로 successHandler 에서 후속작업처리할때 보통은 그다음페이지(root)페이지 로 이동

11. 다시금 SecurityContextPersistenceFilter(1번필터)에서  응답직전에 세션에 인증객체를 저장

인증시도가 아닌 루트 자원 접근
12. SecurityContextPersistenceFilter(1번필터) 가 요청을 받고 지금 접속한 사용자가 사용자 세션에 securityContext 를 저장한 적이 있는지
확인 세션에 저장학 이력이 있어서 세션에서 꺼내온다. SecurityContextHolder 에 저장 새로 SecurityContext 를 생성하지 않는다.
    
13. ConcurrentSessionFilter(4번 필터) 동일한 계정으로 2명이상 접속했을때 동작한다.

14. RememberMeAuthenticationFilter(5번 필터) 현재 사용자가 세션이 만료 되거나 세션이 무효화되어서 그 세션안에 있는 SecurityContext 인증객체가 null
일경우 동작 현재 사용자가 요청 헤더에 remember-me 쿠키값을 저장한 상태로 왔을때
remember-me 기능을 활성화 하고 인증을 받으면 동작
    
15. AnonymousAuthenticationFilter(6번필터) 어떤 사용자가 인증 시도도 하지 않고 권한도 없이 어떤 자원에 다이렉트로 접속을 시도한 경우
이러한 경우 동작 AnonymousAuthenticationToken 을 만들어서 SecurityContext 에 인증 객체 저장
그래서 현재 접근한 사용자가 익명사용자 인지 인증된사용자 인지 구별할수있다.
    
16. SessionManagementFilter(7번 필터) 세션이 없거나 SecurityContext 에 인증객체가 null 인경우 동시성세션 체크 한다.

17. ExceptionTranslationFilter(8번 필터) FilterSecurityInterceptor(9번 필터) 이 두개 필터가
인증이후 자원 접근했을때 가장 큰 역할을 한다.
    
18. ExceptionTranslationFilter  가 try catch 로 감싸서 FilterSecurityInterceptor 을 호출한다.
    ExceptionTranslationFilter 는 예외만 체크한다.
    
19. FilterSecurityInterceptor 인가 처리하는 필터 현재 접속하는 사용자가 SecurityContext 에 인증객체가 존재하는지 
없으면 예외처리 인증 받은 사용자면 인가처리 최종적으로 접근하자고 하는 자원에 대해 승인 거부 판단한다.
거부대면 예외처리
    
첫번째와 동일한 계정으로 두번째 사용자가 인증을 시도한 경우
20. 인증 처리후 SessionManagementFilter 에서 세션 최대 허용개수가 1개여서 인증시도 차단 또는 이전 사용자 만료처리를 한다.
21. 이전 사용자 세션 만료인 경우 루트페이지로 이동하면서 첫번째 사용자와 같은 루트를 탄다.
22. 첫번째 사용자가 다시금 접속할때 ConcurrentSessionFilter(4번필터) 로 오게 된다. 
매요청 마다 현재사용자가 세션이 만료 되었는지 확인한다. 첫번째 사용자가 세션이 만료가 되어서 로그아웃 처리 를 하고
오류 처리 한다.

![image](https://user-images.githubusercontent.com/40969203/113481509-eae84400-94d4-11eb-98b3-1fc7832aacea.png)

    