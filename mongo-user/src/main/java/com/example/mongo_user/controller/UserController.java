package com.example.mongo_user.controller;

import com.example.mongo_user.entity.UserEntity;
import com.example.mongo_user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public UserEntity getUser(@PathVariable String id) {
    return userService.getUserById(id);
  }

  @GetMapping
  public List<UserEntity> getUsers() {
    return userService.findAll();
  }

  @PostMapping
  public UserEntity createUser(@RequestBody UserEntity userToCreate) {
    return userService.saveUser(userToCreate);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    userService.deleteUserById(id);
  }

}
