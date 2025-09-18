package com.example.mongo_user.service;

import com.example.mongo_user.entity.UserEntity;
import com.example.mongo_user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserEntity getUserById(String id) {
    return userRepository.findById(id).get();
  }

  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  public UserEntity saveUser(UserEntity userToCreate) {
    return userRepository.save(userToCreate);
  }

  public void deleteUserById(String id) {
    userRepository.deleteById(id);
  }
}
