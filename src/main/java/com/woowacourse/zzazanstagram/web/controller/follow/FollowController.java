package com.woowacourse.zzazanstagram.web.controller.follow;

import com.woowacourse.zzazanstagram.model.follow.dto.FollowRequest;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResponse;
import com.woowacourse.zzazanstagram.model.follow.dto.FollowResult;
import com.woowacourse.zzazanstagram.model.follow.service.FollowService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberRelationResponse;
import com.woowacourse.zzazanstagram.web.controller.member.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/follow")
@RestController
public class FollowController {
    private static final Logger log = LoggerFactory.getLogger(FollowController.class);
    private static final String TAG = "[FollowController]";

    private FollowService followService;
    private SimpMessagingTemplate simpMessagingTemplate;

    public FollowController(FollowService followService, SimpMessagingTemplate simpMessagingTemplate) {
        this.followService = followService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping
    public ResponseEntity follow(FollowRequest followRequest) {
        FollowResult followResult = followService.follow(followRequest);
        List<String> targetUrls = followService.findTargetEndpoint(followResult.getFollower());
        sendNotificationToLoggedInMember(followResult, targetUrls);

        log.info("{} {}가 {} 에게 follow : {}", TAG, followResult.getFolloweeNickName(), followResult.getFollowerNickName(), followResult.isFollow());

        return new ResponseEntity(HttpStatus.OK);
    }

    private void sendNotificationToLoggedInMember(FollowResult followResult, List<String> targetUrls) {
        for (String targetUrl : targetUrls) {
            simpMessagingTemplate.convertAndSend("/topics/follow-notification/" + targetUrl, followResult);
        }
    }

    @GetMapping("/follower/{memberId}")
    public ResponseEntity<List<FollowResponse>> followers(@PathVariable("memberId") Long id) {
        List<FollowResponse> followResponses = followService.findFollowerResponses(id);
        return new ResponseEntity<>(followResponses, HttpStatus.OK);
    }

    @GetMapping("/following/{memberId}")
    public ResponseEntity<List<FollowResponse>> followings(@PathVariable("memberId") Long id) {
        List<FollowResponse> followResponses = followService.findFollowingResponses(id);
        return new ResponseEntity<>(followResponses, HttpStatus.OK);
    }

    @GetMapping("/relation/{memberId}")
    public ResponseEntity<MemberRelationResponse> isFollow(@PathVariable("memberId") Long memberId, MemberSession memberSession) {
        MemberRelationResponse memberRelationResponse = followService.findMemberRelationResponse(memberId, memberSession.getId());
        return new ResponseEntity<>(memberRelationResponse, HttpStatus.OK);
    }
}

