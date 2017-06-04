package com.lemon.vo.invoice;

import java.math.BigDecimal;

import com.lemon.vo.base.Goods;

/**
 * Created by plin on 2017/2/12. 购买的商品
 */
public class BuyGoods {

    /** 商品 */
    private Goods goods;

    /** 数量 */
    private BigDecimal quantity;

    /** 总金额（不含税） */
    private BigDecimal total;

    // /** 总税额 */
    // private BigDecimal totalTax;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
