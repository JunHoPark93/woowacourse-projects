package com.woowacourse.zzazanstagram.model.follow.service;

import com.woowacourse.zzazanstagram.model.follow.domain.Follow;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowRequest;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResponse;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResult;
import com.woowacourse.zzazanstagram.model.follow.repository.FollowRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberRelationResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberAssembler;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FollowService {
    private MemberService memberService;
    private FollowRepository followRepository;

    @Autowired
    private Map<String, MemberResponse> sessionMap;

    public FollowService(MemberService memberService, FollowRepository followRepository) {
        this.memberService = memberService;
        this.followRepository = followRepository;
    }

    public FollowResult follow(FollowRequest followRequest) {
        Member followee = findMember(followRequest.getFolloweeId());
        Member follower = findMember(followRequest.getFollowerId());
        return followRepository.findByFolloweeAndFollower(followee, follower)
                .map(x -> {
                    followRepository.delete(x);
                    return new FollowResult(MemberAssembler.assemble(followee), MemberAssembler.assemble(follower), false);
                }).orElseGet(() -> {
                    followRepository.save(new Follow(followee, follower));
                    return new FollowResult(MemberAssembler.assemble(followee), MemberAssembler.assemble(follower), true);
                });
    }

    public List<FollowResponse> findFollowers(Long id) {
        Member member = findMember(id);
        List<Follow> follows = followRepository.findByFollower(member);

        return follows.stream()
                .map(Follow::getFollowee)
                .map(MemberAssembler::assemble)
                .map(FollowResponse::new)
                .collect(Collectors.toList());
    }

    public List<Long> findFollowersIds(Long id) {
        Member member = findMember(id);
        List<Follow> follows = followRepository.findByFollower(member);

        return Collections.unmodifiableList(follows.stream()
                .map(Follow::getFollowee)
                .map(Member::getId)
                .collect(Collectors.toList()));
    }

    public List<FollowResponse> findFollowings(Long id) {
        Member member = findMember(id);
        List<Follow> follows = followRepository.findByFollowee(member);

        return follows.stream()
                .map(Follow::getFollower)
                .map(MemberAssembler::assemble)
                .map(FollowResponse::new)
                .collect(Collectors.toList());
    }

    public List<Long> findFollowingsIds(Long id) {
        Member member = findMember(id);
        List<Follow> follows = followRepository.findByFollowee(member);

        return Collections.unmodifiableList(follows.stream()
                .map(Follow::getFollower)
                .map(Member::getId)
                .collect(Collectors.toList()));
    }

    private Member findMember(Long id) {
        return memberService.findById(id);
    }

    public MemberRelationResponse findRelation(Long targetMemberId, Long sessionMemberId) {
        boolean isFollower = followRepository.existsByFolloweeIdAndFollowerId(targetMemberId, sessionMemberId);
        boolean isFollowing = followRepository.existsByFolloweeIdAndFollowerId(sessionMemberId, targetMemberId);
        return new MemberRelationResponse(isFollower, isFollowing);
    }

    public long countFollowees(Long memberId) {
        return followRepository.countByFolloweeId(memberId);
    }

    public long countFollowers(Long memberId) {
        return followRepository.countByFollowerId(memberId);
    }
  
    public List<String> findTargetEndpoint(MemberResponse target) {
        List<String> targets = new ArrayList<>();
        sessionMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(target))
                .map(Map.Entry::getKey)
                .forEach(targets::add);
        return targets;
    }
}
