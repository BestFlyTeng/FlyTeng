package com.best.flyteng.entity.result;

import lombok.Getter;

@Getter
public enum ReturnCode {
  RC200(200,"操作成功"),
  RC400(400,"请检查参数是否正确"),
  RC401(401,"未经授权，禁止访问"),
  RC403(403,"您没有权限访问"),
  RC404(404,"没有找到资源"),
  RC500(500,"服务器错误，请联系系统管理员。");
  /**
   * 自定义状态码
   **/
  private final int code;
  /**
   * 自定义描述
   **/
  private final String message;

  ReturnCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

}
