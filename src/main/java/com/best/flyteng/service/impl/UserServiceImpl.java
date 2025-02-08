package com.best.flyteng.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.best.flyteng.entity.*;
import com.best.flyteng.mapper.UserMapper;
import com.best.flyteng.service.IUserService;
import com.best.flyteng.utils.RedisUtils;
import com.best.flyteng.utils.TokenUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements IUserService {
  @Resource
  private RedisUtils<String> redisUtils;
  @Resource
  private UserMapper userMapper;
  @Resource
  private BCryptPasswordEncoder encoder;
  @Resource
  private AuthenticationManager authenticationManager;
  @Resource
  private EnvironmentProperty environmentProperty;

  @Override
  @Transactional
  public String registry(UserRegistry userRegistry) {
    if (!userRegistry.getPassword().equals(userRegistry.getConfirmPassword())) {
      return "两次密码不相同";
    }
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getEmail, userRegistry.getEmail());
    long one = userMapper.selectCount(queryWrapper);
    if (one >= 1) {
      return "邮箱已被注册";
    }
    String code = redisUtils.get(userRegistry.getEmail());
    if (ObjUtil.isNull(code) || !userRegistry.getCode().equals(code)) {
      return "验证码无效或已过期";
    }
    User user = new User();
    BeanUtils.copyProperties(userRegistry, user);
    user.setPersona(Roles.USER.getName());
    user.setPassword(encoder.encode(user.getPassword()));
    userMapper.insert(user);
    return "注册成功";
  }

  @Override
  public Map<String, String> login(User user) {
    //进行用户认证
    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    Authentication authenticate = authenticationManager.authenticate(authenticationToken);
    //通过了，生成jwt
    UserLogin loginUser = (UserLogin) authenticate.getPrincipal();
    Long id = loginUser.getUser().getId();
    TokenDTO dto = new TokenDTO(id, loginUser.getUser().getPersona());
    String sign = RandomUtil.randomString(32);
    String jwt = TokenUtils.getToken(dto, sign, environmentProperty.getPublicKey());
    redisUtils.set(String.valueOf(id), sign, 1, TimeUnit.HOURS);
    Map<String, String> map = new HashMap<>();
    map.put("token", jwt);
    map.put("tips", "欢迎 " + loginUser.getUser().getName() + " 登录");
    return map;
  }

}
