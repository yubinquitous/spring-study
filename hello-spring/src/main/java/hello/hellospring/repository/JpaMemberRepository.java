package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // JPA는 EntityManager라는 것으로 모든 것이 동작된다. (data-jpa를 라이브러리로 설정하면 스프링 부트가 자동으로 EntityManager를 생성해준다.)

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member); // persist: 영구 저장. insert, id 등 모든 것을 알아서 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)   // Member(=Entity) (as) m이라는 객체에서 m이라는 객체 자체를 불러온다.
                .getResultList();
    }
}
