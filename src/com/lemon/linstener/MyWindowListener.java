package com.lemon.linstener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import com.lemon.utils.ConfigUtils;

/**
 * TODO
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyWindowListener implements WindowListener {
    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
        int i = JOptionPane.showConfirmDialog(e.getComponent(), "确定退出？", "提示", JOptionPane.YES_NO_OPTION);
        if (i == 1) {
            return;
        }
        if (i == 0) {
            // 保存数据
            ConfigUtils.save();
            System.exit(0);
        }
    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }
}
