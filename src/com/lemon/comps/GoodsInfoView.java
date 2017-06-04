/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lemon.comps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Set;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.lemon.utils.ConfigUtils;
import com.lemon.vo.base.Goods;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("ALL")
public class GoodsInfoView extends javax.swing.JPanel {

    public static final String INDEX_NAME = ConfigUtils.INDEX_NAME[2];

    public static final String[] COL_NAMES = { "序号", "商品名称", "规格型号", "单位", "单价" };

    private static boolean isInit = false;

    /**
     * Creates new form SaleCompanyUI
     */
    public GoodsInfoView() {
        initComponents();
        isInit = true;
        initData();
        isInit = false;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Set<Integer> keys = ConfigUtils.goodsMap.keySet();
        for (Integer id : keys) {
            Goods goods = ConfigUtils.goodsMap.get(id);
            model.addRow(new Object[] { goods.getId(), goods.getName(), goods.getModeType(), goods.getUnit(), goods.getPrice() });
        }
    }

    /**
     * 重新加载数据
     */
    public void reInitData() {
        isInit = true;
        Set<Integer> keys = ConfigUtils.goodsMap.keySet();
        int row = keys.size();
        int col = COL_NAMES.length;
        Vector<Object> rowVector = new Vector<Object>();
        for (int id : keys) {
            Vector<Object> colVector = new Vector<Object>();
            Goods goods = ConfigUtils.goodsMap.get(id);
            colVector.add(goods.getId());
            colVector.add(goods.getName());
            colVector.add(goods.getModeType());
            colVector.add(goods.getUnit());
            colVector.add(goods.getPrice());
            rowVector.add(colVector);
        }
        Vector<String> nameVector = new Vector<String>(Arrays.asList(COL_NAMES));
        model.setDataVector(rowVector, nameVector);
        jTable3.repaint();
        isInit = false;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        newBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 400));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, COL_NAMES) {
            Class[] types = new Class[] { java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.Float.class };
            boolean[] canEdit = new boolean[] { false, true, true, true, true };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable3.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        model = (DefaultTableModel) jTable3.getModel();
        model.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (isInit) {
                    return;
                }
                int col = e.getColumn();
                if (col == -1) {
                    return;
                }
                int row = e.getFirstRow();
                System.out.println("row is " + row + ">>>>>>>>>>>>>>>>>>> col is " + col);
                Object index = model.getValueAt(row, 0);
                System.out.println("index is >>>>>>>>>>>>>>> : " + index);
                Goods goods = new Goods((Integer) index, (String) model.getValueAt(row, 1), (String) model.getValueAt(row, 2),
                        (String) model.getValueAt(row, 3), (Float) model.getValueAt(row, 4));
                ConfigUtils.goodsMap.put((Integer) index, goods);
            }
        });

        newBtn.setText("新增");
        newBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int index = ConfigUtils.getIndex(INDEX_NAME);
                model.addRow(new Object[] { index });
                ConfigUtils.goodsMap.put(index, new Goods(index));
            }
        });

        delBtn.setText("删除");
        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int[] rows = jTable3.getSelectedRows();
                for (int i = rows.length - 1; i >= 0; i--) {
                    // 获取id
                    Integer index = (Integer) model.getValueAt(rows[i], 0);
                    model.removeRow(rows[i]);
                    ConfigUtils.goodsMap.remove(index);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup().addComponent(newBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(delBtn).addGap(0, 0,
                                                        Short.MAX_VALUE)))
                                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(newBtn).addComponent(delBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE).addContainerGap()));
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JButton delBtn;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton newBtn;
    private DefaultTableModel model;
    // End of variables declaration
}
