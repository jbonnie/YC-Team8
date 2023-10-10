# REST API 명세
#### 프로젝트 주제 : 야구 정보 공유 커뮤니티

- 3개의 게시판 구현 예정<br><br>
  1. 정보 공유 게시판 (/information)
  2. Q&A 게시판 (/question)
  3. 잡담 게시판 (/talk)
<br><br>

| Index | Resource | Method | URI | Description |
|:-----:|:--------:|:------:|----:|------------:|
|1|user|POST| /users|사용자 등록|
|2|user|GET| /users|사용자 조회|
|3|user|PUT| /users|사용자 정보 수정|
|4|user|DELETE| /users|사용자 삭제|
|5|information|GET| /informations|게시물 목록 조회|
|6|information|POST| /informations|게시물 등록|
|7|information|PUT| /informations/{post_id}|게시물 수정|
|8|information|DELETE| /informations/{post_id}|게시물 삭제|
|9|information|POST| /informations/scrap/{post_id}|게시물 스크랩|
|10|information|GET| /informations/{post_id}/comments|게시물 내용 조회|
|11|information|POST| /informations/{post_id}/comments|댓글 등록|
|12|information|PUT| /informations/{post_id}/comments/{comment_id}|댓글 수정|
|13|information|DELETE| /informations/{post_id}/comments/{comment_id}|댓글 삭제|
|14|question|GET| /questions|게시물 목록 조회|
|15|question|POST| /questions|게시물 등록|
|16|question|PUT| /questions/{post_id}|게시물 수정|
|17|question|DELETE| /questions/{post_id}|게시물 삭제|
|18|question|POST| /questions/scrap/{post_id}|게시물 스크랩|
|19|question|GET| /questions/{post_id}/comments|게시물 내용 조회|
|20|question|POST| /questions/{post_id}/comments|댓글 등록|
|21|question|PUT| /questions/{post_id}/comments/{comment_id}|댓글 수정|
|22|question|DELETE| /questions/{post_id}/comments/{comment_id}|댓글 삭제|
|23|talk|GET| /talks|게시물 목록 조회|
|24|talk|POST| /talks|게시물 등록|
|25|talk|PUT| /talks/{post_id}|게시물 수정|
|26|talk|DELETE| /talks/{post_id}|게시물 삭제|
|27|talk|POST| /talks/scrap/{post_id}|게시물 스크랩|
|28|talk|GET| /talks/{post_id}/comments|게시물 내용 조회|
|29|talk|POST| /talks/{post_id}/comments|댓글 등록|
|30|talk|PUT| /talks/{post_id}/comments/{comment_id}|댓글 수정|
|31|talk|DELETE| /talks/{post_id}/comments/{comment_id}|댓글 삭제|
