### 스프링 시큐리티 기본 API 및 Filter 이해
#### 7) Remember Me 인증 필터 :RememberMeAuthenticationFilter

RememberMeAuthenticationFilter 사용되어지는 경우
  - 인증객체가 없는 경우 
  - 사용자가 Remember-me 쿠기를 가지고 있는 경우


##### Remember 파라미터 를 전달해서 로그인 시도 하면 TokenBasedRememberMeServices.onLoginSuccess() 로 전달

1. 포스트맨으로 테스트시 jseeionId 는 지우고 Remember-me 만 쿠키 전달 해서 테스트
2. 인증객체를 만들고 난뒤에는 form 로그인 방식과 유사하다.

![image](https://user-images.githubusercontent.com/40969203/112840955-e558bb80-90da-11eb-9440-903d513738e7.png)
![image](https://user-images.githubusercontent.com/40969203/112840996-ef7aba00-90da-11eb-81cd-b0b35cb9d8db.png)
