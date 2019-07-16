# 게시글 생성/조회기능 구현하기

## 게시글 생성
* 게시글 작성 페이지 이동
    * 메인페이지(index.html)에서 게시글 생성 버튼을 누르기
    * GET /writing 으로 요청
    * 작성 페이지(article-edit.html)로 이동
* 게시글 작성
    * POST /articles 으로 요청
    * 게시글 생성 시 게시글은 ArticleRepository의 List<Article> articles에 저장한다.
    * 게시글 페이지(article.html)로 이동

## 게시글 목록 조회
* 메인 페이지 이동
    * GET / 으로 요청으로 이동 시 메인 페이지에 게시글 목록이 노출

## 게시글 조회
* 게시글 페이지 이동
    * 메인페이지(index.html)에서 게시글을 클릭 시 게시글 페이지(article.html)으로 이동
    * GET /articles/{articleId} 으로 요청