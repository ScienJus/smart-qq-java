package com.scienjus.smartqq;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class QrCodeFrame extends JFrame{

    private static final long serialVersionUID = 6585257563163019343L;

    private JLabel lblQr;

    public QrCodeFrame() throws HeadlessException {
        initWindow();
        initControls();
    }

    public QrCodeFrame(Image qrImage) throws HeadlessException{
        this();
        setQrImage(qrImage);
    }

    private void initWindow(){
        setTitle("二维码登录");
        setSize(250,230);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initControls(){
        lblQr = new JLabel();
        lblQr.setSize(170,170);
        lblQr.setLocation(40,5);
        add(lblQr);
    }

    public void setQrImage(Image image){
        ImageIcon imageIcon = new ImageIcon(image);
        lblQr.setIcon(imageIcon);
    }

    public void setScanned(){
        lblQr.setIcon(null);
        lblQr.setText("已扫描，请等待手机确认。");
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testqr.png");
        final Image image = ImageIO.read(inputStream);
        final QrCodeFrame[] qrCodeFrame = new QrCodeFrame[1];
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                qrCodeFrame[0] = new QrCodeFrame(image);
                qrCodeFrame[0].setVisible(true);

            }
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        qrCodeFrame[0].setScanned();
    }
}
