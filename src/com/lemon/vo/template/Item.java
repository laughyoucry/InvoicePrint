package com.lemon.vo.template;

/**
 * Created by plin on 2017/2/12. <br>
 *
 * 模版上的内容 <br>
 *
 * TODO 后续内容再补充<br>
 */
public class Item {

    private String id;

    /** X轴 */
    private float x;

    /** Y轴 */
    private float y;

    /** 打印内容 */
    private String text;

    private String align;

    // /** 字体 */
    // private Font font;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }
}
