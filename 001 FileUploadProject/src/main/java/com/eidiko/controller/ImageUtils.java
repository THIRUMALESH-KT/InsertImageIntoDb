package com.eidiko.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageUtils {

    public static byte[] combineImages(List<byte[]> images) throws IOException {
        if (images.isEmpty()) {
            return null;
        }

        BufferedImage combinedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = combinedImage.createGraphics();

        int xOffset = 0;
        for (byte[] imageBytes : images) {
            if (imageBytes != null) {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                g2d.drawImage(image, xOffset, 0, null);
                xOffset += image.getWidth();
            }
        }

        g2d.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(combinedImage, "png", baos);

        return baos.toByteArray();
    }
}
