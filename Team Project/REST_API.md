# REST API 명세
#### 프로젝트 주제 : 야구 정보 공유 커뮤니티

- 총 10개의 구단 별 화면 구현 (/{teamName})
- 10개의 구단 별 3개의 게시판 구현 예정<br><br>
  1. 정보 공유 게시판 (/information)
  2. Q&A 게시판 (/question)
  3. 잡담 게시판 (/talk)
<br><br>

- {teamName} : 10개의 구단 이름
- {postType} : information / question / talk 중 하나가 들어감
<br>

#### 게시판 관련

| Method | URI | Description |
|:------:|----:|------------:|
|**GET**| /{teamName}/{postType}|게시물 목록 조회|
|**GET**| /{teamName}/{postType}/{post_id}|게시물 내용 조회|
|**GET**| /{teamName}/{postType}/create|게시물 생성 화면 띄우기|
|**POST**| /{teamName}/{postType}|게시물 등록|
|**GET**| /{teamName}/{postType}/{post_id}/edit|게시물 수정 화면 띄우기|
|**POST**| /{teamName}/{postType}/{post_id}/update|게시물 수정|
|**GET**| /{teamName}/{postType}/{post_id}/delete|게시물 삭제|
|**GET**| /{teamName}/{postType}/{post_id}/scrap|게시물 스크랩|
|**GET**| /{teamName}/{postType}/{post_id}/comment|댓글 등록 창 띄우기|
|**POST**| /{teamName}/{postType}/{post_id}/comment|댓글 등록|
|**GET**| /{teamName}/{postType}/{post_id}/{comment_id}/edit|댓글 수정 화면 띄우기|
|**POST**| /{teamName}/{postType}/{post_id}/{comment_id}/update|댓글 수정|
|**GET**| /{teamName}/{postType}/{post_id}/{comment_id}/delete|댓글 삭제|

#### 사용자 관련

| Method | URI | Description |
|:------:|----:|------------:|
|**GET**| /login|로그인 화면 띄우기|
|**GET**| /signup|회원가입 화면 띄우기|
|**POST**| /login|로그인|
|**POST**| /signup|회원가입|
|**GET**| /mypage|마이페이지 이동|
|**GET**| /mypage/edit|유저 정보 수정 화면 띄우기|
|**POST**| /mypage/update|유저 정보 수정|
|**GET**| /logout|로그아웃|
|**GET**| /withdrawal|회원 탈퇴|
