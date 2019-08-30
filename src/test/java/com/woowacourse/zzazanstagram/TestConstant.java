package com.woowacourse.zzazanstagram;

import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.member.domain.vo.*;

public class TestConstant {

    /*
        Article
     */
    public static final String IMAGE_VALUE = "image";
    public static final String CONTENTS_VALUE = "contents";

    public static final Image IMAGE = Image.of(IMAGE_VALUE);
    public static final Contents CONTENTS = Contents.of(CONTENTS_VALUE);

    /*
        Comment
     */
    public static final String COMMENT_CONTENTS_VALUE = "comment contents";
    public static final CommentContents COMMENT_CONTENTS = CommentContents.of(COMMENT_CONTENTS_VALUE);

    /*
        Member
     */
    public static final String NICKNAME_VALUE = "nickName";
    public static final String NAME_VALUE = "name";
    public static final String EMAIL_VALUE = "test@gmail.com";
    public static final String PASSWORD_VALUE = "Pas@or@3d";
    public static final String PROFILE_IMAGE_VALUE = "profile@gmail.com";

    public static final NickName NICK_NAME = NickName.of(NICKNAME_VALUE);
    public static final Name NAME = Name.of(NAME_VALUE);
    public static final Email EMAIL = Email.of(EMAIL_VALUE);
    public static final Password PASSWORD = Password.of(PASSWORD_VALUE);
    public static final ProfileImage PROFILE_IMAGE = ProfileImage.of(PROFILE_IMAGE_VALUE);

    /*
        SESSION
     */
    public static String SESSION_KEY = "member";
    public static String SESSION_VALUE = "test";
}
