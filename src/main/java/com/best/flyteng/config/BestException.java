package com.best.flyteng.config;

public class BestException extends Exception {
  public BestException(String message) {
    super(message);
  }

  public BestException(String message, Throwable cause) {
    super(message, cause);
  }
}
