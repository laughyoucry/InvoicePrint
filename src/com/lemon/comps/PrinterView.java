package com.lemon.comps;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import com.lemon.vo.template.Item;

/**
 * TODO
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PrinterView extends JPanel {

    /**  */
    public static final float S = 2.834f;

    private List<Item> items;

    public PrinterView(List<Item> items) {
        super();
        this.items = items;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("宋体", Font.PLAIN, 12));
        FontMetrics fm = null;
        int strWidth = 0;
        int offset = 0;
        for (Item item : this.items) {
            fm = g.getFontMetrics();
            strWidth = fm.stringWidth(item.getText());
            if (item.getAlign().equalsIgnoreCase("center")) {
                // 居中
                offset = -strWidth / 2;
            } else if (item.getAlign().equalsIgnoreCase("right")) {
                // 右对齐
                offset = -strWidth;
            } else {
                // 默认左对齐
                offset = 0;
            }
            g.drawString(item.getText(), (int) (item.getX() * S * 1.5 + offset), (int) (item.getY() * S * 1.2));
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
