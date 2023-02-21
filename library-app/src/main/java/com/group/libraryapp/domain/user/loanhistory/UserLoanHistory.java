package com.group.libraryapp.domain.user.loanhistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @GeneratedValue(strategy = IDENTITY)
  private Long id;    // TODO: Long long으로 둔 이유
  private long userId;
  private String bookName;
  private boolean isReturn;

  public UserLoanHistory(long userId, String bookName) {
    this.userId = userId;
    this.bookName = bookName;
  }

  public UserLoanHistory() {

  }
}
