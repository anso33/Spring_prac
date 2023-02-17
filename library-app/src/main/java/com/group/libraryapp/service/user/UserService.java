package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

// 예외처리 기능 부여
public class UserService {

  private final UserRepository userRepository;

  public UserService(JdbcTemplate jdbcTemplate) {
    this.userRepository = new UserRepository(jdbcTemplate);
  }

  public void updateUser(JdbcTemplate jdbcTemplate, UserUpdateRequest request) {
    if (userRepository.isUserNotExist(jdbcTemplate, request.getId())) {
      throw new IllegalArgumentException();
    }

    userRepository.updateUserName(jdbcTemplate, request.getName(), request.getId());
  }
}
