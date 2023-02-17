package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  private final JdbcTemplate jdbcTemplate;
  private final UserService userService;

  public UserController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.userService = new UserService(jdbcTemplate);
  }

  @PostMapping("/user")
  public void saveUser(@RequestBody UserCreateRequest request) {
    String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
    jdbcTemplate.update(sql, request.getName(), request.getAge());
  }

  @GetMapping("/user")
  public List<UserResponse> getUsers() {
    String sql = "SELECT * FROM user";
//    return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
//      @Override
//      public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
//        long id = rs.getLong("id");
//        String name = rs.getString("name");
//        int age = rs.getInt("age");
//        return new UserResponse(id, name, age);
//      }
//    });
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      long id = rs.getLong("id");
      String name = rs.getString("name");
      int age = rs.getInt("age");
      return new UserResponse(id, name, age);
    });
  }

  @PutMapping("/user")  //API진입지점과 HTTP Body를 객체로 변환하는 역할만 한다.
  public void updateUser(@RequestBody UserUpdateRequest request) {
    userService.updateUser(request);
  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestParam String name) {
    userService.deleteUser(name);
  }
}
