# OAuth 권한 부여 방식 - 1
 - 권한 부여 승인 코드 방식
 - 암묵적 승인 방식

정리자: 정준

https://charming-kyu.tistory.com/36

## 1. Authorization Code Grant │ 권한 부여 승인 코드 방식

권한 부여 승인을 위해 자체 생성한 Authorization Code를 전달하는 방식으로 많이 쓰이고 기본이 되는 방식입니다.    
간편 로그인 기능에서 사용되는 방식으로 클라이언트가 사용자를 대신하여 특정 자원에 접근을 요청할 때 사용되는 방식입니다.   
보통 타사의 클라이언트에게 보호된 자원을 제공하기 위한 인증에 사용됩니다. Refresh Token의 사용이 가능한 방식입니다.   

![image](https://github.com/2020134032/YC-Tech-Academy/assets/128214994/a46c80b5-7ba2-4373-a63f-f4343bc74037)   
 
권한 부여 승인 요청 시 response_type을 code로 지정하여 요청합니다.   
이후 클라이언트는 권한 서버에서 제공하는 로그인 페이지를 브라우저를 띄워 출력합니다.   
이 페이지를 통해 사용자가 로그인을 하면 권한 서버는 권한 부여 승인 코드 요청 시 전달받은 redirect_url로 Authorization Code를 전달합니다.    
Authorization Code는 권한 서버에서 제공하는 API를 통해 Access Token으로 교환됩니다.   

## 2. Implicit Grant │ 암묵적 승인 방식
자격증명을 안전하게 저장하기 힘든 클라이언트(ex: JavaScript등의 스크립트 언어를 사용한 브라우저)에게 최적화된 방식입니다.

![image](https://github.com/2020134032/YC-Tech-Academy/assets/128214994/fed4b40b-dd00-4d45-8a36-5c837d9977bf)


암시적 승인 방식에서는 권한 부여 승인 코드 없이 바로 Access Token이 발급 됩니다. Access Token이 바로 전달되므로 누출의 위험을 방지하기 위해 만료기간을 짧게 설정하여 전달됩니다.

Refresh Token 사용이 불가능한 방식이며, 이 방식에서 권한 서버는 client_secret를 사용해 클라이언트를 인증하지 않습니다. Access Token을 획득하기 위한 절차가 간소화되기에 응답성과 효율성은 높아지지만 Access Token이 URL로 전달된다는 단점이 있습니다.

Authorization Grant: Implicit

권한 부여 승인 요청 시 response_type을 token으로 설정하여 요청합니다. 이후 클라이언트는 권한 서버에서 제공하는 로그인 페이지를 브라우저를 띄워 출력하게 되며 로그인이 완료되면 권한 서버는 Authorization Code가 아닌 Access Token를 redirect_url로 바로 전달합니다.
