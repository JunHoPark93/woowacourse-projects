package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.dto.MemberMyPageResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;
import com.woowacourse.zzazanstagram.model.member.service.MemberFacadeService;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

// TODO facade 제거
@Controller
public class MemberController {
    private MemberFacadeService memberFacadeService;
    private final MemberService memberService;

    public MemberController(MemberFacadeService memberFacadeService, MemberService memberService) {
        this.memberFacadeService = memberFacadeService;
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/members/{nickname}")
    public String myPage(@PathVariable("nickname") String nickName, Model model) {
        MemberMyPageResponse memberMyPageResponse = memberFacadeService.myPage(nickName);
        model.addAttribute("member", memberMyPageResponse);
        return "mypage";
    }

    @PostMapping("/members")
    public String saveMember(@Valid MemberSignUpRequest memberSignupRequest) {
        memberService.save(memberSignupRequest);
        return "redirect:/login";
    }
}
