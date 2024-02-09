# seoul-wifi-open-api
JSP로 OPEN API(서울특별시_공공와이파이)를 활용하여 가까운 위치 정보를 제공합니다.

---
### :cd: 개발환경
- JAVA 11
- IDE : Eclipse
- OS : Windows10
- DB : MariaDB 10.x
- Server : Tomcat 10.0
- ERD : Draw.io
- CSS : BootStrap 3.3

---
### :heavy_exclamation_mark: 활용 전 주의 사항
[https가 아니라 이슈 발생 조치 방법 (localhost는 괜찮음)]
1. chrome://flags/#unsafely-treat-insecure-origin-as-secure 크롬에서 입력
2. http://www.yezzang.pe.kr:1005 입력 (자신의 url) - enabled


---
### :clock10: 프로젝트 활용 순서
1. 간단한 회원가입을 진행합니다.
2. 회원가입한 정보로 로그인 합니다.
3. 나의 위치를 조회합니다.
4. 근처 와이파이 정보를 봅니다.
5. 북마크에 추가할 수 있습니다.

---
### :round_pushpin: Eclipse library 추가 방법
1. src > main > webapp > WEB-INF > lib 경로에 mariadb, org.json library 다운 후 넣기
2. FILE - PROPERTIES - LIBRARY'S - CLASSPATH - ADD JARS - 넣었던 library 추가


---
### :fireworks: 배포 방법
1. Eclipse 프로젝트 우클릭 - export - war
2. Destination에 생성될 위치 경로 정하기
3. ROOT.war로 이름 바꾸고 서버로 배포!

---
### :blue_book: ERD
![readme_io](https://github.com/kimyezzang97/seoul-wifi-open-api/assets/114374243/9e5f8d4b-f2b6-43ee-904c-72d6c1c80f27)


---
### :movie_camera: 시연연상
