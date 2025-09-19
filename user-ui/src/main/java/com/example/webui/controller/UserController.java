package com.example.webui.controller;

import com.example.webui.model.UserDto;
import com.example.webui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  // GET /users - возвращает список пользователей
  @GetMapping
    public String getUsers(@RequestHeader(value = "x-client-id", required = false) String clientId,
                          @RequestParam(value = "x-client-id", required = false) String paramClientId,
                          Model model) {
    List<String> clients = userService.getClients();
        String selectedClient = (clientId != null) ? clientId :
                               (paramClientId != null) ? paramClientId :
                               (clients.isEmpty() ? "" : clients.get(0));

        List<UserDto> users = userService.getAllUsers(selectedClient);

    model.addAttribute("users", users);
    model.addAttribute("clients", clients);
        model.addAttribute("selectedClient", selectedClient);

    return "users/list";
  }

  // GET /users/{id} - возвращает пользователя
  @GetMapping("/{id}")
  public String getUser(@PathVariable String id,
                         @RequestHeader(value = "x-client-id", required = false) String clientId,
                         @RequestParam(value = "x-client-id", required = false) String paramClientId,
      Model model) {
    List<String> clients = userService.getClients();
    String selectedClient = (clientId != null) ? clientId :
                               (paramClientId != null) ? paramClientId :
                               (clients.isEmpty() ? "" : clients.get(0));

    UserDto user = userService.getUserById(id, clientId);

    model.addAttribute("user", user);
    model.addAttribute("clients", clients);
        model.addAttribute("selectedClient", selectedClient);

    return "users/detail";
  }

  // GET /users/new - форма создания пользователя
  @GetMapping("/new")
  public String newUserForm(@RequestParam(value = "x-client-id", required = false) String clientId,
      Model model) {
    List<String> clients = userService.getClients();
    String selectedClient =
        (clientId != null) ? clientId : (clients.isEmpty() ? "" : clients.get(0));

    model.addAttribute("user", new UserDto());
    model.addAttribute("clients", clients);
    model.addAttribute("selectedClient", selectedClient);

    return "users/form";
  }

  // GET /users/{id}/edit - форма редактирования пользователя
  @GetMapping("/{id}/edit")
  public String editUserForm(@PathVariable String id,
      @RequestParam(value = "x-client-id", required = false) String clientId,
      Model model) {
    List<String> clients = userService.getClients();
    String selectedClient =
        (clientId != null) ? clientId : (clients.isEmpty() ? "" : clients.get(0));

    UserDto user = userService.getUserById(id, selectedClient);

    model.addAttribute("user", user);
    model.addAttribute("clients", clients);
    model.addAttribute("selectedClient", selectedClient);

    return "users/form";
  }

  // POST /users - создает пользователя из модели переданной в теле запроса json
  @PostMapping
  @ResponseBody
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto user,
      @RequestHeader("x-client-id") String clientId) {
    UserDto createdUser = userService.createUser(user, clientId);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  // POST /users/form - создает/обновляет пользователя из формы
  @PostMapping("/form")
  public String saveUser(@ModelAttribute UserDto user,
      @RequestParam("clientId") String clientId) {
    if (user.getId() == null || user.getId().isEmpty()) {
      userService.createUser(user, clientId);
    } else {
      userService.updateUser(user, clientId);
    }
    return "redirect:/users?x-client-id=" + clientId;
  }

  // DELETE /users/{id} - удаляет пользователя
  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Void> deleteUser(@PathVariable String id,
      @RequestHeader("x-client-id") String clientId) {
    userService.deleteUser(id, clientId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // DELETE /users/{id}/form - удаляет пользователя из формы
  @PostMapping("/{id}/delete")
  public String deleteUserForm(@PathVariable String id,
      @RequestParam("clientId") String clientId) {
    userService.deleteUser(id, clientId);
    return "redirect:/users?x-client-id=" + clientId;
  }
}