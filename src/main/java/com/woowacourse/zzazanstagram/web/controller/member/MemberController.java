package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.member.dto.MemberMyPageResponse;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;
import com.woowacourse.zzazanstagram.model.member.service.MemberFacadeService;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.web.SessionKeys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

// TODO facade 제거
@Controller
public class MemberController {
    private MemberFacadeService memberFacadeService;
    private final MemberService memberService;
    private final Map<String, MemberResponse> sessionMap;
  
    public MemberController(MemberFacadeService memberFacadeService, MemberService memberService, Map<String, MemberResponse> sessionMap) {
        this.memberFacadeService = memberFacadeService;
        this.memberService = memberService;
        this.sessionMap = sessionMap;
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

    // TODO logincontroller 로 옮기기
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        MemberSession memberSession = (MemberSession) httpSession.getAttribute(SessionKeys.MEMBER);
        sessionMap.entrySet().stream()
                .filter(entry -> entry.getValue().getNickName().equals(memberSession.getNickName()))
                .map(Map.Entry::getKey)
                .forEach(sessionMap::remove);
        httpSession.removeAttribute(SessionKeys.MEMBER);
        return "redirect:/login";
    }
}
