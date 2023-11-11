# REST API 명세
#### 프로젝트 주제 : 야구 정보 공유 커뮤니티

- 총 10개의 구단 별 화면 구현
- 10개의 구단 별 3개의 게시판 구현 예정<br><br>
  1. 정보 공유 게시판 (/information)
  2. Q&A 게시판 (/question)
  3. 잡담 게시판 (/talk)
<br><br>

| Index | Resource | Method | URI | Description |
|:-----:|:--------:|:------:|----:|------------:|
|1|user|POST| /user|사용자 등록|
|2|user|GET| /user|사용자 조회|
|3|user|PUT| /user|사용자 정보 수정|
|4|user|DELETE| /user|사용자 삭제|
|5|information|GET| /{teamName}/information|게시물 목록 조회|
|6|information|POST| /{teamName}/information|게시물 등록|
|7|information|PUT| /{teamName}/information/{post_id}/update|게시물 수정|
|8|information|DELETE| /{teamName}/information/{post_id}/delete|게시물 삭제|
|9|information|POST| /{teamName}/information/{post_id}/scrap|게시물 스크랩|
|10|information|GET| /{teamName}/information/{post_id}|게시물 내용 조회|
|11|information|POST| /{teamName}/information/{post_id}/comment|댓글 등록|
|12|information|PUT| /{teamName}/information/{post_id}/{comment_id}/update|댓글 수정|
|13|information|DELETE| /{teamName}/information/{post_id}/{comment_id}/delete|댓글 삭제|
|14|question|GET| /{teamName}/question|게시물 목록 조회|
|15|question|POST| /{teamName}/question|게시물 등록|
|16|question|PUT| /{teamName}/question/{post_id}/update|게시물 수정|
|17|question|DELETE| /{teamName}/question/{post_id}/delete|게시물 삭제|
|18|question|POST| /{teamName}/question/{post_id}/scrap|게시물 스크랩|
|19|question|GET| /{teamName}/question/{post_id}|게시물 내용 조회|
|20|question|POST| /{teamName}/question/{post_id}/comment|댓글 등록|
|21|question|PUT| /{teamName}/question/{post_id}/{comment_id}/update|댓글 수정|
|22|question|DELETE| /{teamName}/question/{post_id}/{comment_id}/delete|댓글 삭제|
|23|talk|GET| /{teamName}/talk|게시물 목록 조회|
|24|talk|POST| /{teamName}/talk|게시물 등록|
|25|talk|PUT| /{teamName}/talk/{post_id}/update|게시물 수정|
|26|talk|DELETE| /{teamName}/talk/{post_id}/delete|게시물 삭제|
|27|talk|POST| /{teamName}/talk/{post_id}/scrap|게시물 스크랩|
|28|talk|GET| /{teamName}/talk/{post_id}|게시물 내용 조회|
|29|talk|POST| /{teamName}/talk/{post_id}/comment|댓글 등록|
|30|talk|PUT| /{teamName}/talk/{post_id}/{comment_id}/update|댓글 수정|
|31|talk|DELETE| /{teamName}/talk/{post_id}/{comment_id}/delete|댓글 삭제|
