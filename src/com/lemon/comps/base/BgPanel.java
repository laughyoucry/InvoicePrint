package com.lemon.comps.base;

import javax.swing.*;
import java.awt.*;

/**
 * Created by plin on 2017/2/14.
 */
public class BgPanel extends JPanel{

    private ImageIcon icon;

    private Image image;

    public BgPanel(){

    }

    public BgPanel(String imgFile){
        icon = new ImageIcon(getClass().getResource(imgFile));
        image = icon.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(), this);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
