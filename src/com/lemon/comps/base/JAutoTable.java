package com.lemon.comps.base;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 * TODO
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JAutoTable extends JTable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    private int myRow = -1, myCol = -1;
    TableCellEditor myEditor;

    public void setComboCell(int r, int c, TableCellEditor ce) {
        this.myRow = r;
        this.myCol = c;
        this.myEditor = ce;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        System.out.println(row + "," + column + ";" + myRow + "," + myCol + "," + myEditor);
        if (row == myRow && column == myCol && myEditor != null)
            return myEditor;
        return super.getCellEditor(row, column);
    }

    public static void main(String[] args) {
        JAutoTable table = new JAutoTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("A", new Object[] { "item1","item3" });
        model.addColumn("B", new Object[] { "item2","item4" });
        String[] values = new String[] { "1", "2", "3" };
        table.setComboCell(1, 1, new DefaultCellEditor(new JComboBox(values)));
        JScrollPane jp=new JScrollPane(table);
        jp.setViewportView(table);
        jp.setSize(400,300);
        JFrame jf=new JFrame();
        jf.getContentPane().add(jp);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,300);
        jf.setVisible(true);
    }
}
