package com.best.flyteng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private String name;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  private String password;
  private String persona;
  private String enabled;
  private LocalDateTime createTime;
}
