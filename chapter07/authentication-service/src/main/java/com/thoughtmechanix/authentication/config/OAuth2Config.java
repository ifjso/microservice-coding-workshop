package com.thoughtmechanix.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@RequiredArgsConstructor
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()
        .withClient("eagleeye")
        .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("thisissecret"))
        .authorizedGrantTypes("refresh_token", "password", "client_credentials")
        .scopes("webclient", "mobileclient");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }
}
