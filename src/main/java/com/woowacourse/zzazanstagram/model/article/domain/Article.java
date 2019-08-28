package com.woowacourse.zzazanstagram.model.article.domain;

import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleAuthenticationException;
import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import com.woowacourse.zzazanstagram.model.common.BaseEntity;
import com.woowacourse.zzazanstagram.model.ddabong.domain.Ddabong;
import com.woowacourse.zzazanstagram.model.hashtag.domain.ArticleHashtag;
import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import com.woowacourse.zzazanstagram.model.member.domain.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
public class Article extends BaseEntity {
    private static final Pattern WHTIE_SPACE_PATTERN = Pattern.compile("[ \\t\\r\\n\\v\\f]");
    private static final String HASHTAG_PREFIX = "#";
    private static final int NEXT_INDEX_OF_PREFIX = 1;

    private Image image;
    private Contents contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false, foreignKey = @ForeignKey(name = "fk_article_to_member"))
    private Member author;

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    private List<Ddabong> ddabongs = new ArrayList<>();

    // TODO cascade 삭제, orphanRemoval걸기
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ArticleHashtag> articleHashtags = new ArrayList<>();

    protected Article() {
    }

    public Article(final Image image, final Contents contents, final Member author) {
        this.image = image;
        this.contents = contents;
        this.author = author;
    }

    public long getCommentsCount() {
        return comments.size();
    }

    public long getDdabongCount() {
        return ddabongs.stream()
                .filter(Ddabong::isClicked)
                .count();
    }

    public void checkAuthentication(Member member) {
        // TODO member에 email 체킹 기능 구현 & if문안의 비교 메서드 추출
        if (!this.author.getEmail().equals(member.getEmail())) {
            throw new ArticleAuthenticationException("게시글에 대한 권한이 없습니다.");
        }
    }

    // TODO naming 바꾸기 : is~
    public boolean getDdabongClicked(Member member) {
        return ddabongs.stream().filter(ddabong -> ddabong.matchMember(member))
                .findFirst()
                .map(Ddabong::isClicked)
                .orElse(false);
    }

    public List<Hashtag> extractTagKeywords() {
        return Arrays.stream(getContentsValue().split(WHTIE_SPACE_PATTERN.pattern()))
                .filter(x -> x.startsWith(HASHTAG_PREFIX))
                .map(x -> new Hashtag(x.substring(NEXT_INDEX_OF_PREFIX)))
                .collect(Collectors.toList());
    }

    public Image getImage() {
        return image;
    }

    public Contents getContents() {
        return contents;
    }

    public String getImageValue() {
        return image.getUrl();
    }

    public String getContentsValue() {
        return contents.getContents();
    }

    public Member getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public List<Ddabong> getDdabongs() {
        return Collections.unmodifiableList(ddabongs);
    }

    public List<ArticleHashtag> getArticleHashtags() {
        return Collections.unmodifiableList(articleHashtags);
    }
}