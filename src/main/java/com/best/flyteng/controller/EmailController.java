package com.best.flyteng.controller;

import cn.hutool.core.util.RandomUtil;
import com.best.flyteng.config.BestException;
import com.best.flyteng.entity.email.SendVerifyCode;
import com.best.flyteng.service.IEmailService;
import com.best.flyteng.utils.EmailUtils;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {
  @Resource
  private IEmailService emailService;

  @PostMapping("/verify")
  public String verifyCode(@RequestBody @Validated SendVerifyCode sendVerifyCode) throws BestException {
    return emailService.sendVerifyCode(sendVerifyCode);
  }

}
