package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;
import com.woowacourse.zzazanstagram.model.member.exception.MemberEmailFormatException;
import com.woowacourse.zzazanstagram.model.member.exception.MemberProfileUrlFormatException;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/members")
    public String saveMember(@Valid MemberSignUpRequest memberSignupRequest, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("email")) {
            throw new MemberEmailFormatException(bindingResult.getFieldError().getDefaultMessage());
        }
        if (bindingResult.hasFieldErrors("profile")) {
            throw new MemberProfileUrlFormatException(bindingResult.getFieldError().getDefaultMessage());
        }

        memberService.save(memberSignupRequest);
        return "redirect:/login";
    }
}
