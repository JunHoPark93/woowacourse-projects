package com.woowacourse.zzazanstagram.model.article.exception;

public class ArticleAuthenticationException extends IllegalArgumentException {
    public ArticleAuthenticationException() {
        super();
    }

    public ArticleAuthenticationException(String s) {
        super(s);
    }
}
