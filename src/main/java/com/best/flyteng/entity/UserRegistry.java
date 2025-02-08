package com.best.flyteng.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistry {
  @NotBlank
  private String name;
  @NotBlank
  private String password;
  @NotBlank
  private String confirmPassword;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(min = 6, max = 6)
  private String code;
}
