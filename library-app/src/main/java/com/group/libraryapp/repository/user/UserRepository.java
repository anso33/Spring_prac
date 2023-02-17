package com.group.libraryapp.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;

// DB에 SQL을 날려 조작하는, 저장장치로의 접근을 담당하게 한다.
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public boolean isUserNotExist(JdbcTemplate jdbcTemplate, long id) {
    String sql = "SELECT * FROM user WHERE id = ?";
    return jdbcTemplate.query(sql, (rs, rowNum) -> 0, id).isEmpty();
  }

  public void updateUserName(JdbcTemplate jdbcTemplate, String name, long id) {
    String sql = "UPDATE user SET name = ? WHERE id = ?";
    jdbcTemplate.update(sql, name, id);
  }
}
