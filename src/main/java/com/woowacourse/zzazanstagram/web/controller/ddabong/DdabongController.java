package com.woowacourse.zzazanstagram.web.controller.ddabong;

import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongResponse;
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
    public ResponseEntity<DdabongResponse> clickDdabong(@PathVariable Long articleId, MemberSession memberSession) {
        DdabongResponse ddabongResponse = ddabongService.toggleDdabong(articleId, memberSession.getEmail());
        return new ResponseEntity<>(ddabongResponse, HttpStatus.OK);
    }
}
