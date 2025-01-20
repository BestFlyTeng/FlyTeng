package com.best.flyteng.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherController {
  @PostMapping("/test")
  public String test() {
    return "test";
  }
}
