package com.best.flyteng.config;

import com.best.flyteng.config.chain.RequestHandlerFilter;
import com.best.flyteng.entity.EnvironmentProperty;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Resource
  private EnvironmentProperty environmentProperty;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            // 开启授权保护
            .authorizeHttpRequests(authorize -> authorize
                    // 无需认证的接口
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/registry").permitAll()
                    .requestMatchers(HttpMethod.POST, "/emails/verify").permitAll()
                    // 其他接口需认证
                    .anyRequest().authenticated()
            )
            .exceptionHandling(e -> e
                    .authenticationEntryPoint(new AuthenticationEntryPointImpl())
            )
            .addFilterBefore(new RequestHandlerFilter(environmentProperty), DisableEncodeUrlFilter.class)
            // 禁用 CSRF 防护
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            // 禁用session
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 基于token，不需要session
    return http.build();
  }

}
