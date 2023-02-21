package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @Column(nullable = false)
  private String name;

  public Book(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Book(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
    }
    this.name = name;
  }

  public Book() {

  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
