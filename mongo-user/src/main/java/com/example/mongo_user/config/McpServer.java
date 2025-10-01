package com.example.mongo_user.config;

import com.example.mongo_user.service.UserService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpServer {

  @Bean
  public ToolCallbackProvider weatherTools(UserService userService) {
    return MethodToolCallbackProvider.builder().toolObjects(userService).build();
  }

}
