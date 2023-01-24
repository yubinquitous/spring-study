package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {  // public이 아니어도 됨

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 테스트가 끝날 때마다 데이터를 지워줘야 함
    public void afterEach() {   // 메서드가 끝날 때마다 동작
        repository.clearStore();
    }

    /* save 테스트 */
    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");   // 회원 이름 지정

        // when
        repository.save(member);    // 리포지토리에 회원 저장

        // then 검증: 저장한 것과 꺼낸 것이 동일해야 함
        Member result = repository.findById(member.getId()).get();  // 저장된 id를 찾아옴.
        // findById의 반환 타입은 Optional.
        // Optional에서 값을 꺼낼 때 get 이용

        // System.out.println("result = " + (result == member));   // 동일하다면 true 출력
        // Assertions.assertEquals(member, result);    // 동일하지 않으면 오류 발생
        assertThat(member).isEqualTo(result);
    }

    /* findByName 테스트 */
    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();

        // then
        assertThat(result).isEqualTo(member1);
    }

    /* findAll 테스트 */
    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
