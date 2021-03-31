### 스프링 시큐리티 기본 API 및 Filter 이해
#### 13) 사이트간 요청 위조 - CSRF, CsrfFilter

##### CSRF(Cross Site Request Forgery)

* 공격방법
```html
<form action="http://facebook.com/api/content" method="post">
    <input type="hidden" name="body" value="여기 가입하면 돈 10만원 드립니다." />
    <input type="submit" value="Click Me"/>
</form>
사용자가 페이스북에 로그인이 된 상태에서 이 html 메일발송 사용자 클릭.
```

* 방어기법  
1.Referrer 검증  
2.Security Token 사용(CSRF Token)
  (POST, PATCH, DELETE..  GET메소드는 제외)
```java

CsrfToken csrfToken = this.tokenRepository.loadToken(request);
        boolean missingToken = csrfToken == null;
        if (missingToken) {
            csrfToken = this.tokenRepository.generateToken(request);
            this.tokenRepository.saveToken(csrfToken, request, response);
        }

HttpSessionCsrfTokenRepository 구현체에서 토큰 정보를 가지고 온다 세션에서 가지고 온다.없으면 저장
그다음에 파라미터 또는 헤더에서 csrf토큰 값이랑 같은지 비교 
```  
