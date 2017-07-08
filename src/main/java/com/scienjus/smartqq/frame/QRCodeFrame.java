package com.scienjus.smartqq.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

import static java.lang.Thread.sleep;

/**
 * 二维码显示窗体
 *
 * @author Dilant
 * @date 2017/6/30
 */

public class QRCodeFrame extends JFrame {
    private JLabel label;
    private ImageIcon icon;

    //创建窗体时进行初始化
    public QRCodeFrame() {
        this.setTitle("请打开手机QQ并扫描二维码");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(0, 0, 400, 424);

        this.label = new JLabel(null, null, SwingConstants.CENTER);
        this.label.setOpaque(true);
        this.label.setBackground(Color.BLACK);

        this.add(label);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.waitForQRCode(); //默认为等待二维码状态
    }

    //等待二维码
    public void waitForQRCode() {
        this.label.setIcon(null);
        this.label.setText("请等待程序获得二维码");
    }

    //显示二维码
    public void showQRCode(final String filePath) {
        this.icon = new ImageIcon(filePath);
        this.label.setText(null);
        this.label.setIcon(icon); //二维码大小为165*165
    }

    public static void main(String[] args) {
        //测试代码
        QRCodeFrame qrframe = new QRCodeFrame();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        qrframe.showQRCode("qrcode.png");
    }
}
