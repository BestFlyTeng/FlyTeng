package com.best.flyteng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.best.flyteng.entity.*;
import com.best.flyteng.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Resource
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //查询用户信息
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getEmail, username);
    User user = userMapper.selectOne(queryWrapper);
    //如果没有查询到用户，就抛出异常
    if (Objects.isNull(user)) {
      throw new BadCredentialsException("用户名或者密码错误！");
    }
    //将数据封装成UserDetails
    return new UserLogin(user);
  }


}
