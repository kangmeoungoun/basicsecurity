### 스프링 시큐리티 기본 API 및 Filter 이해
#### 6) Remember Me 인증

* 서버가 Remember me 활성화 상태에서 실행하면 서버가 응답헤더에 Remember me 쿠키 응답해준다.
* RememberMeAuthenticationFilter 에서 진행된다.
* Remember me 쿠기값 추출해서 userid 패스워드 추출 해서 user 객체를 얻고
  다시 인증객체를 다시 만들어서 리턴해준다.

![image](https://user-images.githubusercontent.com/40969203/112835365-24cfd980-90d4-11eb-80b6-29362b51afdf.png)
![image](https://user-images.githubusercontent.com/40969203/112835383-2a2d2400-90d4-11eb-9a90-22d2b3c121e8.png)
