package graphics;

import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {
    private BufferedImage image;

    public Renderer() {
    }

    public void draw(int[] outPixels, DrawInfo drawInfo) {
        clear(outPixels);
        int mx = drawInfo.mousePoint.x;
        int my = drawInfo.mousePoint.y;

        drawImage(outPixels, drawInfo, mx, my);

    }

    private void clear(int[] outPixels) {
        Arrays.fill(outPixels, 0);
    }

    public void loadImage(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawImage(int[] outPixels, DrawInfo drawInfo, int x, int y) {
        int sourceX = 0;
        int sourceY = 0;
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int destinationIndex = x + y * drawInfo.width;
        image.getRGB(sourceX, sourceY, sourceWidth, sourceHeight, outPixels, destinationIndex, drawInfo.width);
    }

    public void drawRect(int[] outPixels, DrawInfo drawInfo) {
        int w = 50;
        int h = 50;
        int mx = drawInfo.mousePoint.x;
        int my = drawInfo.mousePoint.y;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int offsetY = y - h / 2;
                int offsetX = x - w / 2;
                int drawX = mx + offsetX;
                int drawY = my + offsetY;
                int index = drawX + drawY * 512;

                if (index < 0 || index >= outPixels.length) {
                    continue;
                }

                if (drawX >= drawInfo.width || drawX < 0) {
                    continue;
                }

                outPixels[index] = 0x00FF00;
            }

        }
    }
}
