package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 스프링 컨테이너에 등록해서 사용하기 - 하나만 등록
    private final MemberService memberService;

    @Autowired  // 스프링이 스프링 컨테이너에 있는 MemberService를 가져다 연결시켜줌(Controller와 Service 연결, DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 등록 폼 컨트롤러 추가
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) { // 등록을 누르면 MemberForm의 객체에 setName() 함수로 전달된 name 값이 들어감
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";    // 홈 화면으로 돌아감
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
