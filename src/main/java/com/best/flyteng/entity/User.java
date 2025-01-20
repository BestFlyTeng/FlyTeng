package com.best.flyteng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private String nickname;
  private String name;
  private String email;
  private String password;
  private String persona;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(persona));
  }

  @Override
  public String getUsername() {
    return this.name;
  }
}
