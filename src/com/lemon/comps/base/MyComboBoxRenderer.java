package com.lemon.comps.base;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 * TODO
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyComboBoxRenderer extends JAutoComboBox implements TableCellRenderer {

    public MyComboBoxRenderer(Vector<String> items) {
        super(items);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        // Select the current value
        setSelectedItem(value);
        System.out.println(value);
        return this;
    }
}
