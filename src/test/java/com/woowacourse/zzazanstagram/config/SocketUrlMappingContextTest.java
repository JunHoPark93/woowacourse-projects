package com.woowacourse.zzazanstagram.config;

import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SocketUrlMappingContextTest {
    private SocketUrlMappingContext socketUrlMappingContext = new SocketUrlMappingContext();

    @Test
    void url_바인딩() {
        // given
        MemberResponse memberResponse = new MemberResponse(1L, "nick", "nicknick", "test@gmail.com", "url");
        socketUrlMappingContext.bindContext("123asd", memberResponse);

        // when
        List<String> results = socketUrlMappingContext.findTargetEndPoints(memberResponse);

        // then
        assertThat(results.size()).isEqualTo(1);

        emptyContext();
    }

    @Test
    void url_바인딩_여러_기기에서의_로그인_환경() {
        // given
        MemberResponse memberResponse1 = new MemberResponse(1L, "nick", "nicknick", "test@gmail.com", "url");
        MemberResponse memberResponse2 = new MemberResponse(1L, "nick", "nicknick", "test@gmail.com", "url");
        MemberResponse memberResponse3 = new MemberResponse(2L, "othernick", "othernick", "test2@gmail.com", "url");
        socketUrlMappingContext.bindContext("123asd", memberResponse1);
        socketUrlMappingContext.bindContext("123zxc", memberResponse2);
        socketUrlMappingContext.bindContext("123rty", memberResponse3);

        // when
        List<String> results = socketUrlMappingContext.findTargetEndPoints(memberResponse1);

        // then
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    void url_바인딩_로그아웃() {
        // given
        MemberResponse memberResponse1 = new MemberResponse(1L, "nick", "nicknick", "test@gmail.com", "url");
        MemberResponse memberResponse2 = new MemberResponse(1L, "nick", "nicknick", "test@gmail.com", "url");
        MemberResponse memberResponse3 = new MemberResponse(2L, "othernick", "othernick", "test2@gmail.com", "url");
        socketUrlMappingContext.bindContext("123asd", memberResponse1);
        socketUrlMappingContext.bindContext("123zxc", memberResponse2);
        socketUrlMappingContext.bindContext("123rty", memberResponse3);

        // when
        socketUrlMappingContext.removeContext(new MemberSession(1L, "nick", "test@gmail.com", "nick", "url"));

        // then
        assertThat(socketUrlMappingContext.getMappedUrlCount()).isEqualTo(1);
    }

    private void emptyContext() {
        socketUrlMappingContext.removeAllContext();
    }
}
