package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity // 저장되고 관리되어야 하는 데이터
public class User {

  @Id // pk
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private Long id;

  @Column(nullable = false, length = 20, name = "name")
  // 필드에 null이 들어갈 수 있는지, 길이 제한, table의 column 명 등을 설정 가능.
  private String name;

  // 근데 사실 @Column 안 붙여도 JPA는 필드가 table에도 있을거라 예상한다.
  private Integer age;

  public User(String name, Integer age) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
    }
    this.name = name;
    this.age = age;
  }

  public User() {}

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public void updateName(String name) {
    this.name = name;
  }
}
