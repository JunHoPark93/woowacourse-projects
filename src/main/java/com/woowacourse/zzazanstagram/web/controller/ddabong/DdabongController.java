package com.woowacourse.zzazanstagram.web.controller.ddabong;

import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongMemberResponse;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongToggleResponse;
import com.woowacourse.zzazanstagram.model.ddabong.service.DdabongService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DdabongController {
    private final DdabongService ddabongService;

    public DdabongController(DdabongService ddabongService) {
        this.ddabongService = ddabongService;
    }

    @GetMapping("/articles/{articleId}/ddabongs")
    public ResponseEntity<DdabongToggleResponse> clickDdabong(@PathVariable Long articleId, MemberSession memberSession) {
        DdabongToggleResponse ddabongToggleResponse = ddabongService.toggleDdabong(articleId, memberSession.getEmail());
        return new ResponseEntity<>(ddabongToggleResponse, HttpStatus.OK);
    }

    // todo url path 수정
    @GetMapping("/ddabongs/members/{articleId}")
    public ResponseEntity<DdabongMemberResponse> fetchDdabongMembers(@PathVariable Long articleId) {
        DdabongMemberResponse ddabongMemberResponse = ddabongService.fetchDdabongMembers(articleId);
        return new ResponseEntity<>(ddabongMemberResponse, HttpStatus.OK);
    }
}
