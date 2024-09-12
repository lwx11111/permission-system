package org.example.common.utils;

import java.math.BigDecimal;

public class LongBigDecimalUtils {
    /**
     * bigDecimal  转换成  Long类型
     *
     * @param b
     * @return
     */
    public static Long bigDecimalToLong(BigDecimal b) {
        BigDecimal c = new BigDecimal(String.valueOf(b));
        Long l = c.longValue();
        return l;
    }

    /**
     * bigDecimal  转换成  int类型
     *
     * @param b
     */

    public static void bigDecimalToInt(BigDecimal b) {
        BigDecimal c = new BigDecimal(String.valueOf(b));
        int i = c.intValue();
    }

    /**
     * int 转换成 BigDecimal 数据类型
     *
     * @param i
     * @return
     */

    public static BigDecimal intToBigDecimal(int i) {
        BigDecimal b = new BigDecimal(i);
        return b;
    }


    /**
     * long转换成 BigDecimal 数据类型
     *
     * @param i
     * @return
     */


    public static BigDecimal longToBigDecimal(Long i) {
        BigDecimal b = new BigDecimal(i);
        return b;
    }
}
