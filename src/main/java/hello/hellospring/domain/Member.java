package hello.hellospring.domain;

public class Member {

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
