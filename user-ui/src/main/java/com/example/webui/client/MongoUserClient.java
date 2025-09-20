package com.example.webui.client;

import com.example.webui.model.UserDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mongo-client", url = "http://127.0.0.1:8001/v1/users")
public interface MongoUserClient {

  @GetMapping("/{id}")
  UserDto getUser(@PathVariable String id);

  @GetMapping
  List<UserDto> getUsers();

  @PostMapping
  UserDto createUser(@RequestBody UserDto userDto);

  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable String id);

}
