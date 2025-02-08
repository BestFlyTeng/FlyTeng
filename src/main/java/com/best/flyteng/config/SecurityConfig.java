package com.best.flyteng.config;

import com.best.flyteng.config.chain.RequestHandlerFilter;
import com.best.flyteng.entity.EnvironmentProperty;
import com.best.flyteng.utils.RedisUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Resource
  private EnvironmentProperty environmentProperty;
  private AuthenticationManager authenticationManager;
  @Resource
  private RedisUtils<String> redisUtils;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            // 开启授权保护
            .authorizeHttpRequests(authorize -> authorize
                    // 无需认证的接口
                    .requestMatchers("/static/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/registry").permitAll()
                    .requestMatchers(HttpMethod.POST, "/emails/verify").permitAll()
                    // 其他接口需认证
                    .anyRequest().authenticated()
            )
            .logout(e -> e
                    .logoutUrl("/user/logout")
                    .addLogoutHandler(logoutHandlerOverride())
                    .logoutSuccessHandler(new LogoutSuccessHandlerOverride())
            )
            .exceptionHandling(e -> e
                    .authenticationEntryPoint(new AuthenticationEntryPointImpl())
            )
            .addFilterBefore(new RequestHandlerFilter(environmentProperty), DisableEncodeUrlFilter.class)
            .addFilterBefore(basicAuthenticationFilter(), BasicAuthenticationFilter.class)
            // 禁用 CSRF 防护
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            // 禁用session
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 基于token，不需要session
    return http.build();
  }

  private BasicAuthenticationFilter basicAuthenticationFilter() {
    return new JWTAuthenticationFilter(authenticationManager, environmentProperty, redisUtils);
  }

  private LogoutHandler logoutHandlerOverride() {
    return new LogoutHandlerOverride(environmentProperty, redisUtils);
  }

  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    AuthenticationManager manager = authenticationConfiguration.getAuthenticationManager();
    authenticationManager = manager;
    return manager;
  }

}
