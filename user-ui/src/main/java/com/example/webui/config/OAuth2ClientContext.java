package com.example.webui.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OAuth2ClientContext {

  private static final ThreadLocal<String> clientIdHolder = new ThreadLocal<>();

  public static void setClientId(String clientId) {
    clientIdHolder.set(clientId);
  }

  public static String getClientId() {
    return clientIdHolder.get();
  }

  public static void clear() {
    clientIdHolder.remove();
  }
}