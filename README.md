### 스프링 시큐리티 주요 아키텍처 이해
#### 1) 위임 필터 및 필터 빈 초기화 - DelegatingProxyChain, FilterChainProxy

* DelegatingProxy 서블릿 필터이기 때문에 가장먼저 요청받게 되고 그 요청을 스프링필터에게 전달한다.
* SpringSecurityFilterChain 이름을 가진 빈이 FilterChainProxy 이다.


- 빈 등록되는 과정
    - DelegatingFilterProxy 이 생성되고 SpringSecurityFilterChain 이름을가진 FilterChainProxy 찾는다.
    
    - SecurityFilterAutoConfiguration 여기서 DelegatingFilterProxy 필터를 등록한다. 
    - DelegatingFilterProxy 생성자에 SpringSecurityFilterChain 이름 셋팅
    - WebSecurityConfiguration springSecurityFilterChain 이 이으로 필터 빈 등록
    - 이떄 이 빈이 FilterChainProxy 이다. WebSecurity 에서 FilterChainProxy 생성후 리턴해준다.
    
- 실행과정
    - DelegatingFilterProxy 요청을 먼저 받고 SpringSecurityFilterChain 이름으로 등록된 Bean 을 리턴받아서 위임한다.
    - 그 대상이 FilterChainProxy 이다.


![image](https://user-images.githubusercontent.com/40969203/113156012-92b30700-9274-11eb-8248-265c5ff8f8e1.png)
![image](https://user-images.githubusercontent.com/40969203/113156040-9777bb00-9274-11eb-830b-4a25c53db133.png)
![image](https://user-images.githubusercontent.com/40969203/113156081-9f375f80-9274-11eb-9961-2fad64303aef.png)
