package com.woowacourse.zzazanstagram.model.follow.service;

import com.woowacourse.zzazanstagram.model.follow.domain.Follow;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowRequest;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResponse;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResult;
import com.woowacourse.zzazanstagram.model.follow.repository.FollowRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberRelationResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
public class FollowServiceTest {
    @InjectMocks
    private FollowService followService;

    @Mock
    private FollowRepository followRepository;

    @Mock
    private MemberService memberService;

    private Member member1;
    private Member member2;
    private MemberResponse memberResponse1;
    private MemberResponse memberResponse2;

    @BeforeEach
    void setUp() {
        member1 = Member.MemberBuilder.aMember()
                .email(EMAIL)
                .name(NAME)
                .nickName(NICKNAME)
                .password(PASSWORD)
                .profile(IMAGE_URL)
                .build();
        ReflectionTestUtils.setField(member1, "id", 1L);

        member2 = Member.MemberBuilder.aMember()
                .email(EMAIL2)
                .name(NAME2)
                .nickName(NICKNAME2)
                .password(PASSWORD)
                .profile(IMAGE_URL)
                .build();
        ReflectionTestUtils.setField(member2, "id", 2L);

        memberResponse1 = new MemberResponse(1L, NICKNAME, NAME, EMAIL, IMAGE_URL);
        memberResponse2 = new MemberResponse(2L, NICKNAME2, NAME2, EMAIL2, IMAGE_URL);
    }

    @Test
    void 팔로우() {
        // given
        FollowRequest followRequest = new FollowRequest(1L, 2L);
        FollowResult followResult = new FollowResult(memberResponse1, memberResponse2, true);

        given(followRepository.findByFolloweeAndFollower(member1, member2)).willReturn(Optional.empty());
        given(memberService.findById(1L)).willReturn(member1);
        given(memberService.findById(2L)).willReturn(member2);

        // when
        FollowResult result = followService.follow(followRequest);

        // then
        assertThat(result).isEqualTo(followResult);
    }

    @Test
    void 언팔로우() {
        FollowRequest followRequest = new FollowRequest(1L, 2L);
        FollowResult followResult = new FollowResult(memberResponse1, memberResponse2, false);
        Follow follow = new Follow(member1, member2);

        given(followRepository.findByFolloweeAndFollower(member1, member2)).willReturn(Optional.of(follow));
        given(memberService.findById(1L)).willReturn(member1);
        given(memberService.findById(2L)).willReturn(member2);

        // when
        FollowResult result = followService.follow(followRequest);

        // then
        assertThat(result).isEqualTo(followResult);
    }

    @Test
    void 팔로워_목록_찾기() {
        // given
        List<Follow> follows = new ArrayList<>();
        follows.add(new Follow(member1, member2));
        given(followRepository.findByFollower(member1)).willReturn(follows);
        given(memberService.findById(1L)).willReturn(member1);

        // when
        List<FollowResponse> responses = followService.findFollowerResponses(1L);

        // then
        assertThat(responses.size()).isEqualTo(1);
    }

    @Test
    void 팔로이_목록_찾기() {
        // given
        List<Follow> follows = new ArrayList<>();
        follows.add(new Follow(member1, member2));
        given(followRepository.findByFollowee(member2)).willReturn(follows);
        given(memberService.findById(2L)).willReturn(member2);

        // when
        List<FollowResponse> responses = followService.findFollowingResponses(2L);

        // then
        assertThat(responses.size()).isEqualTo(1);
    }

    @Test
    void 팔로이_id_목록_찾기() {
        // given
        List<Follow> follows = new ArrayList<>();
        follows.add(new Follow(member1, member2));
        given(followRepository.findByFollowee(member2)).willReturn(follows);
        given(memberService.findById(2L)).willReturn(member2);

        // when
        List<Long> ids = followService.findFollowingsIds(2L);

        // then
        assertThat(ids.size()).isEqualTo(1);
    }

    @Test
    void 멤버_관계_찾기() {
        // given
        given(followRepository.existsByFolloweeIdAndFollowerId(1L, 2L)).willReturn(true);
        given(followRepository.existsByFolloweeIdAndFollowerId(2L, 1L)).willReturn(true);

        // when
        MemberRelationResponse relation = followService.findMemberRelationResponse(1L, 2L);

        // then
        assertTrue(relation.isFollower());
        assertTrue(relation.isFollowing());
    }

    @Test
    void 팔로워수_찾기() {
        // given
        given(followRepository.countByFollowerId(1L)).willReturn(3L);

        // when
        long count = followService.countFollowers(1L);

        // then
        assertThat(count).isEqualTo(3);
    }

    @Test
    void 팔로이수_찾기() {
        // given
        given(followRepository.countByFolloweeId(1L)).willReturn(3L);

        // when
        long count = followService.countFollowees(1L);

        // then
        assertThat(count).isEqualTo(3);
    }
}
