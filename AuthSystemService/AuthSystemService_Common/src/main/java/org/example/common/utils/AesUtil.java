package org.example.common.utils;

import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesUtil {
    /**
     * AES解密
     * @param encryptStr 密文
     * @param decryptKey 秘钥，必须为16个字符组成
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String encryptStr,String key) throws Exception {
        if (StringUtils.isEmpty(encryptStr) || StringUtils.isEmpty(key)) {
            return null;
        }

        byte[] encryptByte = hex2byte(encryptStr);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(hex2byte(key), "AES"));

        byte[] decryptBytes = cipher.doFinal(encryptByte);

        return new String(decryptBytes);
    }

    /**
     * AES加密
     * @param content 明文
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String content,String key) throws Exception {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(key)) {
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(hex2byte(key), "AES"));

        byte[] encryptStr = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return bytes2Hex(encryptStr);
    }

    /**
     * byte数组 转换成 16进制小写字符串
     */
    private static String bytes2Hex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
        {
            String he = Integer.toHexString(bytes[i] & 0xFF);
            if (he.length() == 1)
            {
                he = '0' + he;
            }
            hex.append(he);
        }
        return hex.toString();
    }

    /**
     * 16进制String 转换成 byte数组
     */
    private static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }
}
