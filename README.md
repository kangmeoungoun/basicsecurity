### 스프링 시큐리티 주요 아키텍처 이해
#### 8) 인증 처리자 : AuthenticationProvider

##### AuthenticationProvider 역할
1. 인증객체를 전달받고 시스템에 저장된 사용자 계정 과 일치하는지 검증
2. authenticate()  메소드에서 id 검증,password 검증, 추가검증 
    - id 검증 은 UserDetailService 에서 실질적으로 데이터 계층 조회 있으면 user 객체 생성 후 UserDetails 로 타입변환후 리턴
    - password 검증은 UserDetails 리턴받은 저장된 패스워드를 가지고 오고 로그인시 패스워드 비교 
        - 패스워드 인코더를 통해 password 가 저장되어서 패스워드 인코더 를 통해 로그인시 입력한 패스워드를 인코딩 후 비교
    
3. Authentication 객체를 생성후 생성된 객체를 AuthenticationManager 로 리턴

![image](https://user-images.githubusercontent.com/40969203/113476131-73a2b800-94b4-11eb-8399-64b86a2a2b66.png)
