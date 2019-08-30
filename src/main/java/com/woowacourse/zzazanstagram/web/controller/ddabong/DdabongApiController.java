package com.woowacourse.zzazanstagram.web.controller.ddabong;

import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongMemberResponse;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongToggleResponse;
import com.woowacourse.zzazanstagram.model.ddabong.service.DdabongService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ddabongs")
public class DdabongApiController {
    private final DdabongService ddabongService;

    public DdabongApiController(DdabongService ddabongService) {
        this.ddabongService = ddabongService;
    }

    @GetMapping("/articles/{articleId}")
    public ResponseEntity<DdabongToggleResponse> toggleDdabong(@PathVariable Long articleId, MemberSession memberSession) {
        DdabongToggleResponse ddabongToggleResponse = ddabongService.findDdabongToggleResponseBy(articleId, memberSession.getEmail());
        return new ResponseEntity<>(ddabongToggleResponse, HttpStatus.OK);
    }

    @GetMapping("/members/{articleId}")
    public ResponseEntity<DdabongMemberResponse> fetchDdabongMembers(@PathVariable Long articleId) {
        DdabongMemberResponse ddabongMemberResponse = ddabongService.findDdabongMemberResponseBy(articleId);
        return new ResponseEntity<>(ddabongMemberResponse, HttpStatus.OK);
    }
}
