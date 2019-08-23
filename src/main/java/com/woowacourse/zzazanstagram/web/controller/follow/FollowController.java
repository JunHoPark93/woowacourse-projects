package com.woowacourse.zzazanstagram.web.controller.follow;

import com.woowacourse.zzazanstagram.model.follow.dto.FollowRequest;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResponse;
import com.woowacourse.zzazanstagram.model.follow.service.FollowService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberRelationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FollowController {
    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow")
    public ResponseEntity follow(FollowRequest followRequest) {
        followService.follow(followRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/follow/follower/{memberId}")
    public ResponseEntity<List<FollowResponse>> followers(@PathVariable("memberId") Long id) {
        List<FollowResponse> followResponses = followService.findFollowers(id);
        return new ResponseEntity<>(followResponses, HttpStatus.OK);
    }

    @GetMapping("/follow/following/{memberId}")
    public ResponseEntity<List<FollowResponse>> followings(@PathVariable("memberId") Long id) {
        List<FollowResponse> followResponses = followService.findFollowings(id);
        return new ResponseEntity<>(followResponses, HttpStatus.OK);
    }

    @GetMapping("/follow/relation/{memberId}")
    public ResponseEntity<MemberRelationResponse> isFollow(@PathVariable("memberId") Long memberId, MemberSession memberSession) {
        MemberRelationResponse memberRelationResponse = followService.findRelation(memberId, memberSession.getId());
        return new ResponseEntity<>(memberRelationResponse, HttpStatus.OK);
    }
}
