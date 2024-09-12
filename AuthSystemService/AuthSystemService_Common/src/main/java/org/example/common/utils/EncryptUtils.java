package org.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　   @Author: wang.yangtx　@Date:  2019/7/11 10:12
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 */
@Slf4j
public class EncryptUtils {
    public static String concealMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            String phoneNumber = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            return phoneNumber;
        }
        return StringUtils.EMPTY;
    }

    public static String concealIdCardNumber(String idCardNumber) {
        if (StringUtils.isNotBlank(idCardNumber)) {
            String _idCardNumber = idCardNumber.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2");
            return _idCardNumber;
        }
        return StringUtils.EMPTY;
    }

    public static String concealEmail(String email) {
        if (StringUtils.isNotBlank(email)) {
            String _email = email.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
            return _email;
        }
        return StringUtils.EMPTY;
    }

    public static String passwordEncrypt(String password, String type, String key) {
        String encrypt = password;
        try {
            if (StringUtils.equals("md5", type)) {
                encrypt = DigestUtils.md5Hex(password);
            } else if (StringUtils.equals("sha256", type)) {
                encrypt = DigestUtils.sha256Hex(password);
            } else if (StringUtils.equals("aes", type)) {
                encrypt = AESUtils.byteToHexString(AESUtils.encrypt(password, key.getBytes("utf-8")));
            }
        } catch (Exception e) {
            log.error("passwordEncrypt exception", e);
        }
        return encrypt;
    }

}
