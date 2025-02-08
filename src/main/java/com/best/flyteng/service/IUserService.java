package com.best.flyteng.service;

import com.best.flyteng.entity.User;
import com.best.flyteng.entity.UserRegistry;
import com.github.yulichang.extension.mapping.base.MPJDeepService;

import java.util.Map;

public interface IUserService extends MPJDeepService<User> {
  String registry(UserRegistry userRegistry);

  Map<String, String> login(User user);

}
