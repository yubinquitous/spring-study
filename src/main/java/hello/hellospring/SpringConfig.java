package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // Autowired
    private final DataSource dataSource;    // 스프링 부트에서 제공해줌

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // memberService를 스프링 빈으로 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());   // 스프링 빈에 등록되어 있는 MemberRepository를 MemberService에 넣어줌
    }

    // memberRepository를 스프링 빈으로 등록
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
