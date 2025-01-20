package com.best.flyteng.service.impl;

import com.best.flyteng.entity.User;
import com.best.flyteng.mapper.UserMapper;
import com.best.flyteng.service.IUserService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}
