package com.best.flyteng.controller;

import com.alibaba.fastjson.JSONObject;
import com.best.flyteng.service.IPersonasService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/personas")
public class PersonasController {
  @Resource
  private IPersonasService personasService;

}
