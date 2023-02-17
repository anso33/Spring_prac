package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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

  public void saveUser(String name, Integer age) {
    String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
    jdbcTemplate.update(sql, name, age);
  }

  public List<UserResponse> getUserResponses() {
    String sql = "SELECT * FROM user";
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      long id = rs.getLong("id");
      String name = rs.getString("name");
      int age = rs.getInt("age");
      return new UserResponse(id, name, age);
    });
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
