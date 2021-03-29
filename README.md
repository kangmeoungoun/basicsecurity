### 스프링 시큐리티 기본 API 및 Filter 이해
#### 8) 익명사용자 인증 필터 : AnonymousAuthenticationFilter

* 별도의 익명사용자용 인증 객체를 만든다.
* 인증객체가 없으면 익명 인증객체 생성   

SecurityContextHolder.getContext().getAuthentication().getPrincipal()
"anonymousUser" 문자열을 만든다.

* AnonymousAuthenticationFilter는 현재 접속한 사용자가 인증을 받지 않았으면 익명사용자로 판단하여
  익명사용자 토큰을 만들어서 인증된 사용자와 의 구분을 하기위한 용도

![image](https://user-images.githubusercontent.com/40969203/112844922-38cd0880-90df-11eb-95a0-48162b841bc3.png)
