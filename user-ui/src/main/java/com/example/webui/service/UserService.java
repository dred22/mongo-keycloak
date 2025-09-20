package com.example.webui.service;

import com.example.webui.client.MongoUserClient;
import com.example.webui.model.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final MongoUserClient mongoUserClient;

  public List<UserDto> getAllUsers(String clientId) {
    return mongoUserClient.getUsers();
  }

  public List<String> getClients() {
    return List.of("client-1");
  }

  public UserDto getUserById(String id, String clientId) {
    return mongoUserClient.getUser(id);
  }

  public UserDto createUser(UserDto user, String clientId) {
    return mongoUserClient.createUser(user);
  }

  public UserDto updateUser(UserDto user, String clientId) {
    return mongoUserClient.createUser(user);
  }

  public void deleteUser(String id, String clientId) {
    mongoUserClient.deleteUser(id);
  }
}
