## 💻JAVA SPRING FRAMEWORK실습

#### 목표 : 로그인 / 회원가입 기능
* MySQL 연결 ✅
* salt+sha256을 통한 비밀번호 암호화 ✅
* 암호화된 비밀번호를 통한 회원인증

#### DB구조
* 저장변수 : id, name, email, password, salt
* password : salt값과 사용자가 지정한 비밀번호를 조합하여 암호화된 상태로 저장

#### Spring 작동구조
    > https://www.notion.so/Spring-Boot-0a54e3c1d3164073a87cf1e93927c74d
