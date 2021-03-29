### 스프링 시큐리티 기본 API 및 Filter 이해
#### 9) 동시 세션 제어, 세션 고정 보호, 세션 정책

##### 동시 세션 제어
1. 최대세션 허용개수 초과 했다고 가정 허용개수는 동일한 계정으로 생성되는 세션의 허용개수는 1개라고 가정
2. 동일한 계정의 두번째 사용자가 접속할 경우 이전 사용자 세션 만료
3. 동일한 계정의 두번째 사용자가 접속할 경우 현재 사용자 인증 실패 (인증 예외 발생)

##### 세션 고정 보호
1. 인증에 성공할때마다 세로운 세션 아이디 발급
2. 테스트 시 .sessionFixation().none(); 으로 하고 브라우저 로그인 화면 까지만 접속후 세션아이디 취득
3. 그 세션아이디로 포스트맨 으로 로그인 처리 요청 후 성공확인
4. 브라우저 에서 루트 요청시 로그인 하지 않았는데도 로그인 성공

##### 세션 정책
1. sessionCreationPolicy(SessionCreationPolicy.STATELESS)  JWT 를 사용할때 사용

##### Session 과 Authentication 차이점
인증관련 관계도를 보면 이렇습니다.  
_Session > SecurityContext > Authentication > UserDetials 입니다._  
즉 사용자가 로그인 후 인증을 받게 되면 인증정보를 UserDetials 에 담고  
UserDetials 는 Authentication 에 담고  
Authentication 은 SecurityContext 에 담고  
SecurityContect 는 Session 에 담는 식으로 처리 됩니다.

![image](https://user-images.githubusercontent.com/40969203/112855473-cb72a500-90e9-11eb-817f-0e299333dc89.png)
![image](https://user-images.githubusercontent.com/40969203/112855495-d0375900-90e9-11eb-96e9-c81728bc8822.png)
![image](https://user-images.githubusercontent.com/40969203/112855511-d4fc0d00-90e9-11eb-869f-195659c3a533.png)
![image](https://user-images.githubusercontent.com/40969203/112855572-e6ddb000-90e9-11eb-846c-be52dbed0279.png)
![image](https://user-images.githubusercontent.com/40969203/112855604-ed6c2780-90e9-11eb-84a0-e745bd28ce77.png)


