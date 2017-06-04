package com.lemon.comps.base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

/**
 * 可输入的筛选下拉框
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JAutoComboBox extends JComboBox {

    private AutoCompleter completer;

    public JAutoComboBox() {
        super();
        addCompleter();
    }

    public JAutoComboBox(ComboBoxModel cm) {
        super(cm);
        addCompleter();
    }

    public JAutoComboBox(Object[] items) {
        super(items);
        addCompleter();
    }

    public JAutoComboBox(List v) {
        super((Vector) v);
        addCompleter();
    }

    public void setItems(Vector<String> items) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(items);
        this.setModel(model);
        addCompleter();
    }

    private void addCompleter() {
        setEditable(true);
        completer = new AutoCompleter(this);
    }

    public void autoComplete(String str) {
        this.completer.autoComplete(str);
    }

    public String getText() {
        return ((JTextField) getEditor().getEditorComponent()).getText();
    }

    public void setText(String text) {
        ((JTextField) getEditor().getEditorComponent()).setText(text);
    }

    public boolean containsItem(String itemString) {
        for (int i = 0; i < this.getModel().getSize(); i++) {
            String _item = " " + this.getModel().getElementAt(i);
            if (_item.equals(itemString))
                return true;
        }
        return false;
    }
}

class AutoCompleter implements KeyListener {

    private JComboBox owner = null;
    private JTextField editor = null;

    private ComboBoxModel model = null;

    public AutoCompleter(JComboBox comboBox) {
        owner = comboBox;
        editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(this);
        model = comboBox.getModel();
        // owner.addItemListener(this);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        char ch = e.getKeyChar();
        if (ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch) || ch == KeyEvent.VK_DELETE)
            return;

        String str = editor.getText();
        if (str.length() == 0 || str.trim().equals(""))
            return;
        autoComplete(str);
    }

    /**
     * 自动完成。根据输入的内容，在列表中找到相似的项目.
     */
    protected void autoComplete(String str) {
        Object[] opts;
        opts = getMatchingOptions(str);
        if (owner != null) {
            model = new DefaultComboBoxModel(opts);
            owner.setModel(model);
        }
        if (opts.length > 0) {
            if (owner != null) {
                try {
                    owner.showPopup();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        editor.setText(str.trim());
    }

    /**
     *
     * 找到相似的项目, 并且将之排列到数组的最前面。
     *
     * @param str
     * @return 返回所有项目的列表。
     */
    protected Object[] getMatchingOptions(String str) {
        List v = new Vector();
        List v1 = new Vector();

        for (int k = 0; k < model.getSize(); k++) {
            Object itemObj = model.getElementAt(k);
            if (itemObj != null) {
                String item = itemObj.toString();
                if (item.indexOf(str.trim()) != -1)
                    v.add(model.getElementAt(k));
                else
                    v1.add(model.getElementAt(k));
            } else
                v1.add(model.getElementAt(k));
        }
        for (int i = 0; i < v1.size(); i++) {
            v.add(v1.get(i));
        }
        if (v.isEmpty())
            v.add(str);
        return v.toArray();
    }
}
