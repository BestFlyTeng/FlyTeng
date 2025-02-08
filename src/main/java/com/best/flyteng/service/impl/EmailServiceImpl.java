package com.best.flyteng.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.best.flyteng.config.BestException;
import com.best.flyteng.entity.email.SendVerifyCode;
import com.best.flyteng.service.IEmailService;
import com.best.flyteng.utils.EmailUtils;
import com.best.flyteng.utils.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements IEmailService {
  @Resource
  private EmailUtils emailUtils;
  @Resource
  private RedisUtils<String> redisUtils;

  @Override
  public String sendVerifyCode(SendVerifyCode sendVerifyCode) throws BestException {
    // 重试时间(分钟)
    int retry = 2;
    if (ObjectUtils.isEmpty(redisUtils.get(sendVerifyCode.getTo()))) {
      String code = RandomUtil.randomString(6);
      if (emailUtils.sendEmail(sendVerifyCode.getTo(), "验证码", code)) {
        redisUtils.set(sendVerifyCode.getTo(), code, retry, TimeUnit.MINUTES);
        return "发送成功";
      } else throw new BestException("发送邮件异常");
    }
    return "请在" + retry + "分钟后重试。";
  }
}
