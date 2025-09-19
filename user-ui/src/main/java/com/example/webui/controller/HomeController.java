package com.example.webui.controller;

import com.example.webui.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        List<String> clients = userService.getClients();
        model.addAttribute("clients", clients);
        return "users/home";
    }
}