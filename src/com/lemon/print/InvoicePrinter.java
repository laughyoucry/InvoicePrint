package com.lemon.print;

import java.awt.*;
import java.awt.print.*;

import com.lemon.vo.template.InvoiceTemplate;
import com.lemon.vo.template.Item;

/**
 * 打印类
 *
 * @author linpeng
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class InvoicePrinter implements Printable {

    /**  */
    public static final float S = 2.834f;

    /**
     * 按照此模版打印
     */
    private InvoiceTemplate template;

    public InvoicePrinter(InvoiceTemplate template) {
        super();
        this.template = template;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component c = null;

        // 转换成Graphics2D
        Graphics2D g2 = (Graphics2D) graphics;

        // 设置打印颜色为蓝色
        g2.setColor(Color.BLUE);

        // 打印起点坐标
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();

        switch (pageIndex) {
            case 0:
                // 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
                // Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
                Font font = new Font("新宋体", Font.PLAIN, 9);
                g2.setFont(font);// 设置字体

                float[] dash1 = { 2.0f };

                // 设置打印线的属性。
                // 1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
                g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
                // g2.setStroke(bs_3);//设置线宽
                float heigth = font.getSize2D();// 字体高度
                // -1- 用Graphics2D直接输出
                // 首字符的基线(右下部)位于用户空间中的 (x, y) 位置处
                // g2.drawLine(10,10,200,300);

                FontMetrics fm = null;
                int strWidth = 0;
                int offset = 0;
                for (Item item : template.getItems()) {
                    fm = g2.getFontMetrics();
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
                    if (item.getId() != null && item.getId().equals("32")) {
                        font = new Font("新宋体", Font.PLAIN, 8);
                        g2.setFont(font);
                    } else {
                        font = new Font("新宋体", Font.PLAIN, 9);
                        g2.setFont(font);
                    }
                    g2.drawString(item.getText(), (int) (item.getX() * S + offset), (int) ((item.getY() + 2) * S));
                }
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }
    }

    /**
     * 打印
     */
    public void doPrint() {
        // 通俗理解就是书、文档
        Book book = new Book();

        // 设置打印格式
        PageFormat pf = new PageFormat();

        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        // 纸张大小
        p.setSize(template.getxSize(), template.getySize());
        // A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
        p.setImageableArea(0, 0, template.getxSize(), template.getySize());
        pf.setPaper(p);

        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(this, pf);

        // 获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();

        // 设置打印类
        job.setPageable(book);

        try {
            // 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
            boolean a = job.printDialog();
            if (a) {
                job.print();
            } else {
                job.cancel();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public InvoiceTemplate getTemplate() {
        return template;
    }

    public void setTemplate(InvoiceTemplate template) {
        this.template = template;
    }
}
