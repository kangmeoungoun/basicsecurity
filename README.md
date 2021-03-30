### 스프링 시큐리티 기본 API 및 Filter 이해
#### 10) 세션 제어 필터 SessionManagementFilter,ConcurrentSessionFilter

* user 1 이 로그인 하고 user2 가 같은계정으로 로그인했을때 일단 가정으로(맥스세션 1개고 세션만료 전략인경우)  
user2 가 인증을 마친상태에서 user1 의 세션을 만료시킨다 하지만 현재 등록된 세션 카운트는 2개  
  user1 이 다시 접속했을때 세션만료 체크하고 만료가 되어서 로그아웃 처리 세션 레지스트리에는 계속 쌓인다. 만료 플래그가 true 로 되어있다.


* user1 이 접속할때  
```java
ConcurrentSessionControlAuthenticationStrategy.allowableSessionsExceeded();
현재 내 계정으로 인증된사용자가 있는지 확인하고 맥시멈세션 이랑 비교 세션만료전략 또는 로그인 막는 전략 에 따른 처리 를 한다.
user1 은 처음 접속했기때문에 전부 통과되고 세션 레지스트리에 세션 저장 

SessionRegistryImpl.class 
private final Map<String, SessionInformation> sessionIds;
세션 put 한다.이떄 key 값 sessionid

```
* uesr2 가 접속할때(같은계정으로)
```java
ConcurrentSessionControlAuthenticationStrategy.allowableSessionsExceeded();
세션 만료 전략인 경우 에는 세션 만료 처리
로그인을 막는 전략인 경우에는 예외를 던진다.

```

* user1 이 접속할때(세션만료 전략)
```java
ConcurrentSessionFilter.doFilter()
에서 세션아이디로 레지스트리에 등록된 세션인포메이션을 꺼내온뒤 만료여부를 확인한다.
만료되었을 경우 로그아웃 처리

```