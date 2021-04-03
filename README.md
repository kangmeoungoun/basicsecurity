### 스프링 시큐리티 주요 아키텍처 이해
#### 7) 인증 관리자 : AuthenticationManager


##### ProviderManager 역할
1. ProviderManager 는 인증 필터로 부터 인증처리를 지시 받는데 그때 인증객체를 전달받는다.
2. ProviderManager 그 인증객체를 다시 해당 인증을 처리할수 있는 AuthenticationProvider 을 찾아서 인증객체를 전달
3. 자기가 가지고 있는 해당 인증을 처리할수 있는 Provider 가 없으면  자기 속성의 parent ProviderManager 를 탐색
4. 부모 ProviderManager 가 현재 인증을 처리할수 있는 Provider 가 있으면 그 Provider 에게 인증 처리를 맡긴다.
5. ProviderManager 에서 인증 실패하면 예외처리 
6. 인증 성공하면 인증객체를 자기를 호출할 filter 에게 전달

#### AuthenticationManagerBuilder 초기화
1. AuthenticationManager 클래스 객체를 생성 
2. AuthenticationProvider 를 추가 할수 도 있음.
```java
ProviderManager providerManager = new ProviderManager(this.authenticationProviders, this.parentAuthenticationManager);
DaoAuthenticationProvider 를 포함하고 부모는 없는 providerManager 를 만든다
ProviderManager providerManager = new ProviderManager(this.authenticationProviders, this.parentAuthenticationManager);
이번에는 AnonymousAuthenticationProvider 를 포함하고 부모는 이전에 만들었던 providerManager 가 된다.

ProviderManager 를 2개만든다 하나는 일반 Default 하나는 부모용 ProviderManager 를 만든다.
```
```java
@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        String password = "{noop}1111";
        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
        auth.inMemoryAuthentication().withUser("sys").password(password).roles("SYS");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN","SYS","USER");
        auth.inMemoryAuthentication().withUser("test").password(password).roles("ADMIN","SYS","USER");
    }
내가 SecurityConfig 에 설정 한 user 들의 개수 만큼 DaoAuthenticationProvider 가 생성된다.
```
###### USER 를 저런식으로 생성하는것 말고 DB 에 있는 USER 를 사용할때 DaoAuthenticationProvider 가 어떻게 생성되는지 확인해봐야 겠다.

![image](https://user-images.githubusercontent.com/40969203/113474820-3981e800-94ad-11eb-984e-628f6cbaf9f3.png)
