package com.group.libraryapp.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;

// DB에 SQL을 날려 조작하는, 저장장치로의 접근을 담당하게 한다.
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public boolean isUserNotExist(long id) {
    String sql = "SELECT * FROM user WHERE id = ?";
    return jdbcTemplate.query(sql, (rs, rowNum) -> 0, id).isEmpty();
  }

  public boolean isUserNotExist(String name) {
    String sql = "SELECT * FROM user WHERE name = ?";
    return jdbcTemplate.query(sql, (rs, rowNum) -> 0, name).isEmpty();
  }

  public void updateUserName(String name, long id) {
    String sql = "UPDATE user SET name = ? WHERE id = ?";
    jdbcTemplate.update(sql, name, id);
  }

  public void deleteUserByName(String name) {
    String deleteSql = "DELETE FROM user WHERE name = ?";
    jdbcTemplate.update(deleteSql, name);
  }
}
