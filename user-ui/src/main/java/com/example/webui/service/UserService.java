package com.example.webui.service;

import com.example.webui.model.UserDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  public List<UserDto> getAllUsers(String clientId) {
    return List.of(new UserDto());
  }

  public List<String> getClients() {
    return List.of("client-1");
  }

  public UserDto getUserById(String id, String clientId) {
    return new UserDto();
  }

  public UserDto createUser(UserDto user, String clientId) {
    return new UserDto();
  }

  public void updateUser(UserDto user, String clientId) {

  }

  public void deleteUser(String id, String clientId) {

  }
}
