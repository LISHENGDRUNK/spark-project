package com.spark.electricity.util;

/**
 * @ClassName StringUtils
 * @Time : 2020/8/11 0011 15:04
 * @Author : lisheng
 * @Description: 字符串工具类
 **/
public class StringUtils {

    /**
     * @return boolean
     * @Author lisheng
     * @Description //TODO 判断字符串是否为空
     * @Date 15:06 2020/8/11 0011
     * @Param [str]
     **/
    public static boolean isEmpty(String str) {
        return str == null && "".equals(str);
    }

    /**
     * @return boolean
     * @Author lisheng
     * @Description //TODO 判断字符串是否不为空
     * @Date 15:07 2020/8/11 0011
     * @Param [str]
     **/
    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }

    /**
     * @return java.lang.String
     * @Author lisheng
     * @Description //TODO 截断字符串两侧的逗号
     * @Date 15:09 2020/8/11 0011
     * @Param [str]
     **/

    public static String trimComma(String str) {
        if (str.startsWith(",")) {
            str = str.substring(1);
        }
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * @return java.lang.String
     * @Author lisheng
     * @Description //TODO 补全两位数字
     * @Date 15:11 2020/8/11 0011
     * @Param [str]
     **/
    public static String fulfuill(String str) {
        if (str.length() == 2) {
            return str;
        } else {
            return "0" + str;
        }
    }

    /**
     * @return java.lang.String
     * @Author lisheng
     * @Description //TODO 从拼接的字符串中提取字段
     * @Date 15:15 2020/8/11 0011
     * @Param [str, delimiter, field]
     **/
    public static String getFieldFromConcatString(String str, String delimiter, String field) {
        String[] fields = str.split(delimiter);
        for (String concatField : fields) {
            String fieldName = concatField.split("=")[0];
            String fieldValue = concatField.split("=")[1];
            if (fieldName.endsWith(field)) {
                return fieldValue;
            }
        }
        return null;
    }

    /**
     * @return java.lang.String
     * @Author lisheng
     * @Description //TODO 从拼接的字符串中给字段设置值
     * @Date 15:22 2020/8/11 0011
     * @Param [str, delimiter, field, newFieldValue]
     **/
    public static String setFieldInConcatString(String str, String delimiter, String field, String newFieldValue) {

        String[] fields = str.split(delimiter);

        for (int i = 0; i < field.length(); i++) {
            String fieldName = fields[i].split("=")[0];
            if (fieldName.equals(field)) {
                String concatField = fieldName + "=" + newFieldValue;
                fields[i] = concatField;
                break;
            }
        }
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i]);
            if (i < fields.length - 1) {
                sb.append("|");
            }
        }
        return sb.toString();
    }

}
