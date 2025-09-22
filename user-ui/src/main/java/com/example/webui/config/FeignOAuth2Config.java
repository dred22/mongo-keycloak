package com.example.webui.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FeignOAuth2Config {

  private final OAuth2AuthorizedClientManager clientManager;

  @Bean
  public RequestInterceptor oauth2FeignRequestInterceptor() {
    return requestTemplate -> {
      String clientId = OAuth2ClientContext.getClientId();
      OAuth2ClientContext.clear();

      OAuth2AuthorizeRequest authorizeRequest =
          OAuth2AuthorizeRequest.withClientRegistrationId(clientId)
              .principal("feign-client") // фиктивный principal
              .build();
      log.info("Got registered client [{}]", authorizeRequest.getClientRegistrationId());
      OAuth2AuthorizedClient authorizedClient =
          clientManager.authorize(authorizeRequest);

      if (authorizedClient == null) {
        throw new IllegalStateException("Не удалось получить токен для " + clientId);
      }

      String token = authorizedClient.getAccessToken().getTokenValue();
      requestTemplate.header("Authorization", "Bearer " + token);
    };
  }
}
