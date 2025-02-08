package com.best.flyteng.controller;

import com.best.flyteng.entity.Roles;
import com.best.flyteng.entity.User;
import com.best.flyteng.entity.UserRegistry;
import com.best.flyteng.service.IUserService;
import com.best.flyteng.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
  @Resource
  private IUserService userService;

  @PostMapping("/registry")
  public String registry(@RequestBody @Validated UserRegistry userRegistry) {
    return userService.registry(userRegistry);
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody @Validated User user) {
    return userService.login(user);
  }
  @PostMapping("/test")
  public String test() {
    return "123";
  }
}
