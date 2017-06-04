package com.lemon.comps.base;

import java.util.Vector;

import javax.swing.*;
import javax.swing.event.CellEditorListener;

/**
 * TODO
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyComboBoxEditor extends DefaultCellEditor {

    public MyComboBoxEditor(Vector<String> items) {
        super(new JAutoComboBox(items));
    }
}