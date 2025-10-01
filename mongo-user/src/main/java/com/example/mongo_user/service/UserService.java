package com.example.mongo_user.service;

import com.example.mongo_user.entity.UserEntity;
import com.example.mongo_user.mapper.UserMapper;
import com.example.mongo_user.model.UserDto;
import com.example.mongo_user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Tool(description = "Get user by id")
  public UserEntity getUserById(String id) {
    return userRepository.findById(id).get();
  }

  @Tool(description = "Get all users")
  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  @Tool(description = "Save user")
  public UserEntity saveUser(UserDto userToCreate) {

    return userRepository.save(userMapper.toUserEntity(userToCreate));
  }

  @Tool(description = "Delete user by id")
  public void deleteUserById(String id) {
    userRepository.deleteById(id);
  }
}
