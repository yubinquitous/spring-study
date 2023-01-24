package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음.
 * 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // 0, 1, 2.. 키 값을 생성해주는 시퀀스

    @Override
    public Member save(Member member) { // 회원 저장
        member.setId(++sequence);       // 시스템 id 저장
        store.put(member.getId(), member);  // 저장소에 넣음
        return member;  // 저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) { // id로 회원 찾기
        return Optional.ofNullable(store.get(id));  // null이 반환될 가능성이 있으므로 Optional.ofNullable로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {   // name으로 회원 찾기
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // 회원의 name과 파라미터로 들어온 name 일치 여부 확인
                .findAny(); // 하나라도 찾기. 결과가 null이면 Optional로 감싸져서 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 회원 목록 반환
    }

    public void clearStore() {
        store.clear();
    }
}
