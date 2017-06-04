package com.lemon.comps.base;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by plin on 2017/2/14.
 * 带下滑线的输入框
 */
public class ULTextField extends JTextField{

    public ULTextField(){
        this.setBackground(new Color(0, 0, 0, 0));
        MatteBorder border = new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0));
        this.setBorder(border);
    }
}
