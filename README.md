### created by yapyap

- 최종업데이트 2025. 04. 07

### 사용방법

1. Github에서 소스코드를 fork or copy

2. IntelliJ에서 프로젝트 실행

3. application.yml 에서 포트번호를 변경 가능

4. 포트번호 변경을 하지 않았다면, 브라우저에서 http://localhost 주소 실행

5. 게시판 확인

6. Postman에서 API 테스트 용도로 확인 하고 싶다면,

7. /api/board 로 조회

   - GET (getBoardList) : localhost/api/board/list
   - GET (getBarodListByPage&Size) : localhost/api/board/list?page=2&size=10
   - GET (getBoardDetailById) : localhost/api/board/list/7
   - POST (addNewBoard) : localhost:8282/api/board/
   - PUT (updateBoardById) : localhost/api/board/update/6
   - DELETE (deleteBoardById) : localhost:8282/api/board/list/9
  
### 기능상 특징

- page navigation 기능 삽입

- file-base 형태로 h2 db 생성

- 80 포트로 URL에서 포트 번호 제거

- RESTful API의 CRUD 기능 확인 가능

- API 기능 사용 후 웹페이지에서 동작 확인 가능

### 업데이트 예정 (계획만...)

- 로그인 기능 추가
