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

  public void updateUser(UserUpdateRequest request) {
    if (userRepository.isUserNotExist(request.getId())) {
      throw new IllegalArgumentException();
    }
    userRepository.updateUserName(request.getName(), request.getId());
  }

  public void deleteUser(String name) {
    if (userRepository.isUserNotExist(name)) {
      throw new IllegalArgumentException();
    }
    userRepository.deleteUserByName(name);
  }
}
