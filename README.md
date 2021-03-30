### 스프링 시큐리티 기본 API 및 Filter 이해
#### 12) 예외 처리 및 요청 캐시 필터 : ExceptionTranslationFilter, RequestCacheAwareFilter

* FilterSecurityInterceptor 가 필터 가장 마지막에 존재 AuthenticationException,AccessDeniedException 던진다
* FilterSecurityInterceptor 전이 ExceptionTranslationFilter 필터인데 try catch 안에서 FilterSecurityInterceptor 예를 호출해서 
  예외를 받는다.
  
* authenticationEntryPoint 인증 예외
* accessDeniedHandler 인가 예외

* 인증을 하지 않았을때 동장방식
```java
ExceptionTranslationFilter.class 
익명사용자가 접근을 해서 AccessDeniedException 인가 예외로 떨어진다.
하지만 익명사용자 이기 때문에 인증예외때 처리하는 곳으로 들어온다.
인증객체 null 처리 하고

this.requestCache.saveRequest(request, response); 사용자가 접근하고자 했던 정보를 저장
this.authenticationEntryPoint.commence(request, response, reason); 
내가 SecurityConfig에서 설정해줬던 함수 호출
```

* 인가되지 않았을때 동작방식
```java
ExceptionTranslationFilter.class
AccessDeniedException 으로 떨어지고
this.accessDeniedHandler.handle(request, response, exception);
내가 SecurityConfig에서 설정해줬던 함수 호출
```
* 인증하고 이전 에 요청한 페이지 로 이동할때 동장방식
```java
로그인 successHandler 에서 처리 해준다.
RequestCache requestCache = new HttpSessionRequestCache();
SavedRequest savedRequest = requestCache.getRequest(request , response);
response.sendRedirect(savedRequest.getRedirectUrl());

RequestCacheAwareFilter 에서
이전에 세션에 저장했던 this.requestCache.saveRequest(request, response) 
request 를 꺼내온후 그 request 로 doFilter 을 한다.


맨처음 url /login 으로 요청하면 permitAll 이여서 예외를 발생하지 않아서 this.requestCache.saveRequest(request, response); 저장되지 않는다.
RequestCache requestCache = new HttpSessionRequestCache();
SavedRequest savedRequest = requestCache.getRequest(request , response);
response.sendRedirect(savedRequest.getRedirectUrl());
그래서 여기서 savedRequest null 이 된다 예외처리를 해줄 필요가 있다.
```

![image](https://user-images.githubusercontent.com/40969203/113008024-a8132d00-91b1-11eb-8f8a-701bf60d6714.png)
![image](https://user-images.githubusercontent.com/40969203/113008050-ac3f4a80-91b1-11eb-9da9-647c503ac94c.png)
![image](https://user-images.githubusercontent.com/40969203/113008365-edcff580-91b1-11eb-95e0-f3fa7a1ea61c.png)
