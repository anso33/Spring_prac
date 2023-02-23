package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @GeneratedValue(strategy = IDENTITY)
  private Long id;    // TODO: Long long으로 둔 이유

//  private long userId;
  @ManyToOne
  // userLoanHistory가 다수이고 User는 1개 이다. (N : 1)
  @JoinColumn(nullable = false)
  private User user;

  private String bookName;

  private boolean isReturn;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public UserLoanHistory(User user, String bookName) {
    this.user = user;
    this.bookName = bookName;
    this.isReturn = false;
  }

  public UserLoanHistory() {

  }

  public void doReturn() {
    this.isReturn = true;
  }

  public Long getId() {
    return id;
  }

  public String getBookName() {
    return bookName;
  }

  public boolean isReturn() {
    return isReturn;
  }
}
