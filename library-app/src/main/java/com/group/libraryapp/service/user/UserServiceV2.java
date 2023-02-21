package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 예외처리 기능 부여
@Service
public class UserServiceV2 {

  private final UserRepository userRepository;

  public UserServiceV2(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public void saveUser(UserCreateRequest request) {
    System.out.println(request.getName());
    userRepository.save(new User(request.getName(), request.getAge()));
    // save는 반환값으로 User객체를 가진다.
  }

  @Transactional(readOnly = true)
  public List<UserResponse> getUsers() {
    return userRepository.findAll().stream()
            .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
            .collect(Collectors.toList());  // TODO: 이건되는데 왜 아랫줄은 못불러오지?ㅋㅋㅋㅋㅋㅋㅋ
//    return userRepository.findAll().stream()// TODO: Stream 공부하기
//            .map(UserResponse::new) // TODO: UserResponse에서 new를 받는 생성자를 만든다.????
//            .collect(Collectors.toList());
  }

  @Transactional
  public void updateUser(UserUpdateRequest request) {
    User user = userRepository.findById(request.getId())  // TODO: Optional
            .orElseThrow(IllegalArgumentException::new);
    user.updateName(request.getName());
    userRepository.save(user);
  }

  @Transactional
  public void deleteUser(String name) {
    User user = userRepository.findByName(name);
    if (user == null) {
      throw new IllegalArgumentException();
    }
    userRepository.delete(user);
  }
}
