package com.best.flyteng.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EnvironmentProperty {
  @Value("${sm.private.key}")
  private String privateKey;
}
