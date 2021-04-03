### 스프링 시큐리티 주요 아키텍처 이해
#### 5) 인증 저장소 필터 - SecurityContextPersistenceFilter

* SecurityContextPersistenceFilter 최종 응답시 
```java
SecurityContextHolder.clearContext()
SecurityContext를 삭제한다 매 요청 후에 삭제
SecurityContextHolder 안에 매요청마다 SecurityContext를 저장 하기때문에
```
* SecurityContextPersistenceFilter 역할
```
익명사용자나 인증시에는 SecurityContextHolder 안에 새루운 SecurityContext를 저장
인증후에는 세션에서 SecurityContext를 꺼내어 SecurityContextHolder에 저장
그후 SecurityContext는 TrheadLocal 에 저장되 어 있어서 
SecurityContextHolder.getContext() 에 바로 참조 가능.
```
![image](https://user-images.githubusercontent.com/40969203/113468441-34a73f00-9481-11eb-935b-6ecc3e7408f5.png)
![image](https://user-images.githubusercontent.com/40969203/113468446-3a048980-9481-11eb-8679-8ee34b6b63d7.png)
![image](https://user-images.githubusercontent.com/40969203/113468450-44bf1e80-9481-11eb-85ec-d1b3bb378eae.png)
![image](https://user-images.githubusercontent.com/40969203/113468472-6b7d5500-9481-11eb-9122-4fb3f6008ca4.png)
