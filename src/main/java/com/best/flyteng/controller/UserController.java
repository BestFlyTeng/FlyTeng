package com.best.flyteng.controller;

import com.best.flyteng.entity.User;
import com.best.flyteng.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
  @Resource
  private IUserService userService;

  @PostMapping("/registry")
  public String registry() {
    return null;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody User user) {
    HashMap<String, String> map = new HashMap<>();
    map.put("username", "admin");
    return map;
  }
}
