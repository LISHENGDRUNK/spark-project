package com.spark.electricity.util;

/**
 * @ClassName ValidUtils
 * @Time : 2020/8/11 0011 15:24
 * @Author : lisheng
 * @Description: 校验工具类
 **/
public class ValidUtils {

    /**
     * @return boolean
     * @Author lisheng
     * @Description //TODO 校验数据中指定字段，是否在指定范围内
     * @Date 15:33 2020/8/11 0011
     * @Param [data, dataField, parameter, startParamField, endParamField]
     **/
    public static boolean between(String data, String dataField, String parameter, String startParamField, String endParamField) {

        String startParamFieldStr = StringUtils.getFieldFromConcatString(parameter, "\\|", startParamField);
        String endParamFieldStr = StringUtils.getFieldFromConcatString(parameter, "\\|", endParamField);
        if (startParamFieldStr == null || endParamFieldStr == null) {
            return false;
        }
        Integer startParamFieldVlue = Integer.valueOf(startParamFieldStr);
        Integer endParamFieldVlue = Integer.valueOf(endParamFieldStr);
        String dataFieldStr = StringUtils.getFieldFromConcatString(data, "\\|", dataField);
        if (dataFieldStr != null) {
            Integer dataFieldValue = Integer.valueOf(dataFieldStr);
            if (dataFieldValue >= startParamFieldVlue && dataFieldValue < endParamFieldVlue) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @return boolean
     * @Author lisheng
     * @Description //TODO 校验数据中的指定字段，是否有值与参与字段的值相同
     * @Date 15:41 2020/8/11 0011
     * @Param [data, dataField, parameter, paramField]
     **/
    public static boolean in(String data, String dataField, String parameter, String paramField) {
        String paramFieldValue = StringUtils.getFieldFromConcatString(parameter, "\\|", paramField);
        if (paramFieldValue == null) {
            return false;
        }
        String[] paramFieldValueSplited = paramFieldValue.split(",");

        String dataFieldValue = StringUtils.getFieldFromConcatString(data, "\\|", dataField);
        if (dataFieldValue != null) {
            String[] dataFieldValueSplited = dataFieldValue.split(",");
            for (String singleDataFieldValue : dataFieldValueSplited) {
                for (String singleParamFieldValue : paramFieldValueSplited) {
                    if (singleDataFieldValue.equals(singleParamFieldValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return boolean
     * @Author lisheng
     * @Description //TODO 校验数据中指定字段，是否在指定范围内
     * @Date 15:43 2020/8/11 0011
     * @Param [data, dataField, parameter, paramField]
     **/
    public static boolean equal(String data, String dataField, String parameter, String paramField) {
        String paramFieldValue = StringUtils.getFieldFromConcatString(parameter, "\\|", paramField);
        if (paramFieldValue == null) {
            return false;
        }

        String dataFieldValue = StringUtils.getFieldFromConcatString(data, "\\|", dataField);
        if (dataFieldValue != null) {
            if (dataFieldValue.equals(paramFieldValue)) {
                return true;
            }
        }
        return false;
    }
}
