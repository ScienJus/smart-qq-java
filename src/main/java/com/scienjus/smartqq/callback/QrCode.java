package com.scienjus.smartqq.callback;


import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class QrCode {

    private InputStream inputStream;

    public QrCode(InputStream inputStream) throws IOException{
        this.inputStream = IOUtils.toBufferedInputStream(inputStream);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void file(File file) throws IOException{
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream,fileOutputStream);
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                }catch (IOException e){}
            }
        }
    }

    public Image getImage() throws IOException{
        return ImageIO.read(inputStream);
    }
}
