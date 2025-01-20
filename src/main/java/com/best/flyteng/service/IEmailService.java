package com.best.flyteng.service;

import com.best.flyteng.config.BestException;
import com.best.flyteng.entity.email.SendVerifyCode;

public interface IEmailService {
  String sendVerifyCode(SendVerifyCode sendVerifyCode) throws BestException;
}
