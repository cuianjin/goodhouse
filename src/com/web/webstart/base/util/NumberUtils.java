package com.web.webstart.base.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtils {

	/**
	 * BigDecimal类型转换
	 * @param obj
	 * @return
	 */
	public static BigDecimal parseBigDecimal(Object obj){
		BigDecimal defaultValue = new BigDecimal("0");
        if (obj == null || "".equals(obj)) {
            return defaultValue;
        }
        try {
            return new BigDecimal((String)obj);
        } catch (Exception e) {
            return defaultValue;
        }
	}

	/**
	 * BigInteger类型转换
	 * @param obj
	 * @return
	 */
	public static Long parseBigInteger2Long(Object obj){
		Long defaultValue = new Long(0l);
        if (obj == null || "".equals(obj)) {
            return defaultValue;
        }
        try {
            return ((BigInteger)obj).longValue();
        } catch (Exception e) {
            return defaultValue;
        }
	}

    /**
     * 双精度数字格式转换
     * @param obj 原始对象
     * @return 转换后双精度数字
     */
    public static double parseDouble(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        try {
            return new Double(obj.toString()).doubleValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * int类型数字格式转换
     * @param obj 原始对象
     * @return 转换后int数字
     */
    public static int parseInt(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        try {
            return new Integer(obj.toString()).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 单精度类型数字格式转换
     * @param obj 原始对象
     * @return 转换后单精度数字
     */
    public static float parseFloat(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        try {
            return new Float(obj.toString()).floatValue();
        } catch (Exception e) {
            return 0;
        }
    }
	
}
