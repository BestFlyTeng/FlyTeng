package com.best.flyteng.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.best.flyteng.config.BestException;
import com.best.flyteng.entity.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class TokenUtils {
  public static String getToken(TokenDTO data, String sign, String publicKey) {
    try{
      return Sm2Util.encrypt(publicKey, JWT.create()
              .addPayloads(JSONObject.parseObject(JSONObject.toJSONString(data), new TypeReference<Map<String, String>>() {
              }))
              .sign(JWTSignerUtil.hs512(sign.getBytes())));
    }catch (Exception e){
      throw new RuntimeException("token生成失败");
    }
  }

  public static boolean verifyToken(String token, String sign, String privateKey) {
    try {
      return JWTUtil.parseToken(Sm2Util.decrypt(privateKey, token)).setKey(sign.getBytes()).verify();
    } catch (Exception e) {
      return false;
    }
  }

  public static TokenDTO getValue(String token, String privateKey) throws BestException {
    try {
      return new ObjectMapper().readValue(JWTUtil.parseToken(Sm2Util.decrypt(privateKey, token)).getPayload().getClaimsJson().toString(), TokenDTO.class);
    } catch (Exception e) {
      log.error("获取token载荷异常");
      return null;
    }
  }
}
