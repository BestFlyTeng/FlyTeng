package com.best.flyteng.entity;

import lombok.*;

@AllArgsConstructor
@Getter
public enum Roles {
  ADMIN("ADMIN"),
  USER("USER");
  private final String name;
}
