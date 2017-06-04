package com.lemon.utils;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by plin on 2017/2/12. 有关金额的工具
 */
public class AmountUtil {

    public static char[] NUMS = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };

    public static char[] INTEGER_WORDS = { '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿' };

    public static char[] DECIMAL_WORDS = { '角', '分' };

    public static char ZERO = '零';

    public static char WHOLE = '整';

    /**
     * 人名币金额转换为大写
     * 
     * @param amount 金额
     * @return 大写人民币金额
     */
    public static String getAmountInWords(BigDecimal amount) {
        if (amount == null) {
            return "";
        }
        String words = amount.toString();
        // 零元整
        if ("0".equals(words)) {
            // 零圆整
            return "" + ZERO + INTEGER_WORDS[0] + WHOLE;
        }
        // 结果
        StringBuilder result = new StringBuilder();

        // 此处注意小数点split需要转义
        String[] args = words.split("\\.");
        // 整数部分
        String integer = args[0];
        if (!integer.equals("0")) {
            int intLen = integer.length();
            // 最高10亿
            if (intLen > 9) {
                return "超出本软件的显示范围";
            }
            int n = 0;
            // 前一个是否有零
            boolean hasZero = false;
            for (int i = 0; i < intLen; i++) {
                n = Integer.parseInt(integer.charAt(i) + "");
                if (n != 0) {
                    if (hasZero && (intLen - i) != 4 && (intLen - i) != 0) {
                        result.append(ZERO);
                    }
                    hasZero = false;
                    result.append(NUMS[n]);
                    result.append(INTEGER_WORDS[intLen - i - 1]);
                } else {
                    hasZero = true;
                    // 万元位
                    if (((intLen - i) == 5 && INTEGER_WORDS[8] != result.charAt(result.length() - 1)) || (intLen - i) == 1) {
                        result.append(INTEGER_WORDS[intLen - i - 1]);
                        hasZero = false;
                    }
                }
            }
        }

        // 小数部分，软件上金额显示只有两位（角分）
        String decimal = "";
        if (args.length == 2 && (decimal = args[1]).length() > 0 && !decimal.equals("0") && !decimal.equals("00")) {
            if (decimal.length() == 1) {
                result.append(NUMS[Integer.parseInt(decimal.charAt(0) + "")]);
                result.append(DECIMAL_WORDS[0]);
            } else if (decimal.length() >= 2) {
                result.append(NUMS[Integer.parseInt(decimal.charAt(0) + "")]);
                result.append(DECIMAL_WORDS[0]);
                if (decimal.charAt(1) != '0'){
                    result.append(NUMS[Integer.parseInt(decimal.charAt(1) + "")]);
                    result.append(DECIMAL_WORDS[1]);
                }
            }
        } else {
            result.append(WHOLE);
        }

        return result.toString();
    }

    /**
     * 通过税率和税后单价计算税前单价
     * 税前单价 = 税后单价/（1+税率）
     * @param priceWithTax 税后单价
     * @param taxRate 税率
     * @return 税前单价
     */
    public static BigDecimal calcPriceWithOutTax(BigDecimal priceWithTax, BigDecimal taxRate){
        if (priceWithTax == null || taxRate == null){
            return null;
        }
        return priceWithTax.divide(BigDecimal.ONE.add(taxRate), 10, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 通过税率和税前单价计算税后单价
     * 税后单价 = 税前单价*（1+税率）
     * @param priceWithOutTax 税前单价
     * @param taxRate 税率
     * @return 税后单价
     */
    public static BigDecimal calcPriceWithTax(BigDecimal priceWithOutTax, BigDecimal taxRate){
        if (priceWithOutTax == null || taxRate == null){
            return null;
        }
        BigDecimal result = priceWithOutTax.multiply(BigDecimal.ONE.add(taxRate), MathContext.DECIMAL64);
        return result.setScale(10, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String args[]) {
//        System.out.println(getAmountInWords(new BigDecimal("2.567")));
//        System.out.println(getAmountInWords(new BigDecimal("0034.67")));
//        System.out.println(getAmountInWords(new BigDecimal("5007")));
//        System.out.println(getAmountInWords(new BigDecimal("10900")));
//        System.out.println(getAmountInWords(new BigDecimal("607000.11")));
//        System.out.println(getAmountInWords(new BigDecimal("9.05")));
//        System.out.println(getAmountInWords(new BigDecimal("6572.56")));
//
//        System.out.println(getAmountInWords(new BigDecimal("5017")));
//        System.out.println(getAmountInWords(new BigDecimal("20105017")));
//        System.out.println(getAmountInWords(new BigDecimal("20005017")));
//
//        // 整数
//        System.out.println(getAmountInWords(new BigDecimal("0"))); // 零元整
//        System.out.println(getAmountInWords(new BigDecimal("123"))); // 壹佰贰拾叁元整
//        System.out.println(getAmountInWords(new BigDecimal("1000000"))); // 壹佰万元整
//        System.out.println(getAmountInWords(new BigDecimal("100000001"))); // 壹亿零壹元整
//        System.out.println(getAmountInWords(new BigDecimal("100000000"))); // 壹亿元整
//        System.out.println(getAmountInWords(new BigDecimal("901100101"))); // 玖亿零壹佰壹拾万零壹佰零壹元整
//        System.out.println(getAmountInWords(new BigDecimal("110101010"))); // 壹亿壹仟零壹拾万壹仟零壹拾元整
//
//        // 小数
//        System.out.println(getAmountInWords(new BigDecimal("0.94"))); // 壹角贰分
//        System.out.println(getAmountInWords(new BigDecimal("123.34"))); // 壹佰贰拾叁元叁角肆分
//        System.out.println(getAmountInWords(new BigDecimal("1000000.56"))); // 壹佰万元伍角陆分
//        System.out.println(getAmountInWords(new BigDecimal("100000001.78"))); // 壹亿零壹元柒角捌分
//        System.out.println(getAmountInWords(new BigDecimal("100000000.90"))); // 壹亿元玖角
//        System.out.println(getAmountInWords(new BigDecimal("101100101.00"))); // 壹亿零壹佰壹拾万零壹佰零壹元整
//        System.out.println(getAmountInWords(new BigDecimal("110101010.10"))); // 壹亿壹仟零壹拾万壹仟零壹拾元壹角

        BigDecimal result = calcPriceWithOutTax(new BigDecimal("10300"), new BigDecimal("0.03"));
        System.out.println(result);
        System.out.println(calcPriceWithTax(result, new BigDecimal("0.03")));
    }
}
