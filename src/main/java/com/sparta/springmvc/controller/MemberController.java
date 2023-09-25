package com.sparta.springmvc.controller;

import com.sparta.springmvc.domain.Member;
import com.sparta.springmvc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/member/new")
    public String create(MemberForm form) {
        // 이 메서드는 회원 가입 폼을 제출했을 때 호출되는 컨트롤러 메서드입니다.
        // MemberForm은 클라이언트로부터 입력받은 회원 정보를 담고 있습니다.
        // 이 메서드는 회원 정보를 입력받아 Member 객체를 생성하고, MemberService를 사용하여 회원 정보를 처리합니다.

        // MemberForm에서 받은 데이터를 사용하여 Member 객체를 생성합니다.
        Member member = new Member();

        // MemberForm에서 받은 데이터를 사용하여 Member 객체의 이름을 설정합니다.
        member.setName(form.getName());

        // MemberService를 사용하여 Member 객체를 저장 또는 처리합니다.
        memberService.join(member);

        // 처리가 완료되면 홈 페이지로 리다이렉트합니다.
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        // memberService를 사용하여 회원 목록을 조회합니다.
        List<Member> members = memberService.findMembers();

        // 조회된 회원 목록을 "members"라는 이름으로 Model에 추가합니다.
        // Model은 데이터를 뷰로 전달하는 데 사용됩니다.
        model.addAttribute("members", members);

        // "members/memberList"라는 뷰 템플릿을 반환합니다.
        // 이것은 클라이언트에게 보여줄 화면을 나타내며, 뷰 리졸버에 의해 해석됩니다.
        return "members/memberList";
    }

}
