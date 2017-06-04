package com.lemon.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.lemon.vo.template.Item;

/**
 * TODO
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UT {

    public static final DecimalFormat df = new DecimalFormat("#,##0.00");

    public static String nvl(String in) {
        return in == null ? "" : in;
    }

    public static String nvl(Object in) {
        return in == null ? "" : in.toString();
    }

    public static String format(Object amount) {
        BigDecimal tmp = null;
        if (amount instanceof BigDecimal) {
            tmp = (BigDecimal) amount;
        } else {
            tmp = new BigDecimal(amount.toString());
        }
        return df.format(tmp);
    }

    public static Item copy(Item tmp) {
        Item result = new Item();
        result.setId(tmp.getId());
        result.setX(tmp.getX());
        result.setY(tmp.getY());
        result.setAlign(tmp.getAlign());
        result.setText(tmp.getText());
        return result;
    }

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("121321323.4");
        BigDecimal b2 = new BigDecimal("111223");
        BigDecimal b3 = new BigDecimal("13.4567");
        System.out.println(format(b1));
        System.out.println(format(b2));
        System.out.println(format(b3));
    }
}
