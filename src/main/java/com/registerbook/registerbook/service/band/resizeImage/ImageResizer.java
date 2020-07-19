package com.registerbook.registerbook.service.band.resizeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizer {
    public static BufferedImage resize(String inputImagePath,int scaleWidth, int scaleHeight) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        BufferedImage outputImage = new BufferedImage(scaleWidth,scaleHeight,inputImage.getType());
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage,0,0,scaleWidth,scaleHeight,null);
        g2d.dispose();
        //String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
        //ImageIO.write(outputImage,formatName,new File(outputImagePath));
        return outputImage;
    }
}
