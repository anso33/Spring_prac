package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  // UserLoanHistory의 user 필드를 JPA가 연관관계의 주인으로 인식하게 한다.
  private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

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

  public void loanBook(String bookName) {
    this.userLoanHistories.add(new UserLoanHistory(this, bookName));
  }

  public void returnBook(String bookName) { // TODO: 이해하기
    UserLoanHistory targethistory = this.userLoanHistories.stream()
            .filter(history -> history.getBookName().equals(bookName))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    targethistory.doReturn();
  }
}
