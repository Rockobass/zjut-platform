package org.whatever.aha.zjut.base.util;

import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;

import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author Baby_mo
 */
public class RegexUtil {

    public static void checkPhoneNumber(String phoneNumber) {
        String regex = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[235-8]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[0-35-9]\\d{2}|66\\d{2})\\d{6}$";
        if (!Pattern.matches(regex, phoneNumber)) {
            throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
        }
    }

    public static void checkGrade(String grade) {
        String regex = "^(2\\d{3})$";
        if (!Pattern.matches(regex, grade)) {
            throw new AppException(ErrorCode.INVALID_FORMAT);
        }
    }
}
