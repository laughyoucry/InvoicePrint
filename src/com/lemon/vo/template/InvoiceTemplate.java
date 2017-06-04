package com.lemon.vo.template;

import java.util.List;

/**
 * Created by plin on 2017/2/12.
 */
public class InvoiceTemplate {
    
    public static final int WIDTH = 544;
    public static final int HEIGHT = 286;

    /** 唯一id */
    private int id;

    /** 模版名称 */
    private String name;

    /** 标签内容 */
    private List<Item> items;

    /** 背景图片（要真实大小） */
    private String background;

    /** x轴规格 */
    private int xSize = WIDTH;

    /** y轴规格 */
    private int ySize = HEIGHT;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public double getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceTemplate that = (InvoiceTemplate) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
