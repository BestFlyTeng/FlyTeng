package com.best.flyteng.utils;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;

/**
 * 加解密工具类
 */
public class Sm2Util {
  /**
   * 加密
   *
   * @param publicKey 公钥
   * @param data      明文
   * @return 密文
   */
  public static String encrypt(String publicKey, String data) {
    return SmUtil.sm2(null, publicKey)
            .encryptHex(data.getBytes(), KeyType.PublicKey)
            // 加密后，密文前面会有04，需要去掉
            .substring(2);
  }

  /**
   * 解密
   *
   * @param privateKey 私钥
   * @param data       密文
   * @return 明文
   */
  public static String decrypt(String privateKey, String data) {
    // 前端加密是没有04的，所以解析的时候要加04
    data = "04" + data;
    return SmUtil.sm2(privateKey, null)
            .decryptStr(data, KeyType.PrivateKey);
  }
}