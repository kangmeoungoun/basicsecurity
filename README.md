### 스프링 시큐리티 주요 아키텍처 이해
#### 4) 인증저장소 - SecurityContextHolder, SecurityContext

* SecurityContext 세션에도 저장이 된다. 인증된 사용자가 인증 이후에 사이트 접속할때는
실제로 세션에 저장된 SecurityContext 객체를 가지고 와서 그 객체를 ThreadLocal 에 저장 하는 식으로 처리가 되고 있다.
  

* 자식 쓰레드까지 전파해서 공유
```java
SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
```

![image](https://user-images.githubusercontent.com/40969203/113466816-8ea40680-9479-11eb-9778-88604ed9332b.png)
![image](https://user-images.githubusercontent.com/40969203/113466819-9a8fc880-9479-11eb-89cd-4dd68fd313a5.png)
