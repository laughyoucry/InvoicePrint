package com.lemon.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.*;

import com.lemon.comps.base.BgPanel;
import com.lemon.comps.base.ULTextField;

/**
 * Created by plin on 2017/2/14.
 */
public class InvoicePrintFrame extends JFrame implements Printable {

    private JLabel areaCodeLabel;
    private JTextField areaCodeField;

    private JLabel checkCodeLabel;
    private JTextField checkCodeField;

    private JLabel drawDate;
    // private DatePicker drawDateField;

    private JLabel invoiceCodeLabel;
    private JTextField invoiceCodeField;

    private JLabel buyCompanyLabel;

    private JButton printButton;
    private JButton repaintBtn;

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }

    public InvoicePrintFrame() {
        JPanel panel = new BgPanel("images/bg2.png");
        panel.setLayout(null);
        this.add(panel);
        areaCodeLabel = new JLabel("发票地区代码:");
        areaCodeField = new ULTextField();

        checkCodeLabel = new JLabel("检验码:");
        checkCodeField = new ULTextField();

        drawDate = new JLabel("开票日期:");
        // drawDateField = new DatePicker(this);
        // setDatePicker(drawDateField, DateUtils.getCurrDate());
        // panel.add(drawDateField);

        invoiceCodeLabel = new JLabel("发票号码:");
        invoiceCodeField = new ULTextField();

        buyCompanyLabel = new JLabel("<html>我<br/>要<br/>换<br/>行<br/>啊<br/>");

        printButton = new JButton(new ImageIcon(getClass().getResource("images/document-print.png")));
        printButton.setToolTipText("image button");
        // </snip>
        printButton.putClientProperty("snippetKey", "Create image button");

        repaintBtn = new JButton("调整重画页面");

        addButtonListener(repaintBtn, this);

        this.setSize(960, 600);

        areaCodeLabel.setBounds(5, 5, 100, 26);
        areaCodeField.setBounds(105, 5, 80, 26);

        checkCodeLabel.setBounds(205, 5, 50, 26);
        checkCodeField.setBounds(260, 5, 160, 26);

        // drawDate.setBounds(480, 5, 60, 26);
        // drawDateField.setBounds(540, 5, 130, 30);

        invoiceCodeLabel.setBounds(680, 5, 60, 26);
        invoiceCodeField.setBounds(740, 5, 80, 26);

        buyCompanyLabel.setBounds(5, 45, 10, 100);

        printButton.setBounds(200, 150, 40, 40);
        repaintBtn.setBounds(200, 200, 100, 30);

        panel.add(areaCodeLabel);
        panel.add(areaCodeField);

        panel.add(checkCodeLabel);
        panel.add(checkCodeField);

        // panel.add(drawDate);
        // panel.add(drawDateField);

        panel.add(invoiceCodeLabel);
        panel.add(invoiceCodeField);

        panel.add(buyCompanyLabel);

        panel.add(printButton);
        panel.add(repaintBtn);
    }

    private void addButtonListener(JButton btn, final Component comp) {
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rectangle bounds = checkCodeField.getBounds();
                Point location = bounds.getLocation();
                location.setLocation(location.getX() + 10, location.getY() + 10);
                bounds.setLocation(location);
                checkCodeField.setBounds(bounds);
                comp.repaint();
            }
        });
    }
    //
    // /**
    // * 时间设置
    // *
    // * @param datePicker
    // */
    // private void setDatePicker(DatePicker datePicker, Date date) {
    // datePicker.setLocale(Locale.CHINESE);//设置显示语言
    // datePicker.setPattern("yyyy年MM月dd日");// 设置日期格式化字符串
    // datePicker.setDate(date);
    // datePicker.setEditorable(false);// 设置是否可编辑
    // datePicker.setBackground(Color.gray);// 设置背景色
    // datePicker.setForeground(Color.yellow);// 设置前景色
    // datePicker.setPreferredSize(new Dimension(126, 30));// 设置大小
    // datePicker.setSize(126, 30);
    // datePicker.setTextAlign(DatePicker.CENTER);// 设置文本水平方向位置：DatePicker.CENTER 水平居中，DatePicker.LEFT 水平靠左
    // // DatePicker.RIGHT 水平靠右
    // }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InvoicePrintFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoicePrintFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoicePrintFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoicePrintFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                InvoicePrintFrame sg = new InvoicePrintFrame();
                sg.setVisible(true);
                sg.setResizable(true);
                sg.setLocationRelativeTo(null);
                sg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
