package com.lemon.vo.base;

import java.io.Serializable;

/**
 * Created by plin on 2017/2/12. 商品的基本信息
 */
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 唯一id */
    private int id;

    /** 商品名称 */
    private String name;

    /** 规格型号 */
    private String modeType;

    /** 计量单位（个、支、条...） */
    private String unit;

    /** 单价（不含税） */
    private Float price;

    // /** 单价含税 */
    // private BigDecimal priceWithTax;

    // /** 金额（不含税） */
    // private BigDecimal total;

    // /** 税率 */
    // private BigDecimal taxRate;

    // /** 税额 */
    // private BigDecimal totalTax;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModeType() {
        return modeType;
    }

    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Goods() {
    }

    public Goods(int id) {
        super();
        this.id = id;
    }

    public Goods(int id, String name, String modeType, String unit, Float price) {
        this.id = id;
        this.name = name;
        this.modeType = modeType;
        this.unit = unit;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Goods goods = (Goods) o;

        return id == goods.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
