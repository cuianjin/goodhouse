/*
 *  TextUtils.java  2010-05-28
 *  Copyright  Soho, Inc. All rights reserved.
 */
package com.web.webstart.base.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 处理字符的公共方法
 * @author Jason
 */
public class TextUtils {

    /**
     * <p>将迭代内容以特殊符号进行联加
     * @param glue      特殊联加符
     * @param pieces    需处理的迭代
     * @return          处理完成的字符串
     */
    public final static String join(String glue, Iterator pieces) {
        StringBuffer s = new StringBuffer();
        while (pieces.hasNext()) {
            s.append(pieces.next().toString());
            if (pieces.hasNext()) {
                s.append(glue);
            }
        }
        return s.toString();
    }

    /**
     * <p>将数组内容以特殊符号进行联加
     * @param glue      特殊联加符
     * @param pieces    需处理的数组
     * @return          处理完成的字符串
     */
    public final static String join(String glue, String[] pieces) {
        return join(glue, Arrays.asList(pieces).iterator());
    }

    /**
     * <p>将集合对象内容以特殊符号进行联加
     * @param glue      特殊联加符
     * @param pieces    需处理的集合
     * @return          处理完成的字符串
     */
    public final static String join(String glue, Collection pieces) {
        return join(glue, pieces.iterator());
    }

    /**
     * <p>处理字符串，防止为空，以默认值替代
     * @param str           待处理的字符串
     * @param defaultStr    为空时的替代默认值
     * @return              非空时返回原值<br>否则返回默认值
     */
    public final static String dealNull(String str, String defaultStr) {
        return (strSet(str)) ? str : defaultStr;
    }

    /**
     * <p>处理字符串，防止为空,并指定默认值为""
     * @param str           待处理的字符串
     * @return              非空时返回原值<br>否则返回""
     */
    public final static String dealNull(String str) {
        return dealNull(str, "");
    }
    
    /**
     * <p>处理对象，防止为空，以默认值替代
     * @param obj           待处理的对象
     * @param defaultStr    为空时的替代默认值
     * @return              非空时返回原值<br>否则返回默认值
     */
    public final static String dealNull(Object obj, String defaultStr) {
        return (strSet(obj)) ? obj.toString() : defaultStr;
    }

    /**
     * <p>处理对象，防止为空,并指定默认值为""
     * @param obj           待处理的对象
     * @return              非空时返回原值<br>否则返回""
     */
    public final static String dealNull(Object obj) {
        return dealNull(obj, "");
    }

    /**
     * <p>判断此字符串是否为  非空
     * @param str   被判断的字符串
     * @return      若非空，则返回true
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * <p>判断此字符串是否为  空
     * @param str   被判断的字符串
     * @return      若空，则返回true
     */
    public static boolean isEmpty(String str) {
        return !strSet(str);
    }

    /**
     * <p>检测这个字符串是否 非空
     * @param str   字符串内容
     * @return      是否  非空[null,""]
     * <br>true为非空
     */
    private final static boolean strSet(Object obj) {
        return (obj != null) && !"".equals(obj);
    }

    /**
     * md5加密
     */
    public static String md5(String s) {
        try {
            byte[] strTemp = s.getBytes();
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(strTemp);
            byte tmp[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : tmp) {
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args){
//        java.util.Map  map = new java.util.HashMap();
//        map.put("dd", "dd");
//        System.out.println(dealNull("ddd",""));
//    }
}
