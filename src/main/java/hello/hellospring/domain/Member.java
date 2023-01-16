package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA가 관리하는 엔티티
public class Member {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 값을 임력해주면, 자동으로 id값이 설정됨 (identity 전략)
    private Long id;    // 임의의 값. 고객이 정하는 id가 아니라 데이터 구분을 위한 시스템 id
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
